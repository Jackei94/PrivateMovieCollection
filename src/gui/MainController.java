package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
    private ListView<?> movieView;
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
        // TODO
    }    
    
}
