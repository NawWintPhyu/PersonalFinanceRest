package com.naw.personalfinance.repository.user;

import java.util.List;

import com.naw.personalfinance.user_account.UserAccount;

public interface IUserAccountRepository {
	public List<UserAccount> getUserList(UserAccount userAccount);

	public UserAccount getById(Long id);

	public UserAccount create(UserAccount userAccount);

	public UserAccount update(UserAccount userAccount);

	public void delete(UserAccount userAccount);
}
