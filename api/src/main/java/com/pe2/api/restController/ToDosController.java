package com.pe2.api.restController;

import com.pe2.api.domain.dtos.request.ToDosRequest;
import com.pe2.api.domain.dtos.request.ToDosUpdateRequest;
import com.pe2.api.domain.dtos.response.ToDosResponse;
import com.pe2.api.domain.entity.ToDo;
import com.pe2.api.repository.ToDosRepository;
import com.pe2.api.service.ToDosService;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class ToDosController {

    private ToDosService toDosService;
    private ToDosRepository toDosRepository;

    public ToDosController(ToDosService toDosService, ToDosRepository toDosRepository) {
        this.toDosService = toDosService;
        this.toDosRepository = toDosRepository;
    }


    @GetMapping
    public List<ToDosResponse> getToDo(){
        return toDosService.getAllToDosDto();
    }

    @GetMapping("/{id}")
    public ToDosResponse getToDo(@PathVariable Long id){
        return toDosService.getToDoById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToDosResponse createATodo(@RequestBody @Valid ToDosRequest toDosRequest){
        return toDosService.createToDo(toDosRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ToDosResponse updateExistingToDo(@PathVariable Long id,@RequestBody @Valid ToDosUpdateRequest toDosRequest){
        return toDosService.updateExistingToDo(id,toDosRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ToDo deleteTodo(@PathVariable Long id){
        Optional<ToDo> toDo = this.toDosService.deleteToDo(id);

        if(toDo.isPresent()){
            toDo.get().getAssigneeList()
                    .forEach(assignee -> {
                        assignee.setToDo(null);
                    });

            this.toDosRepository.delete(toDo.get());
            return toDo.get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("ToDo with ID %s not found!", id));
        }

    }
}
