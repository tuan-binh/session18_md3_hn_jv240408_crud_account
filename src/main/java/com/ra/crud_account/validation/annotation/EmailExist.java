package com.ra.crud_account.validation.annotation;

import com.ra.crud_account.validation.handle.EmailExistHandle;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailExistHandle.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailExist
{
	String message() default "email already exist";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
