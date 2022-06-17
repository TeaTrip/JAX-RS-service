package com.wishmaster.ifmo.ws.jaxrs.J2EEServer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;



@RequestScoped
@Path("/movie")
@Produces({MediaType.APPLICATION_JSON})
public class MovieResource {

    @Resource(lookup = "jdbc/ifmo-ws")
    private DataSource dataSource;


    @Path("/selectAll")
    @GET
    public List<Movie> selectAll() {
        PostgreeSQLDAO postgreSQLDAO = new PostgreeSQLDAO(getConnection());
        return postgreSQLDAO.selectAll();
    }

    @Path("/selectById/{id}")
    @GET
    public Movie selectById(@PathParam("id") int id) {
        PostgreeSQLDAO postgreSQLDAO = new PostgreeSQLDAO(getConnection());
        return postgreSQLDAO.selectById(id);
    }

    @Path("/selectByYear/{year}")
    @GET
    public List<Movie> selectByYear(@PathParam("year") int year) {
        PostgreeSQLDAO postgreSQLDAO = new PostgreeSQLDAO(getConnection());
        return postgreSQLDAO.selectByYear(year);
    }

    @Path("/selectByRating")
    @GET
    public List<Movie> selectByRating(@QueryParam("rating") int rating) {
        PostgreeSQLDAO postgreSQLDAO = new PostgreeSQLDAO(getConnection());
        return postgreSQLDAO.selectByRating(rating);
    }

    @Path("/selectByName")
    @GET
    public List<Movie> selectByName(@QueryParam("name") String name) {
        PostgreeSQLDAO postgreSQLDAO = new PostgreeSQLDAO(getConnection());
        return postgreSQLDAO.selectByName(name);
    }

    @Path("/selectByGenre")
    @GET
    public List<Movie> selectByGenre(@QueryParam("genre") String genre) {
        PostgreeSQLDAO postgreSQLDAO = new PostgreeSQLDAO(getConnection());
        return postgreSQLDAO.selectByGenre(genre);
    }

    @Path("/selectByDirector")
    @GET
    public List<Movie> selectByDirector(@QueryParam("director") String director) {
        PostgreeSQLDAO postgreSQLDAO = new PostgreeSQLDAO(getConnection());
        return postgreSQLDAO.selectByDirector(director);
    }

    @Path("/selectByYearAndRating")
    @GET
    public List<Movie> selectByYearAndRating(@QueryParam("year") String year,
                                             @QueryParam("rating") String rating) {
        PostgreeSQLDAO postgreSQLDAO = new PostgreeSQLDAO(getConnection());
        return postgreSQLDAO.selectByYearAndRating(year, rating);
    }

    @Path("/selectByYearAndGenre")
    @GET
    public List<Movie> selectByYearAndGenre(@QueryParam("year") String year,
                                            @QueryParam("genre") String genre) {
        PostgreeSQLDAO postgreSQLDAO = new PostgreeSQLDAO(getConnection());
        return postgreSQLDAO.selectByYearAndGenre(year, genre);
    }

    @Path("/selectByRatingAndGenre")
    @GET
    public List<Movie> selectByRatingAndGenre(@QueryParam("rating") String rating,
                                              @QueryParam("genre") String genre) {
        PostgreeSQLDAO postgreSQLDAO = new PostgreeSQLDAO(getConnection());
        return postgreSQLDAO.selectByRatingAndGenre(rating, genre);
    }

    private Connection getConnection(){
        Connection result = null;
        try{
            result = dataSource.getConnection();
        } catch (SQLException e){
            Logger.getLogger(MovieResource.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
}
