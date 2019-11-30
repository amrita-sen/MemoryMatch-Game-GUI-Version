/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryMatchGameGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * This class is a visual representation of the MemoryGame as displayed to the player, as one panel. It extends JPanel and 
 * implements ActionListener and MouseListener. This class maintains instances of the PlayerHighScoreDatabase, Player, 
 * GreetingsScoreAndDatePanel, TileBoard and UserCommandButtonsPanel objects. It also maintains a private String variable
 * to store the playerUserName that a player enters when prompted at the start of the game. The class has a constructor
 * that sets the layout of the game panel, prompts the player for a username, validates the input, creates a starting tile
 * board and adds the GreetingsScoreAndDatePanel, TileBoard, and the UserCommandButtonsPanel. It implements the actionPerformed 
 * and MouseClicked methods, that call the private methods that control the main functionalities and game play. When a player 
 * mouse clicks a tile facing down on the displayed tile board, the tile will flip displaying the face up image. If the face up 
 * image of two consecutive tiles clicked match, the player gains points and the matched tiles do not flip again. If the chosen 
 * tiles do not match, the player loses points and both tiles will flip again to display the face down image. If the player manages
 * to match all tiles on the tile board, the player's data will be added to the embedded database, if it's a new player, or the
 * date and score date will be updated if the player is an existing player an the score is higher than the one on record. The class
 * has a simple main method.
 * 
 * @author AmritaSen 18048443
 */ 
public class TileBoardMemoryGameGUI extends JPanel implements ActionListener, MouseListener
{
    private PlayerHighScoreDatabase db;                             // Private instance of the PlayerHighScoreDatabase object to access the game database.
    private Player currentPlayer;                                     // Private instance of the Player object representing the current player.
    private GreetingsScoreAndDatePanel greetingsScoreAndDatePanel;  // Private instance of the GreetingsScoreAndDatePanel object representing the game's greetingsScoreAndDatePanel.
    private TileBoard currentTileBoard;                              // Private instance of the TileBoard object representing the current Tile Board.
    private UserCommandButtonsPanel buttonsPanel;                   // Private instance of the GreetingsScoreAndDatePanel object representing the game's buttonsPanel.
    private String playerUserName;
    
    /**
     * Default class constructor that initialises all class variables, generates a starting tile board
     * and adds the JPanels to this panel.
     */
    public TileBoardMemoryGameGUI()  
    {
        super(new BorderLayout());   //invoke super class Jpanel constructor use BorderLayout        
        
        this.db = new PlayerHighScoreDatabase();
        this.db.dbsetup();
        
        // Prompt player for username, create player object and the greetingsScoreAndDatePanel object
        // and handle username input.
        promptForUsername();     
        this.currentPlayer = new Player(playerUserName);
        this.greetingsScoreAndDatePanel = new GreetingsScoreAndDatePanel(currentPlayer);  
        handleValidUsernameInput(playerUserName);

        // Add the greetingsScoreAndDatePanel to this panel at the top.
        add(greetingsScoreAndDatePanel, BorderLayout.NORTH);

        //Generate TileBoard and add to this panel in the center.
        this.currentTileBoard = new TileBoard();    
        this.currentTileBoard.createStartingBoard(this, this);
        add(this.currentTileBoard,BorderLayout.CENTER); 
       
        //Generate buttons panel and add this panel at the bottom.
        this.buttonsPanel = new UserCommandButtonsPanel();
        this.buttonsPanel.addActionListenerToAllButtons(this);        
        add(this.buttonsPanel, BorderLayout.SOUTH);
    }  
    
    /**
     * Depending on the source of the action event, which means the the button that is clicked, the relevant 
     * private method is called.
     * @param e, ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object source = e.getSource();
        
        if(source == buttonsPanel.getPlayAgainButton()){
            handlePlayAgainButtonClick();
        } 
        else if(source == buttonsPanel.getGameIntructionsAndScoringButton()){
            displayGameInstructionsAndScoringInfo();
        }    
        else if(source == buttonsPanel.getFlipAllTilesButton()){
            handleFlipAllTilesButtonClick();
        }  
        else if(source == buttonsPanel.getViewLeadershipBoardButton()){
            displayLeadershipBoard();
        }    
    }
    
    /**
     * This method is only executed if the the source of the MouseEvent is a tile on the tile board.
     * It calls the private handleTileClicksAndMatchingEvents method with the source of the event as
     * a parameter.
     * @param e a MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();        
        handleTileClicksAndMatchingEvents(source);
    }
    
    // Listener methods not implemented by this class
    @Override
    public void mousePressed(MouseEvent e){}  
    @Override
    public void mouseReleased(MouseEvent e){}    
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}  
    
    ////// All private methods of this class /////////
    
    /**
     * This method will prompt the player to input a username using a JOptionPane and validate the input.
     * The player will be prompted in a loop if the text field is empty and nothing is entered or if it has
     * more than 10 characters. If the username has more than 10 characters, an alert message will be displayed
     * and the player will be prompted to input a username again. If the player clicks cancel, System's exit 
     * function is called and the program will end.
     */
    private void promptForUsername()
    {
        do
        {
            playerUserName = JOptionPane.showInputDialog(null, "Please enter your username\n(max. 10 characters): ", "Username (case-sensitive):", 
                                                         JOptionPane.QUESTION_MESSAGE);
            
            if(playerUserName == null){
                System.exit(1);
            } 
            
            if(playerUserName.length() > 10)
            {
                JOptionPane.showMessageDialog(null, "You seem to have entered an invalid username! Click \"OK\" to \n"
                                      + "enter a username again. It must be less than 10 characters.", "Invalid Username!", 
                                      JOptionPane.ERROR_MESSAGE);
            }
        }
        while(playerUserName.isEmpty() || playerUserName.length() > 10); 
    } 
    
    /**
     * If the player inputs a valid username, this method when called will call the dbCheckIfUsernameExists method 
     * of the PlayerHighScoreDatabase class and it the username is found in the database, the player's isNewPayer 
     * value is set to false and an appropriate message is displayed using the JOPtionPane. If the username is not found
     * in the database, the the player's isNewPayer is set to true and an appropriate message is displayed using the 
     * JOPtionPane. 
     * @param usernameInput 
     */
    private void handleValidUsernameInput(String usernameInput)
    {
        String welcomeMessage = "";
        String messageTitle = "";
        
        if(db.dbCheckIfUsernameExists(usernameInput) == true)
        {
            currentPlayer.setIsNewPlayer(false);  

            welcomeMessage = "Your last high score was " + db.dbRetrievePlayerHighScore(currentPlayer) +
                             " in the game you played on " + db.dbRetrievePlayerGameDate(currentPlayer) + ".\n\n" +
                             "Click \"OK\" to begin playing the game.\n\n"                      
                             +"Remember:  If you need a refresher on how this game is played, click the\n"
                             + "\"Game Instructions & Scoring\" button, on the panel below the displayed\n"
                             +"tile board.\n\n"
                             +"Good luck!\n";
            messageTitle = "Welcome back ";
        }
        else 
        {
            currentPlayer.setIsNewPlayer(true);

            welcomeMessage = "You seem to be a new player.\n\n" +
                             "If you know how this game is played, click \"OK\" and start playing!\n\n" +
                             "Otherwise, click \"OK\" and you will see a tile board displayed. Then click\n" + 
                             "the \"Game Instructions & Scoring\" button on the panel below the displayed\n" + 
                             "tile board.\n\n" +
                             "Good luck!\n";
            messageTitle = "Welcome ";
        }

        int userOption = JOptionPane.showConfirmDialog(null, welcomeMessage, messageTitle + currentPlayer.getUsername() + "!",  JOptionPane.OK_CANCEL_OPTION,
                                                       JOptionPane.INFORMATION_MESSAGE);
        
        if(userOption == JOptionPane.CANCEL_OPTION || userOption == JOptionPane.CLOSED_OPTION)
        {
            System.exit(1);
        }
    }
    
    /**
     * This method is called by the ActionPerformed method if the event's source if the play again button.
     * When called, this method displays a message and asks the player to confirm whether or not to start a
     * new game as it will reset the current score. If the player chooses to go ahead, the player's score is 
     * reset, the currentTileBoard's handlePlayAgainButtonActionPerformed method is called and the label of the
     * greetingsScoreAndDatePanel displaying the player's score is updated to reflect the reset scores.
     */
    private void handlePlayAgainButtonClick()
    {
        String startGameMessage = "A new game will begin. Good luck! \nIf you are currently in the middle of a game, it will end and your\n"
                                   + "score will be reset to 0.\n"
                                   + "Click \"Yes\", if you wish to proceed\n";            
        int userOption = JOptionPane.showConfirmDialog(null, startGameMessage, "Start a new game", JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION);            
        if(userOption == JOptionPane.YES_OPTION)
        {
            currentPlayer.getIndividualScore().resetCurrentScore();           
            currentTileBoard.handlePlayAgainButtonActionPerformed(this, this);
            greetingsScoreAndDatePanel.getGameScoreLabel().setText(" " + currentPlayer.getIndividualScore().toString());    
        }
    }
    
    /**
     * This private method is called by the ActionPerformed method if the source of the event is the flipAllTiles button.
     * When called a message will be displaying to the player informing that if a current game is in progress, it will end
     * and the score will be reset. If the player chooses to go ahead, the player's score will be reset by calling the player
     * object's individual score's resetCurrentScore method. It then calls the currentTileBoard's handleFlipAllTilesButtonActionPerformed
     * method and updates the greetingsScoreAndDatePanel object's score label text.     * 
     */
    private void handleFlipAllTilesButtonClick()
    {
        String filpTilesMessage = "Your current game will end and your current score will be reset to 0.";
        int userOption = JOptionPane.showConfirmDialog(null, filpTilesMessage, "Do you really want to flip all tiles?",
        JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION);

        if(userOption == JOptionPane.YES_OPTION)
        {
            currentPlayer.getIndividualScore().resetCurrentScore();              
            currentTileBoard.handleFlipAllTilesButtonActionPerformed(this, this);
            greetingsScoreAndDatePanel.getGameScoreLabel().setText(" " + currentPlayer.getIndividualScore().toString());  
        }
    }
    
    /**
     * This method is the most important method of this class and is called by the MouseClicked method and takes in, as a parameter,
     * an Object representing the source of the mouse event. It controls the main functionality of the tile board game. If a player 
     * clicks a tile facing down, depending on the number of clicks, and the tile clicked, this method will check the tiles should be 
     * matched or not. Accordingly, the relevant private methods of this method are called. There are four local variables to store 
     * the selected tile object, the selected JButton, the currentSelectedTileNumber and the previousSelectedTileNumber. These variables
     * along with the private methods control how the game behaves when a tile on the tile board is clicked. Every 2 separate tiles 
     * clicked will be checked if they match and then execute the methods accordingly.
     * @param source, an Object
     */
    private void handleTileClicksAndMatchingEvents(Object source)
    {
        currentTileBoard.handleNumOfMouseTileClicks();
        
        for(int i = 0; i < currentTileBoard.getNumTiles(); i++)
        {
            if(source == currentTileBoard.getTileButton(i)) // the source of an event is a tile button on the tile board
            {
                Tile selectedTile = currentTileBoard.getTile(i);
                JButton selectedButton = currentTileBoard.getTileButton(i);
                int currentSelectedTileNumber = selectedTile.getTileIndex();
                currentTileBoard.getTilesClicked().add(currentSelectedTileNumber);
                int previousSelectedTileNumber = 0;
        
                
                if(currentTileBoard.tilesCanBeMatched()) // If it can be matched with previous tile.
                {
                    selectedButton.setIcon(new ImageIcon(selectedTile.getFaceUpImageURL()));   
                    previousSelectedTileNumber = currentTileBoard.getTilesClicked().get(currentTileBoard.getTilesClicked().size() - 2);

                    if(currentSelectedTileNumber != previousSelectedTileNumber) // If the selected tile is not the same as the previous tile clicked.
                    {
                        boolean tilesMatch = currentTileBoard.checkMatch(currentSelectedTileNumber, previousSelectedTileNumber);

                        if(tilesMatch == false){
                            handleIfTwoTilesDoNotMatchEvent(selectedButton, selectedTile, previousSelectedTileNumber);
                        }
                        else{
                            handleIfTwoTilesMatchEvent(selectedButton, selectedTile, currentSelectedTileNumber, previousSelectedTileNumber);
                        }

                        System.out.println("Match = " + tilesMatch);
                    }

                    greetingsScoreAndDatePanel.getGameScoreLabel().setText(" " + currentPlayer.getIndividualScore().toString());   

                    if(currentTileBoard.allTilesMatched()){
                        handleIfAllTilesMatchedEvent();
                    }
                }
                else {
                    selectedButton.setIcon(new ImageIcon(selectedTile.getFaceUpImageURL()));
                }
            }
        }
    }
    
    
    /**
     * This private method is called by the handleTileClicksAndMatchingEvents method if the two tiles that a player clicks do not 
     * match.It displays a message to the player indicating that tiles don't match and the points deducted from the score. It then 
     * calls the currentTileBoard's handleTilesNotMatchedEvent method and updates the player's score by calling the Player object's 
     * individualScore's handleMisses method.
     * @param aSelectedButton, a JButton
     * @param aSelectedTile, a Tile object
     * @param aPreviousSelectedTileNumber, an int value
     */
    private void handleIfTwoTilesDoNotMatchEvent(JButton aSelectedButton, Tile aSelectedTile, int aPreviousSelectedTileNumber)
    {
        String notAMatchMessage = "That's not a match! You lose " + this.currentPlayer.getIndividualScore().getPointsLostPerMiss() + " points.";
        JOptionPane.showMessageDialog(null, notAMatchMessage, "Oops...!", JOptionPane.INFORMATION_MESSAGE);
        currentTileBoard.handleTilesNotMatchedEvent(aSelectedButton, aSelectedTile, aPreviousSelectedTileNumber);
        currentPlayer.getIndividualScore().handleMisses();
    }
    
    /**
     * This private method is called by the handleTileClicksAndMatchingEvents method if the two tiles that a player clicks evaluate 
     * as a match.It displays a message to the player indicating of a match the points scored. It then calls the currentTileBoard's 
     * handleTilesMatchedEvent and removeMouseAndActionListenersFromMatchedTiles methods and finally updates the player's score by 
     * calling the Player object's individualScore's handleMatches method.
     * @param aSelectedButton, a JButton
     * @param aSelectedTile, a Tile object.
     * @param aPreviousSelectedTileNumber, an int value.
     * @param aCurrentSelectedTileNumber, an int value.
     */
    private void handleIfTwoTilesMatchEvent(JButton aSelectedButton, Tile aSelectedTile, int aPreviousSelectedTileNumber,int aCurrentSelectedTileNumber)
    {
        String matchMessage = "That's a match! You gain " + this.currentPlayer.getIndividualScore().getPointsScoredPerMatch() + " points.";
        JOptionPane.showMessageDialog(null, matchMessage, "Well done!", JOptionPane.INFORMATION_MESSAGE);
        currentTileBoard.removeMouseAndActionListenersFromMatchedTiles(aSelectedButton, aPreviousSelectedTileNumber, this, this); 
        currentTileBoard.handleTilesMatchedEvent(aSelectedButton, aSelectedTile, aCurrentSelectedTileNumber, aPreviousSelectedTileNumber);                       
        currentPlayer.getIndividualScore().handleMatches();
    }
    
    /**
     * This private method is called by the handleTileClicksAndMatchingEvents method if the player matches all tiles on the tile board. 
     * A congratulatory message is displayed with the final score and the handleGameEndPlayerData of this class is called. Finally, 
     * if the isNewPlayer value of the player is true, is is set to false.
     */
    private void handleIfAllTilesMatchedEvent()
    {
        System.out.println("Well done! You have successfully matched all tiles on your tile board."); 
        String winningMessage = "Well done! You have successfully matched all tiles on your tile board.\n" +
        "Your final score is: " + currentPlayer.getIndividualScore().getPlayerScore() + " points!\n" +
        "If you wish to play the game again, click the \"OK\" button below and then click the \"Play Again\"\n" +
        "button.";
        JOptionPane.showMessageDialog(null, winningMessage, "Congratulations! All tiles have been matched :)", JOptionPane.INFORMATION_MESSAGE);
        
        handleGameEndPlayerData();
        
        if(currentPlayer.getIsNewPlayer() == true){
            currentPlayer.setIsNewPlayer(false);
        }
    }
    
    /**
     * This method is called if the player manages to match all tiles on the tile board. If the player
     * is a new player, the player's username, score and the current date is recorded in the database
     * by calling dbInsertData method with the currentPlayer as a parameter. If the player is an
     * existing player and the current score is greater than the player's last high score in the database,
     * the data is replaced with the current score and date.
     */
    private void handleGameEndPlayerData()
    {
        if(currentPlayer.getIsNewPlayer() == true)
        {
            db.dbInsertData(currentPlayer);
        }
        else if(currentPlayer.getIsNewPlayer() == false && db.dbIsCurrentScoreHigherThanRecord(currentPlayer) == true)
        {
                db.dbUpdateDateAndScore(currentPlayer);
        }  
    }
    
    /**
     * This method is called if the "Game Instructions and Scoring" button is clicked. displays a JOption pane with an Information
     * message that is set to the specified text that includes the game instructions and scoring.
     */
    private void displayGameInstructionsAndScoringInfo()
    {
        String gameInstructionsMessage = "Game Instructions:\n" +
                "====================\n" +
                "1. There are " + currentTileBoard.getNumTiles() + " tiles facing down on the displayed tile board.\n" +
                "2. In each move, you can turn any 2 tiles by using the mouse to click on the tile you want to flip.\n" +
                "3. When a tile, with a face down image is clicked, it will be flipped and the face up image will be displayed.\n" +  
                "4. The 2 tiles you've chosen will be considered a \"match\" if both have the same face up image.\n" +
                "5. If the tiles do not match, they will flip again to display their face down image. Matched tiles will "
                    + "not flip back.\n" +
                "6. The game will end when you successfully match all the tiles on your tile board or, if you choose to "
                    + "quit the game.\n" +
                "7. During the game, you have the below button options available to click:\n"
                    + "    \"Play Again\":  This will start a new game. If you are in the middle of a current game, all scores "
                    + "will reset to zero.\n"
                    + "    \"Flip All Tiles\":  This will end your current game and flip all the tiles on your tile board to display "
                    + "their face up image.\n" 
                    + "    \"View Leadership Board \": The top 10 players and their high scores will be displayed.\n" +
                "8. The goal of the game is to, with every tile flip, memorise the location of an image, and eventually match all the tiles on your\n" +
                "     tile board.\n" +
                "9. You can close the game window by using the mouse to click on the \"X\" button at the top-right corner "
                    + "of the game window.\n\n" +     
                "Scoring:\n" +
                "========\n" +
                "1. Every match, will increase your score by " + currentPlayer.getIndividualScore().getPointsScoredPerMatch() + " points and "
                    + "your \"number of matches\" by 1.\n" +
                "2. Every miss will reduce your score by " + currentPlayer.getIndividualScore().getPointsLostPerMiss() + " points and "
                    + "increase your \"number of misses\" by 1.\n" +
                "3. If you choose to click on the \"Play AGain\" or the \"Flip All Tiles\" button mid-way through your game, the current game\n"
                    + "    will end and your score will be reset to 0 before you begin a new game.\n" +
                "4. Your score is displayed throughout the game, in the panel above your tile board.\n" +
                "5. At the end of the game, your final score will be displayed and your username, score, and date will saved in our records.\n" +
                "6. If you have played the game more than once, the highest score will be saved along with the date the game was played on.\n\n"  +
               
                " <<< Remember, this game is to train your concentration, patience, and memory. So, avoid taking notes. That will be cheating! >>>\n\n";
            
        JOptionPane.showMessageDialog(null, gameInstructionsMessage, "Game Instructions & Scoring", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * This method is called if the "View Leadership Board" button is clicked. When called, the method calls the dbRetrieveTopTenRecords
     * method of the PlayerHighScoreDatabase class that returns a JTable object that is displayed to the player using a JOptionPane.
     */
    private void displayLeadershipBoard()
    {       
        JTable topTenPlayersTable = db.dbRetrieveTopTenRecords();       
        JOptionPane.showMessageDialog(null, new JScrollPane(topTenPlayersTable), "Leadership Board (Top Ten Players)", JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * This is the main method of this class and is the entry point for the TileBoardMemoryGame. When the program is run, an introductory message 
     * will be displayed to the player.
     * @param args
     */
    public static void main(String[] args) 
    {
        String introMessage = "This intellectual, yet fun and easy game is intended to train concentration, patience,\n "
                            + "and short-term memory as you challenge yourself to match tiles on a displayed tile\n"
                            + "board. If you wish to proceed, click the \"Yes\" option at the bottom of this window to\n"
                            + "begin playing.\n\n";
        int selectedOption = JOptionPane.showConfirmDialog(null, introMessage, "The Tile Memory Match Game!", 
                                                           JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        
        if(selectedOption == JOptionPane.NO_OPTION || selectedOption == JOptionPane.CLOSED_OPTION)
        {
            System.exit(1);
        }
        
        TileBoardMemoryGameGUI myPanel = new TileBoardMemoryGameGUI();   // create instance of TileBoardMemoryGameGUI object   
        
        JFrame frame = new JFrame("Tile Memory Match Game!"); //create frame to hold our JPanel subclass	
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(myPanel);  //add instance of MyGUI to the frame
        frame.pack(); //resize frame to fit our Jpanel        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width / 2) - (frame.getWidth() / 2), (d.height / 2) - (frame.getHeight() / 2)));
        frame.setResizable(false);        
        frame.setVisible(true);
    }
}

    
    
    

