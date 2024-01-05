package com.shiftLabs.io.Student.Result.Management.System.services;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.ResultRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.ResultResponseDto;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.ResultSummaryDTO;
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

    public ResultResponseDto registerResult(ResultRequest resultRequest) {
        Result result = mapResult(resultRequest);
        Result savedResult = resultRepository.save(result);

        ResultResponseDto resultResponseDto = new ResultResponseDto();
        resultResponseDto.setId(savedResult.getId());
        resultResponseDto.setCourse(savedResult.getCourse().getCourseName());
        resultResponseDto.setStudent(savedResult.getStudent().getFirstName());
        resultResponseDto.setScore(savedResult.getScore().toString());
        return resultResponseDto;
    }

    public List<ResultSummaryDTO> retrieveResultsList() {
        List<Result> results = resultRepository.findAll();
        return results.stream()
                .map(result -> {
                    ResultSummaryDTO summaryDTO = new ResultSummaryDTO();
                    summaryDTO.setId(result.getId());
                    summaryDTO.setStudentName(result.getStudent().getFirstName());
                    summaryDTO.setCourseName(result.getCourse().getCourseName());
                    summaryDTO.setScore(result.getScore().toString());
                    return summaryDTO;
                })
                .collect(Collectors.toList());
    }

    private Result mapResult(ResultRequest resultRequest) {
        return modelMapper.map(resultRequest, Result.class);
    }

}
