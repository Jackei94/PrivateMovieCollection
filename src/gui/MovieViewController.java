/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import be.Category;
import be.Movie;
import dal.DalException;
import gui.model.MovieModel;
import gui.model.CategoryModel;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            movieModel = MovieModel.getInstance();
            categoryModel = CategoryModel.getInstance();
        } catch (Exception ex)
        {
            Logger.getLogger(MovieViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!movieModel.getSelectedMovie().isEmpty())
        {
            // Sets the data in fields if a song is selected.
            movieName.setText(movieModel.getSelectedMovie().get(0).getName());
            movieRating.setText(Double.toString(movieModel.getSelectedMovie().get(0).getRating()));
//            movieCategoryOne.setValue(movieModel.getSelectedMovie().get(0).get);
            movieFile.setText(movieModel.getSelectedMovie().get(0).getFilelink());
//            movie.setText(Integer.toString(songModel.getSelectedSong().get(0).getTime()));
        }
        this.movieModel = movieModel;
        newOrEditMovie.textProperty().unbind();
        newOrEditMovie.textProperty().bind(movieModel.newOrEditProperty());
        movieCategoryOne.setItems(categoryModel.getAllCategories());
        movieCategoryTwo.setItems(categoryModel.getAllCategories());
        movieCategoryThree.setItems(categoryModel.getAllCategories());
    }

    @FXML
    public void chooseFile(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        Window stage = null;
        File file = fileChooser.showOpenDialog(stage);
        movieFile.setText(file.getPath());
    }

    @FXML
    private void movieCancelButton(ActionEvent event)
    {
        movieModel.getSelectedMovie().clear();
        Stage stage = (Stage) movieCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void movieSaveButton(ActionEvent event) throws DalException
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
        }
         else
        {
            // New movie.
            Movie movie = new Movie();
            Category category = new Category();
            movie.setId(-1);
            movie.setName(movieName.getText());
            movie.setRating(Double.parseDouble(movieRating.getText()));
//            category.setName(.getText());
            movie.setFilelink(movieFile.getText());

            movieModel.createMovie(movie);
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
