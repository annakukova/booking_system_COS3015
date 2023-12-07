package com.cos3015a.project.service;

import com.cos3015a.project.dto.BookedDateDTO;
import com.cos3015a.project.dto.RoomDTO;
import com.cos3015a.project.dto.requestdtos.BookingRequestDTO;
import com.cos3015a.project.exceptionhandler.exception.RoomNotFoundException;
import com.cos3015a.project.exceptionhandler.exception.RoomTimeNotAvailableException;

import java.util.List;

public interface BookingServiceInterface {
    List<RoomDTO> getAllRooms();

    RoomDTO bookRoom(BookingRequestDTO bookingRequestDTO) throws RoomNotFoundException, RoomTimeNotAvailableException;

    RoomDTO addRoom(RoomDTO roomDTO);

    List<BookedDateDTO> getSchedule(String id);

    List<RoomDTO> getAllRoomsByBulding(String building);
}
