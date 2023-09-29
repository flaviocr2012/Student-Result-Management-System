package com.shiftLabs.io.Student.Result.Management.System.models;

import com.shiftLabs.io.Student.Result.Management.System.enums.ScoreEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ScoreEnum score;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
