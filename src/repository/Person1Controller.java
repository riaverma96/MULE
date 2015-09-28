/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author riaverma
 */
public class Person1Controller implements Initializable {
    
    @FXML
    private Button player1;
   
    /**
     * Initializes the controller class.
     */
   @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() instanceof TextField) {
            TextField text = (TextField) event.getSource();
            String name = text.getText();
            Player1.setName(name);
        }
        Button b = (Button) event.getSource();
        b.setTextFill(Color.RED);
        Stage stage;
        Parent root = FXMLLoader.load(getClass().getResource("Person1.fxml"));
        if(event.getSource() == player1) {
            stage = (Stage) player1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Person2.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
        }
        if(Arrays.asList(Player1.races).contains(b.getText())) {
            Player1.setRace(b.getText());
     
        }
        if(Arrays.asList(Player1.colors).contains(b.getText())){
             Player1.setColor(b.getText());
        }
    }
           
     
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
