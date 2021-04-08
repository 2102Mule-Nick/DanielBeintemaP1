package com.beintema.pojo;

public class Deliverer {
	
	private int drivernumber;
	private String drivername;
	private String phone;
	private String licenseplate;
	private String carmodel;
	private String carcolor;
	private double rating;
	private String bankaccount;
	private String username;
	private String password;
	
	public Deliverer() {
		super();
	}
	
	public Deliverer(int drivernumber,String drivername, String phone, String licenseplate, String carmodel, String carcolor, double rating, String bankaccount, String username, String password) {
		this.setDrivernumber(drivernumber);
		this.setDrivername(drivername);
		this.setPhone(phone);
		this.setLicenseplate(licenseplate);
		this.setCarmodel(carmodel);
		this.setCarcolor(carcolor);
		this.setRating(rating);
		this.setBankaccount(bankaccount);
		this.setUsername(username);
		this.setPassword(password);
	}

	public int getDrivernumber() {
		return drivernumber;
	}

	public void setDrivernumber(int drivernumber) {
		this.drivernumber = drivernumber;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLicenseplate() {
		return licenseplate;
	}

	public void setLicenseplate(String licenseplate) {
		this.licenseplate = licenseplate;
	}

	public String getCarmodel() {
		return carmodel;
	}

	public void setCarmodel(String carmodel) {
		this.carmodel = carmodel;
	}

	public String getCarcolor() {
		return carcolor;
	}

	public void setCarcolor(String carcolor) {
		this.carcolor = carcolor;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
