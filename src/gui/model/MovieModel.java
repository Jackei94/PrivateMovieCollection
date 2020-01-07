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

    public MovieModel()
    {
        this.movieManager = movieManager;
        allMovies = FXCollections.observableArrayList();
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

}
