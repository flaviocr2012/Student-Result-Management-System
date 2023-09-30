package com.shiftLabs.io.Student.Result.Management.System.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shiftLabs.io.Student.Result.Management.System.configs.CustomLocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequest {

    private Long id;

    private String firstName;

    private String familyName;

    @JsonFormat(pattern = "MM/dd/yyyy")
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    private LocalDate dateOfBirth;

    private String emailAddress;
}
