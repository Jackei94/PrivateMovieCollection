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

    @FXML
    private ChoiceBox<?> newCategory;
    @FXML
    private TextField movieName;
    @FXML
    private TextField movieRating;
    @FXML
    private ChoiceBox<?> newCategoryTwo;
    @FXML
    private ChoiceBox<?> newCategoryThree;
    @FXML
    private TextField movieFile;
    @FXML
    private Button movieCancel;
    @FXML
    private Button movieSave;
    @FXML
    private Label newOrEditMovie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            movieModel = MovieModel.getInstance();
        } catch (Exception ex)
        {
            Logger.getLogger(MovieViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!movieModel.getSelectedMovie().isEmpty())
        {
            // Sets the data in fields if a song is selected.
            movieName.setText(movieModel.getSelectedMovie().get(0).getName());
//            newArtist.setText(songModel.getSelectedSong().get(0).getSongArtist());
//            newCategory.setValue(songModel.getSelectedSong().get(0).getCategory());
//            newFile.setText(songModel.getSelectedSong().get(0).getFilePath());
//            newTime.setText(Integer.toString(songModel.getSelectedSong().get(0).getTime()));
        }
        newOrEditMovie.textProperty().unbind();
        this.movieModel = movieModel;
        newOrEditMovie.textProperty().bind(movieModel.newOrEditProperty());
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
            category.setName((String) newCategory.getValue());
            movie.setFilelink(movieFile.getText().trim());
            movie.setId(movieModel.getSelectedMovie().get(0).getId());
            movieModel.editMovie(movie);
            movieModel.getSelectedMovie().clear();
        }
//         else
//        {
//            // New.
//            Movie movie = new Movie();
//            movie.setId(-1);
//            movie.setSongName(newTitle.getText());
//            movie.setSongArtist(newArtist.getText());
//            movie.setTime(Integer.parseInt(newTime.getText()));
//            movie.setCategory((String) newCategory.getValue());
//            movie.setFilePath(newFile.getText());
//
//            movieModel.createSongs(movie);
//        }
//        // Close the stage.
//        movieModel.loadSongs();
//        Stage stage = (Stage) newSave.getScene().getWindow();
//        stage.close();
    }

    public void setModel(MovieModel model)
    {
        this.movieModel = model;
    }

}
