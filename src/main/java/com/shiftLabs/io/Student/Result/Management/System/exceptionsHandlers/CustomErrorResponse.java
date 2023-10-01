package com.shiftLabs.io.Student.Result.Management.System.exceptionsHandlers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomErrorResponse {

    private int status;
    private String error;
    private String message;
    private long timestamp;

}
