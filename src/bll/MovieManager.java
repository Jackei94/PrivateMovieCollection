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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LeChampDK
 */
public class MovieManager
{

    private String idMovie;

    private IMovieDao movieDao;

    public MovieManager() throws Exception
    {
        movieDao = (IMovieDao) new MovieDBDAO();
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

    public List<String> getAllMoviesByName() throws DalException
    {
        return movieDao.getAllMoviesByName();
    }

    public List<Movie> getAllUnwatchedMovies() throws DalException
    {
        return movieDao.getAllUnwatchedMovies();
    }

    public void playedMovie(Movie movie) throws DalException
    {
        movieDao.playedMovie(movie);
    }

}
