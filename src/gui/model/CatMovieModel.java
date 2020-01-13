/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.model;

import be.CatMovie;
import bll.CatMovieManager;
import dal.DalException;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LeChampDK
 */
public class CatMovieModel
{

    private static CatMovieModel instance;
    private ObservableList<CatMovie> allCatMovies;
    private ObservableList<CatMovie> selectedCatMovie;
    private ObservableList<CatMovie> allCatForMovies;
    private CatMovieManager catMovieManager;

    public CatMovieModel() throws DalException, Exception
    {
        this.catMovieManager = new CatMovieManager();
        allCatMovies = FXCollections.observableArrayList();
        allCatMovies.addAll(catMovieManager.getAllMovies());
        allCatForMovies = FXCollections.observableArrayList();
        allCatForMovies.addAll(catMovieManager.getCatForMovies());
        selectedCatMovie = FXCollections.observableArrayList();
    }

    public static CatMovieModel getInstance() throws IOException, Exception
    {
        if (instance == null)
        {
            instance = new CatMovieModel();
        }
        return instance;
    }

    public void loadCatMovies() throws DalException
    {
        allCatMovies.clear();
        allCatMovies.addAll(catMovieManager.getAllMovies());
    }

    public ObservableList<CatMovie> getAllCatMovies()
    {
        return allCatMovies;
    }
    
    public ObservableList<CatMovie> getCatForMovies()
    {
        return allCatMovies;
    }

    public void createCatMovies(CatMovie catMovie) throws DalException
    {
        catMovieManager.createCatMovies(catMovie);
    }

    public void editCategory(CatMovie catMovie) throws DalException
    {
        catMovieManager.editMovie(catMovie);
        allCatMovies.add(catMovie);
        allCatMovies.clear();
        allCatMovies.addAll(catMovieManager.getAllMovies());

    }

    public void deleteCatMovie(CatMovie selectedCatMovie) throws DalException
    {
        catMovieManager.deleteMovie(selectedCatMovie);
        allCatMovies.remove(selectedCatMovie);
    }

    public ObservableList<CatMovie> getSelectedCatMovie()
    {
        return selectedCatMovie;
    }
    
    public void addSelectedCategory(CatMovie catMovie)
    {
        selectedCatMovie.add(catMovie);
    }

    
}
