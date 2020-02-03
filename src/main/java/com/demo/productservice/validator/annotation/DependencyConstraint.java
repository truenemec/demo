package com.demo.productservice.validator.annotation;

import com.demo.productservice.validator.DependencyConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = DependencyConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(DependencyConstraints.class)
public @interface DependencyConstraint {

    String attribute();

    String[] dependsOn();

    Class<?>[] groups() default {};

    String message() default "";

    Class<? extends Payload>[] payload() default {};

}
