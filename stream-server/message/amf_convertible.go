package message

import (
	"io"

	"github.com/yutopp/go-amf0"
)

type EncodingType uint8

const (
	EncodingTypeAMF0 EncodingType = 0
	EncodingTypeAMF3 EncodingType = 3
)

type AMFConvertible interface {
	FromArgs(args ...interface{}) error
	ToArgs(ty EncodingType) ([]interface{}, error)
}

type AMFDecoder interface {
	Decode(interface{}) error
	Reset(r io.Reader)
}

func NewAMFDecoder(r io.Reader, encTy EncodingType) AMFDecoder {
	switch encTy {
	case EncodingTypeAMF3:
		panic("Unsupported encoding: AMF3")
	case EncodingTypeAMF0:
		return amf0.NewDecoder(r)
	default:
		panic("Unreachable")
	}
}

type AMFEncoder interface {
	Encode(interface{}) error
	Reset(w io.Writer)
}

func NewAMFEncoder(w io.Writer, encTy EncodingType) AMFEncoder {
	switch encTy {
	case EncodingTypeAMF3:
		panic("Unsupported encoding: AMF3")
	case EncodingTypeAMF0:
		return amf0.NewEncoder(w)
	default:
		panic("Unreachable")
	}
}
