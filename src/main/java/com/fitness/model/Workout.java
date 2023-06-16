package com.fitness.model;

import java.util.Date;

public class Workout {
	private String workoutName;
	private String duration;
	private Date date;
	private String caloriesBurned;
	// Add other workout details as needed

	public Workout(String workoutName, String duration, Date date) {
		this.workoutName = workoutName;
		this.duration = duration;
		this.date = date;
	}

	public Workout(String workoutName, String duration, String caloriesBurned) {
		super();
		this.workoutName = workoutName;
		this.duration = duration;
		this.caloriesBurned = caloriesBurned;
	}

	public String getWorkoutName() {
		return workoutName;
	}

	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	// Add getters and setters for other workout details

	public String getCaloriesBurned() {
		return caloriesBurned;
	}

	public void setCaloriesBurned(String caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}
}
