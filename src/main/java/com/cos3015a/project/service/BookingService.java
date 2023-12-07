package com.cos3015a.project.service;

import com.cos3015a.project.dto.BookedDateDTO;
import com.cos3015a.project.dto.RoomDTO;
import com.cos3015a.project.dto.requestdtos.BookingRequestDTO;
import com.cos3015a.project.exceptionhandler.exception.InvalidRoomNumberException;
import com.cos3015a.project.exceptionhandler.exception.RoomNotFoundException;
import com.cos3015a.project.exceptionhandler.exception.RoomTimeNotAvailableException;
import com.cos3015a.project.exceptionhandler.exception.WrongBuldingNameException;
import com.cos3015a.project.repository.BookingInformationRepository;
import com.cos3015a.project.repository.RoomBookingRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookingService implements BookingServiceInterface {
    private final RoomBookingRespository roomBookingRespository;
    private final BookingInformationRepository dateBookingRepository;

    private final static String ROOM_NUMBER_PATTERN = "(?i)[5-6]\\d{3}";

    @Override
    public List<RoomDTO> getAllRooms() {
        return roomBookingRespository.findAll();
    }

    @Override
    public RoomDTO bookRoom(BookingRequestDTO bookingRequestDTO) throws RoomNotFoundException, RoomTimeNotAvailableException {
        var selectedRoom = roomBookingRespository.findById(bookingRequestDTO.getId());
        if (selectedRoom.isEmpty()) {
            throw new RoomNotFoundException();
        }

        var foundRoom=selectedRoom.get();
        var startingTime= LocalDateTime.parse(bookingRequestDTO.getStartingTime());
        var endingTime= LocalDateTime.parse(bookingRequestDTO.getEndingTime());
        if(foundRoom.getBookedDates().stream()
                .anyMatch(room->!(startingTime.isBefore(room.getStartingTime()) || endingTime.isAfter(room.getEndingTime())))) {
            throw new RoomTimeNotAvailableException(String.format("Room No: %d has been booked between on the %s , between the times of %s and %s",
                    bookingRequestDTO.getId(), startingTime.toLocalDate().toString(), startingTime.toLocalTime().toString(), endingTime.toLocalTime().toString()));
        }
        var bookedDate= BookedDateDTO.builder()
                .startingTime(startingTime)
                .endingTime(endingTime)
                .assignedRoom(foundRoom)
                .studentName(bookingRequestDTO.getStudentName())
                .studentId(bookingRequestDTO.getStudentId())
                .build();
        foundRoom.getBookedDates().add(bookedDate);
        dateBookingRepository.save(bookedDate);
        return roomBookingRespository.save(foundRoom);
    }

    @Override
    public RoomDTO addRoom(RoomDTO roomDTO) {
        if(!roomDTO.getRoomNumber().matches(ROOM_NUMBER_PATTERN)){
            throw new InvalidRoomNumberException();
        }
        if(roomDTO.getRoomNumber().charAt(0)=='6'){
            roomDTO.setBuilding("ABF");
        }
        else{
            roomDTO.setBuilding("BAC");
        }
        roomDTO.setBookedDates(new ArrayList<>());
        return roomBookingRespository.save(roomDTO);
    }

    @Override
    public List<BookedDateDTO> getSchedule(String id) {
        return roomBookingRespository
                .findById(id)
                .orElseThrow(RoomNotFoundException::new)
                .getBookedDates();
    }

    @Override
    public List<RoomDTO> getAllRoomsByBulding(String building) {
        if(!(building.equalsIgnoreCase("BAC") || building.equalsIgnoreCase("ABF"))){
            throw new WrongBuldingNameException();
        }
        return roomBookingRespository.findByBuilding(building);
    }
}
