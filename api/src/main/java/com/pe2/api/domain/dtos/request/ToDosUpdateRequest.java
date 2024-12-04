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


    private String title;

    private String description;

    private ArrayList<Long> assigneeIdList;


    private String  dueDate;

    private Boolean finished;
}
