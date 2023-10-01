package com.shiftLabs.io.Student.Result.Management.System.controllers;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.ResultRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.ResultResponseDto;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.ResultSummaryDTO;
import com.shiftLabs.io.Student.Result.Management.System.services.ResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/results")
@CrossOrigin(origins = "*")
public class ResultController {

    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<ResultResponseDto> createResult(
            @RequestBody @Valid ResultRequest resultRequest) {
        var savedResult = resultService.registerResult(resultRequest);
        return new ResponseEntity<>(savedResult, HttpStatus.CREATED);
    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ResultSummaryDTO>> getAllResults() {
        List<ResultSummaryDTO> resultsList = resultService.retrieveResultsList();
        return ResponseEntity.ok(resultsList);
    }

}
