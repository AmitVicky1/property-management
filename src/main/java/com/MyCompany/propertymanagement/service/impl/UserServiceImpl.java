package com.MyCompany.propertymanagement.service.impl;

import com.MyCompany.propertymanagement.converter.UserConverter;
import com.MyCompany.propertymanagement.dto.UserDTO;
import com.MyCompany.propertymanagement.entity.UserEntity;
import com.MyCompany.propertymanagement.repository.UserRepository;
import com.MyCompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {

        UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
        userRepository.save(userEntity);
        userDTO = userConverter.convertEntityToDTO(userEntity);

        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        return null;
    }
}
