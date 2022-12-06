package rtmp

import "bytes"

type ChunkStreamReader struct {
	basicHeader     chunkBasicHeader
	messageHeader   chunkMessageHeader
	timestamp       uint32
	timestampDelta  uint32
	messageLength   uint32
	messageTypeID   byte
	messageStreamID uint32
	buf             bytes.Buffer
	complated       bool
}

func (r *ChunkStreamReader) Read(b []byte) (int, error) {
	return r.buf.Read(b)
}
