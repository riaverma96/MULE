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
public class GamePlay {
    
    private String level;
    private String mapType;
    private Player[] thePlayers;
    
    public GamePlay(String aLevel, String aMap, Player[] p) {
       level = aLevel;
       mapType = aMap;
       thePlayers = p;
                
    }
    
}
