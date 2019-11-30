/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryMatchGameGUI;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * This Tile class represents a Tile object, the building block of the Memory Match game, and maintains 4 private variables, a faceUpvalue of 
 * type String and a faceDownValue of type int. It also maintains a default class constructor and getter and setter methods for the variables.
 * The TileBoard class maintains a list of Tile objects.
 * 
 * @author AmritaSen 18048443
 */
public class Tile 
{
    private JButton tileButton; // Visual representaion of a tile on the tile.
    private final String faceDownImageURL = "thinking-face-icon.png"; // Private final String variable to store the faceDownImageURL for this tile.
    private String faceUpImageURL;                                  // Private String variable to store the faceUpImageURL for this tile.
    private int tileIndex;                                          // Private int variable to store the index of this tile.
    
    /**
     *  This is a default class constructor that initialises the variables with default values.
     */
    public Tile()
    {
        
        this.faceUpImageURL = "beaming-face-with-smiling-eyes-icon.png";
        this.tileButton = new JButton(new ImageIcon(faceUpImageURL));
        this.tileIndex = 1;
    } 
    
    /**
     * This public getter method returns a JButton representing the this.tileButton, a visual representation
     * of a tile object. It does not take in any parameters.
     * @return this.tileButton, a JButton
     */
    public JButton getTileButton() 
    {
        return this.tileButton;
    }
        
    /**
     * This public method checks if the specified image url is an image file and if it is, creates and sets the 
     * specified icon to a tile button.
     * @param imageURL
     * @throws NullPointerException if the specified String parameter is null.
     */
    public void createAndSetTileButtonIcon(String imageURL) throws NullPointerException
    { 
        if(imageURL == null)
            throw new NullPointerException("The specified image url is null.");
        
        try {
            if(ImageIO.read(new File(imageURL)) != null)
            {
                Icon tileImage = new ImageIcon(imageURL);
                tileButton.setIcon(tileImage);
            }
        } catch (IOException ex) {
            Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
     /**
     * This public getter method returns a String representing the URL of the faceDownImage of the Tile. It does not 
     * take in any parameters.
     * 
     * @return this.faceDownImageURL.
     */
    public String getFaceDownImageURL() 
    {
        return this.faceDownImageURL;
    }
    
    /**
     * This public getter method returns a String representing the URL of the faceUpImage of the Tile. It does not 
     * take in any parameters.
     * 
     * @return this.faceUpImageURL;
     */
    public String getFaceUpImageURL() 
    {
        return this.faceUpImageURL;
    } 
    
    /**
     * This public setter method, takes in a String value representing the URL of an image, checks if it is an image file
     * and, if yes, assigns it to the private faceUpValue variable of this class. It does not return any value.
     * 
     * @param faceUpImageURL  
     */
    public void setFaceUpImageURL(String faceUpImageURL) 
    {
        try {
            if(ImageIO.read(new File(faceUpImageURL)) != null)
            {
                this.faceUpImageURL = faceUpImageURL;
            }
        } catch (IOException ex) {
            Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
    
    /**
     * This public getter method returns a int value representing the index assigned to a tile. It does not take in
     * any parameters.
     * 
     * @return this.tileIndex, an int value.
     */
    public int getTileIndex() 
    {
        return this.tileIndex;
    } 
    
    /**
     * This public setter method, takes in, as a parameter, an int value representing the index of a tile and assigns 
     * it to the this.tileIndex variable of this class.     * 
     * @param tileIndex, an int value.
     */
    public void setTileIndex(int tileIndex) 
    {
        this.tileIndex = tileIndex;
    } 
}
