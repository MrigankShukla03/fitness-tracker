package com.fitness.model;

public class Nutrition {
    private String foodName;
    private int calories;
    private String date;
    // Add other nutrition details as needed
    
    public Nutrition(String foodName, int calories, String date) {
		super();
		this.foodName = foodName;
		this.calories = calories;
		this.date = date;
	}

	public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
    
    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
    // Add getters and setters for other nutrition details
}
