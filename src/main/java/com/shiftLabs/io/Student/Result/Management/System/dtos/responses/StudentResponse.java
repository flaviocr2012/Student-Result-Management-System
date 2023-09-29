package com.shiftLabs.io.Student.Result.Management.System.dtos.responses;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {

    private Long id;

    private String firstName;

    private String familyName;

    private LocalDate dateOfBirth;

    private String emailAddress;
}
