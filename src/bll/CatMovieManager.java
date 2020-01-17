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
 * @author Jacob, Christian, René & Charlie
 */
public class CatMovieManager
{

    private ICatMovieDao catMovieDao;

    /**
     * Constructor for CatMovieManager
     *
     * @throws Exception
     */
    public CatMovieManager() throws Exception
    {
        catMovieDao = (ICatMovieDao) new CatMovieDBDAO();
    }

    /**
     * Sends the list to Gui Layer
     *
     * @return
     * @throws DalException
     */
    public List<CatMovie> getAllMovies() throws DalException
    {
        return catMovieDao.getAllCatMovies();
    }

    /**
     * Infomation of create Movie
     *
     * @param catMovie
     * @throws DalException
     */
    public void createCatMovies(CatMovie catMovie) throws DalException
    {
        catMovieDao.createCatMovies(catMovie);
    }

    /**
     * Information of Edit Movie
     *
     * @param catMovie
     * @throws DalException
     */
    public void editMovie(CatMovie catMovie) throws DalException
    {
        catMovieDao.editCatMovies(catMovie);
    }

    /**
     * Information of delete Movie
     *
     * @param catMovie
     * @throws DalException
     */
    public void deleteMovie(CatMovie catMovie) throws DalException
    {
        catMovieDao.deleteCatMovies(catMovie);
    }

    /**
     * Sends the list to Gui Layer
     *
     * @return
     * @throws DalException
     */
    public List<Category> getCatForMovies(Movie chosenMovie) throws DalException
    {
        return catMovieDao.getCatForMovies(chosenMovie);
    }

    /**
     * Sends the list to Gui Layer
     *
     * @param chosenCat
     * @return
     * @throws DalException
     */
    public List<Movie> getMoviesFromCats(Category chosenCat) throws DalException
    {
        return catMovieDao.getMoviesFromCats(chosenCat);
    }
}
