package com.shiftLabs.io.Student.Result.Management.System.services;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.ResultRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.ResultResponse;
import com.shiftLabs.io.Student.Result.Management.System.models.Result;
import com.shiftLabs.io.Student.Result.Management.System.repositories.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;

    private final ModelMapper modelMapper;

    public ResultResponse registerResult(ResultRequest resultRequest) {
      Result result = mapResult(resultRequest);
      Result savedResult = resultRepository.save(result);
      return modelMapper.map(savedResult, ResultResponse.class);
    }

    public List<ResultResponse> retrieveResultsList() {
        List<Result> results = resultRepository.findAll();
        return results.stream()
                .map(result -> modelMapper.map(result, ResultResponse.class))
                .collect(Collectors.toList());
    }

    private Result mapResult(ResultRequest resultRequest) {
        return modelMapper.map(resultRequest, Result.class);
    }


}
