package com.pe2.api.restController;

import com.pe2.api.domain.dtos.request.AssigneeUpdateRequest;
import com.pe2.api.domain.dtos.response.AssigneeResponse;
import com.pe2.api.domain.dtos.request.AssigneeRequest;
import com.pe2.api.service.AssigneeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignees")
public class AssigneeController {

    private AssigneeService assigneeService;

    public AssigneeController(AssigneeService assigneeService) {
        this.assigneeService = assigneeService;
    }


    @GetMapping
    public ResponseEntity<List<AssigneeResponse>> getAssignees(){
        return ResponseEntity.ok(assigneeService.getAllAssignees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssigneeResponse> getAssigneeById(@PathVariable Long id) {
        return assigneeService.getAssigneeById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AssigneeResponse> createAssignee( @RequestBody  @Valid AssigneeRequest assignee) {
        AssigneeResponse response = assigneeService.saveAssignee(assignee);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AssigneeResponse> updateAssignee(@PathVariable Long id, @RequestBody @Valid AssigneeUpdateRequest assigneeUpdateRequest) {
        AssigneeResponse response = assigneeService.updateAssignee(id,assigneeUpdateRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignee(@PathVariable Long id){
        assigneeService.deleteAssignee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // If deleted successfully
    }
}
