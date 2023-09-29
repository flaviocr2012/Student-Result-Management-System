package com.shiftLabs.io.Student.Result.Management.System.dtos.responses;

import com.shiftLabs.io.Student.Result.Management.System.enums.ScoreEnum;
import com.shiftLabs.io.Student.Result.Management.System.models.Course;
import com.shiftLabs.io.Student.Result.Management.System.models.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultResponse {

    private Long id;

    private ScoreEnum score;

    private Course courseName;

}
