/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import be.Movie;
import java.sql.Connection;
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
public class MovieDBDAO
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
                movie.setRating(rs.getInt("rating"));
                movie.setFilelink(rs.getString("filelink"));
                movie.setLastview(rs.getInt("lastview"));

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

}
