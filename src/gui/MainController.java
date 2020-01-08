package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import be.Movie;
import dal.DalException;
import gui.model.MovieModel;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Jacob
 */
public class MainController implements Initializable
{

    private MovieModel movieModel;
    private ObservableList<Movie> searchedMovies;

    private Label label;
    @FXML
    private ListView<Movie> categoryView;
    @FXML
    private Button categoryNewButton;
    @FXML
    private Button categoryEditButton;
    @FXML
    private Button categoryDeleteButton;
    @FXML
    private ListView<Movie> movieView;
    @FXML
    private Button movieEditButton;
    @FXML
    private Button movieDeleteButton;
    @FXML
    private Button moviePlayButton;
    @FXML
    private TextField searchField;
    @FXML
    private Button exitApp;
    @FXML
    private Button movieNew;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            movieModel = new MovieModel();
            movieView.setItems(movieModel.getAllMovies());
        } catch (Exception ex)
        {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MainController() throws Exception
    {
        this.searchedMovies = FXCollections.observableArrayList();
    }

    @FXML
    public void movieNewButton(ActionEvent event) throws IOException
    {
        this.movieModel = movieModel;
        movieModel.setNewOrEdit("New Movie");

        Parent loader = FXMLLoader.load(getClass().getResource("MovieView.fxml"));

        Scene scene = new Scene(loader);

        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void movieEditButton(ActionEvent event) throws IOException
    {
        this.movieModel = movieModel;
        movieModel.setNewOrEdit("Edit Movie");
        Movie movie = movieView.getSelectionModel().getSelectedItem();
        movieModel.addSelectedMovie(movie);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MovieView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        MovieViewController controller = fxmlLoader.getController();
        controller.setModel(movieModel);
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void exitApp(ActionEvent event) throws IOException
    {
        // Popup stage with confirmation on exit of app.
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Exit", ButtonType.YES, ButtonType.NO);
        deleteAlert.setContentText("Are you sure? ");
        // Close App.
        deleteAlert.showAndWait();
        if (deleteAlert.getResult() == ButtonType.YES)
        {
            // Get a handle to the stage
            Stage stage = (Stage) exitApp.getScene().getWindow();
            // Do what you have to do - Outrageous!
            stage.close();

        } else
        {
            // Close the popup stage.
            deleteAlert.close();
        }
    }

    @FXML
    private void searchMovie(KeyEvent event)
    {
        searchField.textProperty().addListener((observable, oldValue, newValue)
                ->
        {
            searchedMovies.setAll(movieModel.search(movieModel.getAllMovies(), newValue));
            movieView.setItems(searchedMovies);
        });
    }

}
