package com.shiftLabs.io.Student.Result.Management.System.services;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.CourseRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.CourseResponse;
import com.shiftLabs.io.Student.Result.Management.System.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;

    private ModelMapper modelMapper;
    public ResponseEntity<CourseResponse> registerCourse(CourseRequest courseRequest) {
        return Optional.ofNullable(courseRequest)
                .map(this::mapCourse)
                .map(courseRepository::save)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    private CourseResponse mapCourse(CourseRequest courseRequest){
        return modelMapper.map(courseRequest, CourseResponse.class);
    }
}
