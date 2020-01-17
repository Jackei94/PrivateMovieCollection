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
 * @author Jacob, Christian, René & Charlie
 */
public class MovieModel
{

    private static MovieModel instance;
    private final ObservableList<Movie> allMovies;
    private final MovieManager movieManager;
    private final StringProperty newOrEdit;
    private final ObservableList<Movie> selectedMovie;
    private final ObservableList<Movie> unwMovieList;
    private final ObservableList<Movie> unwatchedMovies;
    DatePicker datePicker = new DatePicker(LocalDate.now());

    /**
     * Constructor for the MovieModel
     *
     * @throws Exception
     */
    public MovieModel() throws Exception
    {
        this.newOrEdit = new SimpleStringProperty();
        this.movieManager = new MovieManager();
        allMovies = FXCollections.observableArrayList();
        allMovies.addAll(movieManager.getAllMovies());
        selectedMovie = FXCollections.observableArrayList();
        unwMovieList = FXCollections.observableArrayList();
        unwatchedMovies = FXCollections.observableArrayList();

    }

    /**
     * Gets the instance of the model.
     *
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static MovieModel getInstance() throws IOException, Exception
    {
        if (instance == null)
        {
            instance = new MovieModel();
        }
        return instance;
    }

    /**
     * Returns all movies.
     *
     * @return
     */
    public ObservableList<Movie> getAllMovies()
    {
        return allMovies;
    }

    /**
     * Loads all movies.
     *
     * @throws DalException
     */
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

    /**
     * Uses the search field to update the movie view.
     *
     * @param movie
     * @param searchQuery
     * @return
     */
    public ArrayList<Movie> search(List<Movie> movie, String searchQuery)
    {
        return movieManager.search(movie, searchQuery);
    }

    /**
     * Creates the movie.
     *
     * @param movie
     * @throws DalException
     */
    public void createMovie(Movie movie) throws DalException
    {
        movieManager.createMovie(movie);
    }

    /**
     * Returns the selected movie.
     *
     * @return
     */
    public ObservableList<Movie> getSelectedMovie()
    {
        return selectedMovie;
    }

    /**
     * Edits the movie.
     *
     * @param movie
     * @throws DalException
     */
    public void editMovie(Movie movie) throws DalException
    {
        movieManager.editMovie(movie);
        allMovies.add(movie);
        allMovies.clear();
        allMovies.addAll(movieManager.getAllMovies());
    }

    /**
     * Adds the selected movie.
     *
     * @param movie
     */
    public void addSelectedMovie(Movie movie)
    {
        selectedMovie.add(movie);
    }

    /**
     * Deletes the selected movie.
     *
     * @param selectedMovie
     * @throws DalException
     */
    public void deleteMovie(Movie selectedMovie) throws DalException
    {
        movieManager.deleteMovie(selectedMovie);
        allMovies.remove(selectedMovie);
    }

    /**
     * Plays the movie.
     *
     * @param watchMovie
     * @throws IOException
     */
    public void playMovie(Movie watchMovie) throws IOException
    {
        movieManager.playMovie(watchMovie);
    }

    /**
     * Returns all movies by name.
     *
     * @return
     * @throws DalException
     */
    public List<String> getAllMoviesByName() throws DalException
    {
        return movieManager.getAllMoviesByName();
    }

    /**
     * Returns all unwatched movies.
     *
     * @return
     * @throws DalException
     */
    public ObservableList<Movie> getAllUnwatchedMovies() throws DalException
    {
        unwatchedMovies.clear();
        unwatchedMovies.addAll(movieManager.getAllUnwatchedMovies());
        return unwatchedMovies;
    }

    /**
     * Updates the movie lastview.
     *
     * @param movie
     * @throws DalException
     */
    public void playedMovie(Movie movie) throws DalException
    {
        movieManager.playedMovie(movie);
    }

    /**
     * Returns the "bad" movies.
     *
     * @return
     */
    public ObservableList<Movie> unwMovieList()
    {
        for (int i = 0; i < allMovies.size(); i++)
        {
            LocalDateTime dateMinusTwoYears = LocalDateTime.now().minusYears(2);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate lastViewDate = LocalDate.parse(allMovies.get(i).getLastview().toString(), formatter);
            LocalDateTime localLastViewDate = LocalDateTime.of(lastViewDate, LocalDateTime.now().toLocalTime());
            boolean afterTwoYears = localLastViewDate.isBefore(dateMinusTwoYears);

            if (afterTwoYears == true && allMovies.get(i).getRating() < 6)
            {
                if (afterTwoYears == true)
                {
                    unwMovieList.add(allMovies.get(i));
                }
            }
        }
        return unwMovieList;
    }
}
