/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import be.Movie;
import dal.DalException;
import dal.IMovieDao;
import dal.database.MovieDBDAO;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.json.JSONObject;

/**
 *
 * @author LeChampDK
 */
public class MovieManager
{

    private String apiKey;
    private String idMovie;

    private IMovieDao movieDao;

    public MovieManager() throws Exception
    {
        movieDao = (IMovieDao) new MovieDBDAO();
        Properties tmdbAPI = new Properties();
        tmdbAPI.load(new FileReader("TMDB API.txt"));
        apiKey = tmdbAPI.getProperty("API");
    }

    public List<Movie> getAllMovies() throws DalException
    {
        return movieDao.getAllMovies();
    }

    public ArrayList<Movie> search(List<Movie> movie, String searchQuery)
    {
        ArrayList<Movie> result = new ArrayList<>();

        for (Movie movies : movie)
        {
            String name = movies.getName().trim().toLowerCase();
            // double rating = movies.getRating();

            if (name.contains(searchQuery.toLowerCase().trim())
                    //  || rating.contains(searchQuery)
                    && !result.contains(movie))
            {
                result.add(movies);
            }
        }
        return result;
    }

    public void createMovie(Movie movie) throws DalException
    {
        movieDao.createMovie(movie);
    }

    public void editMovie(Movie movie) throws DalException
    {
        movieDao.editMovie(movie);
    }

    public void deleteMovie(Movie movie) throws DalException
    {
        movieDao.deleteMovie(movie);
    }

    public void playMovie(Movie watchMovie) throws IOException
    {
        watchMovie = watchMovie;
        String movieFile = watchMovie.getFilelink();
        Desktop.getDesktop().open(new File(movieFile));
    }

    public double getTmdbRating(Movie movie) throws MalformedURLException, IOException
    {
        try {
            idMovie = movie.getName();
//            idMovie = URLEncoder.encode(movie.getName(), StandardCharsets.UTF_8.toString());
//             idMovie = URLEncoder.encode(movie.getName(), java.nio.charset.StandardCharsets.UTF_8.name());
//                    movie.getName();
//            yearMovie = movie.getYear()
//            http://api.themoviedb.org/3/movie/22%20Jump%20street?api_key=f98e7f631bc6ae0e0af3f84614d30686
//            https://api.themoviedb.org/3/search/movie?api_key=f98e7f631bc6ae0e0af3f84614d30686&query=22%20Jump%20Street&page=1
//            "http://api.themoviedb.org/3/movie/" + idMovie + "?api_key=" + apiKey;
            String url = "https://api.themoviedb.org/3/search/movie?api_key=f98e7f631bc6ae0e0af3f84614d30686&query=22%20Jump%20Street&page=1";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            } in.close();
            movie.setTmdbRating(Double.parseDouble(response.indexOf(":") + 1));
//            System.out.println(response);
//            JSONObject obj_JSONObject = new JSONObject(response.toString());
//            System.out.println(obj_JSONObject);
//            movie.setTmdbRating(obj_JSONObject.getDouble("vote_average"));
//            System.out.println(obj_JSONObject.getString("vote_average"));
//            String a = "";
//            a.indexOf("vote_average");
            // "vote_average":6.9
            // s.substring(s.indexOf(":") + 2, s.indexOf(":") + 6);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return movie.getTmdbRating();
        
//        URL url = new URL("http://api.themoviedb.org/3/movie/550?api_key="apiKey");
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//
//        con.setDoOutput(true);
//        con.setRequestMethod("GET");
//        con.setRequestProperty("Content-Type", "application/json");
//
//        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
//
//        JSONObject obj_JSONObject = new JSONObject(con.getInputStream())
//        
//        
//        JSONReader rdr = JSON.createReader(con.getInputStream());
//        JSONObject obj = rdr.readObject();
//        String title = obj.getString("original_title");

//        String output;
//        System.out.println("Output from Server .... \n");
//        while ((output = br.readLine()) != null) {
//            System.out.println(output);
    }
}

