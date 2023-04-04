package com.MyCompany.propertymanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table(name = "PROPERTY_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {
    @Id         // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)  // Responsibility of DB to generate according to the default method for h2 DB
    private long id;
    // Hibernate: create sequence hibernate_sequence start with 1 increment by 1
    @Column(name="PROPERTY_TITLE",nullable=false)
    private String title; // property name
    private String description;
//    private String ownerName;
//    @Column(name="EMAIL",nullable=false)  // if not given names created like ownerEmail
//    private String ownerEmail;
    // have been made part of USER entity
    private Double price;
    private String address;
}
