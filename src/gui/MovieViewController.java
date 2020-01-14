/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import be.CatMovie;
import be.Category;
import be.Movie;
import bll.BLLException;
import dal.DalException;
import gui.model.CatMovieModel;
import gui.model.MovieModel;
import gui.model.CategoryModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Jacob
 */
public class MovieViewController implements Initializable
{

    private MovieModel movieModel;
    private CategoryModel categoryModel;
    private CatMovieModel catMovieModel;
    private int Category1;
    private int Category2;
    private int Category3;

    @FXML
    private TextField movieName;
    @FXML
    private TextField movieRating;
    @FXML
    private TextField movieFile;
    @FXML
    private Button movieCancel;
    @FXML
    private Button movieSave;
    @FXML
    private Label newOrEditMovie;
    @FXML
    private ChoiceBox<Category> movieCategoryOne;
    @FXML
    private ChoiceBox<Category> movieCategoryTwo;
    @FXML
    private ChoiceBox<Category> movieCategoryThree;
    @FXML
    private TextField movieYear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
//        Category1 = catMovieModel.getCatForMovies().get(0).getCategoryId();
//        Category2 = catMovieModel.getCatForMovies().get(1).getCategoryId();
//        Category3 = catMovieModel.getCatForMovies().get(2).getCategoryId();
                
        try
        {
            movieModel = MovieModel.getInstance();
            categoryModel = CategoryModel.getInstance();
            catMovieModel = CatMovieModel.getInstance();
        } catch (Exception ex)
        {
            Logger.getLogger(MovieViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!movieModel.getSelectedMovie().isEmpty())
        {
            // Sets the data in fields if a song is selected.
            movieName.setText(movieModel.getSelectedMovie().get(0).getName());
            movieRating.setText(Double.toString(movieModel.getSelectedMovie().get(0).getRating()));
//            movieCategoryOne.setValue(catMovieModel.getAllCatMovies().get(0).getCategoryId());
//            movieCategoryOne.setValue(movieModel.getSelectedMovie().get(0).get);
            movieFile.setText(movieModel.getSelectedMovie().get(0).getFilelink());
        }
        this.movieModel = movieModel;
        newOrEditMovie.textProperty().unbind();
        newOrEditMovie.textProperty().bind(movieModel.newOrEditProperty());

        movieCategoryOne.setItems(categoryModel.getAllCategoriesToChoicebox());
        movieCategoryTwo.setItems(categoryModel.getAllCategoriesToChoicebox());
        movieCategoryThree.setItems(categoryModel.getAllCategoriesToChoicebox());

        movieCategoryOne.setValue(categoryModel.getAllCategoriesToChoicebox().get(0));
        movieCategoryTwo.setValue(categoryModel.getAllCategoriesToChoicebox().get(0));
        movieCategoryThree.setValue(categoryModel.getAllCategoriesToChoicebox().get(0));
    }

    @FXML
    public void chooseFile(ActionEvent event) throws IOException
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("Movies"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("mp4 files", "*.mp4", "*.mpeg4"));
        Window stage = null;
        File file = fileChooser.showOpenDialog(stage);
        movieFile.setText("Movies/" + file.getName());
        movieName.setText(file.getName().replace(".mp4", ""));
    }

    @FXML
    private void movieCancelButton(ActionEvent event)
    {
        movieModel.getSelectedMovie().clear();
        Stage stage = (Stage) movieCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void movieSaveButton(ActionEvent event) throws DalException, BLLException, IOException
    {
        if (!movieModel.getSelectedMovie().isEmpty())
        {
            //Edits.
            Movie movie = new Movie();
            Category category = new Category();
            movie.setName(movieName.getText());
            movie.setRating(Double.parseDouble(movieRating.getText()));
            category.setName((String) movieCategoryOne.getItems().toString());
            movie.setFilelink(movieFile.getText().trim());
            movie.setId(movieModel.getSelectedMovie().get(0).getId());
            movieModel.editMovie(movie);
            movieModel.getSelectedMovie().clear();
        } else
        {
            // New movie.
            Movie movie = new Movie();
            Category category = new Category();
            CatMovie catMovie = new CatMovie();
            movie.setId(-1);
            movie.setName(movieName.getText());
            movie.setYear(Integer.parseInt(movieYear.getText()));
            movie.setRating(Double.parseDouble(movieRating.getText()));
            movie.setFilelink(movieFile.getText());
            movie.setTmdbRating(movieModel.getTmdbRating(movie));
            movieModel.createMovie(movie);

            catMovie.setMovieId(movie.getId());
            if (movieCategoryOne.getSelectionModel().getSelectedItem().getId() != 3)
            {
                catMovie.setCategoryId(movieCategoryOne.getSelectionModel().getSelectedItem().getId());
                catMovieModel.createCatMovies(catMovie);
            }
            if (movieCategoryTwo.getSelectionModel().getSelectedItem().getId() != 3)
            {
                catMovie.setCategoryId(movieCategoryTwo.getSelectionModel().getSelectedItem().getId());
                catMovieModel.createCatMovies(catMovie);
            }
            if (movieCategoryThree.getSelectionModel().getSelectedItem().getId() != 3)
            {
                catMovie.setCategoryId(movieCategoryThree.getSelectionModel().getSelectedItem().getId());
                catMovieModel.createCatMovies(catMovie);
            }
        }
        // Close the stage.
        movieModel.loadMovies();
        Stage stage = (Stage) movieSave.getScene().getWindow();
        stage.close();
    }

    public void setModel(MovieModel model)
    {
        this.movieModel = model;
    }

}
