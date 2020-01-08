/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.database;

import dal.database.DatabaseConnector;
import be.Movie;
import dal.DalException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dal.IMovieDao;
import java.time.LocalDate;

/**
 *
 * @author LeChampDK
 */
public class MovieDBDAO implements IMovieDao
{

    private DatabaseConnector dbCon;

    public MovieDBDAO() throws Exception
    {
        dbCon = new DatabaseConnector();
    }

    public List<Movie> getAllMovies() throws DalException
    {
        ArrayList<Movie> allMovies = new ArrayList<>();
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "SELECT * FROM Movie;";
            // Create statement.
            Statement statement = con.createStatement();
            // Attempts to execute the statement.
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

                allMovies.add(movie);
            }
            //Return
            return allMovies;

        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDBDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new DalException();
        }
    }

    public void createMovie(Movie movie) throws DalException
    {
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code
            String sql = "INSERT INTO Movie (name, rating, filelink, lastview) VALUES (?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Sets the Strings
            ps.setString(1, movie.getName());
            ps.setDouble(2, movie.getRating());
            ps.setString(3, movie.getFilelink());
            ps.setString(4, movie.getLastview().toString());

            // Attempts to update the database
            int affectedRows = ps.executeUpdate();
            if (affectedRows < 1)
            {
                throw new SQLException("Can't save movie");
            }

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
            {
                movie.setId(rs.getInt(1));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editMovie(Movie movie) throws DalException
    {
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "UPDATE Movie SET name=? rating=? filelink=? WHERE id=?;";
            // Prepared statement
            PreparedStatement ps = con.prepareStatement(sql);
            // Sets the strings.
            ps.setString(1, movie.getName());
            ps.setDouble(2, movie.getRating());
            ps.setString(3, movie.getFilelink());
            ps.setInt(4, movie.getId());
            // Attempts to execute SQL code.
            int affected = ps.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Can't edit movie");
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteMovie(Movie selectedMovie) throws DalException
    {
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "DELETE FROM Movie WHERE id=?;";
            // Prepared statement. 
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, selectedMovie.getId());
            // Attempts to execute the statement.
            ps.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}
