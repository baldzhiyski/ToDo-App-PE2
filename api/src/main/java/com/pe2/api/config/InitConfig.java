package com.pe2.api.config;

import com.pe2.api.domain.dtos.request.ToDosRequest;
import com.pe2.api.domain.dtos.response.ToDosResponse;
import com.pe2.api.domain.entity.ToDo;
import com.pe2.api.repository.AssigneeRepository;
import com.pe2.api.repository.ToDosRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class InitConfig {
    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource,
                                                       AssigneeRepository assigneeRepository,
                                                       ToDosRepository todoRepository,
                                                       ResourceLoader resourceLoader) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);

        if (assigneeRepository.count() == 0 && todoRepository.count() == 0) {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(resourceLoader.getResource("classpath:data.sql"));
            initializer.setDatabasePopulator(populator);
        }

        return initializer;
    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
