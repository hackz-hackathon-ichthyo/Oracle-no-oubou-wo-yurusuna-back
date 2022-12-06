package message

type UserCtrlEvent interface{}

// UserCtrlEventStreamBegin (0)
type UserCtrlEventStreamBegin struct {
	StreamID uint32
}

// UserCtrlEventStreamEOF (1)
type UserCtrlEventStreamEOF struct {
	StreamID uint32
}

// UserCtrlEventStreamDry (2)
type UserCtrlEventStreamDry struct {
	StreamID uint32
}

// UserCtrlEventSetBufferLength (3)
type UserCtrlEventSetBufferLength struct {
	StreamID uint32
	LengthMs uint32
}

// UserCtrlEventStreamIsRecorded (4)
type UserCtrlEventStreamIsRecorded struct {
	StreamID uint32
}

// UserCtrlEventPingRequest (6)
type UserCtrlEventPingRequest struct {
	Timestamp uint32
}

// UserCtrlEventPingResponse (7)
type UserCtrlEventPingResponse struct {
	Timestamp uint32
}
