package com.recruitment.validation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target( {ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PeselValidator.class)
public @interface Pesel {
  String message() default "Invalid Pesel value or user is under 18 years old!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};


}
