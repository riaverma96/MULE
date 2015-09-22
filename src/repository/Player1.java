package repository;

/**
 *
 * @author dbrbbff7814
 */
public class Player1 {
    
    private String name;
    private String race;
    private String color;
    private int money;
    
    public Player1() {
        name = "aha";
        money = 1000;
    }
    public void buyLand() {
        money -= 300;
    }
    
    public boolean DaddyHasMoney() {
        return money >= 300;
    }
}
