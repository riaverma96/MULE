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
    // [0] = food; [1] = energy; [3] = ore
    private int[] cost_of_mules = {125, 150, 175};
    
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
       if (timer.elapsedTime() >= 50.0) {
            System.out.println("turn is over!");
       }
       //default round = 1
       if (id.equals("pub")) {
        double roundBonus = roundBonusIndex[round - 1];
        double moneyWon = roundBonus * timerScore;

        if(moneyWon > 250 ) {
            moneyWon = 250;
        }
        System.out.println("Money Won is = " + moneyWon);
        Player1.addMoney(moneyWon);
       }
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
        System.out.println("Welcome to the store!");
        System.out.println("Do you want to buy or sell?");
        String buy_or_sell = scanner.next();
        if (buy_or_sell.equals("Buy") || buy_or_sell.equals("buy")) {
            System.out.println("Do you want to buy a mule or resources?");
            String buy_type = scanner.next();
            if (buy_type.equals("mule") || buy_type.equals("Mule")
                    || buy_type.equals("MULE")) {
                // checks if MULE is in-stock
                if (store_resources[4] < 1) {
                    System.out.println("Sorry! There are no more MULES "
                            + "in the store");
                    System.out.println("If you would like to buy resources "
                            + "instead click '1' otherwise to end your turn "
                            + "click '2'");
                    String no_mules = scanner.next();
                    if (no_mules.equals("1")) {
                        buy_resources();
                    } else {
                        return;
                    }
                } else {
                    buy_mule();
                }
                
            } else if (buy_type.equals("resources") 
                    || buy_type.equals("Resources")) {
                buy_resources();
            }
        } else if (buy_or_sell.equals("Sell") || buy_or_sell.equals("sell")) {
            sell();
        }
    }

    private void buy_mule() {
        // pre-req: there is at least 1 mule in the store
        Scanner scanner = new Scanner(System.in);
        System.out.println("Buying a MULE 101:");
        System.out.println(" -----------------------------");
        System.out.println("|    TYPE        |   COST     |");
        System.out.println("|----------------|------------|");
        System.out.println("|    Food        |   125      |");
        System.out.println("|    Energy      |   150      |");
        System.out.println("|    Ore         |   175      |");
        System.out.println(" -----------------------------");
        System.out.println("There are " + store_resources[4] 
                + " MULES available to buy.");
        
        // tells Player how much money he has
        if (Player1.myTurn()) {
            System.out.println("You have " + Player1.getMoney() + " money.");
        } else if (Player2.myTurn()) {
            System.out.println("You have " + Player2.getMoney() + " money.");
        } else if (Player3.myTurn()) {
            System.out.println("You have " + Player3.getMoney() + " money.");
        } else if (Player4.myTurn()) {
            System.out.println("You have " + Player4.getMoney() + " money.");
        }
        
        // MULE type selection
        System.out.println("What type of MULE would you like to buy?"
                + " Choose either a Food, Energy, Ore or Crystite MULE?");
        String choice = scanner.next();
        
        // final purchase price
        int cost = 100;
        int index_of_type = -1;
        if (choice.equals("Food") || choice.equals("food")) {
            cost += 25;
            index_of_type = 0;
        } else if (choice.equals("Energy") || choice.equals("energy")) {
            cost += 50;
            index_of_type = 1;
        } else if (choice.equals("Ore") || choice.equals("ore")) {
            cost += 75;
            index_of_type = 2;
        } else {
            System.out.println("Invalid type");
            return;
        }

        // checks if player has enough money 
        int purchase_price = 0;
        if (Player1.myTurn()) {
            if (cost < Player1.getMoney()) {
                Player1.activate_mule(true);
                store_resources[4] -= 1;
                Player1.add_mule(1);
                purchase_price = cost_of_mules[index_of_type];
                Player1.addMoney(-(purchase_price));
                System.out.println("You have purchased a MULE!");
            }
        } else if (Player2.myTurn()) {
            if (cost < Player1.getMoney()) {
                Player2.activate_mule(true);
                store_resources[4] -= 1;
                Player2.add_mule(1);
                purchase_price = cost_of_mules[index_of_type];
                Player2.addMoney(-(purchase_price));
                System.out.println("You have purchased a MULE!");
            }
        } else if (Player3.myTurn()) {
            if (cost < Player3.getMoney()) {
                Player3.activate_mule(true);
                store_resources[4] -= 1;
                Player3.add_mule(1);
                purchase_price = cost_of_mules[index_of_type];
                Player3.addMoney(-(purchase_price));
                System.out.println("You have purchased a MULE!");
            }
        } else if (Player4.myTurn()) {
            if (cost < Player4.getMoney()) {
                Player4.activate_mule(true);
                store_resources[4] -= 1;
                Player4.add_mule(1);
                purchase_price = cost_of_mules[index_of_type];
                Player4.addMoney(-(purchase_price));
                System.out.println("You have purchased a MULE!");
            }
        } 
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 
    private void buy_resources() {
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
                    store_resources[3] -= quantity;
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
                    store_resources[0] -= quantity;
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
                    store_resources[2] -= quantity;
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
                    store_resources[1] -= quantity;
                } else {
                    if (store_resources[1] >= quantity) {
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
                    store_resources[3] -= quantity;
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
                    store_resources[0] -= quantity;
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
                    store_resources[2] -= quantity;
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
                    store_resources[1] -= quantity;
                } else {
                    if (store_resources[1] >= quantity) {
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
                    store_resources[3] -= quantity;
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
                    store_resources[0] -= quantity;
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
                    store_resources[2] -= quantity;
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
                    store_resources[1] -= quantity;
                } else {
                    if (store_resources[1] >= quantity) {
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
                    store_resources[3] -= quantity;
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
                    store_resources[0] -= quantity;
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
                    store_resources[2] -= quantity;
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
                    store_resources[1] -= quantity;
                } else {
                    if (store_resources[1] >= quantity) {
                        System.out.println("Sorry! "
                                + "Store doesn't have enough resources!");
                    }  
                    System.out.println("You don't have enough money!");
                }
            } 
        }    
    }
    
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
