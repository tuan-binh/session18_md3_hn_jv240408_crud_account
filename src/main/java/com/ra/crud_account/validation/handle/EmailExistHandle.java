package com.ra.crud_account.validation.handle;

import com.ra.crud_account.dao.IAccountDao;
import com.ra.crud_account.validation.annotation.EmailExist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
@RequiredArgsConstructor
public class EmailExistHandle implements ConstraintValidator<EmailExist,String>
{
	private final IAccountDao accountDao;
	
	
	@Override
	public void initialize(EmailExist constraintAnnotation)
	{
	
	}
	
	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
	{
		return !accountDao.existByEmail(s);
	}
}
