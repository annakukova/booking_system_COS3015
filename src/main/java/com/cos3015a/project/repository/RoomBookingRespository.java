package com.cos3015a.project.repository;

import com.cos3015a.project.dto.RoomDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomBookingRespository extends JpaRepository<RoomDTO, String> {
    List<RoomDTO> findByBuilding(String building);
}
