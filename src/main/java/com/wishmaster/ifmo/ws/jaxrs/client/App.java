package com.wishmaster.ifmo.ws.jaxrs.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.wishmaster.ifmo.ws.jaxrs.server.Movie;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
public class App {
    private static final String URL =
            "http://localhost:8080/rest/movie";

    public static void main(String[] args) {
        Client client = Client.create();
        printList(selectAll(client));
        System.out.println();
        System.out.println(selectById(client, 21));
        System.out.println();
        printList(selectByYear(client, 1999));
        System.out.println();
        printList(selectByRating(client,8));
        System.out.println();
        printList(selectByName(client, "Китобой"));
        System.out.println();
        printList(selectByGenre(client, "Драма"));
        System.out.println();
        printList(selectByDirector(client, "Obama"));
        System.out.println();
        printList(selectByYearAndRating(client, "2007", "4"));
        System.out.println();
        printList(selectByYearAndGenre(client, "2007", "hi"));
        System.out.println();
        printList(selectByRatingAndGenre(client, "8", "so good"));
        System.out.println(createNewMovie(client, "Futurama", 1998, 8, "Comedy", "Noname"));
        System.out.println();
        System.out.println(updateMovie(client, 19, "FIlM", 1998, 8, "just film", "wow"));
        System.out.println(deleteMovie(client, 23));
    }
    
   /* private static List<Movie> selectAll(Client client)
    {
        WebResource webResource = client.resource(URL);
        if (name != null) {
            webResource = webResource.queryParam("name", name);
        }
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() !=
                ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Movie>> type = new GenericType<List<Movie>>() {};
        return response.getEntity(type);
    } */

    
    private static List<Movie> selectAll(Client client) {
        WebResource webResource = client.resource(URL + "/selectAll");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() !=
                ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Movie>> type = new GenericType<List<Movie>>() {};
        return response.getEntity(type);
    }

    
    private static Movie selectById(Client client, int id) {
        WebResource webResource = client.resource(URL + "/selectById/" + id);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() !=
                ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<Movie> type = new GenericType<Movie>() {};
        return response.getEntity(type);
    }

    
    private static List<Movie> selectByYear(Client client, int year) {
        WebResource webResource = client.resource(URL + "/selectByYear/" + year);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() !=
                ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Movie>> type = new GenericType<List<Movie>>() {};
        return response.getEntity(type);
    }

    
    private static List<Movie> selectByRating(Client client,int rating) {
        WebResource webResource = client.resource(URL + "/selectByRating");
        webResource = webResource.queryParam("rating", String.valueOf(rating));
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() !=
                ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Movie>> type = new GenericType<List<Movie>>() {};
        return response.getEntity(type);
    }

    
    private static List<Movie> selectByName(Client client,String name) {
        WebResource webResource = client.resource(URL + "/selectByName");
        if (name != null) {
            webResource = webResource.queryParam("name", name);
        }
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() !=
                ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Movie>> type = new GenericType<List<Movie>>() {};
        return response.getEntity(type);
    }

    
    private static List<Movie> selectByGenre(Client client,String genre) {
        WebResource webResource = client.resource(URL + "/selectByGenre");
        if (genre != null) {
            webResource = webResource.queryParam("genre", genre);
        }
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() !=
                ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Movie>> type = new GenericType<List<Movie>>() {};
        return response.getEntity(type);
    }

    
    private static List<Movie> selectByDirector(Client client,String director) {
        WebResource webResource = client.resource(URL + "/selectByDirector");
        if (director != null) {
            webResource = webResource.queryParam("director", director);
        }
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() !=
                ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Movie>> type = new GenericType<List<Movie>>() {};
        return response.getEntity(type);
    }

    
    private static List<Movie> selectByYearAndRating(Client client, String year, String rating) {
        WebResource webResource = client.resource(URL + "/selectByYearAndRating");
        if (year != null && rating != null) {
            webResource = webResource.queryParam("year", year);
            webResource = webResource.queryParam("rating", rating);
        }
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() !=
                ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Movie>> type = new GenericType<List<Movie>>() {};
        return response.getEntity(type);
    }

    
    private static List<Movie> selectByYearAndGenre(Client client,String year,String genre) {
        WebResource webResource = client.resource(URL + "/selectByYearAndGenre");
        if (year != null && genre != null) {
            webResource = webResource.queryParam("year", year);
            webResource = webResource.queryParam("genre", genre);
        }
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() !=
                ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Movie>> type = new GenericType<List<Movie>>() {};
        return response.getEntity(type);
    }

    
    private static List<Movie> selectByRatingAndGenre(Client client,String rating,String genre) {

        WebResource webResource = client.resource(URL + "/selectByRatingAndGenre");
        if (rating != null && genre != null) {
            webResource = webResource.queryParam("rating", rating);
            webResource = webResource.queryParam("genre", genre);
        }
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() !=
                ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Movie>> type = new GenericType<List<Movie>>() {};
        return response.getEntity(type);
    }


    private static Movie createNewMovie(Client client, String name, int year, int rating, String genre, String director){
        WebResource webResource = client.resource(URL + "/createNewMovie");
        Movie movie = new Movie(0,year,rating,name,genre,director);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(ClientResponse.class,  movie);
        GenericType<Movie> type = new GenericType<Movie>() {};
        return response.getEntity(type);
    }

    private static Movie updateMovie(Client client, int id, String name, int year, int rating, String genre, String director){
        WebResource webResource = client.resource(URL + "/updateMovie");
        Movie movie = new Movie(id,year,rating,name,genre,director);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).put(ClientResponse.class,  movie);
        GenericType<Movie> type = new GenericType<Movie>() {};
        return response.getEntity(type);
    }

    private static int deleteMovie(Client client, int id){
        WebResource webResource = client.resource(URL + "/deleteMovie/" + id);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
        GenericType<List<Movie>> type = new GenericType<List<Movie>>() {};
        return response.getStatus();
    }


    private static void printList(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}
