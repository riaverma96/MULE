/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

/**
 *
 * @author riaverma
 */
public class MULE {
    private String type;
    private String owner;
    
    public MULE(String type, String owner) {
        this.type = type;
        this.owner = owner;
    }
    
    String getType() {
        return type;
    }
    
    void setType(String aType) {
        type = aType;
    }
    
    String getOwner() {
        return owner;
    }
    
    void setOwner(String aOwner) {
        type = aOwner;
    }
}
