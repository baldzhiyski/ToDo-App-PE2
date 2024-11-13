package com.pe2.api.domain.dtos.request;

import com.pe2.api.validation.annotations.ValidUnixTimestamp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ToDosUpdateRequest {

    @NotBlank(message = "Title must not be empty.")
    @Size(min = 1, message = "Title must have at least one character.")
    private String title;

    private String description;

    private ArrayList<Long> assigneeIdList;

    @ValidUnixTimestamp
    private Long dueDate;

    @NotNull
    private Boolean finished;
}