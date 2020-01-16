/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

/**
 *
 * @author Jacob, René, Christian & Charlie
 */
public class CatMovie
{
    private int id;
    private int categoryId;
    private int movieId;

    /**
     * Gets the Id of CatMovie
     * @return id
     */
    public int getId()
    {
        return id;
    }
/**
 * Sets the Id of CatMovie
 * @param id 
 */
    public void setId(int id)
    {
        this.id = id;
    }
/**
 * Gets the Id of Category
 * @return categoryId
 */
    public int getCategoryId()
    {
        return categoryId;
    }
/**
 * Sets the Id of Category
 * @param categoryId 
 */
    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }
/**
 * Gets the Id of Movie
 * @return movieId
 */
    public int getMovieId()
    {
        return movieId;
    }
/**
 * Sets the Id of Movie
 * @param movieId 
 */
    public void setMovieId(int movieId)
    {
        this.movieId = movieId;
    }

}