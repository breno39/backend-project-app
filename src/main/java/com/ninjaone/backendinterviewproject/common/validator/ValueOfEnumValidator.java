package com.ninjaone.backendinterviewproject.common.validator;

import com.ninjaone.backendinterviewproject.common.annotation.MustBeInServiceType;
import com.ninjaone.backendinterviewproject.service.domain.ServiceType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<MustBeInServiceType, ServiceType> {
    private List<String> acceptedValues;

    @Override
    public void initialize(MustBeInServiceType annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(ServiceType value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(value.toString());
    }
}
