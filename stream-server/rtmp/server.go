package rtmp

import (
	"io"
	"net"
	"sync"

	"github.com/pkg/errors"
)

type ServerConfig struct {
	OnConnect func(net.Conn) (io.ReadWriteCloser, *ConnConfig)
}
type Server struct {
	config *ServerConfig

	listener net.Listener
	mu       sync.Mutex
	doneCh   chan struct{}
}

func NewServer(config *ServerConfig) *Server {
	return &Server{
		config: config,
	}
}

func (srv *Server) Serve(l net.Listener) error {
	if err := srv.registerListener(l); err != nil {
		return errors.Wrap(err, "Already served")
	}
	defer l.Close()

	for {
		rwc, err := l.Accept()
		if err != nil {
			select {
			case <-srv.getDoneCh():
				return net.ErrClosed
			default:
			}
			continue
		}
		go srv.handleConn(rwc)
	}
}

func (srv *Server) Close() error {
	srv.mu.Lock()
}
