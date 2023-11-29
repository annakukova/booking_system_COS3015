package com.cos3015a.project.exceptionhandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InvalidRoomNumberException extends RuntimeException{
    public InvalidRoomNumberException(){
        super("Room number has to be written in format 5xxx or 6xxx");
    }
}
