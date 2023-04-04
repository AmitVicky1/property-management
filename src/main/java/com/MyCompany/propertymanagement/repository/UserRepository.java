package com.MyCompany.propertymanagement.repository;

import com.MyCompany.propertymanagement.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByOwnerEmailAndPassword(String email, String password);
    // custom findBy methods can be written by us
    // WE TOOk help of JPA, we don't have to write @QUERY
    // we directly mentioned the names of entities

    Optional<UserEntity> findByOwnerEmail(String email);

}
