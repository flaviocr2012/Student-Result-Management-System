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

    private final ResultRepository resultRepository;

    private final ModelMapper modelMapper;

    public ResultResponse registerResult(ResultRequest resultRequest) {
      var result = mapResult(resultRequest);
      var savedResult = resultRepository.save(result);
      return modelMapper.map(savedResult, ResultResponse.class);
    }

    private ResultResponse mapResult(ResultRequest resultRequest) {
        return modelMapper.map(resultRequest, ResultResponse.class);
    }
}
