package com.cos3015a.project.controller;

import com.cos3015a.project.dto.BookedDateDTO;
import com.cos3015a.project.dto.RoomDTO;
import com.cos3015a.project.dto.requestdtos.BookingRequestDTO;
import com.cos3015a.project.exceptionhandler.exception.RoomNotFoundException;
import com.cos3015a.project.exceptionhandler.exception.RoomTimeNotAvailableException;
import com.cos3015a.project.service.BookingServiceInterface;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookingController {

    public BookingController(){

        this.bookingServiceInterface = null; x
    }
    private final BookingServiceInterface bookingServiceInterface;

    @GetMapping("/getallrooms")
    public ResponseEntity<List<RoomDTO>> getAllRooms(){
        return ResponseEntity.ok().body(bookingServiceInterface.getAllRooms());
    }
    @GetMapping("/getallroomsbybuilding")
    public ResponseEntity<List<RoomDTO>> getAllRoomsByBuilding(@RequestBody String building){
        return ResponseEntity.ok().body(bookingServiceInterface.getAllRoomsByBulding(building));
    }
    @PutMapping("/bookroom")
    public ResponseEntity<RoomDTO> bookRoom(@RequestBody BookingRequestDTO bookingRequestDTO) throws RoomNotFoundException, RoomTimeNotAvailableException {
        return ResponseEntity.ok(bookingServiceInterface.bookRoom(bookingRequestDTO));
    }
    @PostMapping("/addroom")
    public ResponseEntity<RoomDTO> addRoom(@RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok().body(bookingServiceInterface.addRoom(roomDTO));
    }
    @GetMapping("/getschedule/{id}")
    public ResponseEntity<List<BookedDateDTO>> getSchedule(@PathVariable String id){
        return ResponseEntity.ok().body(bookingServiceInterface.getSchedule(id));
    }
}
