package com.cos3015a.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "booking_date")
public class BookedDateDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @ManyToOne
    @JoinColumn(name = "room_number")
    @JsonIgnore
    private RoomDTO assignedRoom;
    private LocalDateTime startingTime;
    private LocalDateTime endingTime;
    private String studentName;
    private String studentId;
}
