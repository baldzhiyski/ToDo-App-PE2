package com.pe2.api.domain.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssigneeResponse {

    private Long id;

    private String prename;


    private String name;

    private String email;

    private ToDoDetails toDoDetails;



}
