package com.demo.productservice.validator;

import com.demo.productservice.validator.annotation.DependencyConstraint;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class DependencyConstraintValidator implements ConstraintValidator<DependencyConstraint, Object> {

    //private final MessageResolver messageResolver;
    private String attribute;
    private Set<String> dependsOn;

//    public DependencyConstraintValidator(MessageResolver messageResolver){
//        this.messageResolver = messageResolver;
//    }

    @Override
    public void initialize(DependencyConstraint constraintAnnotation){
        attribute = constraintAnnotation.attribute();
        dependsOn = Arrays.stream(constraintAnnotation.dependsOn()).collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object property = getProperty(o, attribute);
        if(property == null){
            return true;
        }
        List<String> errors = dependsOn.stream()
                .filter(d -> getProperty(o, d) == null)
                .collect(Collectors.toList());
        if(!errors.isEmpty()){
//            constraintValidatorContext.disableDefaultConstraintViolation();
//            String msgDefault = constraintValidatorContext.getDefaultConstraintMessageTemplate();
//            String msgWithParams = String.format(msgDefault, String.join(",", errors));
//            constraintValidatorContext.buildConstraintViolationWithTemplate(msgWithParams).addConstraintViolation();
            constraintValidatorContext.disableDefaultConstraintViolation();
            HibernateConstraintValidatorContext hibernateConstraintValidatorContext = constraintValidatorContext.unwrap( HibernateConstraintValidatorContext.class );

            hibernateConstraintValidatorContext.addMessageParameter("failedConstraint", String.join(",", errors));
            //String message = messageResolver.getMessage(constraintValidatorContext.getDefaultConstraintMessageTemplate(), String.join(",", errors));
            constraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate())
                    .addConstraintViolation();
        }

        return errors.isEmpty();
    }
    private Object getProperty(Object object, String property){
        return new BeanWrapperImpl(object).getPropertyValue(property);
    }
}
