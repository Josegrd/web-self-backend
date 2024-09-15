package com.josegrd.web_self_api.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ValidationUtil {

    private final Validator validator;

    public void validate(Object obj){
        Set<ConstraintViolation<Object>> results = validator.validate(obj);
        if(!results.isEmpty()){
            throw new ConstraintViolationException(results);
        }
    }
}
