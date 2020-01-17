/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.model;

import be.Category;
import bll.CategoryManager;
import dal.DalException;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jacob, Christian, René & Charlie
 */
public class CategoryModel
{

    private static CategoryModel instance;
    private final ObservableList<Category> allCategories;
    private final ObservableList<Category> allCategoriesToChoicebox;
    private final ObservableList<Category> selectedCategory;
    private final CategoryManager categoryManager;
    private final StringProperty newOrEdit;

    /**
     * Constructor for CategoryModel.
     *
     * @throws DalException
     * @throws Exception
     */
    public CategoryModel() throws DalException, Exception
    {
        this.newOrEdit = new SimpleStringProperty();
        this.categoryManager = new CategoryManager();
        allCategories = FXCollections.observableArrayList();
        allCategories.addAll(categoryManager.getAllCategories());
        allCategoriesToChoicebox = FXCollections.observableArrayList();
        allCategoriesToChoicebox.addAll(categoryManager.getAllCategoriesToChoicebox());
        selectedCategory = FXCollections.observableArrayList();
    }

    /**
     * Gets the instance of the model.
     *
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static CategoryModel getInstance() throws IOException, Exception
    {
        if (instance == null)
        {
            instance = new CategoryModel();
        }
        return instance;
    }

    /**
     * Loads all the categories.
     *
     * @throws DalException
     */
    public void loadCategories() throws DalException
    {
        allCategories.clear();
        allCategories.addAll(categoryManager.getAllCategories());
    }

    /**
     * Returns all the categories.
     *
     * @return
     */
    public ObservableList<Category> getAllCategories()
    {
        return allCategories;
    }

    /**
     * Returns all the categories to choicebox.
     *
     * @return
     */
    public ObservableList<Category> getAllCategoriesToChoicebox()
    {
        return allCategoriesToChoicebox;
    }

    /**
     * Creates the category.
     *
     * @param category
     * @throws DalException
     */
    public void createCategory(Category category) throws DalException
    {
        categoryManager.createCategory(category);
    }

    /**
     * Edits the category.
     *
     * @param category
     * @throws DalException
     */
    public void editCategory(Category category) throws DalException
    {
        categoryManager.editCategory(category);
        allCategories.add(category);
        allCategories.clear();
        allCategories.addAll(categoryManager.getAllCategories());

    }

    /**
     * Deletes the category.
     *
     * @param selectedCategory
     * @throws DalException
     */
    public void deleteCategory(Category selectedCategory) throws DalException
    {
        categoryManager.deleteCategory(selectedCategory);
        allCategories.remove(selectedCategory);
    }

    /**
     * Returns the selected category.
     *
     * @return
     */
    public ObservableList<Category> getSelectedCategory()
    {
        return selectedCategory;
    }

    /**
     * Adds the selected category.
     *
     * @param category
     */
    public void addSelectedCategory(Category category)
    {
        selectedCategory.add(category);
    }

    /**
     * Returns the newOrEditProperty.
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

}
