package com.dematic.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CustomIndexValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IndexValue {
	
	int min();
	int max();

	String message() default "{Invalid Index}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
