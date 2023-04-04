package com.MyCompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // IGNORE NULL fields like passwords
@JsonIgnoreProperties(ignoreUnknown = true)

public class UserDTO {
    private long id;
    private String ownerName;
    private String ownerEmail;
    private String phone;
    private String password;

}
