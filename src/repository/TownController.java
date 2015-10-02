/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
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
    
    double timerScore;
    double[] roundBonusIndex = {50, 50, 50, 100, 100, 100, 100, 150, 150, 
            150, 150, 200}; 
    int round = 1;
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
               double timePassed = 50 - timer.elapsedTime();
               if (timePassed >= 37.0) {
                   timerScore = 200;
               } else if (timePassed >= 25) {
                   timerScore = 150; 
               } else if (timePassed >= 12) {
                   timerScore = 100; 
               } else {
                   timerScore = 50; 
               }
           } else if (id.equals("MULE")) {
               buyMULE();
           }
        break; 
       }
       System.out.println("turn is over!");
       //default round = 1
       double roundBonus = roundBonusIndex[round - 1];
       double moneyWon = roundBonus * timerScore;
       
       if(moneyWon > 250 ) {
           moneyWon = 250;
       }
       System.out.println("Money Won is = " + moneyWon);
       Player1.addMoney(moneyWon);
    }
    
    public void buyMULE() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What type of MULE? Choose either Energy, Food or"
                + " Ore?");
        String choice = scanner.next();
        String type = "";
        
        if (choice.equals("Energy") || choice.equals("energy")) {
            type = "energy";
        } else if (choice.equals("Food") || choice.equals("food")) {
            type = "food";
        } else if (choice.equals("Ore") || choice.equals("ore")) {
            type = "ore";
        } else {
            System.out.println("Invalid type");
        }
        
        checkMoney();        
        
    }
    
    public void checkMoney() {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
