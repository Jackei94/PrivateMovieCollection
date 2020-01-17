/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

/**
 *
 * @author Jacob, Christian, René & Charlie
 */
public class DalException extends Exception
{
    /**
     * Catch the Exceptions
     * @param msg 
     */
    public DalException (String msg)
    {
    super( msg); 
    }
}
