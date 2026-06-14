package com.bharti.stripe_provider_service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public <T> T convertJsonToObject(String json, Class<T> clazz){

        if(json==null || clazz==null){
            return null;
        }
             try{
                 return objectMapper.readValue(json, clazz);
             }
             catch(Exception e){
                 log.error("Failed to convert json to object", e);
                 return null;
             }
    }


    public String convertObjectToJson(Object obj){
        if(obj==null){
            return null;
        }

        try{
             return objectMapper.writeValueAsString(obj);
        }
        catch(Exception e){
            log.error("Failed to convert object to json", e);
            return null;
        }
    }


}
