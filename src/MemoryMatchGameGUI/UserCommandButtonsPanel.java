/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryMatchGameGUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This UserCommandButtonsPanel class extends JPanel and is a visual representation of the UserCommandButtonsPanel object. 
 * It maintains and displays four JButtons, each representing a user command option that the player can execute by clicking 
 * the button using the mouse while the game is in progress. The class also maintains a list to store the JButtons. The class  
 * has a getter method for each JButton and a method to an action listener to each JButton in the buttons list. An instance   
 * of this class is created in the TileBoardMemoryGameGUI class and added to the main panel.
 * @author AmritaSen 18048443
 */
public class UserCommandButtonsPanel extends JPanel
{
    private JButton playAgainButton, gameIntructionsAndScoringButton; // Private JButton instances to store the "play again" and "game instructions and scoring" buttons
    private JButton flipAllTilesButton, viewLeadershipBoardButton; // Private JButton instances to store the "flip all tiles" and "view leadership board" buttons
    private List<JButton> buttonsList;    // A private List to store the JButtons maintained by this class.
    
    /**
     * This default class constructor sets the preferred size of this panel. It initialises the buttonsList to an ArrayList,
     * create the JButtons, sets the text, and then adds them to the list of JButtons. Once added, a for each loop adds each   
     * JButton in the list to the panel.
     * 
     * @param currentPlayer 
     */
    public UserCommandButtonsPanel()
    {
        setPreferredSize(new Dimension(0, 87));
        
        // Initialise buttonsList to an ArrayList
        this.buttonsList = new ArrayList<>();
        
        // Create JButtons, set button preferred size and text and add to the buttonsList.
        // JButton to allow the player to play a new game again.
        this.playAgainButton = new JButton();
        this.playAgainButton.setPreferredSize(new Dimension(130, 35));
        this.playAgainButton.setText("Play Again");
        this.buttonsList.add(playAgainButton);
        
        // JButton to allow the player to view the game isntructions and scoring.
        this.gameIntructionsAndScoringButton = new JButton();
        this.gameIntructionsAndScoringButton.setPreferredSize(new Dimension(214, 35));
        this.gameIntructionsAndScoringButton.setText("Game Instructions & Scoring"); 
        this.buttonsList.add(gameIntructionsAndScoringButton);
        
        // JButton to allow the player to flip all tiles on the tile board.
        this.flipAllTilesButton = new JButton();
        this.flipAllTilesButton.setPreferredSize(new Dimension(130, 35));
        this.flipAllTilesButton.setText("Flip All Tiles!");
        this.buttonsList.add(flipAllTilesButton);
        
        // JButton to allow the player to view the leadership board.
        this.viewLeadershipBoardButton = new JButton();
        this.viewLeadershipBoardButton.setPreferredSize(new Dimension(190, 35));
        this.viewLeadershipBoardButton.setText("View Leadership Board");
        this.buttonsList.add(viewLeadershipBoardButton);
        
        // Iterate through the buttonsList, and for each button, customise the font and  
        // add to this UserCommandButtonsPanel.
        for(JButton button : buttonsList)
        {
            button.setFont(new Font("Ariel", Font.BOLD, 13));
            add(button);
        }       
    }
    
    /**
     * This public getter method returns the JButton playAgainButton added to the panel.
     * It does not take in any parameters.
     * @return this.playAgainButton, a JButton object.
     */
    public JButton getPlayAgainButton()
    {
        return this.playAgainButton;
    }
    
    /**
     * This public getter method returns the JButton gameIntructionsButtonAndScoring added to the panel.
     * It does not take in any parameters.
     * @return this.gameIntructionsButtonAndScoring, a JButton object.
     */
    public JButton getGameIntructionsAndScoringButton()
    {
        return this.gameIntructionsAndScoringButton;
    }
    
    /**
     * This public getter method returns the JButton flipAllTilesButton added to the panel.
     * It does not take in any parameters.
     * @return this.flipAllTilesButton, a JButton object.
     */
    public JButton getFlipAllTilesButton()
    {
        return this.flipAllTilesButton;
    }
    
    /**
     * This public getter method returns the JButton viewLeadershipBoardButton added to the panel.
     * It does not take in any parameters.
     * @return this.viewLeadershipBoardButton, a JButton object.
     */
    public JButton getViewLeadershipBoardButton()
    {
        return this.viewLeadershipBoardButton;
    }
    
    /**
     * This public method takes in an ActionListener object as a parameter and adds it to each
     * JButton stored in the buttonsList using a for each loop.
     * @param aL, an ActionListener object 
     * @throws NullPointerException if ActionListener object is null.
     */
    public void addActionListenerToAllButtons(ActionListener aL) throws NullPointerException
    {
        if(aL == null)
            throw new NullPointerException("The specified ActionListener is null");
        
        if(!buttonsList.isEmpty())
        {
            // Iterate through the buttonsList and add the ActionListener onject to each button in 
            // the arrayList.
            for(JButton aButton : buttonsList)
            {
                aButton.addActionListener(aL);
            }
        }
    }
}
