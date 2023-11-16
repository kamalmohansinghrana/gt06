package com.onelap.gt06onelap.decoder;

public class ApiResponse<T>{
    T body;


    public ApiResponse(T body) {
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
