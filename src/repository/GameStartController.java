
package repository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    Button button24;
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

    private int counter = 1;
    private int playerTurn = 0;
    private boolean[][] clicked = new boolean[5][9]; 
    private boolean player1Pass = false;
    private boolean player2Pass = false;
    private boolean player3Pass = false;
    private boolean player4Pass = false;
    private Player1 player1 = new Player1();
    private Player2 player2 = new Player2();
    private Player3 player3 = new Player3();
    private Player4 player4 = new Player4();
    

    
    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        String id = b.getId();
        Stage stage;
        
        Parent root = FXMLLoader.load(getClass().getResource("GameStart.fxml"));
        if (id.equals("button24")) {  
            if (playerTurn >= 8 && player1Pass & player2Pass & player3Pass & player4Pass) {
                stage = (Stage) b.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("Town.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);                
                stage.show();
                
            } else {
                System.out.println("You cant enter town yet!!!");
            }
        
        } else {
            String xlocation = id.substring(6,7);
            String ylocation = id.substring(7);
            int x = Integer.valueOf(xlocation);
            int y = Integer.valueOf(ylocation);
        
            if (clicked[x][y] == false && playerTurn < 8) {
                
                if (counter == 1) {
                    System.out.println("Player 2 pick land");
                    String color = Player1.getColor();
                    b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                } else if (counter == 2) {
                     System.out.println("Player 3 pick land");
                    String color = Player2.getColor();
                    b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                } else if (counter == 3) {
                    System.out.println("Player 4 pick land");
                    String color = Player3.getColor();
                    b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                } else if (counter == 4) {
                    if (playerTurn < 7) {
                         System.out.println("Player 1 pick land");
                    }
                    String color = Player4.getColor();
                    b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                }
            counter++;

            if (counter >= 5) {
                counter = 1;
            }
            playerTurn++;
                clicked[x][y] = true;
                
            } else {
                if (counter == 1) {
                        System.out.println("Do you buy the land? 1 for yes, 2 for no");
                        Scanner scanner = new Scanner(System.in);
                        int choice = scanner.nextInt();
                        if (choice == 1) {
                            if (player1.DaddyHasMoney()) {
                                String color = Player1.getColor();
                                b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                                player1.buyLand();
                                clicked[x][y] = true;
                            } else {
                                System.out.println("You dont have money");
                                player1Pass = true;
                            }
                        } else {
                            System.out.println("You pass");
                            player1Pass = true;
                        }
             } else if (counter == 2) {
                    System.out.println("Do you buy the land? 1 for yes, 2 for no");
                        Scanner scanner = new Scanner(System.in);
                        int choice = scanner.nextInt();
                        if (choice == 1) {
                            if (player2.DaddyHasMoney()) {
                                String color = Player2.getColor();
                                b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                                player2.buyLand();
                                clicked[x][y] = true;
                            } else {
                                System.out.println("You dont have money");
                                player2Pass = true;
                            }
                        } else {
                            System.out.println("You pass");
                            player2Pass = true;
                        }
              } else if (counter == 3) {
                    System.out.println("Do you buy the land? 1 for yes, 2 for no");
                        Scanner scanner = new Scanner(System.in);
                        int choice = scanner.nextInt();
                        if (choice == 1) {
                            if (player3.DaddyHasMoney()) {
                                String color = Player3.getColor();
                                b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                                player3.buyLand();
                                clicked[x][y] = true;
                            } else {
                                System.out.println("You dont have money");
                                player3Pass = true;
                            }
                        } else {
                            System.out.println("You pass");
                            player3Pass = true;
                        }
            } else {
                  System.out.println("Do you buy the land? 1 for yes, 2 for n");
                        Scanner scanner = new Scanner(System.in);
                        int choice = scanner.nextInt();
                        if (choice == 1) {
                            if (player4.DaddyHasMoney()) {
                                String color = Player4.getColor();
                                b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                                player4.buyLand();
                            } else {
                                System.out.println("You dont have money");
                                player4Pass = true;
                            }
                        } else {
                            System.out.println("You pass");
                            player4Pass = true;
                    }
                }
            counter++;
            if (counter >= 5) {
                counter = 1;
            }
            playerTurn++;
        }
       }
    }
        
        
        
              
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}