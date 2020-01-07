/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import be.Movie;
import dal.DalException;
import dal.IMovieDao;
import dal.database.MovieDBDAO;
import java.util.List;

/**
 *
 * @author LeChampDK
 */
public class MovieManager
{

    private IMovieDao movieDao;

    public MovieManager() throws Exception
    {
        movieDao = (IMovieDao) new MovieDBDAO();
    }

    public List<Movie> getAllMovies() throws DalException
    {
        return movieDao.getAllMovies();
    }

//    public ArrayList<Movie> search(List<Movie> movie, String searchQuery)
//    {
//        ArrayList<Movie> result = new ArrayList<>();
//
//        for (Movie movies : movie)
//        {
//            String name = movies.getName().trim().toLowerCase();
//            double rating = movies.getRating();
//
//            if (name.contains(searchQuery.toLowerCase().trim())
//                    || rating.contains(searchQuery)
//                    && !result.contains(movie))
//            {
//                result.add(movies);
//            }
//        }
//        return result;
//    }
}
