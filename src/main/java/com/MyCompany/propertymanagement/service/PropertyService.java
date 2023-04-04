package com.MyCompany.propertymanagement.service;

import com.MyCompany.propertymanagement.dto.PropertyDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// return the same object input by the user
public interface PropertyService {
    PropertyDTO saveProperty(PropertyDTO propertyDTO); // no need to mention as public PropertyDTO
    List<PropertyDTO> getAllProperties();
    PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId);
    PropertyDTO updatePropertyDescription(@RequestBody PropertyDTO propertyDTO, Long PropertyId);
    PropertyDTO updatePropertyPrice(@RequestBody PropertyDTO propertyDTO, Long PropertyId);
    void deleteProperty(Long propertyId);
}
