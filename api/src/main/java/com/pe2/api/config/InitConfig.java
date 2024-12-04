package com.pe2.api.config;

import com.pe2.api.domain.dtos.request.ToDosRequest;
import com.pe2.api.domain.dtos.response.ToDosResponse;
import com.pe2.api.domain.entity.ToDo;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class InitConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
