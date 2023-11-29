package com.cos3015a.project.exceptionhandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WrongBuldingNameException extends RuntimeException{
    public WrongBuldingNameException(){
        super("Room has to be either BAC or ABF");
    }
}
