package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import be.Movie;
import dal.DalException;
import gui.model.MovieModel;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author Jacob
 */
public class MainController implements Initializable
{

    private MovieModel movieModel;

    private Label label;
    @FXML
    private ListView<?> categoryView;
    @FXML
    private Button categoryNewButton;
    @FXML
    private Button categoryEditButton;
    @FXML
    private Button categoryDeleteButton;
    @FXML
    private ListView<Movie> movieView;
    @FXML
    private Button movieNewButton;
    @FXML
    private Button movieEditButton;
    @FXML
    private Button movieDeleteButton;
    @FXML
    private Button moviePlayButton;
    @FXML
    private TextField searchField;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
           // movieModel.getInstance();
        } catch (Exception ex)
        {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        movieView.setItems(movieModel.getAllMovies());

//        try
//        {
//            // Loads all songs
//            movieModel.loadMovies();
//        } catch (DalException ex)
//        {
//            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
