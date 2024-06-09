package com.naw.personalfinance.user_account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.naw.personalfinance.common.UserAccountCommonContants;
import com.naw.personalfinance.enumeration.UserRole;

@NamedQueries({
		@NamedQuery(name = UserAccountCommonContants.GET_USER_LIST_BY_NAME, query = "from UserAccount userAccount where userAccount.name = :name order by userAccount.id ASC"),
		@NamedQuery(name = UserAccountCommonContants.GET_USER_LIST_BY_ROLE, query = "from UserAccount userAccount where userAccount.userRole= :userRole order by userAccount.id ASC"),
		@NamedQuery(name = UserAccountCommonContants.GET_USER_LIST_BY_NAME_AND_ROLE, query = "from UserAccount userAccount where userAccount.name= :name and userAccount.userRole= :userRole order by userAccount.id ASC"),
		@NamedQuery(name = UserAccountCommonContants.GET_ALL_USER_LIST, query = "from UserAccount userAccount order by userAccount.id ASC") })
@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable {
	private static final long serialVersionUID = 7102932081342240052L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "address")
	private String address;
	@Column(name = "email")
	private String email;
	@Column(name = "user_role")
	private UserRole userRole;
	@Column(name = "password")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}
