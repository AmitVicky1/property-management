package com.MyCompany.propertymanagement.service.impl;

import com.MyCompany.propertymanagement.converter.PropertyConverter;
import com.MyCompany.propertymanagement.dto.PropertyDTO;
import com.MyCompany.propertymanagement.entity.PropertyEntity;
import com.MyCompany.propertymanagement.repository.PropertyRepository;
import com.MyCompany.propertymanagement.service.PropertyService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// FACTORY DESIGN Pattern => same interface, multiple implementing classes

// We have to tell spring to take care of object creation for this class
// by creating a singleton instance ,i.e, single bean instance(object) as it saves memory as OBJECT IS CREATED ONLY ONCE
// irrespective of request from different clients, the same bean is used for storing object data
// @Service, @Configuration , @Component, @repository , @Controller al have same functionality, create object as singleton instance
// as Service Layer, we go for @Service

@Service // Service is SingleTON, many instaces is called PROTOTYPE
public class PropertyServiceImpl implements PropertyService {

    @Value("${pms.dummy:}")
    private String dummy; // dummy config variable we need to access inside java class

    @Value("${spring.datasource.url:}")
    private String dbUrl;

    @Autowired
    private PropertyRepository propertyRepository; // spring auto implements the interface using @Autowired else we get null pointer error
    @Autowired
    private PropertyConverter propertyConverter;

    @Override // should be PROTOTYPE Design pattern, to store Different Properties of different users as they should'nt be able to access each others info like many different types of PROPERTIES like APARTMENTS, ROW HOUSE etc. // A new instance should be created everytime to keep local copy of the data
    public PropertyDTO saveProperty(PropertyDTO propertyDTO){

        // Converter code removed from here and created new package and new converter class
        PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
        pe=propertyRepository.save(pe); // save() only accepts entity type as args and we only have a DTO so we copy values in the pe PROPERTY ENTITY

//        PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
//        return dto;
        // can also use the args variable
        propertyDTO = propertyConverter.convertEntityToDTO(pe);
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {

        System.out.println("Inside Service "+dummy);
        System.out.println("Inside Service "+dbUrl);
        List<PropertyEntity> listOfProperties = (List<PropertyEntity>)propertyRepository.findAll(); // TYPE CASTING
        List<PropertyDTO> propertyList = new ArrayList<>();
        for(PropertyEntity pe:listOfProperties){
            PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
            propertyList.add(dto);
        }
        return propertyList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId); // OPTIONAL<PropertyEntity> helps us to do null check
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get(); // data from DB
            pe.setTitle(propertyDTO.getTitle());
            pe.setAddress(propertyDTO.getAddress());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setOwnerName(propertyDTO.getOwnerName());
            pe.setPrice(propertyDTO.getPrice());
            pe.setDescription(propertyDTO.getDescription());

            dto = propertyConverter.convertEntityToDTO(pe); // although we dont need to do this since we already have it in form of propertyDTO in args
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId); // OPTIONAL<PropertyEntity> helps us to do null check
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get(); // data from DB
            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntityToDTO(pe); // although we dont need to do this since we already have it in form of propertyDTO in args
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId); // OPTIONAL<PropertyEntity> helps us to do null check
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get(); // data from DB
            pe.setPrice(propertyDTO.getPrice());
            dto = propertyConverter.convertEntityToDTO(pe); // although we dont need to do this since we already have it in form of propertyDTO in args
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId){
        propertyRepository.deleteById(propertyId);
    }
}
