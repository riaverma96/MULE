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
    private static Player1 player1 = new Player1();
    private static Player2 player2 = new Player2();
    private static Player3 player3 = new Player3();
    private static Player4 player4 = new Player4();
    private MULE[][] mules = new MULE[5][9];
    private static Button[][] button_array = new Button[5][9];
    private static String[][] color_array = new String[5][9];
    private Button b = new Button();
    private String id;
    Stage stage;
    public static String[][] mapTypeArray = new String[][]{
        {"P", "P", "M1", "P", "R", "P", "M3", "P", "P"},
        {"P", "M1", "P", "P", "R", "P", "P", "P", "M3"},
        {"M3", "P", "P", "P", "TOWN", "P", "P", "P", "M1"},
        {"P", "M2", "P", "P", "R", "P", "M2", "P", "P"},
        {"P", "P", "M2", "P", "R", "P", "P", "P", "M2"}};
    public static String[][] playerOwnedArray = new String[5][9];
    String xlocation;
    String ylocation;
    int x;
    int y;
    
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
        xlocation = id.substring(6,7);
        ylocation = id.substring(7);
        x = Integer.valueOf(xlocation);
        y = Integer.valueOf(ylocation);
        
        boolean selectionDone = false;
        Parent root = FXMLLoader.load(getClass().getResource("GameStart.fxml"));
        if (id.equals("button24")) {  
            // player is trying to enter town
            if (townEnter) {
                // everyone passed for one round
                Stage newstage = new Stage();
                root = FXMLLoader.load(getClass().getResource("Town.fxml"));
                Scene scene = new Scene(root);
                newstage.setScene(scene);                
                newstage.show();
                selectionDone = true;
            } else {
                System.out.println("You cant enter town yet!!!");
            }
        } else if (!townEnter && !initialBuy) {
            buyInitialLand();
        }
        
        //get MULE image to show up
        setMule();
    }
    
    private void setMule() {
        if (Player1.myTurn() && Player1.getYNNewMule()) {
            MULE m = Player1.placeMule();
            if (playerOwnedArray[x][y].equals("Player 1")) {
//                if (Player1.getColor().equals("Red")) {
//                    if (m.getType().equals("food")) {
                        button_array[x][y].setStyle("-fx-background-image: url('http://static.arttoday.com/thm/thm4/dg_animal1F/animals1/oanimals/oan017k.thm.jpg')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_RED.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_RED.png')");
//                    }
//                } else if (Player1.getColor().equals("Blue")) {
//                    if (m.getType().equals("food")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('FoodMULE_BLUE.png')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_BLUE.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_BLUE.png')");
//                    }
//                } else if (Player1.getColor().equals("Green")) {
//                    if (m.getType().equals("food")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('FoodMULE_GREEN.png')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_GREEN.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_GREEN.png')");
//                    }
//                } else if (Player1.getColor().equals("Orange")) {
//                    if (m.getType().equals("food")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('FoodMULE_ORANGE.png')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_ORANGE.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_ORANGE.png')");
//                    }
//                }
                mules[x][y] = Player1.placeMule();
                Player1.noNewMule();
            }
        } else if (Player2.myTurn() && Player2.getYNNewMule()) {
            MULE m = Player2.placeMule();
            if (playerOwnedArray[x][y].equals("Player 2")) {
//                if (Player2.getColor().equals("Red")) {
//                    if (m.getType().equals("food")) {
                        button_array[x][y].setStyle("-fx-background-image: url('http://static.arttoday.com/thm/thm4/dg_animal1F/animals1/oanimals/oan017k.thm.jpg')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_RED.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_RED.png')");
//                    }
//                } else if (Player2.getColor().equals("Blue")) {
//                    if (m.getType().equals("food")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('FoodMULE_BLUE.png')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_BLUE.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_BLUE.png')");
//                    }
//                } else if (Player2.getColor().equals("Green")) {
//                    if (m.getType().equals("food")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('FoodMULE_GREEN.png')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_GREEN.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_GREEN.png')");
//                    }
//                } else if (Player2.getColor().equals("Orange")) {
//                    if (m.getType().equals("food")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('FoodMULE_ORANGE.png')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_ORANGE.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_ORANGE.png')");
//                    }
//                }
                mules[x][y] = Player2.placeMule();
                Player2.noNewMule();
            }
        } else if (Player3.myTurn() && Player3.getYNNewMule()) {
            MULE m = Player3.placeMule();
            if (playerOwnedArray[x][y].equals("Player 3")) {
//                if (Player3.getColor().equals("Red")) {
//                    if (m.getType().equals("food")) {
                        button_array[x][y].setStyle("-fx-background-image: url('http://static.arttoday.com/thm/thm4/dg_animal1F/animals1/oanimals/oan017k.thm.jpg')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_RED.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_RED.png')");
//                    }
//                } else if (Player3.getColor().equals("Blue")) {
//                    if (m.getType().equals("food")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('FoodMULE_BLUE.png')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_BLUE.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_BLUE.png')");
//                    }
//                } else if (Player3.getColor().equals("Green")) {
//                    if (m.getType().equals("food")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('FoodMULE_GREEN.png')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_GREEN.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_GREEN.png')");
//                    }
//                } else if (Player3.getColor().equals("Orange")) {
//                    if (m.getType().equals("food")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('FoodMULE_ORANGE.png')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_ORANGE.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_ORANGE.png')");
//                    }
//                }
                mules[x][y] = Player3.placeMule();
                Player3.noNewMule();
            }
        } else if (Player4.myTurn() && Player4.getYNNewMule()) {
            MULE m = Player4.placeMule();
            if (playerOwnedArray[x][y].equals("Player 4")) {
//                if (Player4.getColor().equals("Red")) {
//                    if (m.getType().equals("food")) {
                        button_array[x][y].setStyle("-fx-background-image: url('http://static.arttoday.com/thm/thm4/dg_animal1F/animals1/oanimals/oan017k.thm.jpg')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_RED.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_RED.png')");
//                    }
//                } else if (Player4.getColor().equals("Blue")) {
//                    if (m.getType().equals("food")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('FoodMULE_BLUE.png')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_BLUE.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_BLUE.png')");
//                    }
//                } else if (Player4.getColor().equals("Green")) {
//                    if (m.getType().equals("food")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('FoodMULE_GREEN.png')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_GREEN.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_GREEN.png')");
//                    }
//                } else if (Player4.getColor().equals("Orange")) {
//                    if (m.getType().equals("food")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('FoodMULE_ORANGE.png')");
//                    } else if (m.getType().equals("ore")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('OreMULE_ORANGE.png')");
//                    } else if (m.getType().equals("energy")) {
//                        button_array[x][y].setStyle("-fx-background-image: url('EnergyMULE_ORANGE.png')");
//                    }
//                }
                mules[x][y] = Player4.placeMule();
                Player4.noNewMule();
            }
        }
    }
    
    private void buyInitialLand() {
            if (clicked[x][y] == false && playerTurn < 8) {        
                if (counter == 1) {
                    System.out.println("Player 2 pick land");
                    String color = Player1.getColor();
                    b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                    button_array[x][y] = b;
                    color_array[x][y] = color;
                    setPlayerTurn("player1");
                    playerOwnedArray[x][y] = "Player 1";
                } else if (counter == 2) {
                    System.out.println("Player 3 pick land");
                    String color = Player2.getColor();
                    b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                    button_array[x][y] = b;
                    color_array[x][y] = color;
                    setPlayerTurn("player2");
                    playerOwnedArray[x][y] = "Player 2";
                } else if (counter == 3) {
                    System.out.println("Player 4 pick land");
                    String color = Player3.getColor();
                    b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                    button_array[x][y] = b;
                    color_array[x][y] = color;
                    setPlayerTurn("player3");
                    playerOwnedArray[x][y] = "Player 3";
                } else if (counter == 4) {
                    if (playerTurn < 7) {
                         System.out.println("Player 1 pick land");
                         setPlayerTurn("player4");
                    }
                    String color = Player4.getColor();
                    b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                    button_array[x][y] = b;
                    color_array[x][y] = color;
                    playerOwnedArray[x][y] = "Player 4";
                }
            counter++;

            if (counter >= 5) {
                counter = 1;
            }
            playerTurn++;
            clicked[x][y] = true;
                
            } else {
                if (counter == 1) {
                    setPlayerTurn("player1");
                    Stage newstage = (Stage) b.getScene().getWindow();
                    AlertBox box = new AlertBox(newstage, "Land Selection");
                    int choice = box.getAnswer(); 
                    if (choice == 1) {
                        if (player1.DaddyHasMoney()) {
                            String color = Player1.getColor();
                            b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                            player1.buyLand();
                            clicked[x][y] = true;
                            playerOwnedArray[x][y] = "Player 1";
                        } else {
                            System.out.println("You dont have money");
                            player1Pass = true;
                            }
                        } else {
                            System.out.println("You pass");
                            player1Pass = true;
                        }
             } else if (counter == 2) {
                        setPlayerTurn("player2");
                        Stage newstage = (Stage) b.getScene().getWindow();
                        AlertBox box = new AlertBox(newstage, "Land Selection");
                        int choice = box.getAnswer(); 
                        if (choice == 1) {
                            if (player2.DaddyHasMoney()) {
                                String color = Player2.getColor();
                                b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                                player2.buyLand();
                                clicked[x][y] = true;
                                playerOwnedArray[x][y] = "Player 2";
                            } else {
                                System.out.println("You dont have money");
                                player2Pass = true;
                            }
                        } else {
                            System.out.println("You pass");
                            player2Pass = true;
                        }
              } else if (counter == 3) {
                        setPlayerTurn("player3");
                        Stage newstage = (Stage) b.getScene().getWindow();
                        AlertBox box = new AlertBox(newstage, "Land Selection");
                        int choice = box.getAnswer(); 
                        if (choice == 1) {
                            if (player3.DaddyHasMoney()) {
                                String color = Player3.getColor();
                                b.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                                player3.buyLand();
                                clicked[x][y] = true;
                                playerOwnedArray[x][y] = "Player 3";
                            } else {
                                System.out.println("You dont have money");
                                player3Pass = true;
                            }
                        } else {
                            System.out.println("You pass");
                            player3Pass = true;
                        }
            } else {
                  setPlayerTurn("player4");
                  Stage newstage = (Stage) b.getScene().getWindow();
                        AlertBox box = new AlertBox(newstage, "Land Selection");
                        int choice = box.getAnswer(); 
                        if (choice == 1) {
                            if (player4.DaddyHasMoney()) {
                                String color = Player4.getColor();
                                b.setStyle("-fx-background-color: " + color 
                                        + "; -fx-text-fill: white;");
                                player4.buyLand();
                                playerOwnedArray[x][y] = "Player 4";
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
            //the following line added for demo purposes only
            setPlayerTurn("player1");
        }
    }
    public static void setPlayerTurn(String player) {
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
        button_array = MapTile.getButtonArray();
        color_array = MapTile.getColorArray();
    }
    
    public static Player1 getPlayer1() {
        return player1;
    }
    public static Player2 getPlayer2() {
        return player2;
    }
    public static Player3 getPlayer3() {
        return player3;
    }
    public static Player4 getPlayer4() {
        return player4;
    }
}
