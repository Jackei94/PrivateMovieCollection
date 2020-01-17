/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Jacob, René, Christian & Charlie
 */
public class Movie
{

    public int id;
    public String name;
    public double rating;
    private String filelink;
    private LocalDate lastview;
    private double imdbRating;
    private LocalDate unwatched;

    public Movie()
    {
        unwatched = LocalDate.of(1990, Month.JANUARY, 01);
    }

    /**
     * Gets the Id of Movie
     *
     * @return id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Sets the Id of Movie
     *
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Gets the Name of Movie
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the Name of Movie
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the Rating of Movie
     *
     * @return rating
     */
    public double getRating()
    {
        return rating;
    }

    /**
     * Sets the Rating of Movie
     *
     * @param rating
     */
    public void setRating(double rating)
    {
        this.rating = rating;
    }

    /**
     * Gets the Link to the file of Movie
     *
     * @return filelink
     */
    public String getFilelink()
    {
        return filelink;
    }

    /**
     * Sets the Link to the file of Movie
     *
     * @param filelink
     */
    public void setFilelink(String filelink)
    {
        this.filelink = filelink;
    }

    /**
     * Gets the Lastview of Movie
     *
     * @return lastview
     */
    public LocalDate getLastview()
    {
        return lastview;
    }

    /**
     * Sets Lastview of Movie
     *
     * @param lastview
     */
    public void setLastview(LocalDate lastview)
    {
        this.lastview = lastview;
    }

    /**
     * Gets ImdbRationg of Movie
     *
     * @return imdbRating
     */
    public double getImdbRating()
    {
        return imdbRating;
    }

    /**
     * Sets imdbRating of Movie
     *
     * @param tmdbRating
     */
    public void setImdbRating(double tmdbRating)
    {
        this.imdbRating = tmdbRating;
    }

    /**
     * Checks when a Movie last was viewed
     *
     * @return
     */
    public int checkIfWatched()
    {
        if (lastview.compareTo(unwatched) == 0)
        {
            return 1;
        } else
        {
            return 0;
        }
    }
}
