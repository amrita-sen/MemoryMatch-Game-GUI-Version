/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryMatchGameGUI;

/**
 * This Player class represents a Player object, representing a user who plays the Memory Match game, and maintains 3 private variables, a username of 
 * type String, an instance of the Score object representing this player's individual score, and a boolean to track if the player is a new player.
 * It also maintains a default class constructor, getter and setter methods for the userName and the isNewPlayer variables and a getter method for the 
 * individualScore variable. 
 * 
 * @author AmritaSen 18048443
 */
public class Player 
{
    private String username;            // Private String variable to store the user's username.
    private Score individualScore;      // Private instance of the Score object representing this player's individual score.
    private boolean isNewPlayer;        // Private boolean variable to indicate if the player is a new player.

    /**
     *  This is a default class constructor that initialises the variables with default values.
     */
    public Player()
    {
        this.username = ""; 
        this.individualScore = new Score();
        this.isNewPlayer = false;
    }
    
    /**
     * A second class constructor that takes in a String object representing the username of player and  
     * assigns it to this.username variable of this class. It also calls the default constructor that 
     * initiaiises the  individualScore and isNewPlayer variables.
     * 
     * @param username, type String
     */
    public Player(String username)
    {
        this();
        this.username = username; 
    }
 
    /**
     * This public getter method returns the username of type String. It does not take in any parameters.
     * 
     * @return this.username, of type String.
     */
    public String getUsername()
    {
        return this.username;
    }
    
    /**
     * This public setter method, takes in a String value representing a player's username and assigns
     * it to the this.username variable of this class. It does not return any value.
     * 
     * @param username, of type String. 
     */
    public void setUsername(String username)
    {
        this.username = username;
    }   
    
    /**
     * This public getter method returns true if this.isNewPlayer is set to true, else it will return false.
     * It does not take in any parameters.
     * 
     * @return this.isNewPlayer, of type boolean
     */
    public boolean getIsNewPlayer()
    {
        return this.isNewPlayer;
    }
    
    /**
     * This public setter method, takes in a boolean value indicating whether a  player is a new player or not and
     * assigns it to the this.isNewPlayer variable of this class. It does not return any value.
     * 
     * @param isNewPlayer, of type boolean. 
     */
    public void setIsNewPlayer(boolean isNewPlayer)
    {
        this.isNewPlayer = isNewPlayer;
    }    
        
    /**
     * This public getter method returns an instance of the Score object, representing this player's individual score.
     * It does not take in any parameters.
     * 
     * @return this.individualScore, an instance of the Score object.
     */
    public Score getIndividualScore()
    {
        return this.individualScore;
    }   
}