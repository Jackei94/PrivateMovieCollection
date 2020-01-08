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
import java.util.ArrayList;
import java.util.List;
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
    private ObservableList<Movie> selectedMovie;

    public MovieModel() throws Exception
    {
        this.movieManager = new MovieManager();
        allMovies = FXCollections.observableArrayList();
        allMovies.addAll(movieManager.getAllMovies());
        selectedMovie = FXCollections.observableArrayList();
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

    public ArrayList<Movie> search(List<Movie> movie, String searchQuery)
    {
        return movieManager.search(movie, searchQuery);
    }

    public void createMovie(Movie movie) throws DalException
    {
        movieManager.createMovie(movie);
    }

    public ObservableList<Movie> getSelectedMovie()
    {
        return selectedMovie;
    }

    public void editMovie(Movie movie) throws DalException
    {
        movieManager.editMovie(movie);
    }

    public void addSelectedMovie(Movie movie)
    {
        selectedMovie.add(movie);
    }

    public void deleteMovie(Movie selectedMovie) throws DalException
    {
        movieManager.deleteMovie(selectedMovie);
        allMovies.remove(selectedMovie);
    }
    
}
