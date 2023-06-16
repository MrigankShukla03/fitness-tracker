package com.fitness.model;

public class Nutrition {
    private String foodName;
    private int calories;
    // Add other nutrition details as needed

    public Nutrition(String foodName, int calories) {
        this.foodName = foodName;
        this.calories = calories;
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
    // Add getters and setters for other nutrition details
}
