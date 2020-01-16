/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import be.Movie;
import java.util.List;

/**
 *
 * @author Jacob, Christian, René & Charlie
 */
public interface IMovieDao
{

    /**
     * Interface for our Movie
     *
     * @return
     * @throws DalException
     */
    List<Movie> getAllMovies() throws DalException;

    void createMovie(Movie movie) throws DalException;

    void editMovie(Movie movie) throws DalException;

    void deleteMovie(Movie selectedMovie) throws DalException;

    List<String> getAllMoviesByName() throws DalException;

    List<Movie> getAllUnwatchedMovies() throws DalException;
    
    void playedMovie(Movie movie) throws DalException;
}
