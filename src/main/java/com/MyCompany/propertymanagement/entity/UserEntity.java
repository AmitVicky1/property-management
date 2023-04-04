package com.MyCompany.propertymanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USER_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id         // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)  // Responsibility of DB to generate according to the default method for h2 DB
    private long id;
    // Hibernate: create sequence hibernate_sequence start with 1 increment by 1

    private String ownerName;
    @Column(name="EMAIL",nullable=false)  // if not given names created like ownerEmail
    private String ownerEmail;
    private String phone;
    private String password;
}

