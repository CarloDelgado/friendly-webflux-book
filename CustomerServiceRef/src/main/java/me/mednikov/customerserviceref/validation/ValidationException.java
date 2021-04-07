package me.mednikov.customerserviceref.validation;

import am.ik.yavi.core.ConstraintViolation;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public class ValidationException extends RuntimeException {
    
    @Getter
    final List<ConstraintViolation> errors;
    
    
}
