package com.MyCompany.propertymanagement.controller;

import com.MyCompany.propertymanagement.dto.CalculatorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// best practices, class level mapping of url to a controller class
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

//    http://localhost:8080/api/v1/calculator/add
//    http://localhost:8080/api/v1/calculator/add?num111=2.4&num222=3.8
    @GetMapping("/add/{num3}") // method level mapping of url to a controller function
    // mixed up @pathVar and @RequestPar
//    http://localhost:8080/api/v1/calculator/add/7.9?num111=2.4&num222=3.8
    public Double add(@RequestParam("num111") Double num1, @RequestParam("num222") Double num2, @PathVariable("num3") Double num3){
        // http://localhost:8080/api/v1/calculator/add?num111=2.4&num222=3.8
        // num111 is key and it's value is assigned to num1
        return num1+num2+num3;
    }

    @GetMapping("/sub/{num11}/{num22}") // map the values of url to java variables by PATH VARIABLE method
    public Double subtract(@PathVariable("num11") Double num1, @PathVariable("num22") Double num2){
//          http://localhost:8080/api/v1/calculator/sub/10.3/3.6
        Double result=null;
        if(num1>num2){
            result=num1-num2;
        } else{
            result=num2-num1;
        }
        return result;
    }
    // Now we use POST since its not possible to pass all the args in URl, what if 1000 numbers to be added? , so use POST
    // BROWSER can only fire GET Mapping , not POST Mapping ; POSTMAN->POST->BODY->raw->{"key1": Value, "key2": value,}ex: num1: 23.3, ; keys should have same name as data members of DTO else error
    // Evary HTTP has a HEADER and BODY ,in HEADER, we pass type of data : application/JSON and in BODY,we pass JSON Object
    @PostMapping("/mul")
    public ResponseEntity<Double> multiply(@RequestBody CalculatorDTO calculatorDTO){   // changed double to ResponseEntity<Double>
        Double result = null; // WRAPPER object default value is null
        result = calculatorDTO.getNum1() * calculatorDTO.getNum2() * calculatorDTO.getNum3() * calculatorDTO.getNum4();
        ResponseEntity<Double> responseEntity = new ResponseEntity<>(result, HttpStatus.CREATED); // Default was ok 200, now its Created 201
        return responseEntity;
    }
    // PUT(update all parts of a resource in server) = GET + POST(new resource in server) ; body+url args
    // DELETE(existing resource) = ONLY URL args
    // PATCH(Updates only some parts of resource like employee salary)
}
