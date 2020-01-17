/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import be.Category;
import bll.BLLException;
import dal.DalException;
import gui.model.CategoryModel;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jacob, Christian, René & Charlie
 */
public class CategoryViewController implements Initializable
{

    private CategoryModel categoryModel;

    @FXML
    private Label newOrEditCategory;
    @FXML
    private TextField categoryName;
    @FXML
    private Button categoryCancel;
    @FXML
    private Button categorySave;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            categoryModel = CategoryModel.getInstance();
        } catch (Exception ex)
        {
            Logger.getLogger(CategoryViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!categoryModel.getSelectedCategory().isEmpty())
        {
            // Sets the data in fields if a song is selected.
            categoryName.setText(categoryModel.getSelectedCategory().get(0).getName());
        }
        newOrEditCategory.textProperty().unbind();
        this.categoryModel = categoryModel;
        newOrEditCategory.textProperty().bind(categoryModel.newOrEditProperty());
    }

    /**
     * Closes the window.
     *
     * @param event
     */
    @FXML
    private void categoryCancelButton(ActionEvent event)
    {
        categoryModel.getSelectedCategory().clear();
        Stage stage = (Stage) categoryCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Saves the data.
     *
     * @param event
     * @throws DalException
     * @throws BLLException
     */
    @FXML
    private void categorySaveButton(ActionEvent event) throws DalException, BLLException
    {
        if (!categoryModel.getSelectedCategory().isEmpty())
        {
            // Edits. - Edit who the fuck is Edit!
            Category category = new Category();
            category.setName(categoryName.getText());
            category.setId(categoryModel.getSelectedCategory().get(0).getId());
            categoryModel.editCategory(category);
            categoryModel.getSelectedCategory().clear();
        } else
        {
            // New.
            Category category = new Category();
            category.setId(-1);
            category.setName(categoryName.getText());

            categoryModel.createCategory(category);
        }

        categoryModel.loadCategories();
        Stage stage = (Stage) categorySave.getScene().getWindow();
        stage.close();
    }

    /**
     * Sets the model.
     *
     * @param model
     */
    public void setModel(CategoryModel model)
    {
        this.categoryModel = model;
    }

}
