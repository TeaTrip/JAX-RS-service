package com.wishmaster.ifmo.ws.jaxrs.server;

import java.util.List;

import javax.resource.spi.work.SecurityContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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


    @Path("/createNewMovie")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON })
    public Movie createNewMovie(@HeaderParam("Authorization") String credential, Movie movie) throws IllegalParameterException {
        if(credential == null || !credential.equals("Basic cm9vdDphZG1pbg==")){
            throw new IllegalParameterException("Auth failed, plc check login/password");
        }
        if(movie.getName() == null || movie.getName().trim().isEmpty())
            throw new IllegalParameterException("name is not specified");
        if(movie.getYear() < 0)
            throw new IllegalParameterException("year cannot be negative");
        if(movie.getRating() < 1 && movie.getRating() > 10)
            throw new IllegalParameterException("rating should be in 1 to 10 range");
        if(movie.getGenre() == null || movie.getGenre().trim().isEmpty())
            throw new IllegalParameterException("genre is not specified");
        if(movie.getDirector() == null || movie.getDirector().trim().isEmpty())
            throw new IllegalParameterException("director is not specified");
        movie.setId(0);
        int movieId = dbMovie.createNewMovie(movie);
        return dbMovie.selectById(movieId);
    }

    @Path("/updateMovie")
    @PUT
    public Movie updateMovie(@HeaderParam("Authorization") String credential, Movie movie) throws IllegalParameterException {
        if(credential == null || !credential.equals("Basic cm9vdDphZG1pbg==")){
            throw new IllegalParameterException("Auth failed, plc check login/password");
        }
        if(dbMovie.selectById(movie.getId()).getId() == 0)
            throw new IllegalParameterException("Row with this id not found");
        if(movie.getYear() < 0)
            throw new IllegalParameterException("year cannot be negative");
        if(movie.getRating() < 1 && movie.getRating() > 10)
            throw new IllegalParameterException("rating should be in 1 to 10 range");
        if(movie.getGenre() == null || movie.getGenre().trim().isEmpty())
            throw new IllegalParameterException("genre is not specified");
        if(movie.getDirector() == null || movie.getDirector().trim().isEmpty())
            throw new IllegalParameterException("director is not specified");
        dbMovie.updateMovie(movie);
        return dbMovie.selectById(movie.getId());
    }

    @Path("/deleteMovie/{id}")
    @DELETE
    public Response deleteMovie(@HeaderParam("Authorization") String credential, @PathParam("id") int id) throws IllegalParameterException{
        if(credential == null || !credential.equals("Basic cm9vdDphZG1pbg==")){
            throw new IllegalParameterException("Auth failed, plc check login/password");
        }
        if(dbMovie.selectById(id).getId() == 0)
            throw new IllegalParameterException("Row with this id not found");
        int statuscode = dbMovie.deleteMovie(id);
        if (statuscode == 1){
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.GONE).build();
    }
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