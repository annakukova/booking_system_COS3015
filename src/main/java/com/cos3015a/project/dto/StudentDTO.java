package com.cos3015a.project.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@Data
@Builder
@Entity
@NoArgsConstructor
public class StudentDTO {
    private String name;
    private String standing;

    @Id
    private String studentId;
}
