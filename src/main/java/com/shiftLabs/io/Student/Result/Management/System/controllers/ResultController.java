package com.shiftLabs.io.Student.Result.Management.System.controllers;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.ResultRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.ResultResponse;
import com.shiftLabs.io.Student.Result.Management.System.services.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/result")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ResultController {

    private ResultService resultService;

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResultResponse> createResult(
            @RequestBody @Valid ResultRequest resultRequest) {
        var savedResult = resultService.registerResult(resultRequest);
        return new ResponseEntity<>(savedResult.getStatusCode());
    }



}
