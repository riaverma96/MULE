/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dbrbbff7814
 */
public class TownController implements Initializable {
    
    private double timerScore;
    private double[] roundBonusIndex = {50, 50, 50, 100, 100, 100, 100, 150, 
        150, 150, 150, 200}; 
    private int round = 1;
    // [0] = food; [1] = energy; [2] = ore; [3] = crystite; [4] = mule
    private int[] store_resources = {16, 16, 0, 0, 25};
    private int[] cost_of_resources = {30, 25, 50, 100, 100};
    
    
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
           } else if (id.equals("store")) {
               go_to_store();
                //buyMULE();
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
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleMapButton(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        String id = b.getId();
        Stage stage;
        if (id.equals("map")) {
            GameStartController.resetButtons();
            stage = (Stage) b.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass()
                    .getResource("GameStart.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);                
            stage.show();
            
        }
    }
    
    private void go_to_store() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to buy or sell?");
        String buy_or_sell = scanner.next();
        if (buy_or_sell.equals("Buy") || buy_or_sell.equals("buy")) {
            buy();
        } else if (buy_or_sell.equals("Sell") || buy_or_sell.equals("sell")) {
            sell();
        }
    }

    private void buyMULE() {
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
 
    private void buy() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which resource do you want to buy? Crystite, Food,"
                + "Ore or Energy?");
        String resource = scanner.next();
        
        // inform user how many resourcs the store has
        if (resource.equals("Crystite") || resource.equals("crystite")) {
            System.out.println("The store currently has " 
                + store_resources[3] + " crystite.");
        } else if (resource.equals("Food") || resource.equals("food")) {
            System.out.println("The store currently has " 
                + store_resources[0] + " food.");
        } else if (resource.equals("Ore") || resource.equals("ore")) {
            System.out.println("The store currently has " 
                + store_resources[2] + " ore.");
        } else if (resource.equals("Energy") || resource.equals("energy")) {
            System.out.println("The store currently has " 
                + store_resources[1] + " energy.");
        } else if (resource.equals("Mule") || resource.equals("mule")) {
            System.out.println("The store currently has " 
                + store_resources[4] + " energy.");
        }
        System.out.println("How much do you want to buy?");
        int quantity = scanner.nextInt();
        
        if (Player1.myTurn()) {    
            if (resource.equals("Crystite") || resource.equals("crystite")) {
                if (store_resources[3] >= quantity 
                        && (store_resources[3] * cost_of_resources[3]) 
                        < Player1.getMoney()) {
                    Player1.set_crystite(Player1.get_crystite() + quantity);
                    Player1.addMoney(-(store_resources[3] 
                            * cost_of_resources[3]));
                } else {
                    if (store_resources[3] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Food") || resource.equals("food")) {
                if (store_resources[0] >= quantity 
                        && (store_resources[0] * cost_of_resources[0]) 
                        < Player1.getMoney()) {
                    Player1.set_food(Player1.get_food() + quantity);
                    Player1.addMoney(-(store_resources[0] 
                            * cost_of_resources[0]));
                } else {
                    if (store_resources[0] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Ore") || resource.equals("ore")) {
                if (store_resources[2] >= quantity 
                        && (store_resources[2] * cost_of_resources[2]) 
                        < Player1.getMoney()) {
                    Player1.set_ore(Player1.get_ore() + quantity);
                    Player1.addMoney(-(store_resources[2] 
                            * cost_of_resources[2]));
                } else {
                    if (store_resources[2] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Energy") || resource.equals("energy")) {
                if (store_resources[1] >= quantity 
                        && (store_resources[1] * cost_of_resources[1]) 
                        < Player1.getMoney()) {
                    Player1.set_energy(Player1.get_energy() + quantity);
                    Player1.addMoney(-(store_resources[1] 
                            * cost_of_resources[1]));
                } else {
                    if (store_resources[1] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Mule") || resource.equals("mule") 
                    || resource.equals("MULE")) {
                if (store_resources[4] >= quantity 
                        && (store_resources[4] * cost_of_resources[4]) 
                        < Player1.getMoney()) {
                    Player1.add_mule(quantity);
                    Player1.addMoney(-(store_resources[4] 
                            * cost_of_resources[4]));
                } else {
                    if (store_resources[4] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            }
        } else if (Player2.myTurn()) {
            if (resource.equals("Crystite") || resource.equals("crystite")) {
                if (store_resources[3] >= quantity 
                        && (store_resources[3] * cost_of_resources[3]) 
                        < Player2.getMoney()) {
                    Player2.set_crystite(Player2.get_crystite() + quantity);
                    Player2.addMoney(-(store_resources[3] 
                            * cost_of_resources[3]));
                } else {
                    if (store_resources[3] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Food") || resource.equals("food")) {
                if (store_resources[0] >= quantity 
                        && (store_resources[0] * cost_of_resources[0]) 
                        < Player2.getMoney()) {
                    Player2.set_food(Player2.get_food() + quantity);
                    Player2.addMoney(-(store_resources[0] 
                            * cost_of_resources[0]));
                } else {
                    if (store_resources[0] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Ore") || resource.equals("ore")) {
                if (store_resources[2] >= quantity 
                        && (store_resources[2] * cost_of_resources[2]) 
                        < Player2.getMoney()) {
                    Player2.set_ore(Player2.get_ore() + quantity);
                    Player2.addMoney(-(store_resources[2] 
                            * cost_of_resources[2]));
                } else {
                    if (store_resources[2] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Energy") || resource.equals("energy")) {
                if (store_resources[1] >= quantity 
                        && (store_resources[1] * cost_of_resources[1]) 
                        < Player2.getMoney()) {
                    Player2.set_energy(Player2.get_energy() + quantity);
                    Player2.addMoney(-(store_resources[1] 
                            * cost_of_resources[1]));
                } else {
                    if (store_resources[1] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Mule") || resource.equals("mule") 
                    || resource.equals("MULE")) {
                if (store_resources[4] >= quantity 
                        && (store_resources[4] * cost_of_resources[4]) 
                        < Player2.getMoney()) {
                    Player2.add_mule(quantity);
                    Player2.addMoney(-(store_resources[4] 
                            * cost_of_resources[4]));
                } else {
                    if (store_resources[4] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            }
        } else if (Player3.myTurn()) {
            if (resource.equals("Crystite") || resource.equals("crystite")) {
                if (store_resources[3] >= quantity 
                        && (store_resources[3] * cost_of_resources[3]) 
                        < Player3.getMoney()) {
                    Player3.set_crystite(Player3.get_crystite() + quantity);
                    Player3.addMoney(-(store_resources[3] 
                            * cost_of_resources[3]));
                } else {
                    if (store_resources[3] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Food") || resource.equals("food")) {
                if (store_resources[0] >= quantity 
                        && (store_resources[0] * cost_of_resources[0]) 
                        < Player3.getMoney()) {
                    Player3.set_food(Player3.get_food() + quantity);
                    Player3.addMoney(-(store_resources[0] 
                            * cost_of_resources[0]));
                } else {
                    if (store_resources[0] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Ore") || resource.equals("ore")) {
                if (store_resources[2] >= quantity 
                        && (store_resources[2] * cost_of_resources[2]) 
                        < Player3.getMoney()) {
                    Player3.set_ore(Player3.get_ore() + quantity);
                    Player3.addMoney(-(store_resources[2] 
                            * cost_of_resources[2]));
                } else {
                    if (store_resources[2] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Energy") || resource.equals("energy")) {
                if (store_resources[1] >= quantity 
                        && (store_resources[1] * cost_of_resources[1]) 
                        < Player3.getMoney()) {
                    Player3.set_energy(Player3.get_energy() + quantity);
                    Player3.addMoney(-(store_resources[1] 
                            * cost_of_resources[1]));
                } else {
                    if (store_resources[1] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Mule") || resource.equals("mule") 
                    || resource.equals("MULE")) {
                if (store_resources[4] >= quantity 
                        && (store_resources[4] * cost_of_resources[4]) 
                        < Player3.getMoney()) {
                    Player3.add_mule(quantity);
                    Player3.addMoney(-(store_resources[4] 
                            * cost_of_resources[4]));
                } else {
                    if (store_resources[4] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            }
        } else if (Player4.myTurn()) {
            if (resource.equals("Crystite") || resource.equals("crystite")) {
                if (store_resources[3] >= quantity 
                        && (store_resources[3] * cost_of_resources[3]) 
                        < Player4.getMoney()) {
                    Player4.set_crystite(Player4.get_crystite() + quantity);
                    Player4.addMoney(-(store_resources[3] 
                            * cost_of_resources[3]));
                } else {
                    if (store_resources[3] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Food") || resource.equals("food")) {
                if (store_resources[0] >= quantity 
                        && (store_resources[0] * cost_of_resources[0]) 
                        < Player4.getMoney()) {
                    Player4.set_food(Player4.get_food() + quantity);
                    Player4.addMoney(-(store_resources[0] 
                            * cost_of_resources[0]));
                } else {
                    if (store_resources[0] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Ore") || resource.equals("ore")) {
                if (store_resources[2] >= quantity 
                        && (store_resources[2] * cost_of_resources[2]) 
                        < Player4.getMoney()) {
                    Player4.set_ore(Player4.get_ore() + quantity);
                    Player4.addMoney(-(store_resources[2] 
                            * cost_of_resources[2]));
                } else {
                    if (store_resources[2] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Energy") || resource.equals("energy")) {
                if (store_resources[1] >= quantity 
                        && (store_resources[1] * cost_of_resources[1]) 
                        < Player4.getMoney()) {
                    Player4.set_energy(Player4.get_energy() + quantity);
                    Player4.addMoney(-(store_resources[1] 
                            * cost_of_resources[1]));
                } else {
                    if (store_resources[1] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } else if (resource.equals("Mule") || resource.equals("mule") 
                    || resource.equals("MULE")) {
                if (store_resources[4] >= quantity 
                        && (store_resources[4] * cost_of_resources[4]) 
                        < Player4.getMoney()) {
                    Player4.add_mule(quantity);
                    Player4.addMoney(-(store_resources[4] 
                            * cost_of_resources[4]));
                } else {
                    if (store_resources[4] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            }
        }
        
        
        
            
        
    }
    
    // forgot to sell MULES <--fix
    private void sell() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which resource do you want to sell? Crystite, Food,"
                + "Ore or Energy?");
        String resource = scanner.next();
        
        // inform user how many resourcs he has
        if(Player1.myTurn()) {
            if (resource.equals("Crystite") || resource.equals("crystite")) {
                System.out.println("You currently have " + Player1.get_crystite() 
                        + " crystite.");
            } else if (resource.equals("Food") || resource.equals("food")) {
                System.out.println("You currently have " + Player1.get_food() 
                        + " food.");
            } else if (resource.equals("Ore") || resource.equals("ore")) {
                System.out.println("You currently have " + Player1.get_ore() 
                        + " ore.");
            } else if (resource.equals("Energy") || resource.equals("energy")) {
                System.out.println("You currently have " + Player1.get_energy() 
                        + " energy.");
            }

            System.out.println("How much do you want to sell?");
            int quantity = scanner.nextInt();
            if (resource.equals("Crystite") || resource.equals("crystite")) {
                if (Player1.get_crystite() >= quantity) {
                    Player1.set_crystite(Player1.get_crystite() - quantity);
                    Player1.addMoney(quantity * 100);
                } else {
                    System.out.println("You don't have enough resources");
                }
            } else if (resource.equals("Food") || resource.equals("food")) {
                if (Player1.get_food() >= quantity) {
                    Player1.set_food(Player1.get_food() - quantity);
                    Player1.addMoney(quantity * 30);
                } else {
                    System.out.println("You don't have enough resources");
                }
            } else if (resource.equals("Ore") || resource.equals("ore")) {
                if (Player1.get_ore() >= quantity) {
                    Player1.set_ore(Player1.get_ore() - quantity);
                    Player1.addMoney(quantity * 50);
                } else {
                    System.out.println("You don't have enough resources");
                }
            } else if (resource.equals("Energy") || resource.equals("energy")) {
                if (Player1.get_energy() >= quantity) {
                    Player1.set_energy(Player1.get_energy() - quantity);
                    Player1.addMoney(quantity * 25);
                } else {
                    System.out.println("You don't have enough resources");
                }
            }
        } else if (Player2.myTurn()) {
            if (resource.equals("Crystite") || resource.equals("crystite")) {
                System.out.println("You currently have " + Player2.get_crystite() 
                        + " crystite.");
            } else if (resource.equals("Food") || resource.equals("food")) {
                System.out.println("You currently have " + Player2.get_food() 
                        + " food.");
            } else if (resource.equals("Ore") || resource.equals("ore")) {
                System.out.println("You currently have " + Player2.get_ore() 
                        + " ore.");
            } else if (resource.equals("Energy") || resource.equals("energy")) {
                System.out.println("You currently have " + Player2.get_energy() 
                        + " energy.");
            }

            System.out.println("How much do you want to sell?");
            int quantity = scanner.nextInt();
            if (resource.equals("Crystite") || resource.equals("crystite")) {
                if (Player2.get_crystite() >= quantity) {
                    Player2.set_crystite(Player2.get_crystite() - quantity);
                    Player2.addMoney(quantity * 100);
                } else {
                    System.out.println("You don't have enough resources");
                }
            } else if (resource.equals("Food") || resource.equals("food")) {
                if (Player2.get_food() >= quantity) {
                    Player2.set_food(Player2.get_food() - quantity);
                    Player2.addMoney(quantity * 30);
                } else {
                    System.out.println("You don't have enough resources");
                }
            } else if (resource.equals("Ore") || resource.equals("ore")) {
                if (Player2.get_ore() >= quantity) {
                    Player2.set_ore(Player2.get_ore() - quantity);
                    Player2.addMoney(quantity * 50);
                } else {
                    System.out.println("You don't have enough resources");
                }
            } else if (resource.equals("Energy") || resource.equals("energy")) {
                if (Player2.get_energy() >= quantity) {
                    Player2.set_energy(Player2.get_energy() - quantity);
                    Player2.addMoney(quantity * 25);
                } else {
                    System.out.println("You don't have enough resources");
                }
            }
        } else if (Player3.myTurn()) {
            if (resource.equals("Crystite") || resource.equals("crystite")) {
                System.out.println("You currently have " + Player3.get_crystite() 
                        + " crystite.");
            } else if (resource.equals("Food") || resource.equals("food")) {
                System.out.println("You currently have " + Player3.get_food() 
                        + " food.");
            } else if (resource.equals("Ore") || resource.equals("ore")) {
                System.out.println("You currently have " + Player3.get_ore() 
                        + " ore.");
            } else if (resource.equals("Energy") || resource.equals("energy")) {
                System.out.println("You currently have " + Player3.get_energy() 
                        + " energy.");
            }

            System.out.println("How much do you want to sell?");
            int quantity = scanner.nextInt();
            if (resource.equals("Crystite") || resource.equals("crystite")) {
                if (Player3.get_crystite() >= quantity) {
                    Player3.set_crystite(Player3.get_crystite() - quantity);
                    Player3.addMoney(quantity * 100);
                } else {
                    System.out.println("You don't have enough resources");
                }
            } else if (resource.equals("Food") || resource.equals("food")) {
                if (Player3.get_food() >= quantity) {
                    Player3.set_food(Player3.get_food() - quantity);
                    Player3.addMoney(quantity * 30);
                } else {
                    System.out.println("You don't have enough resources");
                }
            } else if (resource.equals("Ore") || resource.equals("ore")) {
                if (Player3.get_ore() >= quantity) {
                    Player3.set_ore(Player3.get_ore() - quantity);
                    Player3.addMoney(quantity * 50);
                } else {
                    System.out.println("You don't have enough resources");
                }
            } else if (resource.equals("Energy") || resource.equals("energy")) {
                if (Player3.get_energy() >= quantity) {
                    Player3.set_energy(Player3.get_energy() - quantity);
                    Player3.addMoney(quantity * 25);
                } else {
                    System.out.println("You don't have enough resources");
                }
            }
        } else if (Player4.myTurn()) {
            if (resource.equals("Crystite") || resource.equals("crystite")) {
                System.out.println("You currently have " + Player4.get_crystite() 
                        + " crystite.");
            } else if (resource.equals("Food") || resource.equals("food")) {
                System.out.println("You currently have " + Player4.get_food() 
                        + " food.");
            } else if (resource.equals("Ore") || resource.equals("ore")) {
                System.out.println("You currently have " + Player4.get_ore() 
                        + " ore.");
            } else if (resource.equals("Energy") || resource.equals("energy")) {
                System.out.println("You currently have " + Player4.get_energy() 
                        + " energy.");
            }

            System.out.println("How much do you want to sell?");
            int quantity = scanner.nextInt();
            if (resource.equals("Crystite") || resource.equals("crystite")) {
                if (Player4.get_crystite() >= quantity) {
                    Player4.set_crystite(Player4.get_crystite() - quantity);
                    Player4.addMoney(quantity * 100);
                } else {
                    System.out.println("You don't have enough resources");
                }
            } else if (resource.equals("Food") || resource.equals("food")) {
                if (Player4.get_food() >= quantity) {
                    Player4.set_food(Player4.get_food() - quantity);
                    Player4.addMoney(quantity * 30);
                } else {
                    System.out.println("You don't have enough resources");
                }
            } else if (resource.equals("Ore") || resource.equals("ore")) {
                if (Player4.get_ore() >= quantity) {
                    Player4.set_ore(Player4.get_ore() - quantity);
                    Player4.addMoney(quantity * 50);
                } else {
                    System.out.println("You don't have enough resources");
                }
            } else if (resource.equals("Energy") || resource.equals("energy")) {
                if (Player4.get_energy() >= quantity) {
                    Player4.set_energy(Player4.get_energy() - quantity);
                    Player4.addMoney(quantity * 25);
                } else {
                    System.out.println("You don't have enough resources");
                }
            }
        }
    }
}
