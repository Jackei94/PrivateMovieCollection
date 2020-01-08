/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;


import java.util.Date;

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
    private Date lastview;

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

    public Date getLastview()
    {
        return lastview;
    }

    public void setLastview(Date lastview)
    {
        this.lastview = lastview;
    }


    
    @Override
    public String toString()
    {
        return rating + " - " + name;
    }

}
