package com.cos3015a.project.exceptionhandler;

import com.cos3015a.project.exceptionhandler.exception.InvalidRoomNumberException;
import com.cos3015a.project.exceptionhandler.exception.RoomNotFoundException;
import com.cos3015a.project.exceptionhandler.exception.RoomTimeNotAvailableException;
import com.cos3015a.project.exceptionhandler.exception.WrongBuldingNameException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BookingExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RoomNotFoundException.class})
    public ResponseEntity<Object> handleRoomNotFoundException(RoomNotFoundException exception, WebRequest request){
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({RoomTimeNotAvailableException.class})
    public ResponseEntity<Object> handleRoomTimeNotAvailableException(RoomTimeNotAvailableException exception, WebRequest request){
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler({WrongBuldingNameException.class})
    public ResponseEntity<Object> handleWrongBuildingNameException(WrongBuldingNameException exception, WebRequest request){
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({InvalidRoomNumberException.class})
    public ResponseEntity<Object> handleInvalidRoomNumberException(InvalidRoomNumberException exception, WebRequest request){
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }
}
