/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.database;

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
import java.time.Month;

/**
 *
 * @author Jacob, Christian, René & Charlie
 */
public class MovieDBDAO implements IMovieDao
{

    private final DatabaseConnector dbCon;
    private final LocalDate unwatched;

    public MovieDBDAO() throws Exception
    {
        dbCon = new DatabaseConnector();
        unwatched = LocalDate.of(1990, Month.JANUARY, 01);
    }

    public List<Movie> getAllMovies() throws DalException
    {
        ArrayList<Movie> allMovies = new ArrayList<>();
        // Attempts to connect to the database.
        try (Connection con = dbCon.getConnection())
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
                movie.setImdbRating(rs.getDouble("imdbrating"));

                allMovies.add(movie);
            }
            //Return
            return allMovies;

        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDBDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new DalException("Can´t make list");
        }
    }

    public void createMovie(Movie movie) throws DalException
    {
        // Attempts to connect to the database.
        try (Connection con = dbCon.getConnection())
        {
            // SQL code
            String sql = "INSERT INTO Movie (name, rating, filelink, lastview, imdbrating) VALUES (?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Sets the Strings
            ps.setString(1, movie.getName());
            ps.setDouble(2, movie.getRating());
            ps.setString(3, movie.getFilelink());
            ps.setString(4, unwatched.toString());
            ps.setDouble(5, movie.getImdbRating());

            // Attempts to update the database
            int affectedRows = ps.executeUpdate();
            if (affectedRows < 1)
            {
          
                throw new DalException("Can't save movie");
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
        try (Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "UPDATE Movie SET name=?, rating=?, filelink=?, imdbrating=? WHERE id=?;";
            // Prepared statement
            PreparedStatement ps = con.prepareStatement(sql);
            // Sets the strings.
            ps.setString(1, movie.getName());
            ps.setDouble(2, movie.getRating());
            ps.setString(3, movie.getFilelink());
            ps.setDouble(4, movie.getImdbRating());
            ps.setInt(5, movie.getId());
            
            // Attempts to execute SQL code.
            int affected = ps.executeUpdate();
            if (affected < 1)
            {
                throw new DalException("Can't edit movie");
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteMovie(Movie selectedMovie) throws DalException
    {
        // Attempts to connect to the database.
        try (Connection con = dbCon.getConnection())
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
        }
    }

    public List<String> getAllMoviesByName()
    {
        List<String> getAllMoviesByName = new ArrayList();
        // Attempts to connect to the database.
        try (Connection con = dbCon.getConnection())
        {
            // Prepared statement. 
            PreparedStatement pstmt
                    = con.prepareStatement("SELECT name FROM Movie");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                getAllMoviesByName.add(rs.getString("name"));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Return
        return getAllMoviesByName;
    }

    public List<Movie> getAllUnwatchedMovies() throws DalException
    {
        ArrayList<Movie> unwatchedMovies = new ArrayList<>();
        // Attempts to connect to the database.
        try (Connection con = dbCon.getConnection())
        {

            // SQL code. 
            String sql = "SELECT * FROM Movie WHERE lastview ='" + unwatched + "';";
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
                movie.setImdbRating(rs.getDouble("imdbrating"));

                unwatchedMovies.add(movie);
            }
            //Return
            return unwatchedMovies;

        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDBDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new DalException("Can´t create list");
        }
    }
    
    public void playedMovie(Movie movie) throws DalException
    {
        // Attempts to connect to the database.
        try (Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "UPDATE Movie SET lastview=? WHERE id=?;";
            // Prepared statement
            PreparedStatement ps = con.prepareStatement(sql);
            // Sets the strings.
            ps.setString(1, LocalDate.now().toString());
            ps.setInt(2, movie.getId());
            
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

}
