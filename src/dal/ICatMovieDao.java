/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import be.CatMovie;
import be.Category;
import be.Movie;
import java.util.List;

/**
 *
 * @author Jacob
 */
public interface ICatMovieDao
{

    List<CatMovie> getAllCatMovies() throws DalException;

    void createCatMovies(CatMovie catmovie) throws DalException;

    void editCatMovies(CatMovie catMovie) throws DalException;

    void deleteCatMovies(CatMovie selectedCatMovie) throws DalException;
    
    List<CatMovie> getCatForMovies() throws DalException;
    
    List<Movie> getMoviesFromCats(Category chosenCat) throws DalException;
}
