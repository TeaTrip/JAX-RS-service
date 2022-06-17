package com.wishmaster.ifmo.ws.jaxrs.J2EEServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgreeSQLDAO {

    private final Connection connection;

    public  PostgreeSQLDAO(Connection connection){
        this.connection = connection;
    }

    private static List<Movie> serializeResult(ResultSet result){
        List<Movie> movies = new ArrayList<>();
        try{
            while (result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                int year = result.getInt("year");
                int rating = result.getInt("rating");
                String genre = result.getString("genre");
                String director = result.getString("director");
                Movie movie = new Movie(id, year, rating,name,genre,director);
                movies.add(movie);
            }
        } catch (SQLException e) {
            System.out.println("ResultSet incorrect");
            e.printStackTrace();
        }

        return movies;
    }

    public List<Movie> selectAll() {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = this.connection){
            Statement statement = connection.createStatement();
            String SqlQuery = "SELECT * FROM movies";
            ResultSet result = statement.executeQuery(SqlQuery);
            movies = this.serializeResult(result);
        } catch (SQLException e) {
            System.out.println("selectAll error");
            e.printStackTrace();
        }
        return movies;
    }

    public Movie selectById(int id) {
        Movie movie = new Movie();
        try (Connection connection = this.connection){
            Statement statement = connection.createStatement();
            String SqlQuery = "SELECT * FROM movies WHERE id=" + id;
            ResultSet result = statement.executeQuery(SqlQuery);
            while (result.next()) {
                int _id = result.getInt("id");
                String name = result.getString("name");
                int year = result.getInt("year");
                int rating = result.getInt("rating");
                String genre = result.getString("genre");
                String director = result.getString("director");
                movie = new Movie(_id, year, rating, name, genre, director);
            }
        } catch (SQLException e) {
            System.out.println("selectById error");
            e.printStackTrace();
        }
        return movie;
    }

    public List<Movie> selectByYear(int year) {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = this.connection){
            Statement statement = connection.createStatement();
            String SqlQuery = "SELECT * FROM movies WHERE year="+year;
            ResultSet result = statement.executeQuery(SqlQuery);
            movies = this.serializeResult(result);
        } catch (SQLException e) {
            System.out.println("selectByYear all error");
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> selectByRating(int rating) {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = this.connection){
            Statement statement = connection.createStatement();
            String SqlQuery = "SELECT * FROM movies WHERE rating="+rating;
            ResultSet result = statement.executeQuery(SqlQuery);
            movies = this.serializeResult(result);
        } catch (SQLException e) {
            System.out.println("selectByRating error");
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> selectByName(String name) {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = this.connection){
            Statement statement = connection.createStatement();
            String SqlQuery = String.format("SELECT * FROM movies WHERE name=\'%s\'",name);
            ResultSet result = statement.executeQuery(SqlQuery);
            movies = this.serializeResult(result);
        } catch (SQLException e) {
            System.out.println("selectByName error");
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> selectByGenre(String genre) {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = this.connection){
            Statement statement = connection.createStatement();
            String SqlQuery = String.format("SELECT * FROM movies WHERE genre=\'%s\'",genre);
            ResultSet result = statement.executeQuery(SqlQuery);
            movies = this.serializeResult(result);
        } catch (SQLException e) {
            System.out.println("selectByGenre error");
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> selectByDirector(String director) {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = this.connection){
            Statement statement = connection.createStatement();
            String SqlQuery = String.format("SELECT * FROM movies WHERE director=\'%s\'",director);
            ResultSet result = statement.executeQuery(SqlQuery);
            movies = this.serializeResult(result);
        } catch (SQLException e) {
            System.out.println("selectByDirector error");
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> selectByYearAndRating(String year, String rating) {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = this.connection){
            Statement statement = connection.createStatement();
            String SqlQuery = String.format("SELECT * FROM movies WHERE year=\'%s\' and rating=\'%s\'",year,rating);
            ResultSet result = statement.executeQuery(SqlQuery);
            movies = this.serializeResult(result);
        } catch (SQLException e) {
            System.out.println("selectByYearAndRating error");
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> selectByYearAndGenre(String year, String genre) {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = this.connection){
            Statement statement = connection.createStatement();
            String SqlQuery = String.format("SELECT * FROM movies WHERE year=\'%s\' and genre=\'%s\'",year,genre);
            ResultSet result = statement.executeQuery(SqlQuery);
            movies = this.serializeResult(result);
        } catch (SQLException e) {
            System.out.println("selectByYearAndGenre error");
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> selectByRatingAndGenre(String rating, String genre) {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = this.connection){
            Statement statement = connection.createStatement();
            String SqlQuery = String.format("SELECT * FROM movies WHERE rating=\'%s\' and genre=\'%s\'",rating,genre);
            ResultSet result = statement.executeQuery(SqlQuery);
            movies = this.serializeResult(result);
        } catch (SQLException e) {
            System.out.println("selectByRatingAndGenre error");
            e.printStackTrace();
        }
        return movies;
    }

    public int createNewMovie(Movie movie) {
        int id = -1;
        try (Connection connection = this.connection){
            Statement statement = connection.createStatement();
            String SqlQuery = String.format("INSERT INTO movies (name, year, rating, genre, director) " +
                            "values ('%s', '%d', '%d', '%s', '%s') RETURNING id"
                    ,movie.getName(),movie.getYear(),movie.getRating(), movie.getGenre(), movie.getDirector());
            ResultSet result = statement.executeQuery(SqlQuery);
            result.next();
            id = result.getInt("id");
            System.out.println("id is" + id);
        } catch (SQLException e) {
            System.out.println("createNewMovie error");
            e.printStackTrace();
        }
        return id;
    }

    public int updateMovie(Movie movie) {
        int result = -1;
        try (Connection connection = this.connection){
            Statement statement = connection.createStatement();
            String SqlQuery = String.format("UPDATE movies set name='%s', year='%d', rating='%d', genre='%s', director='%s' WHERE id='%d';"
                    ,movie.getName(), movie.getYear(), movie.getRating(), movie.getGenre(), movie.getDirector(), movie.getId());
            result = statement.executeUpdate(SqlQuery);
        } catch (SQLException e) {
            System.out.println("updateMovie error");
            e.printStackTrace();
        }
        return result;
    }

    public int deleteMovie(int id) {
        int result = -1;
        try (Connection connection = this.connection){
            Statement statement = connection.createStatement();
            String SqlQuery = String.format("DELETE FROM movies WHERE id='%d'", id);
            result = statement.executeUpdate(SqlQuery);
        } catch (SQLException e) {
            System.out.println("deleteMovie error");
            e.printStackTrace();
        }
        return result;
    }
}
