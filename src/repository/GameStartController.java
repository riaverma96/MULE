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
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author dbrbbff7814
 */
public class GameStartController implements Initializable {
    
    Button button00;
    Button button01;
    Button button02;
    Button button03;
    Button button04;
    Button button05;
    Button button06;
    Button button07;
    Button button08;
    Button button10;
    Button button11;
    Button button12;
    Button button13;
    Button button14;
    Button button15;
    Button button16;
    Button button17;
    Button button18;
    Button button20;
    Button button21;
    Button button22;
    Button button23;
    Button button25;
    Button button26;
    Button button27;
    Button button28;
    Button button30;
    Button button31;
    Button button32;
    Button button33;
    Button button34;
    Button button35;
    Button button36;
    Button button37;
    Button button38;
    Button button40;
    Button button41;
    Button button42;
    Button button43;
    Button button44;
    Button button45;
    Button button46;
    Button button47;
    Button button48;
    Button town;
    private int counter = 1;
    private int playerTurn = 0;
    private boolean[][] clicked = new boolean[5][9]; 
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        String id = b.getId();
        String xlocation = id.substring(6,7);
        String ylocation = id.substring(7);
        int x = Integer.valueOf(xlocation);
        int y = Integer.valueOf(ylocation);
        
       if (clicked[x][y] == false) {
           if (playerTurn < 8) {
             if (counter == 1) {
                b.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
            } else if (counter == 2) {
                b.setStyle("-fx-background-color: Green; -fx-text-fill: white;");
            } else if (counter == 3) {
                b.setStyle("-fx-background-color: Orange; -fx-text-fill: white;");
            } else if (counter == 4) {
                b.setStyle("-fx-background-color: Red; -fx-text-fill: white;");
            }
         counter++;
         
         if (counter >= 5) {
             counter = 1;
         }
        }
        playerTurn++;
        clicked[x][y] = true;
       }
        
        
              
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
