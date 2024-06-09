package com.naw.personalfinance.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naw.personalfinance.repository.user.IUserAccountRepository;
import com.naw.personalfinance.user_account.UserAccount;

@Service
public class UserAccountService implements IUserAccountService {

	@Autowired
	private IUserAccountRepository userAccountRepository;

	@Override
	public List<UserAccount> getUserList(UserAccount userAccount) {

		List<UserAccount> userAccountList = userAccountRepository.getUserList(userAccount);
		return userAccountList;
	}

	@Override
	public UserAccount getUserById(Long id) {
		UserAccount userAccount = userAccountRepository.getById(id);
		return userAccount;
	}

	@Override
	public UserAccount createUser(UserAccount userAccount) {
		userAccount = userAccountRepository.create(userAccount);

		return userAccount;
	}

	@Override
	public UserAccount updateUser(UserAccount userAccount) {
		userAccount = userAccountRepository.update(userAccount);
		return userAccount;
	}

	@Override
	public void deleteUser(UserAccount userAccount) {
		userAccountRepository.delete(userAccount);
	}


}
