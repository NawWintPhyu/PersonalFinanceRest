package com.naw.personalfinance.service.user;

import java.util.List;

import com.naw.personalfinance.user_account.UserAccount;

public interface IUserAccountService {
	public List<UserAccount> getUserList(UserAccount userAccount);

	public UserAccount getUserById(Long id);

	public UserAccount createUser(UserAccount userAccount);

	public UserAccount updateUser(UserAccount userAccount);

	public void deleteUser(UserAccount userAccount);

}
