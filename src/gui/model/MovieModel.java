/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.model;

import be.Movie;
import bll.MovieManager;
import dal.DalException;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LeChampDK
 */
public class MovieModel
{

    private static MovieModel instance;
    private ObservableList<Movie> allMovies;
    private MovieManager movieManager;
    private StringProperty newOrEdit = new SimpleStringProperty();

    public MovieModel() throws Exception
    {
//        this.movieManager = movieManager;
//        allMovies = FXCollections.observableArrayList();
        movieManager = new MovieManager();
        allMovies = FXCollections.observableArrayList();
        allMovies.addAll(movieManager.getAllMovies());
    }

    public static MovieModel getInstance() throws IOException, Exception
    {
        if (instance == null)
        {
            instance = new MovieModel();
        }
        return instance;
    }

    public ObservableList<Movie> getAllMovies()
    {
        return allMovies;
    }

    public void loadMovies() throws DalException
    {
        allMovies.clear();
        
        allMovies.addAll(movieManager.getAllMovies());
    }
    
    /**
     * Returns the newOrEdit stringproperty.
     *
     * @return
     */
    public StringProperty newOrEditProperty()
    {
        return newOrEdit;
    }

    /**
     * Sets the newOrEdit stringproperty.
     *
     * @param newOrEdit
     */
    public void setNewOrEdit(String newOrEdit)
    {
        newOrEditProperty().set(newOrEdit);
    }

    /**
     * Returns newOrEditProperty with the get() method.
     *
     * @return
     */
    public final String getNewOrEdit()
    {
        return newOrEditProperty().get();
    }

}
