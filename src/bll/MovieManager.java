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
 * @author Jacob, Christian, René & Charlie
 */
public class MovieManager
{

    private IMovieDao movieDao;

    /**
     * Constructor for MovieManager
     *
     * @throws Exception
     */
    public MovieManager() throws Exception
    {
        movieDao = (IMovieDao) new MovieDBDAO();
    }

    /**
     * Returns all movies.
     *
     * @return
     * @throws DalException
     */
    public List<Movie> getAllMovies() throws DalException
    {
        return movieDao.getAllMovies();
    }

    /**
     * Creates the movie.
     *
     * @param movie
     * @throws DalException
     */
    public void createMovie(Movie movie) throws DalException
    {
        movieDao.createMovie(movie);
    }

    /**
     * Edits the movie
     *
     * @param movie
     * @throws DalException
     */
    public void editMovie(Movie movie) throws DalException
    {
        movieDao.editMovie(movie);
    }

    /**
     * Deletes the movie
     *
     * @param movie
     * @throws DalException
     */
    public void deleteMovie(Movie movie) throws DalException
    {
        movieDao.deleteMovie(movie);
    }

    /**
     * Opens the system default player and plays it.
     *
     * @param watchMovie
     * @throws IOException
     */
    public void playMovie(Movie watchMovie) throws IOException
    {
        watchMovie = watchMovie;
        String movieFile = watchMovie.getFilelink();
        Desktop.getDesktop().open(new File(movieFile));
    }

    /**
     * Returns all movies by name
     *
     * @return
     * @throws DalException
     */
    public List<String> getAllMoviesByName() throws DalException
    {
        return movieDao.getAllMoviesByName();
    }

    /**
     * Returns all unwatched movies.
     *
     * @return
     * @throws DalException
     */
    public List<Movie> getAllUnwatchedMovies() throws DalException
    {
        return movieDao.getAllUnwatchedMovies();
    }

    /**
     * Updates the movie for when it is played.
     *
     * @param movie
     * @throws DalException
     */
    public void playedMovie(Movie movie) throws DalException
    {
        movieDao.playedMovie(movie);
    }

    /**
     * Search the current movies, and displays it.
     *
     * @param movie
     * @param searchQuery
     * @return
     */
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

}
