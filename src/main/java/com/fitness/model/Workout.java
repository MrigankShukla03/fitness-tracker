package com.fitness.model;

public class Workout {
	private String workoutName;
	private String duration;
	private String date;
	private String caloriesBurned;
	// Add other workout details as needed

	public Workout(String workoutName, String duration, String date) {
		this.workoutName = workoutName;
		this.duration = duration;
		this.date = date;
	}

	public Workout(String workoutName, String duration, String caloriesBurned, String date) {
		super();
		this.workoutName = workoutName;
		this.duration = duration;
		this.caloriesBurned = caloriesBurned;
		this.date = date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
