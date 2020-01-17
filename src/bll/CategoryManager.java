/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import be.Category;
import dal.DalException;
import dal.ICategoryDao;
import dal.database.CategoryDBDAO;
import java.util.List;

/**
 *
 * @author Jacob, Christian, René & Charlie
 */
public class CategoryManager
{

    private ICategoryDao categoryDao;

    /**
     * Constructor for the CategoryManager.
     *
     * @throws Exception
     */
    public CategoryManager() throws Exception
    {
        categoryDao = (ICategoryDao) new CategoryDBDAO();
    }

    /**
     * Sends the list to Gui Layer
     *
     * @return
     * @throws DalException
     */
    public List<Category> getAllCategories() throws DalException
    {
        return categoryDao.getAllCategories();
    }

    /**
     * Sends the list to Gui Layer
     *
     * @return
     * @throws DalException
     */
    public List<Category> getAllCategoriesToChoicebox() throws DalException
    {
        return categoryDao.getAllCategoriesToChoicebox();
    }

    /**
     * Information of create Category
     *
     * @param category
     * @throws DalException
     */
    public void createCategory(Category category) throws DalException
    {
        categoryDao.createCategory(category);
    }

    /**
     * Information of edit Category
     *
     * @param category
     * @throws DalException
     */
    public void editCategory(Category category) throws DalException
    {
        categoryDao.editCategory(category);
    }

    /**
     * Information of delete Category
     *
     * @param selectedCategory
     * @throws DalException
     */
    public void deleteCategory(Category selectedCategory) throws DalException
    {
        categoryDao.deleteCategory(selectedCategory);
    }

}
