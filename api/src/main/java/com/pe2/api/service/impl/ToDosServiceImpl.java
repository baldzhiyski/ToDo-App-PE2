package com.pe2.api.service.impl;

import com.pe2.api.domain.dtos.request.ToDosUpdateRequest;
import com.pe2.api.domain.dtos.response.AssigneeResponse;
import com.pe2.api.domain.dtos.request.ToDosRequest;
import com.pe2.api.domain.dtos.response.ToDosResponse;
import com.pe2.api.domain.entity.Assignee;
import com.pe2.api.domain.entity.ToDo;
import com.pe2.api.exceptions.InvalidDueDateException;
import com.pe2.api.repository.AssigneeRepository;
import com.pe2.api.repository.ToDosRepository;
import com.pe2.api.service.ToDosService;
import com.pe2.api.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ToDosServiceImpl implements ToDosService {

    private final ToDosRepository toDosRepository;
    private final AssigneeRepository assigneeRepository;
    private final ModelMapper mapper;

    public ToDosServiceImpl(ToDosRepository toDosRepository, AssigneeRepository assigneeRepository, ModelMapper mapper) {
        this.toDosRepository = toDosRepository;
        this.assigneeRepository = assigneeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ToDosResponse> getAllToDosDto() {
        List<ToDo> allToDos = this.toDosRepository.findAll();

        return allToDos.stream()
                .map(toDo -> {
                    // Manually map fields from ToDo to ToDosResponse
                    ToDosResponse mapped = new ToDosResponse();
                    mapped.setId(toDo.getId());
                    mapped.setTitle(toDo.getTitle());
                    mapped.setFinished(toDo.isFinished());
                    mapped.setDescription(toDo.getDescription());
                    mapped.setCreatedDate(toDo.getCreatedDate() != null ? toDo.getCreatedDate().getTime() : null);
                    mapped.setDueDate(toDo.getDueDate() != null ? toDo.getDueDate().getTime() : null);
                    mapped.setFinishedDate(toDo.getFinishedDate() != null ? toDo.getFinishedDate().getTime() : null);

                    // Map assignees manually
                    List<AssigneeResponse> mappedAssignees = toDo.getAssigneeList().stream()
                            .map(assignee -> {
                                AssigneeResponse assigneeResponse = new AssigneeResponse();
                                assigneeResponse.setId(assignee.getId());
                                assigneeResponse.setName(assignee.getName());
                                assigneeResponse.setEmail(assignee.getEmail());
                                assigneeResponse.setPrename(assignee.getPrename() != null ? assignee.getPrename() : ""); // Default to empty string
                                return assigneeResponse;
                            })
                            .collect(Collectors.toList());

                    mapped.setAssigneeList(mappedAssignees);
                    return mapped;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ToDosResponse getToDoById(Long id) {
        ToDo toDo = this.toDosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "No such ToDo with id " + id));
        return getToDosResponse(toDo, toDo.getAssigneeList());
    }

    @Override
    public ToDosResponse createToDo(ToDosRequest toDosRequest) {
        ToDo mapped = new ToDo();
        mapped.setDescription(toDosRequest.getDescription());
        mapped.setTitle(toDosRequest.getTitle());

        // Convert the dueDate (timestamp) to Date
        if (toDosRequest.getDueDate() != null) {
            try {
                long timestamp = Long.parseLong(toDosRequest.getDueDate());  // Now it's a long, no need to parse
                mapped.setDueDate(new Date(timestamp));  // Set the dueDate as a Date object
            } catch (NumberFormatException e) {
                throw new InvalidDueDateException("Invalid date format : " + toDosRequest.getDueDate());
            }
        }

        mapped.setCreatedDate(new Date()); // Set the current date as created date

        List<Assignee> assignees = new ArrayList<>();
        if (toDosRequest.getAssigneeIdList() != null && !toDosRequest.getAssigneeIdList().isEmpty()) {
            validateAssigneesExist(mapped, assignees, toDosRequest.getAssigneeIdList());
        }
        return getToDosResponse(mapped, assignees);
    }

    @Override
    @Transactional
    public Optional<ToDo> deleteToDo(Long todoId) {
        return this.toDosRepository.findById(todoId);
    }

    @Override
    @Transactional
    public ToDosResponse updateExistingToDo(Long toDoId, ToDosUpdateRequest toDosRequest) {
        ToDo toDo = this.toDosRepository.findById(toDoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.valueOf(404),String.format(Constants.TODO_NOT_FOUND_MESSAGE, toDoId)));

        List<Assignee> assignees = new ArrayList<>();
        if (!toDosRequest.getTitle().isBlank()) {
            toDo.setTitle(toDosRequest.getTitle());
        }

        if (!toDosRequest.getDescription().isBlank()) {
            toDo.setDescription(toDosRequest.getDescription());
        }

        // Convert dueDate from String to Long (timestamp)
        if (toDosRequest.getDueDate() != null && !toDosRequest.getDueDate().isBlank()) {
            Date dueDate = new Date(Long.parseLong(toDosRequest.getDueDate()));

            if (dueDate.before(new Date())) {
                throw new InvalidDueDateException("The due date cannot be in the past.");
            }

            toDo.setDueDate(dueDate);
        }

        toDo.setFinished(toDosRequest.getFinished());

        if (toDosRequest.getAssigneeIdList() != null && !toDosRequest.getAssigneeIdList().isEmpty()) {
            // Detach all current assignees
            toDo.getAssigneeList().forEach(assignee -> assignee.setToDo(null));
            toDo.getAssigneeList().clear();
            validateAssigneesExist(toDo, assignees, toDosRequest.getAssigneeIdList());
        }

        return getToDosResponse(toDo, assignees);
    }

    private ToDosResponse getToDosResponse(ToDo toDo, List<Assignee> assignees) {
        toDo.setAssigneeList(assignees);
        this.toDosRepository.saveAndFlush(toDo);

        ToDosResponse mappedResponse = new ToDosResponse();
        mappedResponse.setDescription(toDo.getDescription());
        mappedResponse.setTitle(toDo.getTitle());
        mappedResponse.setId(toDo.getId());
        mappedResponse.setDueDate(toDo.getDueDate().getTime());
        mappedResponse.setFinished(toDo.isFinished());
        mappedResponse.setCreatedDate(toDo.getCreatedDate() != null ? toDo.getCreatedDate().getTime() : null);
        mappedResponse.setFinishedDate(toDo.getFinishedDate() != null ? toDo.getFinishedDate().getTime() : null);

        List<AssigneeResponse> mappedAssignees = toDo.getAssigneeList().stream()
                .map(assignee -> {
                    AssigneeResponse assigneeResponse = new AssigneeResponse();
                    assigneeResponse.setId(assignee.getId());
                    assigneeResponse.setPrename(assignee.getPrename() != null ? assignee.getPrename() : ""); // Ensure prename is set
                    assigneeResponse.setEmail(assignee.getEmail());
                    assigneeResponse.setName(assignee.getName());
                    assigneeResponse.setId(assignee.getId());
                    return assigneeResponse;
                })
                .collect(Collectors.toList());

        mappedResponse.setAssigneeList(mappedAssignees);
        return mappedResponse;
    }

    private void validateAssigneesExist(ToDo mapped, List<Assignee> assignees, ArrayList<Long> assigneeIdList) {
        Set<Long> assigneeIdSet = new HashSet<>(assigneeIdList);

        assigneeIdSet.forEach(id -> {
            Assignee assignee = this.assigneeRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(400),String.format(Constants.ASSIGNEE_NOT_FOUND_MESSAGE, id)));
            assignee.setToDo(mapped);
            assignees.add(assignee);
        });
    }

}
