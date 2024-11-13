package com.pe2.api.service;

import com.pe2.api.domain.dtos.request.ToDosRequest;
import com.pe2.api.domain.dtos.request.ToDosUpdateRequest;
import com.pe2.api.domain.dtos.response.ToDosResponse;

import java.util.List;
import java.util.Optional;

public interface ToDosService {
    List<ToDosResponse> getAllToDosDto();

    Optional<ToDosResponse> getToDoById(Long id);

    ToDosResponse createToDo(ToDosRequest toDosRequest);

    void deleteToDo(Long todoId);

    ToDosResponse updateExistingToDo(Long toDoId, ToDosUpdateRequest toDosRequest);
}
