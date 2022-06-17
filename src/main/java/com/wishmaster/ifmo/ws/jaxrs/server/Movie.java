package com.wishmaster.ifmo.ws.jaxrs.server;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Movie {
    private int id;
    private int year;
    private int rating;
    private String name;
    private String genre;
    private String director;

    public Movie(){

    }

    public Movie(int id, int year, int rating, String name, String genre, String director){
        this.id = id;
        this.year = year;
        this.rating = rating;
        this.name = name;
        this.genre = genre;
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", year=" + year +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }


}