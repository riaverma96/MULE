package repository;

import java.util.ArrayList;

/**
 *
 * @author dbrbbff7814
 */
public class Player4 {
    
    private static String name;
    private static String race;
    private static String color;
    private static int money;
    public static final String[] races = {"Humanoid" , "Mechtron", "Spheroid", "Flapper"}; 
    public static final String[] colors = {"Blue" , "Red", "Green", "Orange"};
    private static int landNum;
    private static boolean myTurn;
    private static int crystite;
    private static int food;
    private static int ore;
    private static int energy;
    private static int mule;
    private static boolean mule_sprite;
    private static boolean initialLandSelection = false;
    private static MULE nMule;
    private static boolean newMule = false;
   
    public static boolean getYNNewMule() {
        return newMule;
    }
   
    public static void hasNewMule() {
        newMule = true;
    }
   
    public static void noNewMule() {
        newMule = false;
    }
   
    public static void getMule(String type) {
        nMule = new MULE(type, "Player 4");
    }
   
    public static MULE placeMule() {
        return nMule;
    }
    
    public static void setInitialLandSelectionTrue() {
        initialLandSelection = true;
    }
    public static boolean getInitialLandSelection() {
        return initialLandSelection;
    }
    
    public static void activate_mule(boolean b) {
        mule_sprite = b;
    }
    
    public static boolean is_mule_active() {
        return mule_sprite;
    }

    public static void set_crystite(int a_crystite) {
        crystite = a_crystite;
    }
    public static int get_crystite() {
        return crystite;
    }  
    
    //food
    public static void set_food(int a_food) {
        food = a_food;
    }
    public static int get_food() {
        return food;
    } 
    
    //ore
    public static void set_ore(int a_ore) {
        ore = a_ore;
    }
    public static int get_ore() {
        return ore;
    } 
    
    //energy
    public static void set_energy(int a_energy) {
        energy = a_energy;
    }
    public static int get_energy() {
        return energy;
    } 
    
    //mule
    public static void add_mule(int difference) {
        mule += difference;
    }
    
    public static int get_mule() {
        return mule;
    }
    
    public static boolean myTurn() {
        return myTurn;
    }
    
    public static void setMyTurn(boolean turn) {
        myTurn = turn;
    }
    
    public Player4() {
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
    public static int getMoney() {
        return money;
    }
    public static void addMoney(double someMoney) {
        money += someMoney;
    }
}
