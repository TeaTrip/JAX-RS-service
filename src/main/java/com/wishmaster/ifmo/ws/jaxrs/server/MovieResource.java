package com.wishmaster.ifmo.ws.jaxrs.server;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/movie")
@Produces({MediaType.APPLICATION_JSON})
public class MovieResource {
    private final PostgreeSQLDAO dbMovie = new PostgreeSQLDAO();

    @Path("/selectAll")
    @GET
    public List<Movie> selectAll() {
        return dbMovie.selectAll();
    }
    
    @Path("/selectById/{id}")
    @GET
    public Movie selectById(@PathParam("id") int id) {
        return dbMovie.selectById(id);
    }

    @Path("/selectByYear/{year}")
    @GET
    public List<Movie> selectByYear(@PathParam("year")int year) {
        return dbMovie.selectByYear(year);
    }

    @Path("/selectByRating")
    @GET
    public List<Movie> selectByRating(@QueryParam("rating")int rating) {
        return dbMovie.selectByRating(rating);
    }

    @Path("/selectByName")
    @GET
    public List<Movie> selectByName(@QueryParam("name")String name) {
        return dbMovie.selectByName(name);
    }

    @Path("/selectByGenre")
    @GET
    public List<Movie> selectByGenre(@QueryParam("genre")String genre) {
        return dbMovie.selectByGenre(genre);
    }

    @Path("/selectByDirector")
    @GET
    public List<Movie> selectByDirector(@QueryParam("director")String director) {
        return dbMovie.selectByDirector(director);
    }

    @Path("/selectByYearAndRating")
    @GET
    public List<Movie> selectByYearAndRating(@QueryParam("year")String year,
                                             @QueryParam("rating")String rating) {
        return dbMovie.selectByYearAndRating(year, rating);
    }

    @Path("/selectByYearAndGenre")
    @GET
    public List<Movie> selectByYearAndGenre(@QueryParam("year")String year,
                                            @QueryParam("genre")String genre) {
        return dbMovie.selectByYearAndGenre(year, genre);
    }

    @Path("/selectByRatingAndGenre")
    @GET
    public List<Movie> selectByRatingAndGenre(@QueryParam("rating")String rating,
                                              @QueryParam("genre")String genre) {
        return dbMovie.selectByRatingAndGenre(rating, genre);
    }

    /*
    @Path("/createNewMovie")
    public int createNewMovie(@QueryParam("name") String name,
                              @QueryParam("year") int year,
                              @QueryParam("rating") int rating,
                              @QueryParam("genre") String genre,
                              @QueryParam("director") String director)
            throws IllegalParameterException {
        if(name == null || name.trim().isEmpty()){
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            throw new IllegalParameterException("name is not specified", fault);
        }
        if(year < 0){
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            throw new IllegalParameterException("year cannot be negative", fault);
        }
        if(rating < 1 && rating > 10){
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            throw new IllegalParameterException("rating should be in 1 to 10 range", fault);
        }
        if(genre == null || genre.trim().isEmpty()){
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            throw new IllegalParameterException("genre is not specified", fault);
        }
        if(director == null || director.trim().isEmpty()){
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            throw new IllegalParameterException("director is not specified", fault);
        }
        Movie movie = new Movie(0, year, rating, name, genre, director);
        return dbMovie.createNewMovie(movie);
    }

    @Path("/updateMovie")
    
    public int updateMovie(@QueryParam("id") int id,
                           @QueryParam("name") String name,
                           @QueryParam("year") int year,
                           @QueryParam("rating") int rating,
                           @QueryParam("genre") String genre,
                           @QueryParam("director") String director)
            throws IllegalParameterException {

        System.out.println(selectById(id).getId());
        if(selectById(id).getId() == 0){
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            throw new IllegalParameterException("Row with this id not found", fault);
        }

        if(year < 0){
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            throw new IllegalParameterException("year cannot be negative", fault);
        }
        if(rating < 1 && rating > 10){
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            throw new IllegalParameterException("rating should be in 1 to 10 range", fault);
        }
        if(genre == null || genre.trim().isEmpty()){
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            throw new IllegalParameterException("genre is not specified", fault);
        }
        if(director == null || director.trim().isEmpty()){
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            throw new IllegalParameterException("director is not specified", fault);
        }
        Movie movie = new Movie(id, year, rating, name, genre, director);
        return dbMovie.updateMovie(movie);
    }

    @Path("/deleteMovie")
    
    public int deleteMovie(@QueryParam("id") int id) throws IllegalParameterException{
        if(selectById(id).getId() == 0){
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            throw new IllegalParameterException("Row with this id not found", fault);
        }
        return dbMovie.deleteMovie(id);
    }

     */
}




/*
@Path("/persons")

@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {
    @GET
    public List<Person> getPersons(@QueryParam("name") String name) {
        List<Person> persons = new PostgreSQLDAO().getPersonsByName(name);
        return persons;
    }
}
*/