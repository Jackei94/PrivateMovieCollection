/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.database;

import be.CatMovie;
import dal.DalException;
import dal.ICatMovieDao;
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
 * @author LeChampDK
 */
public class CatMovieDBDAO implements ICatMovieDao
{

    private DatabaseConnector dbCon;

    public CatMovieDBDAO() throws Exception
    {
        dbCon = new DatabaseConnector();
    }

    public List<CatMovie> getAllCatMovies() throws DalException
    {
        ArrayList<CatMovie> allCatMovies = new ArrayList<>();
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "SELECT * FROM CatMovie;";
            // Create statement.
            Statement statement = con.createStatement();
            // Attempts to execute the statement.
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {

                // Add all to a list
                CatMovie catMovie = new CatMovie();
                catMovie.setCategoryId(rs.getInt("categoryId"));
                catMovie.setMovieId(rs.getInt("movieId"));

                allCatMovies.add(catMovie);
            }
            //Return
            return allCatMovies;

        } catch (SQLException ex)
        {
            Logger.getLogger(CatMovieDBDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new DalException();
        }
    }

    public void createCatMovies(CatMovie catMovie) throws DalException
    {
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code
            String sql = "INSERT INTO CatMovie (categoryId, movieId) VALUES (?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Sets the Strings
            ps.setInt(1, catMovie.getCategoryId());
            ps.setInt(2, catMovie.getMovieId());

            // Attempts to update the database
            int affectedRows = ps.executeUpdate();
            if (affectedRows < 1)
            {
                throw new SQLException("Can't save category to movie");
            }

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
            {
                catMovie.setId(rs.getInt(1));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(CatMovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editCatMovies(CatMovie catMovie) throws DalException
    {
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "UPDATE CatMovie SET categoryId=? movieId=? WHERE id=?;";
            // Prepared statement
            PreparedStatement ps = con.prepareStatement(sql);
            // Sets the strings.
            ps.setInt(1, catMovie.getCategoryId());
            ps.setInt(2, catMovie.getMovieId());
            ps.setInt(3, catMovie.getId());

            // Attempts to execute SQL code.
            int affected = ps.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Can't edit category to movie");
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(CatMovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCatMovies(CatMovie selectedCatMovie) throws DalException
    {
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "DELETE FROM CatMovie WHERE id=?;";
            // Prepared statement. 
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, selectedCatMovie.getId());
            // Attempts to execute the statement.
            ps.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(CatMovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public List<CatMovie> getCatForMovies() throws DalException
    {
        ArrayList<CatMovie> allCatForMovies = new ArrayList<>();
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "SELECT * FROM CatMovie WHERE movieId=?;";
            // Create statement.
            Statement statement = con.createStatement();
            // Attempts to execute the statement.
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {

                // Add all to a list
                CatMovie catMovie = new CatMovie();
                catMovie.setCategoryId(rs.getInt("categoryId"));
                catMovie.setMovieId(rs.getInt("movieId"));

                allCatForMovies.add(catMovie);
            }
            //Return
            return allCatForMovies;

        } catch (SQLException ex)
        {
            Logger.getLogger(CatMovieDBDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new DalException();
        }
    }
}
