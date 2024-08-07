package com.ra.crud_account.dao.impl;

import com.ra.crud_account.dao.IAccountDao;
import com.ra.crud_account.model.Account;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountDaoImpl implements IAccountDao
{
	// Dependencies Injection
	private final SessionFactory sessionFactory;
	
	@Override
	public List<Account> findAll()
	{
		Session session = sessionFactory.openSession();
		try
		{
			// HQL - Hibernate Query Language
			return session.createQuery("select a from Account as a", Account.class)
					  .getResultList();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public Account findById(Long id)
	{
		Session session = sessionFactory.openSession();
		try
		{
			// HQL - Hibernate Query Language
			return session.get(Account.class, id);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void save(Account account)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			if (account.getId() == null)
			{
				session.save(account);
			}
			else
			{
				session.update(account);
			}
			transaction.commit();
		}
		catch (Exception e)
		{
			transaction.rollback();
			throw new RuntimeException(e);
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void deleteById(Long id)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.delete(findById(id));
			transaction.commit();
		}
		catch (Exception e)
		{
			transaction.rollback();
			throw new RuntimeException(e);
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public boolean existByEmail(String email)
	{
		Session session = sessionFactory.openSession();
		try
		{
			Long check = session
					  .createQuery("select count(a.id) from Account a where a.email like :email", Long.class)
					  .setParameter("email", email)
					  .getSingleResult();
			return check > 0;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			session.close();
		}
	}
}
