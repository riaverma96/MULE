/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

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
public class TownController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button b = (Button) event.getSource();
        String id = b.getId();

        Stopwatch timer = new Stopwatch();
    
       
       while (timer.elapsedTime() < 50.0) {
           if (id.equals("pub")) {
               double timePassed = timer.elapsedTime();
               if (timePassed >= 37.0) {
                   Player1.addMoney(250);
                   System.out.println("You won $250");
               } else if (timePassed >= 25) {
                   Player1.addMoney(200);
                   System.out.println("You won $200");
               } else if (timePassed >= 12) {
                   Player1.addMoney(150);
                   System.out.println("You won $150");
               } else {
                   Player1.addMoney(100);
                   System.out.println("You won $100");
               }
           }
        break; 
       }
       System.out.println("Time is up ");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
