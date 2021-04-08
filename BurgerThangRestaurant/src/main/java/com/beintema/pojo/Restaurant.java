package com.beintema.pojo;

import java.sql.Time;

public class Restaurant{
	
	private int restaurant_id;
	private String restaurantname;
	private String address;
	private int numitems;
	private Time opening;
	private Time closing;
	private String bankaccount;
	private int restaurant_x;
	private int restaurant_y;
	
	public Restaurant() {
		super();
	}
	
	public Restaurant(String restaurantname, String address, int numitems, Time opening, Time closing, String bankaccount, int x, int y) {
		this.setRestaurantname(restaurantname);
		this.setAddress(address);
		this.setNumitems(numitems);
		this.setOpening(opening);
		this.setClosing(closing);
		this.setBankaccount(bankaccount);
		this.setRestaurant_x(x);
		this.setRestaurant_y(y);
	}

	public String getRestaurantname() {
		return restaurantname;
	}

	public void setRestaurantname(String restaurantname) {
		this.restaurantname = restaurantname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNumitems() {
		return numitems;
	}

	public void setNumitems(int numitems) {
		this.numitems = numitems;
	}

	public Time getOpening() {
		return opening;
	}

	public void setOpening(Time opening) {
		this.opening = opening;
	}

	public Time getClosing() {
		return closing;
	}

	public void setClosing(Time closing) {
		this.closing = closing;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public int getRestaurant_x() {
		return restaurant_x;
	}

	public void setRestaurant_x(int restaurant_x) {
		this.restaurant_x = restaurant_x;
	}

	public int getRestaurant_y() {
		return restaurant_y;
	}

	public void setRestaurant_y(int restaurant_y) {
		this.restaurant_y = restaurant_y;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
}
