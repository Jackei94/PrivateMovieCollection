/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

/**
 *
 * @author Jacob, Jonas Charlie & René
 */
public class BLLException extends Exception
{

    /**
     * Catches BLLExeptions and keeps the program running.
     *
     * @param message
     */
    public BLLException(String message)
    {
        super(message);
    }

}
