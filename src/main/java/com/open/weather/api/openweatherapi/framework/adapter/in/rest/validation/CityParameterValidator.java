package com.open.weather.api.openweatherapi.framework.adapter.in.rest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class CityParameterValidator implements ConstraintValidator<CityNameConstraint, String> {

    @Override
    public void initialize(CityNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        value = value.replaceAll("[^a-zA-Z0-9]", "");
        boolean isValid = !StringUtils.isNumeric(value) && !StringUtils.isAllBlank(value);
        if (!isValid) {

            context.buildConstraintViolationWithTemplate(value).addConstraintViolation();
            log.info("The city parameter is not valid. value:{}", value);
        }
        return !StringUtils.isNumeric(value) && !StringUtils.isAllBlank(value);
    }
}