package message

import (
	"fmt"
)

type UnknownDataBodyDecodeError struct {
	Name string
	Objs []interface{}
}

func (e *UnknownDataBodyDecodeError) Error() string {
	return fmt.Sprintf("UnknownDataBodyDecodeError: Name = %s, Objs = %+v", e.Name, e.Objs)
}

type UnknownCommandBodyDecodeError struct {
	Name          string
	TransactionID int64
	Objs          []interface{}
}

func (e *UnknownCommandBodyDecodeError) Error() string {
	return fmt.Sprintf("UnknownCommandMessageDecodeError: Name = %s, TransactionID = %d, Objs = %+v",
		e.Name,
		e.TransactionID,
		e.Objs,
	)
}
