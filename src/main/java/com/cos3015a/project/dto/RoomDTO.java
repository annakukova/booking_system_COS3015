package com.cos3015a.project.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "room_booking")
public class RoomDTO {
    @Id
    private String roomNumber;
    private String building;
    private int capacity;
    @OneToMany(mappedBy = "assignedRoom")
    private List<BookedDateDTO> bookedDates;
}
