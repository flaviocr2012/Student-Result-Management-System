package com.shiftLabs.io.Student.Result.Management.System.components;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperBean {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
