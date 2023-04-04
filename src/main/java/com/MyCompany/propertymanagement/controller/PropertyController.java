package com.MyCompany.propertymanagement.controller;

import com.MyCompany.propertymanagement.dto.PropertyDTO;
import com.MyCompany.propertymanagement.service.PropertyService;
import com.MyCompany.propertymanagement.service.impl.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1") // good habit to name versions and full name to url
public class PropertyController {

    @Value("${pms.dummy:}")
    private String dummy; // dummy config variable we need to access inside java class

    @Value("${spring.datasource.url:}")
    private String dbUrl;

    // http://localhost:8080/api/v1/properties/hello
    // RESTFUL API is just mapping of a url(@RequestMapping) to a java class function
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    } // we transformed normal java class function to RESTful API
    // which can be accessed via HTTP protocol with help of a url.
    //And now what this function returns goes via as a Response of this RESTful Webservice,
    // back to the client, here it was the browser.

//    We convert normal Java class and its function into a RESTful Webservice
//    @RestController and @GetMapping using these two annotations.

    // @Autowired = dependency injection, where its applied should also be a bean @RestController
    @Autowired // creates object by providing MEMORY to OBJECT(BEAN INSTANCE) of implementing class, which will be repointed by interface and INJECTED here.
    private PropertyService propertyService;

    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO){
//        propertyService.saveProperty(propertyDTO);
//        System.out.println(propertyDTO); // object not created but still will print value beacuse referenced in argument
//        return propertyDTO;

        propertyDTO = propertyService.saveProperty(propertyDTO);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/properties") // this is get the other is post so same url mapping can be used
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){
        System.out.println(dummy); // print config properties on console
        System.out.println(dbUrl);
        List<PropertyDTO> propertyList = propertyService.getAllProperties();
        ResponseEntity<List<PropertyDTO>> responseEntity = new ResponseEntity<>(propertyList, HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        propertyDTO = propertyService.updateProperty(propertyDTO, propertyId);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
        return responseEntity;
    }

    @PatchMapping("/properties/update-description/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        propertyDTO = propertyService.updatePropertyDescription(propertyDTO, propertyId);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;

    }

    @PatchMapping("/properties/update-price/{propertyId}") // PATCH
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        propertyDTO = propertyService.updatePropertyPrice(propertyDTO, propertyId);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;
    }
    @DeleteMapping("/properties/{propertyId}") // DELETE //Hibernate: delete from property_table where id=?
    public ResponseEntity<Void> deleteProperty(@PathVariable Long propertyId){
        propertyService.deleteProperty(propertyId);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return responseEntity;
    }
}
