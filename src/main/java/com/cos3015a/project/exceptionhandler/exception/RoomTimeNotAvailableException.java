package com.cos3015a.project.exceptionhandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class RoomTimeNotAvailableException extends RuntimeException{
    public RoomTimeNotAvailableException(String message){
        super(message);
    }
}
