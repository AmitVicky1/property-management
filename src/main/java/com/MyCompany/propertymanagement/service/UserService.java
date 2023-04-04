package com.MyCompany.propertymanagement.service;

import com.MyCompany.propertymanagement.dto.UserDTO;

public interface UserService {

    UserDTO register(UserDTO userDTO);
    UserDTO login(String email, String password);
}
