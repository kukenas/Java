package com.dematic.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom Index Validator to define MIN and MAX values
 * 
 * @author Aurimas
 *
 */
public class CustomIndexValidator implements ConstraintValidator<IndexValue, Integer> {

	private int min;
	private int max;

	@Override
	public void initialize(IndexValue constraintAnnotation) {
		this.min = constraintAnnotation.min();
		this.max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return (value >= min && value <= max);
	}

}
