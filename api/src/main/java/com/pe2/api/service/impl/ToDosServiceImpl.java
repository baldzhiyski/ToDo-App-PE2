package com.pe2.api.service.impl;

import com.pe2.api.domain.dtos.request.ToDosUpdateRequest;
import com.pe2.api.domain.dtos.response.AssigneeResponse;
import com.pe2.api.domain.dtos.request.ToDosRequest;
import com.pe2.api.domain.dtos.response.ToDosResponse;
import com.pe2.api.domain.entity.Assignee;
import com.pe2.api.domain.entity.ToDo;
import com.pe2.api.exceptions.InvalidDueDateException;
import com.pe2.api.exceptions.NoSuchAssigneeException;
import com.pe2.api.exceptions.NoSuchToDoException;
import com.pe2.api.repository.AssigneeRepository;
import com.pe2.api.repository.ToDosRepository;
import com.pe2.api.service.ToDosService;
import com.pe2.api.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DateTimeException;
import java.time.Instant;
import java.util.*;


@Service
public class ToDosServiceImpl implements ToDosService {

    private ToDosRepository toDosRepository;

    private AssigneeRepository assigneeRepository;

    private ModelMapper mapper;

    public ToDosServiceImpl(ToDosRepository toDosRepository, AssigneeRepository assigneeRepository, ModelMapper mapper) {
        this.toDosRepository = toDosRepository;
        this.assigneeRepository = assigneeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ToDosResponse> getAllToDosDto() {
        return this.toDosRepository.findAll()
                .stream()
                .map(toDo -> {
                    ToDosResponse mapped = this.mapper.map(toDo, ToDosResponse.class);
                    List<AssigneeResponse> mappedAssignees = toDo.getAssigneeList().stream()
                            .map(assignee -> mapper.map(assignee, AssigneeResponse.class))
                            .toList();
                    mapped.setAssigneeResponseList(mappedAssignees);

                    return mapped;
                })
                .toList();
    }

    @Override
    public Optional<ToDosResponse> getToDoById(Long id) {
        return this.toDosRepository.findById(id)
                .map(toDo -> {
                    List<AssigneeResponse> mappedAssignees = toDo.getAssigneeList().stream()
                            .map(assignee -> mapper.map(assignee, AssigneeResponse.class))
                            .toList();
                    ToDosResponse mapped = mapper.map(toDo, ToDosResponse.class);
                    mapped.setAssigneeResponseList(mappedAssignees);
                    return mapped;
                });
    }

    @Override
    @Transactional
    public ToDosResponse createToDo(ToDosRequest toDosRequest) {
        ToDo mapped = new ToDo();
        mapped.setDescription(toDosRequest.getDescription());
        mapped.setTitle(toDosRequest.getTitle());
        if(toDosRequest.getDueDate() != null) {
            mapped.setDueDate(new Date(toDosRequest.getDueDate()));
        }
        mapped.setCreatedDate(new Date());

        List<Assignee> assignees = new ArrayList<>();
        // Assignee Validation
        if (toDosRequest.getAssigneeIdList() != null && !toDosRequest.getAssigneeIdList().isEmpty()) {
            // Ensure all assignee IDs are unique
            Set<Long> assigneeIdSet = new HashSet<>(toDosRequest.getAssigneeIdList());


            // Validate each assignee exists in the DB
            assigneeIdSet.forEach(id -> {
                Assignee assignee = this.assigneeRepository.findById(id)
                        .orElseThrow(() -> new NoSuchAssigneeException(String.format(Constants.ASSIGNEE_NOT_FOUND_MESSAGE,id)));
                assignee.setToDo(mapped);
                assignees.add(assignee);
            });
        }
        return getToDosResponse(mapped, assignees);

    }

    @Override
    @Transactional
    public void deleteToDo(Long todoId) {
        ToDo toDoToBeDeleted = this.toDosRepository.findById(todoId)
                .orElseThrow(() -> new NoSuchToDoException(String.format(Constants.TODO_NOT_FOUND_MESSAGE, todoId)));

        toDoToBeDeleted.getAssigneeList()
                .forEach(assignee -> {
                    assignee.setToDo(null);
                });

        this.toDosRepository.delete(toDoToBeDeleted);
    }

    @Override
    @Transactional
    public ToDosResponse updateExistingToDo(Long toDoId, ToDosUpdateRequest toDosRequest) {
        ToDo toDo = this.toDosRepository.findById(toDoId)
                .orElseThrow(() -> new NoSuchToDoException(String.format(Constants.TODO_NOT_FOUND_MESSAGE, toDoId)));

        List<Assignee> assignees = new ArrayList<>();
        if(!toDosRequest.getTitle().isBlank()) {
            toDo.setTitle(toDosRequest.getTitle());
        }

        if(!toDosRequest.getDescription().isBlank()) {
            toDo.setDescription(toDosRequest.getDescription());
        }
        // Validate and set the dueDate
        if (toDosRequest.getDueDate() != null) {
            try {
                Instant dueDateInstant = Instant.ofEpochMilli(toDosRequest.getDueDate());
                Date dueDate = Date.from(dueDateInstant);

                // Example: Optional check for dueDate not in the past
                if (dueDate.before(new Date())) {
                    throw new InvalidDueDateException("The due date cannot be in the past.");
                }
                toDo.setDueDate(dueDate);
            } catch (DateTimeException | IllegalArgumentException e) {
                throw new InvalidDueDateException("Invalid timestamp for due date.");
            }
        }
        toDo.setFinished(toDosRequest.getFinished());

        if (toDosRequest.getAssigneeIdList() != null
        && !toDosRequest.getAssigneeIdList().isEmpty()) {
            Set<Long> assigneeIdsList = new HashSet<>(toDosRequest.getAssigneeIdList());

            assigneeIdsList.forEach(id -> {
                Assignee assignee = this.assigneeRepository.findById(id)
                        .orElseThrow(() -> new NoSuchAssigneeException(String.format(Constants.ASSIGNEE_NOT_FOUND_MESSAGE, id)));
                assignee.setToDo(toDo);
                assignees.add(assignee);

            });
        }
        return getToDosResponse(toDo, assignees);
    }

    private ToDosResponse getToDosResponse(ToDo toDo, List<Assignee> assignees) {
        toDo.setAssigneeList(assignees);
        this.toDosRepository.saveAndFlush(toDo);

        ToDosResponse mappedResponse = this.mapper.map(toDo, ToDosResponse.class);
        List<AssigneeResponse> mappedAssignees = toDo.getAssigneeList().stream()
                .map(assignee -> mapper.map(assignee, AssigneeResponse.class))
                .toList();
        mappedResponse.setAssigneeResponseList(mappedAssignees);

        return mappedResponse;
    }


}
