package com.bharti.stripe_provider_service.controller;

import com.bharti.stripe_provider_service.pojo.TestRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("test")
public class TestController {

    private final ObjectMapper objectMapper;

    @GetMapping
    public String test(@RequestParam int val1, @RequestParam  int val2){
        log.info("test method called with val1: {} and vale2: {}", val1, val2);
        return "test method 1: " + (val1 + val2);
    }

    @GetMapping("/m2")
    public String test2(){
        return "test method 2";
    }

    /*
   Create a method convertJsonObjectToJavaObject that takes a JSON string as input and output is TestRequest object
   using the Jackson library and handle exceptions that may occur during the conversion process. The method
   should return the TestRequest object if the conversion is successful, or throw an appropriate exception
    if there is an error in the JSON format or if the required fields are missing. use try catch
     */
//    private TestRequest convertJsonObjectToJavaObject(String json) throws JsonProcessingException {
//        //ObjectMapper objectMapper = new ObjectMapper();
//        TestRequest testRequest = null;
//        try {
//             testRequest = objectMapper.readValue(json, TestRequest.class);
//            return testRequest;
//        } catch (JsonProcessingException e) {
//            log.error("Error converting JSON to Java object: {}", e.getMessage());
//            throw e;
//        }
//    }
    //above method is commented bcz now springboot will handle the conversion of json to java object
    //bcz springboot will convert the incoming json string into java object and pass to our method

    @PostMapping("/{id1}/{id2}")
    public String testPost(@RequestParam("value1") int val1, @RequestParam int val2 ,
                           @PathVariable("id1") String id, @PathVariable String id2,
                           @RequestHeader("my-header") String myHeader,
//                           @RequestBody String requestBody
                           @RequestBody TestRequest testRequest
                           ) throws JsonProcessingException {
        log.info("testPost method called with val1: {} and vale2: {}", val1, val2);
        log.info("testPost method called with id: {} and id2: {}", id, id2);
        log.info("testPost method called with my-header: {}", myHeader);
//        TestRequest testRequest = convertJsonObjectToJavaObject(requestBody);
        int newAge  = 2 + testRequest.getAge();

//        log.info("testPost method called with body: {}", requestBody);
        log.info("testPost method called with converted object: {}", testRequest);
        log.info("testPost method called with new age: {}", newAge);

       return "test method post: " + (val1 * val2) + " id: " + id + " id2: " + id2 +
               " my-header: " + myHeader +
               "Converted Json object: " + testRequest +
               "Changed age is:" + newAge;
    }


}
