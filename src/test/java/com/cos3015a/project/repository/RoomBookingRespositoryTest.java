package com.cos3015a.project.repository;

import com.cos3015a.project.dto.RoomDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class RoomBookingRespositoryTest {

    @Autowired
    RoomBookingRespository roomBookingRespository;

    @Test
    void findByBuilding() {
        var room1=RoomDTO.builder()
                .roomNumber("5230")
                .building("BAC")
                .capacity(20)
                .build();
        var room2=RoomDTO.builder()
                .roomNumber("5310")
                .building("BAC")
                .capacity(20)
                .build();
        var room3=RoomDTO.builder()
                .roomNumber("6230")
                .building("ABF")
                .capacity(20)
                .build();

        roomBookingRespository.saveAll(List.of(room1,room2,room3));

        assertEquals(2, roomBookingRespository.findByBuilding("BAC").size());

    }


}