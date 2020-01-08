/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import be.Category;
import java.util.List;

/**
 *
 * @author Jacob
 */
public interface ICategoryDao
{

    List<Category> getAllCategories() throws DalException;

    void createCategory(Category category) throws DalException;

    void editCategory(Category category) throws DalException;

    void deleteCategory(Category selectedCategory) throws DalException;
}
