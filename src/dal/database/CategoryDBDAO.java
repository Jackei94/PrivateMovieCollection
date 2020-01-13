/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.database;

import be.Category;
import be.Movie;
import dal.DalException;
import dal.ICategoryDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jacob
 */
public class CategoryDBDAO implements ICategoryDao
{

    private DatabaseConnector dbCon;

    public CategoryDBDAO() throws Exception
    {
        dbCon = new DatabaseConnector();
    }

    public List<Category> getAllCategories() throws DalException
    {
        ArrayList<Category> allCategories = new ArrayList<>();
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "SELECT * FROM Category EXCEPT SELECT '3', 'Select Category' FROM Category;";
            // Create statement.
            Statement statement = con.createStatement();
            // Attempts to execute the statement.
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {

                // Add all to a list
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));

                allCategories.add(category);
            }
            //Return
            return allCategories;

        } catch (SQLException ex)
        {
            Logger.getLogger(CategoryDBDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new DalException();
        }
    }
    
    public List<Category> getAllCategoriesToChoicebox() throws DalException
    {
        ArrayList<Category> allCategories = new ArrayList<>();
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "SELECT * FROM Category EXCEPT SELECT '1', 'All Movies' FROM Category EXCEPT SELECT '2', 'Unwatched' FROM Category;";
            // Create statement.
            Statement statement = con.createStatement();
            // Attempts to execute the statement.
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {

                // Add all to a list
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));

                allCategories.add(category);
            }
            //Return
            return allCategories;

        } catch (SQLException ex)
        {
            Logger.getLogger(CategoryDBDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new DalException();
        }
    }

    public void createCategory(Category category) throws DalException
    {
        // Attempts to connect to the database.
        try (Connection con = dbCon.getConnection())
        {
            // SQL code
            String sql = "INSERT INTO Category (name) VALUES (?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Sets the Strings
            ps.setString(1, category.getName());

            // Attempts to update the database
            int affectedRows = ps.executeUpdate();
            if (affectedRows < 1)
            {
                throw new SQLException("Can't save category");
            }

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
            {
                category.setId(rs.getInt(1));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editCategory(Category category) throws DalException
    {
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "UPDATE Category SET name=? WHERE id=?;";
            // Prepared statement
            PreparedStatement ps = con.prepareStatement(sql);
            // Sets the strings.
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            // Attempts to execute SQL code.
            int affected = ps.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Can't edit category");
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCategory(Category selectedCategory) throws DalException
    {
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "DELETE FROM Category WHERE id=?;";
            // Prepared statement. 
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, selectedCategory.getId());
            // Attempts to execute the statement.
            ps.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}
