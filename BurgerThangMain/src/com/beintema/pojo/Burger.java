package com.beintema.pojo;

public class Burger{
	
	private int burger_id;
	private String burgername;
	private Restaurant restaurant;
	private String ingredients;
	private double price;
	private int preptime;
	
	public Burger() {
		super();
	}
	
	public Burger(int burger_id, String burgername, Restaurant restaurant, String ingredients, double price, int preptime) {
		this.setBurger_id(burger_id);
		this.setBurgername(burgername);
		this.setRestaurant(restaurant);
		this.setIngredients(ingredients);
		this.setPrice(price);
		this.setPreptime(preptime);
	}

	public String getBurgername() {
		return burgername;
	}

	public void setBurgername(String burgername) {
		this.burgername = burgername;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPreptime() {
		return preptime;
	}

	public void setPreptime(int time) {
		this.preptime = time;
	}

	public int getBurger_id() {
		return burger_id;
	}

	public void setBurger_id(int burger_id) {
		this.burger_id = burger_id;
	}
	
	
}
