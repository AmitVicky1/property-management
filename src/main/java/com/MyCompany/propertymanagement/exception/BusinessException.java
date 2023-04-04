package com.MyCompany.propertymanagement.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor // no need for default constructor
public class BusinessException extends RuntimeException{

    private List<ErrorModel> errors; // Bind all erors once and throw them all

    public BusinessException(List<ErrorModel> errors){
        this.errors = errors;
    }
}
