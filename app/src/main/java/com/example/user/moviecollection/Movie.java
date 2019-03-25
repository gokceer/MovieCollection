package com.example.user.moviecollection;

public class Movie {
    int id;
    String title;
    String year;
    String director;
    String category;

    public Movie() {

    }

    public Movie(String title, String year, String director, String category) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.category = category;
    }

    public Movie(int id, String title, String year, String director, String category) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
