package com.cos3015a.project.service;

import com.cos3015a.project.dto.RoomDTO;
import com.cos3015a.project.exceptionhandler.exception.InvalidRoomNumberException;
import com.cos3015a.project.exceptionhandler.exception.WrongBuldingNameException;
import com.cos3015a.project.repository.BookingInformationRepository;
import com.cos3015a.project.repository.RoomBookingRespository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookingServiceTest {

    private static final List<RoomDTO> rooms = List.of(
            RoomDTO.builder()
                    .building("BAC")
                    .roomNumber(5230)
                    .capacity(20)
                    .bookedDates(new ArrayList<>())
                    .build(),

            RoomDTO.builder()
                    .building("BAC")
                    .roomNumber(5203)
                    .capacity(20)
                    .bookedDates(new ArrayList<>())
                    .build(),

            RoomDTO.builder()
                    .building("BAF")
                    .roomNumber(6512)
                    .capacity(20)
                    .bookedDates(new ArrayList<>())
                    .build()
    );

    @Mock
    RoomBookingRespository mockBookingRepository;

    @Mock
    BookingInformationRepository mockBookingInformationRepository;

    @InjectMocks
    BookingService bookingService;

//    @BeforeEach
//    void setUp(){
//        bookingService = new BookingService(mockBookingRepository, mockBookingInformationRepository);
//    }

    @Test
    void ShouldGiveAllRooms(){
        when(mockBookingRepository.findAll()).thenReturn(rooms);
        assertEquals(rooms, bookingService.getAllRooms());


    }
    @ParameterizedTest
    @MethodSource("provideWrongBuildingNames")
    void whenGivenWrongBuildingNameShouldThrowException(String building){
        when(mockBookingRepository.findByBuilding(building)).thenThrow(new WrongBuldingNameException());
        Assertions.assertThrows(WrongBuldingNameException.class, ()->bookingService.getAllRoomsByBulding(building));
    }

    @ParameterizedTest
    @MethodSource("provideCorrectBuildingNames")
    void whenGivenCorrectBuildingNameShouldReturnListOfObjects(String building){
        when(mockBookingRepository.findByBuilding(building)).thenReturn(List.of(
                RoomDTO.builder()
                        .building(building)
                        .roomNumber(5230)
                        .capacity(20)
                        .bookedDates(new ArrayList<>())
                        .build(),

                RoomDTO.builder()
                        .building(building)
                        .roomNumber(5203)
                        .capacity(20)
                        .bookedDates(new ArrayList<>())
                        .build()
        ));

        Assertions.assertTrue(bookingService
                .getAllRoomsByBulding(building)
                .stream()
                .allMatch(room->room.getBuilding().equals(building)));
    }

    @ParameterizedTest
    @MethodSource("provideCorrectRooms")
    void whenGivenRoomShouldSaveIt(RoomDTO roomDTO){
        when(mockBookingRepository.save(roomDTO)).thenReturn(roomDTO);
        assertEquals(bookingService.addRoom(roomDTO).getBuilding(), roomDTO.getBuilding());
    }

    @ParameterizedTest
    @MethodSource("provideWrongRooms")
    void whenGivenWrongRoomShouldThrowException(RoomDTO roomDTO){
        when(mockBookingRepository.save(roomDTO)).thenThrow(new InvalidRoomNumberException());
        assertThrows(InvalidRoomNumberException.class, ()->bookingService.addRoom(roomDTO));
    }

    private static Stream<Arguments> provideCorrectRooms(){
        return Stream.of(
                Arguments.of(RoomDTO.builder()
                        .roomNumber(5300)
                        .capacity(23)
                        .build()),
                Arguments.of(RoomDTO.builder()
                        .roomNumber(6300)
                        .capacity(25)
                        .build()),
                Arguments.of(RoomDTO.builder()
                        .roomNumber(5321)
                        .capacity(26)
                        .build()),
                Arguments.of(RoomDTO.builder()
                        .roomNumber(6530)
                        .capacity(15)
                        .build())
        );
    }
    private static Stream<Arguments> provideWrongRooms(){
        return Stream.of(
                Arguments.of(RoomDTO.builder()
                        .roomNumber(7300)
                        .capacity(23)
                        .build()),
                Arguments.of(RoomDTO.builder()
                        .roomNumber(3300)
                        .capacity(25)
                        .build()),
                Arguments.of(RoomDTO.builder()
                        .roomNumber(1000)
                        .capacity(26)
                        .build()),
                Arguments.of(RoomDTO.builder()
                        .roomNumber(5320)
                        .capacity(15)
                        .build())
        );
    }

    private static Stream<Arguments> provideWrongBuildingNames(){
        return Stream.of(
                Arguments.of("Skapto 1"),
                Arguments.of("Skapto 2"),
                Arguments.of("Skapto 3"),
                Arguments.of("Main Building"),
                Arguments.of("Bbac")
        );
    }
    private static Stream<Arguments> provideCorrectBuildingNames(){
        return Stream.of(
                Arguments.of("ABF"),
                Arguments.of("BAC"),
                Arguments.of("BaC"),
                Arguments.of("abf")
        );
    }
}