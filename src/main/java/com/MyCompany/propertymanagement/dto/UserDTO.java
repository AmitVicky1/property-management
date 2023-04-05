package com.MyCompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // IGNORE NULL fields like passwords
@JsonIgnoreProperties(ignoreUnknown = true)

public class UserDTO {
    private Long id;
    private String ownerName;
    // requires separate dependency from hibernate // <artifactId>spring-boot-starter-validation</artifactId> invokes JAVAX validation constraints
    @NotNull(message = "Owner Email is mandatory")
    @NotEmpty(message = "Owner Email cannot be empty")
    @Size(min = 1, max = 50, message = "Owner Email should be between 1 and 50 characters!")
    private String ownerEmail; // should never be null as login not possible
    private String phone;
    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

}
