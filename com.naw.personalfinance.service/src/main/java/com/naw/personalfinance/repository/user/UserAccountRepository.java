package com.naw.personalfinance.repository.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.naw.personalfinance.common.DataAccessManager;
import com.naw.personalfinance.common.IDataAccessManager;
import com.naw.personalfinance.common.PasswordEncryptor;
import com.naw.personalfinance.common.UserAccountCommonContants;
import com.naw.personalfinance.enumeration.UserRole;
import com.naw.personalfinance.user_account.UserAccount;

@Repository
public class UserAccountRepository implements IUserAccountRepository {

	@Override
	public List<UserAccount> getUserList(UserAccount userAccount) {
		IDataAccessManager dataAccessManager = new DataAccessManager();

		Session currentSession = null;
		Transaction transaction = null;
		List<UserAccount> userAccountList = new ArrayList<UserAccount>();
		try {
			SessionFactory sessionFactory = dataAccessManager.getSessionFactory();
			currentSession = sessionFactory.openSession();
			transaction = currentSession.beginTransaction();

			Query<UserAccount> query = null;
			if (userAccount.getName() != null && userAccount.getName().trim().isEmpty() == false
					&& userAccount.getUserRole() != null) {
				query = currentSession.createNamedQuery(UserAccountCommonContants.GET_USER_LIST_BY_NAME_AND_ROLE,
						UserAccount.class);
				query.setParameter("name", userAccount.getName().trim());
				query.setParameter("userRole", userAccount.getUserRole());
			}

			else if (userAccount.getName() != null && userAccount.getName().trim().isEmpty() == false) {
				query = currentSession.createNamedQuery(UserAccountCommonContants.GET_USER_LIST_BY_NAME,
						UserAccount.class);
				query.setParameter("name", userAccount.getName().trim());
			}

			else if (userAccount.getUserRole() != null) {
				query = currentSession.createNamedQuery(UserAccountCommonContants.GET_USER_LIST_BY_ROLE,
						UserAccount.class);
				query.setParameter("userRole", userAccount.getUserRole());
			} else {
				query = currentSession.createNamedQuery(UserAccountCommonContants.GET_ALL_USER_LIST, UserAccount.class);
			}

			userAccountList = query.getResultList();

			transaction.commit();

		} catch (Exception exp) {
			if (transaction != null)
				transaction.rollback();
			exp.printStackTrace();
		} finally {

			if (currentSession != null) {
				currentSession.close();
			}

		}

		return userAccountList;
	}

	@Override
	public UserAccount getById(Long id) {
		IDataAccessManager dataAccessManager = new DataAccessManager();

		Session currentSession = null;
		Transaction transaction = null;
		UserAccount userAccount = null;
		try {
			SessionFactory sessionFactory = dataAccessManager.getSessionFactory();
			currentSession = sessionFactory.openSession();
			transaction = currentSession.beginTransaction();
			userAccount = currentSession.get(UserAccount.class, id);

			transaction.commit();

		} catch (Exception exp) {
			if (transaction != null)
				transaction.rollback();
			exp.printStackTrace();
		} finally {

			if (currentSession != null) {
				currentSession.close();
			}

		}
		return userAccount;
	}

	@Override
	public UserAccount create(UserAccount userAccount) {
		IDataAccessManager dataAccessManager = new DataAccessManager();

		Session currentSession = null;
		Transaction transaction = null;
		try {
			SessionFactory sessionFactory = dataAccessManager.getSessionFactory();
			currentSession = sessionFactory.openSession();
			transaction = currentSession.beginTransaction();
			String userPassword = PasswordEncryptor.hashPassword(userAccount.getPassword());
			userAccount.setPassword(userPassword);
			currentSession.save(userAccount);
			transaction.commit();

		} catch (Exception exp) {
			if (transaction != null)
				transaction.rollback();
			exp.printStackTrace();
		} finally {

			if (currentSession != null) {
				currentSession.close();
			}

		}

		return userAccount;
	}

	@Override
	public UserAccount update(UserAccount userAccount) {

		IDataAccessManager dataAccessManager = new DataAccessManager();

		Session currentSession = null;
		Transaction transaction = null;

		try {
			SessionFactory sessionFactory = dataAccessManager.getSessionFactory();
			currentSession = sessionFactory.openSession();
			transaction = currentSession.beginTransaction();
			if (userAccount != null && userAccount.getId() != null) {
				UserAccount userAccountTemp = getById(userAccount.getId());
				if (userAccount.getAddress() != null && userAccount.getAddress().trim().isEmpty() == false) {
					userAccountTemp.setAddress(userAccount.getAddress());
				}
				if (userAccount.getEmail() != null && userAccount.getEmail().trim().isEmpty() == false) {
					userAccountTemp.setEmail(userAccount.getEmail());
				}
				if (userAccount.getName() != null && userAccount.getName().trim().isEmpty() == false) {
					userAccountTemp.setName(userAccount.getName());
				}
				if (userAccount.getUserRole() != null) {
					userAccountTemp.setUserRole(userAccount.getUserRole());
				}
				userAccount = (UserAccount) currentSession.merge(userAccountTemp);

			}

			transaction.commit();

		} catch (Exception exp) {
			if (transaction != null)
				transaction.rollback();
			exp.printStackTrace();
		} finally {

			if (currentSession != null) {
				currentSession.close();
			}

		}
		return userAccount;
	}

	@Override
	public void delete(UserAccount userAccount) {
		IDataAccessManager dataAccessManager = new DataAccessManager();

		Session currentSession = null;
		Transaction transaction = null;

		try {
			SessionFactory sessionFactory = dataAccessManager.getSessionFactory();
			currentSession = sessionFactory.openSession();
			transaction = currentSession.beginTransaction();
			userAccount = getById(userAccount.getId());
			currentSession.delete(userAccount);

			transaction.commit();

		} catch (Exception exp) {
			if (transaction != null)
				transaction.rollback();
			exp.printStackTrace();
		} finally {

			if (currentSession != null) {
				currentSession.close();
			}

		}

	}

}
