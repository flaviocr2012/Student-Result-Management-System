package com.shiftLabs.io.Student.Result.Management.System.dtos.responses;

import com.shiftLabs.io.Student.Result.Management.System.enums.ScoreEnum;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ScoreEnum score;
}