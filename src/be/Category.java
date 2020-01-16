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
public class Category
{

    private int id;
    private String name;

    /**
     * Gets the Id of Category
     *
     * @return id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Sets the Id of Category
     *
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Gets the Name of Category
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the Name of Category
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name + "";
    }

}
