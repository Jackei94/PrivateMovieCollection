/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.database;

import be.CatMovie;
import be.Category;
import be.Movie;
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
 * @author Jacob, Christian, René & Charlie
 */
public class CatMovieDBDAO implements ICatMovieDao
{

    private final DatabaseConnector dbCon;

    /**
     * Constructor for CatMovieDBDAO.
     *
     * @throws Exception
     */
    public CatMovieDBDAO() throws Exception
    {
        dbCon = new DatabaseConnector();
    }

    /**
     * Returns all CatMovies from the Database.
     *
     * @return
     * @throws DalException
     */
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
            throw new DalException("Can´t do that");
        }
    }

    /**
     * Creates CatMovies in the Database.
     *
     * @param catMovie
     * @throws DalException
     */
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

    /**
     * Edits the CatMovie in the Database.
     *
     * @param catMovie
     * @throws DalException
     */
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

    /**
     * Deletes the CatMovie in the database.
     *
     * @param selectedCatMovie
     * @throws DalException
     */
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
        }
    }

    /**
     * Returns all categories for a chosen movie.
     *
     * @param chosenMovie
     * @return
     * @throws DalException
     */
    public List<Category> getCatForMovies(Movie chosenMovie) throws DalException
    {
        ArrayList<Category> allCatForMovies = new ArrayList<>();
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            Integer idMov = chosenMovie.getId();
            // SQL code. 
            String sql = "SELECT * FROM Category INNER JOIN CatMovie ON Category.id = CatMovie.categoryId WHERE movieId='" + idMov + "';";
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

                allCatForMovies.add(category);
            }
            //Return
            return allCatForMovies;

        } catch (SQLException ex)
        {
            Logger.getLogger(CatMovieDBDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new DalException("Can´t do that");
        }
    }

    /**
     * Returns all movies on a chosen category.
     *
     * @param chosenCat
     * @return
     * @throws DalException
     */
    public List<Movie> getMoviesFromCats(Category chosenCat) throws DalException
    {

        ArrayList<Movie> categoryMovies = new ArrayList<>();
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            Integer idCat = chosenCat.getId();
            // SQL code. 
            String sql = "SELECT * FROM Movie INNER JOIN CatMovie ON Movie.id = CatMovie.movieId WHERE categoryId=" + idCat + ";";

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                // Add all to a list
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setName(rs.getString("name"));
                movie.setRating(rs.getDouble("rating"));
                movie.setFilelink(rs.getString("filelink"));
                movie.setLastview(rs.getDate("lastview").toLocalDate());
                movie.setImdbRating(rs.getDouble("imdbrating"));
                categoryMovies.add(movie);
            }
            //Return
            return categoryMovies;

        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDBDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new DalException("Nope can´t do");
        }

    }
}
