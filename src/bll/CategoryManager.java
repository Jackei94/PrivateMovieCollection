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
 * @author LeChampDK
 */
public class CategoryManager
{

    private ICategoryDao categoryDao;
    
    public CategoryManager() throws Exception
    {
        categoryDao = (ICategoryDao) new CategoryDBDAO();
    }

    public List<Category> getAllCategories() throws DalException
    {
        return categoryDao.getAllCategories();
    }
    
    public List<Category> getAllCategoriesToChoicebox() throws DalException
    {
        return categoryDao.getAllCategoriesToChoicebox();
    }
    
    public void createCategory(Category category) throws DalException
    {
        categoryDao.createCategory(category);
    }
    
    public void editCategory(Category category) throws DalException
    {
        categoryDao.editCategory(category);
    }
    
    public void deleteCategory(Category selectedCategory) throws DalException
    {
        categoryDao.deleteCategory(selectedCategory);
    }
    
}
