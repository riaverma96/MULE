/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;
import javafx.scene.control.Button;
/**
 *
 * @author Junko
 */
public class MapTile {
    private static Button[][] bArray = new Button[5][9];
    private static String[][] cArray = new String[5][9];
    
    /**
     *
     */
    public MapTile() {
    }
    
    /**
     *
     * @param x
     * @param y
     * @param b
     * @param s
     */
    public static void recordBuy(int x, int y, Button b, String s){
        bArray[x][y] = b;
        cArray[x][y] = s;
    }
    
    /**
     *
     * @return
     */
    public static Button[][] getButtonArray(){
      return bArray;
    }
    
    /**
     *
     * @return
     */
    public static String[][] getColorArray(){
      return cArray;
    }
    
    
    
}
