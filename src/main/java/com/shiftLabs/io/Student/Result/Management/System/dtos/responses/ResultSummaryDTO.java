package com.shiftLabs.io.Student.Result.Management.System.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultSummaryDTO {

    private Long id;
    private String studentName;
    private String courseName;
    private String score;
}
