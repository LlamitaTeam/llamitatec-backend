package com.llamitatec.backend.request.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateRequestResource {
    @NotNull
    @NotBlank
    @Size(max=200)
    private String title;

    @NotNull
    @NotBlank
    @Size(max = 1000)
    private String description;

    @NotNull
    @NotBlank
    private String urlToImage;

    private Boolean paid;

    private Long clientId;

    private Long serviceId;

    private Long employeeId;
}
