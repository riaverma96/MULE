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

    /**
     *
     */
    public static final String[] races = {"Humanoid" , "Mechtron", "Spheroid", "Flapper"}; 

    /**
     *
     */
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
   
    /**
     *
     * @return
     */
    public static boolean getYNNewMule() {
        return newMule;
    }
   
    /**
     *
     */
    public static void hasNewMule() {
        newMule = true;
    }
   
    /**
     *
     */
    public static void noNewMule() {
        newMule = false;
    }
   
    /**
     *
     * @param type
     */
    public static void getMule(String type) {
        nMule = new MULE(type, "Player 2");
    }
   
    /**
     *
     * @return
     */
    public static MULE placeMule() {
        return nMule;
    }
    
    /**
     *
     */
    public Player2() {
        name = "aha";
        money = 1000;
        food = 8;
        energy = 4;
    }
    
    /**
     *
     */
    public static void setInitialLandSelectionTrue() {
        initialLandSelection = true;
    }

    /**
     *
     * @return
     */
    public static boolean getInitialLandSelection() {
        return initialLandSelection;
    }
    
    /**
     *
     * @param b
     */
    public static void activate_mule(boolean b) {
        mule_sprite = b;
    }
    
    /**
     *
     * @return
     */
    public static boolean is_mule_active() {
        return mule_sprite;
    }

    /**
     *
     * @param a_crystite
     */
    public static void set_crystite(int a_crystite) {
        crystite = a_crystite;
    }

    /**
     *
     * @return
     */
    public static int get_crystite() {
        return crystite;
    }  
    
    //food

    /**
     *
     * @param a_food
     */
    public static void set_food(int a_food) {
        food = a_food;
    }

    /**
     *
     * @return
     */
    public static int get_food() {
        return food;
    } 
    
    //ore

    /**
     *
     * @param a_ore
     */
    public static void set_ore(int a_ore) {
        ore = a_ore;
    }

    /**
     *
     * @return
     */
    public static int get_ore() {
        return ore;
    } 
    
    //energy

    /**
     *
     * @param a_energy
     */
    public static void set_energy(int a_energy) {
        energy = a_energy;
    }

    /**
     *
     * @return
     */
    public static int get_energy() {
        return energy;
    } 
    
    //mule

    /**
     *
     * @param difference
     */
    public static void add_mule(int difference) {
        mule += difference;
    }
    
    /**
     *
     * @return
     */
    public static int get_mule() {
        return mule;
    }
    
    /**
     *
     * @return
     */
    public static boolean myTurn() {
        return myTurn;
    }
    
    /**
     *
     * @param turn
     */
    public static void setMyTurn(boolean turn) {
        myTurn = turn;
    }
    
    /**
     *
     */
    public void buyLand() {
        money -= 300;
        landNum++;
    }

    /**
     *
     * @return
     */
    public static int getScore() {
        return money + (landNum * 500);
        //Remember to add resources in later 
    }

    /**
     *
     * @param aName
     */
    public static void setName(String aName) {
        name = aName;
    }

    /**
     *
     * @param aColor
     */
    public static void setColor(String aColor) {
        color = aColor;
    }

    /**
     *
     * @param aRace
     */
    public static void setRace(String aRace) {
        race = aRace;
    }

    /**
     *
     * @return
     */
    public static String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public static String getColor() {
        return color;
    }

    /**
     *
     * @return
     */
    public static String getRace() {
        return race;
    }
    
    /**
     *
     * @return
     */
    public boolean DaddyHasMoney() {
        return money >= 300;
    }

    /**
     *
     * @return
     */
    public static int getMoney() {
        return money;
    }

    /**
     *
     * @param someMoney
     */
    public static void addMoney(double someMoney) {
        money += someMoney;
    }
}
