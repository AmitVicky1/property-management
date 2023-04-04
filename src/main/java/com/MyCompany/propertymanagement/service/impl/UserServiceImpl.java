package com.MyCompany.propertymanagement.service.impl;

import com.MyCompany.propertymanagement.converter.UserConverter;
import com.MyCompany.propertymanagement.dto.UserDTO;
import com.MyCompany.propertymanagement.entity.UserEntity;
import com.MyCompany.propertymanagement.exception.BusinessException;
import com.MyCompany.propertymanagement.exception.ErrorModel;
import com.MyCompany.propertymanagement.repository.UserRepository;
import com.MyCompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {

        // check if user already exists
        Optional<UserEntity> optUE = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if(optUE.isPresent()){
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("USER_ALREADY_REGISTERED");
            errorModel.setMessage("This Email is already registered.");
            errorModelList.add(errorModel);
            throw  new BusinessException(errorModelList);
        }

        UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
        userRepository.save(userEntity);
        userDTO = userConverter.convertEntityToDTO(userEntity);

        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {

        UserDTO userDTO = null;
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email, password);
        // findByID of CRUD is Optional type means may be null also
        if(optionalUserEntity.isPresent()){
            userDTO = userConverter.convertEntityToDTO(optionalUserEntity.get());
        }else{ // new code ERROR HANDLING
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Incorrect EMAIl or PASSWORD");
            errorModelList.add(errorModel);
            /// we can keep adding errors like this only in the errorModelList

            throw  new BusinessException(errorModelList);  // we need to declare throws Exception everywhere
            // else we can do is declare Business Exception extends RunTimeExceptions() and not extend Exceptions()
        }
        return userDTO;
    }
}
