package org.example.bigevent.anno;

import jakarta.validation.Constraint;
import org.example.bigevent.validation.StateValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = StateValidation.class)
public @interface State {
    String message() default "state参数的值只能是已发布或者草稿";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
