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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

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
    private ObservableList<Movie> unwMovieList;
    DatePicker datePicker = new DatePicker(LocalDate.now());

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

    public double getTmdbRating(Movie movie) throws IOException
    {
        return movieManager.getTmdbRating(movie);
    }

    public ObservableList<Movie> getSelectedMovie()
    {
        return selectedMovie;
    }

    public void editMovie(Movie movie) throws DalException
    {
        movieManager.editMovie(movie);
        allMovies.add(movie);
        allMovies.clear();
        allMovies.addAll(movieManager.getAllMovies());
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

    public void playMovie(Movie watchMovie) throws IOException
    {
        movieManager.playMovie(watchMovie);
    }
 public List<String> getAllMoviesByName() throws DalException {
        return movieManager.getAllMoviesByName();
}
 
 public ObservableList<Movie> unwMovieList() {
        for (int i = 0; i < allMovies.size(); i++) {
            LocalDateTime dateMinusTwoYears = LocalDateTime.now().minusYears(2);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate lastViewDate = LocalDate.parse(allMovies.get(i).getLastview().toString(), formatter);
            LocalDateTime localLastViewDate = LocalDateTime.of(lastViewDate, LocalDateTime.now().toLocalTime());
            boolean afterTwoYears = localLastViewDate.isBefore(dateMinusTwoYears);
            if(afterTwoYears == true) 
                unwMovieList.add(allMovies.get(i));
            System.out.println(unwMovieList);
        }
        return unwMovieList;
       
    }
 
}
