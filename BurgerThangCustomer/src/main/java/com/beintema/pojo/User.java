package com.beintema.pojo;

public class User {
	
	private int user_id;
	private String user_name;
	private String password;
	private String phonenumber;
	private String bank_account;
	private int customer_x;
	private int customer_y;
	
	public User() {
		super();
	}
	
	public User(int user_id, String user_name, String password, String phonenumber, String bankaccount, int customer_x, int customer_y) {
		this.setUser_id(user_id);
		this.setUser_name(user_name);
		this.setPassword(password);
		this.setPhonenumber(phonenumber);
		this.setBank_account(bankaccount);
		this.setCustomer_x(customer_x);
		this.setCustomer_y(customer_y);
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public int getCustomer_x() {
		return customer_x;
	}

	public void setCustomer_x(int customer_x) {
		this.customer_x = customer_x;
	}

	public int getCustomer_y() {
		return customer_y;
	}

	public void setCustomer_y(int customer_y) {
		this.customer_y = customer_y;
	}
	

	
}
