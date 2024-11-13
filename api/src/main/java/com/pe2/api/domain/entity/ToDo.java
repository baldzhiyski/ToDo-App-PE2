package com.pe2.api.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Table(name = "todos")
@Entity
@Getter
@Setter
public class ToDo extends BaseEntity {

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private boolean finished;

    @OneToMany(mappedBy = "toDo")
    private List<Assignee> assigneeList;

    @Column
    private Date createdDate;

    @Column
    private Date dueDate;

    @Column
    private Date finishedDate;
}
