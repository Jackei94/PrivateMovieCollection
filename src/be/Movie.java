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
 * @author tramm
 */
public class Movie
{

    public int id;
    public String name;
    public double rating;
    private String filelink;
    private LocalDate lastview;
    private double tmdbRating;
    private int year;
    private LocalDate unwatched;

    public Movie()
    {
        unwatched = LocalDate.of(1990, Month.JANUARY, 01);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getRating()
    {
        return rating;
    }

    public void setRating(double rating)
    {
        this.rating = rating;
    }

    public String getFilelink()
    {
        return filelink;
    }

    public void setFilelink(String filelink)
    {
        this.filelink = filelink;
    }

    public LocalDate getLastview()
    {
        return lastview;
    }

    public void setLastview(LocalDate lastview)
    {
        this.lastview = lastview;
    }
    
    public double getTmdbRating()
    {
        return tmdbRating;
    }

    public void setTmdbRating(double tmdbRating)
    {
        this.tmdbRating = tmdbRating;
    }
    
     public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

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

    @Override
    public String toString()
    {
        if(checkIfWatched() == 1)
        {
            return tmdbRating + " ☆ - " + name + " (" + rating + ")";
        }
        else 
        {
            return tmdbRating + " ★ - " + name + " (" + rating + ")";
        }
    }

}
