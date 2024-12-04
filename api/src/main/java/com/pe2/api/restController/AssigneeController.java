package com.pe2.api.restController;

import com.pe2.api.domain.dtos.request.AssigneeUpdateRequest;
import com.pe2.api.domain.dtos.response.AssigneeResponse;
import com.pe2.api.domain.dtos.request.AssigneeRequest;
import com.pe2.api.domain.entity.Assignee;
import com.pe2.api.service.AssigneeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assignees")
public class AssigneeController {

    private AssigneeService assigneeService;

    public AssigneeController(AssigneeService assigneeService) {
        this.assigneeService = assigneeService;
    }


    @GetMapping
    public List<AssigneeResponse> getAssignees() {
        // Fetch the list of assignees

        // Return the list directly (no need to wrap in a Map)
        return assigneeService.getAllAssignees();
    }

    @GetMapping("/{id}")
    public AssigneeResponse getAssigneeById(@PathVariable Long id) {
        return assigneeService.getAssigneeById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignee not found"));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssigneeResponse createAssignee( @RequestBody  @Valid AssigneeRequest assignee) {
        return assigneeService.saveAssignee(assignee);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AssigneeResponse> updateAssignee(@PathVariable Long id, @RequestBody  AssigneeUpdateRequest assigneeUpdateRequest) {
        AssigneeResponse response = assigneeService.updateAssignee(id,assigneeUpdateRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignee(@PathVariable Long id){
        assigneeService.deleteAssignee(id);
        return new ResponseEntity<>(HttpStatus.OK);  // If deleted successfully
    }
}
