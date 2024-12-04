package com.pe2.api.domain.dtos.request;

import com.pe2.api.validation.annotations.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssigneeRequest {
    @Size(min = 1)
    private String prename;

    @Size(min = 1)
    private String name;

    @NotBlank
    @ValidEmail
    private String email;
}
