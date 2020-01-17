/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;



/**
 *
 * @author Jacob, Christian, René & Charlie
 */
public class AlertWindow
{
       //Show Error window and sets text
  public void displayAlert(AlertType type, String title, String message )
  {
      Alert alert = new Alert(type);
      alert.setTitle(title);
      alert.setContentText(message);
      alert.showAndWait();
      
  }
}
