package com.pe2.api.service;

import com.pe2.api.domain.dtos.request.AssigneeRequest;
import com.pe2.api.domain.dtos.request.AssigneeUpdateRequest;
import com.pe2.api.domain.dtos.response.AssigneeResponse;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.Optional;

public interface AssigneeService {
    ArrayList<AssigneeResponse> getAllAssignees();

    Optional<AssigneeResponse> getAssigneeById(Long id);

    AssigneeResponse saveAssignee(@Valid AssigneeRequest assignee);

    AssigneeResponse updateAssignee(Long id, AssigneeUpdateRequest assigneeUpdateRequest);

    void deleteAssignee(Long id);
}
