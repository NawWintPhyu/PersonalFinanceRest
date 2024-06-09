package com.naw.personalfinance.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naw.personalfinance.service.user.IUserAccountService;
import com.naw.personalfinance.user_account.UserAccount;

@RestController
@RequestMapping("/user-accounts")
public class UserAccountController {

	@Autowired
	private IUserAccountService userAccountService;

	@GetMapping
	public ResponseEntity<Object> get() {
		UserAccount userAccount = new UserAccount();
		List<UserAccount> productList = userAccountService.getUserList(userAccount);
		return new ResponseEntity<Object>(productList, HttpStatus.OK);
	}

	@PostMapping("/user-account")
	public ResponseEntity<Object> create(@RequestBody UserAccount userAccount) {
		userAccount = userAccountService.createUser(userAccount);

		return new ResponseEntity<Object>(userAccount, HttpStatus.OK);
	}

	@PutMapping("/user-account")
	public ResponseEntity<Object> update(@RequestBody UserAccount userAccount) {
		userAccount = userAccountService.updateUser(userAccount);
		return new ResponseEntity<Object>(userAccount, HttpStatus.OK);
	}

	@DeleteMapping("/user-account/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		UserAccount userAccount = new UserAccount();
		userAccount.setId(id);
		userAccountService.deleteUser(userAccount);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
