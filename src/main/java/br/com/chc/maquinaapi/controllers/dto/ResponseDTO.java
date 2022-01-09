package br.com.chc.maquinaapi.controllers.dto;

public class ResponseDTO<T> {
    private String message;
    private T data;

    public ResponseDTO(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
