package repository;

import java.util.ArrayList;

/**
 *
 * @author dbrbbff7814
 */
public class Player2 {
    
    private static String name;
    private static String race;
    private static String color;
    private static int money;
    public static final String[] races = {"Humanoid" , "Mechtron", "Spheroid", "Flapper"}; 
    public static final String[] colors = {"Blue" , "Red", "Green", "Orange"};
    private static int landNum;
    private static boolean myTurn;
    
    public static boolean myTurn() {
        return myTurn;
    }
    
    public static void setMyTurn(boolean turn) {
        myTurn = turn;
    }
    
    public Player2() {
        name = "aha";
        money = 1000;
    }
    public void buyLand() {
        money -= 300;
        landNum++;
    }
    public static int getScore() {
        return money + (landNum * 500);
        //Remember to add resources in later 
    }
    public static void setName(String aName) {
        name = aName;
    }
    public static void setColor(String aColor) {
        color = aColor;
    }
     public static void setRace(String aRace) {
        race = aRace;
    }
    public static String getName() {
        return name;
    }
    public static String getColor() {
        return color;
    }
    public static String getRace() {
        return race;
    }
    
    public boolean DaddyHasMoney() {
        return money >= 300;
    }
    public static void addMoney(double someMoney) {
        money += someMoney;
    }
}
