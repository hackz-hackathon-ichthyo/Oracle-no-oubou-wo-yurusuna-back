package message

import (
	"github.com/pkg/errors"
	"github.com/yutopp/go-amf0"
)

func EncodeBodyAnyValues(e AMFEncoder, v AMFConvertible) error {
	if v == nil {
		return nil // Do nothing
	}

	var amfTy EncodingType
	switch e.(type) {
	case *amf0.Encoder:
		amfTy = EncodingTypeAMF0
	default:
		return errors.Errorf("Unsupported AMF Encoder: Type = %T", e)
	}

	args, err := v.ToArgs(amfTy)
	if err != nil {
		return err
	}

	for _, arg := range args {
		if err := e.Encode(arg); err != nil {
			return err
		}
	}

	return nil
}
