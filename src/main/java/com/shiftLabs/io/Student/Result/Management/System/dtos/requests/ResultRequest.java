package com.shiftLabs.io.Student.Result.Management.System.dtos.requests;

import com.shiftLabs.io.Student.Result.Management.System.enums.ScoreEnum;
import com.shiftLabs.io.Student.Result.Management.System.models.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultRequest {

    private Long id;

    private ScoreEnum score;

    private Course course;


}
