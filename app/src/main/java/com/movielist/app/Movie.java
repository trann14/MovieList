package com.movielist.app;

public class Movie {

    private String title;
    private String category;
    private boolean watched;
    private boolean planToWatch;
    private float rating;
    private String notes;


    // Constructor

    public Movie(String title) {
        this.title = title;
        this.category = "";
        this.watched = false;
        this.planToWatch = true; // default
        this.rating = 0;
        this.notes = "";
    }


    // Getters

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public boolean isWatched() {
        return watched;
    }

    public boolean isPlanToWatch() {
        return planToWatch;
    }

    public float getRating() {
        return rating;
    }

    public String getNotes() {
        return notes;
    }

    // Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public void setPlanToWatch(boolean planToWatch) {
        this.planToWatch = planToWatch;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}