package rtmp

import (
	"github.com/pkg/errors"
	"notchman.tech/stream-server/handshake"
)

type ServerConn struct {
	conn *Conn
}

func NewServerConnection(conn *Conn) *ServerConn {
	return &ServerConn{
		conn: conn,
	}
}

func (sc *ServerConn) Serve() error {
	if err := handshake.HandshakeWithClient(sc.conn.rwc, sc.conn.rwc, &handshake.Config{
		SkipHandshakeVerification: sc.conn.config.SkipHandshakeVerification,
	}); err != nil {
		return errors.Wrap(err, "Failed to handshake")
	}

	ctrlStream, err := sc.conn.streams.Create(ControlStreamID)
	if err != nil {
		return errors.Wrap(err, "Failed to create control stream")
	}
	ctrlStream.handler.ChangeState(streamStateServerNotConnected)

	sc.conn.streamer.controlStreamWriter = ctrlStream.Write

	if sc.conn.handler != nil {
		sc.conn.handler.OnServe(sc.conn)
	}

	return sc.conn.handleMessageLoop()
}

func (sc *ServerConn) Close() error {
	return sc.conn.Close()
}
