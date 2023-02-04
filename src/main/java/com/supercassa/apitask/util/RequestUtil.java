package com.supercassa.apitask.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supercassa.apitask.api.model.Request;
import com.supercassa.apitask.exception.ApiRequestException;

import java.util.Objects;

public class RequestUtil {

    public static Request validateJsonAndMap(String json) {
        Request request;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            request = objectMapper.readValue(json, Request.class);
        } catch (JsonProcessingException e) {
            throw new ApiRequestException(e.getMessage());
        }

        if(Objects.isNull(request.id()) || Objects.isNull(request.add())){
            throw new ApiRequestException("neither 'id' nor 'add' can be empty");
        }
        return request;
    }
}
