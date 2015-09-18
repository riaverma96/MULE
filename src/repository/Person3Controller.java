/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

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

/**
 * FXML Controller class
 *
 * @author riaverma
 */
public class Person3Controller implements Initializable {
    
    @FXML
    private Button player3;
   
    /**
     * Initializes the controller class.
     */
   @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
         Button b = (Button) event.getSource();
        b.setTextFill(Color.RED);
        Stage stage;
        Parent root = FXMLLoader.load(getClass().getResource("Person3.fxml"));
        if(event.getSource() == player3) {
            stage = (Stage) player3.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Person4.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
        }
     
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
