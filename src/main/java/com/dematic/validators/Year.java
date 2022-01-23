package com.dematic.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CustomYearValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Year {

	int max();
	
	String message() default "{Invalid Year}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
