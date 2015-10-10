
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

    private int counter = 1;
    private int playerTurn = 0;
    private boolean[][] clicked = new boolean[5][9]; 
    private boolean player1Pass;
    private boolean player2Pass;
    private boolean player3Pass;
    private boolean player4Pass;
    private boolean townEnter = false;
    private boolean initialBuy;
    private Player1 player1 = new Player1();
    private Player2 player2 = new Player2();
    private Player3 player3 = new Player3();
    private Player4 player4 = new Player4();
    private MULE[][] mules = new MULE[5][9];
    private static Button[][] button_array = new Button[5][9];
    private static String[][] color_array = new String[5][9];
    private Button b = new Button();
    private String id;
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (player1Pass && player2Pass && player3Pass && player4Pass){
            townEnter = true;
            Player1.setInitialLandSelectionTrue();
            Player2.setInitialLandSelectionTrue();
            Player3.setInitialLandSelectionTrue();
            Player4.setInitialLandSelectionTrue();
        }
        b = (Button) event.getSource();
        id = b.getId();
        Stage stage;
        boolean selectionDone = false;
        Parent root = FXMLLoader.load(getClass().getResource("GameStart.fxml"));
        if (id.equals("button24")) {  
            // player is trying to enter town
            if (townEnter) {
                // everyone passed for one round
                stage = (Stage) b.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("Town.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);                
                stage.show();
                selectionDone = true;
            } else {
                System.out.println("You cant enter town yet!!!");
            }
        } else if (!townEnter && !initialBuy) {
            buyInitialLand();
        }
    }
//       if (playerTurn > 13 || counter > 13) {
//           resetButtons();
//       }
    

    private void buyInitialLand() {
    String xlocation = id.substring(6,7);
            String ylocation = id.substring(7);
            int x = Integer.valueOf(xlocation);
            int y = Integer.valueOf(ylocation);
            button_array[x][y] = b;
            if (clicked[x][y] == false && playerTurn < 8) {        
                if (counter == 1) {
                    System.out.println("Player 2 pick land");
                    String color = Player1.getColor();
                    b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                    color_array[x][y] = color;
                    setPlayerTurn("player1");
                } else if (counter == 2) {
                    System.out.println("Player 3 pick land");
                    String color = Player2.getColor();
                    b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                    color_array[x][y] = color;
                    setPlayerTurn("player2");
                } else if (counter == 3) {
                    System.out.println("Player 4 pick land");
                    String color = Player3.getColor();
                    b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                    color_array[x][y] = color;
                    setPlayerTurn("player3");
                } else if (counter == 4) {
                    if (playerTurn < 7) {
                         System.out.println("Player 1 pick land");
                         setPlayerTurn("player4");
                    }
                    String color = Player4.getColor();
                    b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                    color_array[x][y] = color;
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
                                b.setStyle("-fx-background-color: " + color 
                                        + "; -fx-text-fill: white;");
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
    private void setPlayerTurn(String player) {
        if(player.equals("player1")) {
            Player1.setMyTurn(true);
            Player2.setMyTurn(false);
            Player3.setMyTurn(false);
            Player4.setMyTurn(false);
        } else if(player.equals("player2")) {
            Player1.setMyTurn(false);
            Player2.setMyTurn(true);
            Player3.setMyTurn(false);
            Player4.setMyTurn(false);
        } else if(player.equals("player3")) {
            Player1.setMyTurn(false);
            Player2.setMyTurn(false);
            Player3.setMyTurn(true);
            Player4.setMyTurn(false);
        } else if(player.equals("player4")) {
            Player1.setMyTurn(false);
            Player2.setMyTurn(false);
            Player3.setMyTurn(false);
            Player4.setMyTurn(true);
        }
    }
       
    public static void resetButtons() {
        for (int i = 0; i < 5; i++) {
           for (int j = 0; j < 9; j++) {
               if (color_array[i][j] != null) {
                   Button b = button_array[i][j];
                   String color = color_array[i][j];
                   b.setStyle("-fx-background-color: " + color + 
                           "; -fx-text-fill: white;");
               }
           } 
        }
        System.out.println("Color_array = " + color_array);
        System.out.println("Button_array = " + button_array);
    }
          
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player1Pass = Player1.getInitialLandSelection();
        player2Pass = Player2.getInitialLandSelection();
        player3Pass = Player3.getInitialLandSelection();
        player4Pass = Player4.getInitialLandSelection();

    }    
    
}