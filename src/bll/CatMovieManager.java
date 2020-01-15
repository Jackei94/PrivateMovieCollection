/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import be.CatMovie;
import be.Category;
import be.Movie;
import dal.DalException;
import dal.ICatMovieDao;
import dal.database.CatMovieDBDAO;
import java.util.List;

/**
 *
 * @author LeChampDK
 */
public class CatMovieManager
{
    
    private ICatMovieDao catMovieDao;
    
    public CatMovieManager() throws Exception
    {
        catMovieDao = (ICatMovieDao) new CatMovieDBDAO();
    }
    
    public List<CatMovie> getAllMovies() throws DalException
    {
        return catMovieDao.getAllCatMovies();
    }
    
    public void createCatMovies(CatMovie catMovie) throws DalException
    {
        catMovieDao.createCatMovies(catMovie);
    }
    
    public void editMovie(CatMovie catMovie) throws DalException
    {
        catMovieDao.editCatMovies(catMovie);
    }
    
    public void deleteMovie(CatMovie catMovie) throws DalException
    {
        catMovieDao.deleteCatMovies(catMovie);
    }
    
    public List<CatMovie> getCatForMovies() throws DalException
    {
        return catMovieDao.getCatForMovies();
    }
    
    public List<Movie> getMoviesFromCats(Category chosenCat) throws DalException
    {
        return catMovieDao.getMoviesFromCats(chosenCat);
    }
}
