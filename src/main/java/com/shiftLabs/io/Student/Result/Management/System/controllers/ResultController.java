package com.shiftLabs.io.Student.Result.Management.System.controllers;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.ResultRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.ResultResponse;
import com.shiftLabs.io.Student.Result.Management.System.services.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/result")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ResultController {

    private final ResultService resultService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<ResultResponse> createResult(
            @RequestBody @Valid ResultRequest resultRequest) {
        var savedResult = resultService.registerResult(resultRequest);
        return new ResponseEntity<>(savedResult, HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ResultResponse>> getAllResults(){
        List<ResultResponse> resultsList = resultService.retrieveResultsList();
        return ResponseEntity.ok(resultsList);
    }

}
