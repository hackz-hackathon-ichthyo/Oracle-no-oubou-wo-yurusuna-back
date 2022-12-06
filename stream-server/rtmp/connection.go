package rtmp

import (
	"bufio"
	"io"
	"sync"

	"github.com/sirupsen/logrus"
)

type ConnConfig struct {
	Handler                   Handler
	SkipHandshakeVerification bool

	IgnoreMessagesOnNotExistStream          bool
	IgnoreMessagesOnNotExistStreamThreshold uint32

	ReaderBufferSize int
	WriterBufferSize int

	ControlState StreamControlStateConfig

	Logger  logrus.FieldLogger
	RPreset ResponsePreset
}
type Conn struct {
	rwc      io.ReadWriteCloser
	bufr     *bufio.Reader
	bufw     *bufio.Writer
	streamer *ChunkStreamer
	streams  *streams
	handler  Handler

	config *ConnConfig
	logger logrus.FieldLogger

	ignoredMessages uint32

	m        sync.Mutex
	isClosed bool
}
