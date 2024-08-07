package com.ra.crud_account.service.impl;

import com.ra.crud_account.dao.IAccountDao;
import com.ra.crud_account.model.Account;
import com.ra.crud_account.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService
{
	private final IAccountDao accountDao;
	
	@Override
	public List<Account> findAll()
	{
		return accountDao.findAll();
	}
	
	@Override
	public Account findById(Long id)
	{
		return accountDao.findById(id);
	}
	
	@Override
	public void save(Account account)
	{
		accountDao.save(account);
	}
	
	@Override
	public void deleteById(Long id)
	{
		accountDao.deleteById(id);
	}
}
