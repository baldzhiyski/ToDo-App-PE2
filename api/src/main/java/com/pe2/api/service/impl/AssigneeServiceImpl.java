package com.pe2.api.service.impl;

import com.pe2.api.domain.dtos.request.AssigneeUpdateRequest;
import com.pe2.api.domain.dtos.response.AssigneeResponse;
import com.pe2.api.domain.dtos.request.AssigneeRequest;
import com.pe2.api.domain.dtos.response.ToDoDetails;
import com.pe2.api.domain.entity.Assignee;
import com.pe2.api.domain.entity.ToDo;
import com.pe2.api.exceptions.InvalidEmailException;
import com.pe2.api.exceptions.NoSuchAssigneeException;
import com.pe2.api.repository.AssigneeRepository;
import com.pe2.api.repository.ToDosRepository;
import com.pe2.api.service.AssigneeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssigneeServiceImpl implements AssigneeService {
    private AssigneeRepository assigneeRepository;

    private ToDosRepository toDosRepository;
    private ModelMapper mapper;

    public AssigneeServiceImpl(AssigneeRepository assigneeRepository, ToDosRepository toDosRepository, ModelMapper mapper) {
        this.assigneeRepository = assigneeRepository;
        this.toDosRepository = toDosRepository;
        this.mapper = mapper;
    }

    @Override
    public List<AssigneeResponse> getAllAssignees() {
        return this.assigneeRepository.findAll()
                .stream().map(assignee -> this.mapper.map(assignee, AssigneeResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AssigneeResponse> getAssigneeById(Long id) {
        return  this.assigneeRepository.findById(id)
                .map(assignee -> {
                    ToDoDetails mapped = this.mapper.map(assignee.getToDo(), ToDoDetails.class);
                    AssigneeResponse assigneeResponse = this.mapper.map(assignee, AssigneeResponse.class);
                    assigneeResponse.setToDoDetails(mapped);

                    return assigneeResponse;
                });
    }

    @Override
    public AssigneeResponse saveAssignee(AssigneeRequest assignee) {
        Assignee toBeSaved = this.mapper.map(assignee, Assignee.class);
        this.assigneeRepository.saveAndFlush(toBeSaved);

        return this.mapper.map(toBeSaved,AssigneeResponse.class);
    }

    @Override
    public AssigneeResponse updateAssignee(Long id, AssigneeUpdateRequest assigneeUpdateRequest) {
        Assignee assignee = this.assigneeRepository.findById(id)
                .orElseThrow(() -> new NoSuchAssigneeException("Assignee with id " + id + " not existing in the DB !"));

        if (this.assigneeRepository.existsByEmail(assigneeUpdateRequest.getEmail())) {
            throw new InvalidEmailException("There is an existing user with the email : " + assigneeUpdateRequest.getEmail());
        }
        if(!assigneeUpdateRequest.getName().isBlank()) {
            assignee.setName(assigneeUpdateRequest.getName());
        }

        if(!assigneeUpdateRequest.getEmail().isBlank()) {
            assignee.setEmail(assigneeUpdateRequest.getEmail());
        }

        if(!assigneeUpdateRequest.getPrename().isBlank()) {
            assignee.setPrename(assigneeUpdateRequest.getPrename());
        }

        this.assigneeRepository.saveAndFlush(assignee);

        Assignee updatedAssignee = this.assigneeRepository.findById(id).get();

        return this.mapper.map(updatedAssignee,AssigneeResponse.class);
    }

    @Override
    @Transactional
    public void deleteAssignee(Long id) {
        Assignee assignee = this.assigneeRepository.findById(id)
                .orElseThrow(() -> new NoSuchAssigneeException("Assignee with id " + id + " not existing in the DB !"));


        ToDo toDo = assignee.getToDo();
        if(toDo!=null) {
            toDo.getAssigneeList().remove(assignee);

            toDosRepository.saveAndFlush(toDo);

        }
        this.assigneeRepository.delete(assignee);
    }
}
