package com.dematic.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom Year Validator to define MAX Release Date
 * @author Aurimas
 *
 */
public class CustomYearValidator implements ConstraintValidator<Year, Integer> {

	private int max;

	@Override
	public void initialize(Year constraintAnnotation) {
		this.max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return (value < max);
	}

}
