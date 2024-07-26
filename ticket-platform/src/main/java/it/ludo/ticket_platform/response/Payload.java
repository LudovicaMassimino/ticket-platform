package it.ludo.ticket_platform.response;

import org.springframework.http.HttpStatus;

public class Payload<T> {

    private T payload;
    private String errorMessage;
    private HttpStatus status;

//    costruttori

    public Payload() {
    }

    public Payload(T payload, String errorMessage, HttpStatus status) {
        this.payload = payload;
        this.errorMessage = errorMessage;
        this.status = status;
    }

    // getter e setter
    
    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
