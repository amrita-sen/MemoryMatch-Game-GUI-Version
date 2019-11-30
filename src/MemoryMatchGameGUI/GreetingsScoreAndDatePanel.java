/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryMatchGameGUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This GreetingsScoreAndDatePanel class extends JPanel and is a visual representation of the GreetingsScoreAndDatePanel
 * object. It is used to display the greeting text, the string representation of a player's individual score and the  
 * current date the game is played on, using JLabels. The class maintains a final instance of the GridLayout object that 
 * sets the layout of the panel in to a grid of 3 rows and 1 columns. The class maintains 3 JLabels, each with its text
 * set to the relevant information. The JLabels are stored in a List. In addition, the class tracks the current time the 
 * game is played on. The class has two get methods, one to retrieve the greetings label and the other to retrieve the 
 * score label. These methods are called when the label text needs to be updated. An instance of this class is created in
 * the TileBoardMemoryGameGUI class and added to the main panel.
 * 
 * @author AmritaSen 18048443
 */
public class GreetingsScoreAndDatePanel extends JPanel
{
    private final GridLayout panelLayout = new GridLayout(3, 1); // GridLayout object to set the the layout of this panel.
    private JLabel playerGreetingsLabel, gameScoreLabel, gameDateLabel; // JLabels to display information as text.
    private List<JLabel> labelsList; // List to store JLabels maintained by this class
    private DateFormat dateFormat; // An instance of DateFormat object to maintain the format in which the current date is to be displayed.
    private Date currentDate; // An instance of Date object to track the current date. 
    
    /**
     * This class constructor takes in a Player object and sets the preferred size and layout of this panel. It initialises 
     * the JLabels, sets the text, and then adds them to the list of JLabels that is initialised to an ArrayList. Once added, 
     * a for each loop adds each JLabel in the list to the panel.
     * 
     * @param currentPlayer 
     */
    public GreetingsScoreAndDatePanel(Player currentPlayer)
    {
        this.panelLayout.setHgap(5);
        
        setPreferredSize(new Dimension(0, 68));
        setLayout(panelLayout);
        
        // Initialise labelsList to an ArrayList
        this.labelsList = new ArrayList<>();
        
        // Create JLabels, set label text and add to labelsList.
        // Label to display player greetings text.
        this.playerGreetingsLabel = new JLabel();
        this.playerGreetingsLabel.setText(" Current player: " + currentPlayer.getUsername());
        this.labelsList.add(playerGreetingsLabel); 
        
        // Label to display player's current score.
        this.gameScoreLabel = new JLabel();
        this.gameScoreLabel.setText(" " + currentPlayer.getIndividualScore().toString());
        this.labelsList.add(gameScoreLabel);       
        
        // Label to display current date.
        this.gameDateLabel = new JLabel();
        this.dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        this.currentDate = new Date();
        this.gameDateLabel.setText(" Date: " + this.dateFormat.format(this.currentDate));
        this.labelsList.add(gameDateLabel);
        
        // Iterate through the labelsList, and for each label, customise the font and add 
        // to this GreetingsScoreAndDatePanel.
        for(JLabel label : labelsList)
        {
            label.setFont(new Font("Ariel", Font.BOLD, 13));
            add(label);
        }
    }
    
    /**
     * This public getter method returns the JLabel playerGreetingsLabel added to the panel.
     * It does not take in any parameters.
     * @return this.playerGreetingsLabel, a JLabel
     */
    public JLabel getPlayerGreetingsLabel()
    {
        return this.playerGreetingsLabel;
    }
    
    /**
     * This public getter method returns the JLabel gameScoreLabel added to the panel.
     * It does not take in any parameters.
     * @return this.gameScoreLabel, a JLabel
     */
    public JLabel getGameScoreLabel()
    {
        return this.gameScoreLabel;
    }
}
