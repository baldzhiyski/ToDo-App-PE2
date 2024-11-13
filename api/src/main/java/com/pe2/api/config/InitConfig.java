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
    public ModelMapper mapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Mapping from ToDoRequest to ToDo
        modelMapper.addMappings(new PropertyMap<ToDosRequest, ToDo>() {
            @Override
            protected void configure() {
                // Map the ToDoRequest createdDate (Date) to ToDo createdDate (Date)
                map(source.getDueDate(), destination.getDueDate());
            }
        });

        // Mapping from ToDo to ToDoResponse
        modelMapper.addMappings(new PropertyMap<ToDo, ToDosResponse>() {
            @Override
            protected void configure() {
                // Safely convert ToDo createdDate (Date) to Unix timestamp (Long)
                using(ctx -> {
                    Date sourceDate = (Date) ctx.getSource();
                    return sourceDate != null ? sourceDate.getTime() : null;  // Return null if sourceDate is null
                }).map(source.getDueDate(), destination.getDueDate());

                using(ctx -> {
                    Date sourceDate = (Date) ctx.getSource();
                    return sourceDate != null ? sourceDate.getTime() : null;  // Return null if sourceDate is null
                }).map(source.getCreatedDate(), destination.getCreatedDate());

                using(ctx -> {
                    Date sourceDate = (Date) ctx.getSource();
                    return sourceDate != null ? sourceDate.getTime() : null;  // Return null if sourceDate is null
                }).map(source.getFinishedDate(), destination.getFinishedDate());
            }
        });

        return modelMapper;
    }

}
