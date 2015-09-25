/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.ImageIcon;

/**
 * FXML Controller class
 *
 * @author riaverma
 */
public class Person4Controller implements Initializable {
    
    @FXML
    private Button startGame;
    private Button bGreen = new Button();
    private Button bRed = new Button();
    private Button bBlue = new Button();
    private Button bOrange = new Button();
    private Button humanoid = new Button();
    private Button spheroid = new Button();
    private Button mechtron = new Button();
    private Button flapper = new Button();
    
    Image image;
   
    /**
     * Initializes the controller class.
     */
   @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        String id = b.getId();
        Stage stage;
        Parent root = FXMLLoader.load(getClass().getResource("Person4.fxml"));
        if(id.equals("flapper")) {
            flapper = b;
            flapper.setTextFill(Color.RED);
            humanoid.setTextFill(Color.BLACK);
            spheroid.setTextFill(Color.BLACK);
            mechtron.setTextFill(Color.BLACK);
        } else if(id.equals("humanoid")) {
            humanoid = b;
            flapper.setTextFill(Color.BLACK);
            humanoid.setTextFill(Color.RED);
            spheroid.setTextFill(Color.BLACK);
            mechtron.setTextFill(Color.BLACK);
        } else if(id.equals("spheroid")) {
            spheroid = b;
            flapper.setTextFill(Color.BLACK);
            humanoid.setTextFill(Color.BLACK);
            spheroid.setTextFill(Color.RED);
            mechtron.setTextFill(Color.BLACK);
        } else if(id.equals("mechtron")) {
            mechtron = b;
            flapper.setTextFill(Color.BLACK);
            humanoid.setTextFill(Color.BLACK);
            spheroid.setTextFill(Color.BLACK);
            mechtron.setTextFill(Color.RED);
        }
        
        if(id.equals("bRed")) {
            bRed = b;
            bRed.setTextFill(Color.RED);
            bBlue.setTextFill(Color.BLACK);
            bGreen.setTextFill(Color.BLACK);
            bOrange.setTextFill(Color.BLACK);
        } else if(id.equals("bBlue")) {
            bBlue = b;
            bRed.setTextFill(Color.BLACK);
            bBlue.setTextFill(Color.RED);
            bGreen.setTextFill(Color.BLACK);
            bOrange.setTextFill(Color.BLACK);
        } else if(id.equals("bGreen")) {
            bGreen = b;
            bRed.setTextFill(Color.BLACK);
            bBlue.setTextFill(Color.BLACK);
            bGreen.setTextFill(Color.RED);
            bOrange.setTextFill(Color.BLACK);
        } else if(id.equals("bOrange")) {
            bOrange = b;
            bRed.setTextFill(Color.BLACK);
            bBlue.setTextFill(Color.BLACK);
            bGreen.setTextFill(Color.BLACK);
            bOrange.setTextFill(Color.RED);
        }
        if(event.getSource() == startGame) {
            stage = (Stage) startGame.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("GameStart.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Player 1 pick land");
        
        }
     
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
