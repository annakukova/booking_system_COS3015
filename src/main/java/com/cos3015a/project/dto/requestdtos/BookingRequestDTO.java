package com.cos3015a.project.dto.requestdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingRequestDTO {
    private String id;
    private String startingTime;
    private String endingTime;
    private String studentName;
    private String studentId;

}
