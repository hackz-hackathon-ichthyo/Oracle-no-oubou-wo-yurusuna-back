package main

import (
	"log"
	"net/http"
	"net/http/httputil"
	"time"
)

func stream(w http.ResponseWriter, r *http.Request) {
	flusher, _ := w.(http.Flusher)
	cw := httputil.NewChunkedWriter(w)
	for i := 0; i < 3; i++ {
		cw.Write([]byte("hello"))
		flusher.Flush()
		time.Sleep(time.Second)
	}
}

func main() {
	http.HandleFunc("/", stream)
	log.Fatal(http.ListenAndServe(":8080", nil))
}
