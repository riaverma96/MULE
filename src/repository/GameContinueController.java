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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author dbrbbff7814
 */
public class GameContinueController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
     @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        Button b = (Button) event.getSource();
        b.setStyle("-fx-background-image: url('http://static.arttoday.com/thm/thm4/dg_animal1F/animals1/oanimals/oan017k.thm.jpg')");
    }
    
}
