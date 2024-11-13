package com.pe2.api.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Table(name = "assignees")
@Entity
@Getter
@Setter
public class Assignee extends BaseEntity {

    @Column
    private String prename;

    @Column
    private String name;

    @Column
    @Email
    private String email;

    @ManyToOne
    private ToDo toDo;
}
