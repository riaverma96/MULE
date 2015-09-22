/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

/**
 *
 * @author dbrbbff7814
 */
public class Player2 {
    
    private String name;
    private String race;
    private String color;
    private int money;
    
    public Player2() {
        money = 1000;
    }
    public void buyLand() {
        money -= 300;
    }
    
    public boolean DaddyHasMoney() {
        return money >= 300;
    }
}
