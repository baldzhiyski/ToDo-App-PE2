package com.pe2.api.restController;

import com.pe2.api.domain.dtos.request.ToDosRequest;
import com.pe2.api.domain.dtos.request.ToDosUpdateRequest;
import com.pe2.api.domain.dtos.response.ToDosResponse;
import com.pe2.api.service.ToDosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDosController {

    private ToDosService toDosService;

    public ToDosController(ToDosService toDosService) {
        this.toDosService = toDosService;
    }


    @GetMapping
    public ResponseEntity<List<ToDosResponse>> getAllTodos(){
        return ResponseEntity.ok(toDosService.getAllToDosDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDosResponse> getAllTodos(@PathVariable Long id){
        return toDosService.getToDoById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ToDosResponse> createATodo(@RequestBody @Valid ToDosRequest toDosRequest){
        ToDosResponse response = toDosService.createToDo(toDosRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDosResponse> updateExistingToDo(@PathVariable Long id,@RequestBody @Valid ToDosUpdateRequest toDosRequest){
        ToDosResponse response = toDosService.updateExistingToDo(id,toDosRequest);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id){
        this.toDosService.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
