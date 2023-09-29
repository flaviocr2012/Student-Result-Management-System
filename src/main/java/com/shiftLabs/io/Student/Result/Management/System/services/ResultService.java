package com.shiftLabs.io.Student.Result.Management.System.services;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.ResultRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.ResultResponse;
import com.shiftLabs.io.Student.Result.Management.System.repositories.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultService {

    private ResultRepository resultRepository;

    private ModelMapper modelMapper;

    public ResponseEntity<ResultResponse> registerResult(ResultRequest resultRequest) {
        return Optional.ofNullable(resultRequest)
                .map(this::mapResult)
                .map(resultRepository::save)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    private ResultResponse mapResult(ResultRequest resultRequest) {
        return modelMapper.map(resultRequest, ResultResponse.class);
    }
}
