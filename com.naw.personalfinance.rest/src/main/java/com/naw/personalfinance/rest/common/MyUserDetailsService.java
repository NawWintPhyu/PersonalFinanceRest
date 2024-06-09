package com.naw.personalfinance.rest.common;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.naw.personalfinance.service.user.IUserAccountService;
import com.naw.personalfinance.user_account.UserAccount;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	IUserAccountService userAccountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount userAccountTemp = new UserAccount();
		userAccountTemp.setName(username);
		List<UserAccount> userAccountList = userAccountService.getUserList(userAccountTemp);
		UserAccount userAccount = null;
		if (userAccountList != null && userAccountList.size() > 0) {
			userAccount = userAccountList.get(0);
		}

		if (userAccount != null) {
			if (username.equals(userAccount.getName())) {
				List<SimpleGrantedAuthority> roles = null;
				roles = Arrays.asList(new SimpleGrantedAuthority(userAccount.getUserRole().getName()));

				return new User(username, userAccount.getPassword(), roles);
			}
		}

		throw new UsernameNotFoundException("User not found with the name " + username);
	}
}
