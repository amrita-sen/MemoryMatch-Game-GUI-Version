/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryMatchGameGUI;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class extends JPanel and is a visual representation of a TileBoard object. It maintains a private list of Tile objects,  
 * JButtons and integers. It also maintain 2 final integer variables to store the number of tiles and number of rows on the tile  
 * board. It addition, there is a final instance of the GridLayout object to set the layout of the tile board to grid of rows 
 * and columns depending on the number of tiles specified. The class also has private and public methods to control the functionality 
 * and game play of the tile board when a player clicks on a particular tile.
 *
 * @author AmritaSen 18048443
 */
public class TileBoard  extends JPanel
{
    private List<Tile> tiles;                          // Private List to store Tile objects.
    private List<JButton> tileButtons;                 // Private List to store JButtons.
    private List<Integer>  tilesClicked, tilesMatched; // List to Private Private store Integer values for tilesClicked and tilesMatched.
    private final int NUM_TILES = 20;                  // Private final int variable to store number of tiles. Set to 20.
    private final int NUM_ROWS = 5;                    // Private final int variable to store number of tiles. Set to 20.
    private final GridLayout tileBoardLayout = new GridLayout(NUM_ROWS, (NUM_TILES/NUM_ROWS)); // Fianl GridLayour object to set layout of this panel.
    private int numOfTileClicks;                       // Private integer variable to store number of tile clicks.
    
    /**
     * This is the default class constructor that initialises the variables of the class and sets the
     * layout of the tile board.
     */
    public TileBoard()
    {
        this.tileBoardLayout.setHgap(2);
        this.tileBoardLayout.setVgap(2);        
        setLayout(tileBoardLayout);
        this.tiles = new ArrayList<>(); 
        this.tileButtons = new  ArrayList<>();    
        this.tilesClicked = new ArrayList<>(); 
        this.tilesMatched = new ArrayList<>(); 
        this.numOfTileClicks = 0;   
    }   
    
    /**
     * This public method creates a tile board to be added to the main game panel. It takes in as parameters, 
     * a MouseListener object and an ActionListener object. Using a for loop it creates NUM_TILES new tile 
     * objects and adds each tile object to the tiles list. It calls the Tile object's createAndSetTileButtonIcon 
     * method with aTile.getFaceDownImageURL() as a parameter and adds the Tile object's JButton to the tile 
     * board. Each JButton is also added to the tileButtons list. Outside the for loop, the methods - 
     * allocateFaceUpImages(), shuffleTiles(), and allocateFaceTileIndices() are called. Finally the specified 
     * MouseListener and ActionListener objects are added to each JButton by calling the 
     * addMouseAndActionListenerToAllTiles(mL, aL) method with the specified MouseListener and ActionListener 
     * objects as parameters.
     * 
     * @param mL, a MouseListener
     * @param aL an ActionListener
     * @throws NullPointerException if any of the parameters is null
     */
    public void createStartingBoard(MouseListener mL, ActionListener aL) throws NullPointerException
    {        
        if(mL == null || aL == null)
            throw new NullPointerException("One or both of the specified values are null");
        
        for(int i = 0; i < NUM_TILES; i++)
        {   
            Tile aTile = new Tile();       
            tiles.add(aTile);
            aTile.createAndSetTileButtonIcon(aTile.getFaceDownImageURL());
            add(aTile.getTileButton());
            tileButtons.add(aTile.getTileButton());
        }
        
        allocateFaceUpImages();
        shuffleTiles();
        allocateFaceTileIndices();   
        addMouseAndActionListenersToAllTiles(mL, aL);
    }
    
    /**
     * This private method is called by the createStartingBoard method. When called, the method will read 
     * string values representing image urls from the specified text file, and set it as a face down image 
     * url value for each tile object. A scanner object is used to read the text file.
     * 
     */
    private void allocateFaceUpImages() 
    {   
        File textFile = new File("faceUpImageList.txt");  // new File object created.
        int index = 0;

        try (Scanner sc = new Scanner(new FileReader(textFile)))
        {
            while (sc.hasNext() && index != this.NUM_TILES) 
            {
                for(Tile aTile : tiles)
                {
                    aTile.setFaceUpImageURL(sc.nextLine());
                    index++;
                }
            }

          sc.close();
        } catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
    }
    
    /**
     * This private method is called by the createStartingBoard method. When called, the method will use 
     * a for each loop to assigns a unique integer value, starting from 1, to each Tile object.
     * 
     */
    private void allocateFaceTileIndices()
    {
        int index = 1;
        
        for(Tile anotherTile : tiles)
        {
            anotherTile.setTileIndex(index);
            index++;
        }
    }
    
    /**
     * This private method is called by the createStartingBoard method and the handlePlayAgainButtonActionPerformed
     * method. When called, the method will apply the shuffle operation on the tiles list this.tiles and return 
     * this.tiles with shuffled Tile objects (the position of each object will change to a random position).
     * 
     * @return this.tiles
     */
    private List<Tile> shuffleTiles()
    {
        Collections.shuffle(this.tiles);
        return this.tiles;
    }
   
    /**
     * If the tiles list is not empty, when called, for each Tile object in the tiles list, it calls the Tile object's
     * createAndSetTileButtonIcon, with the Tile object's getFaceDownImageURL() as a parameter.
     * It displays of a tile board with tiles facing down displaying the face down image.
     */
    private void displayTilesFaceDown()
    {
        if(!tiles.isEmpty())
        {
            for(Tile aTile : tiles)
            {
                aTile.createAndSetTileButtonIcon(aTile.getFaceDownImageURL());
            }
        }
    }
    
    /**
     * If the tiles list and the tileButtons list are not empty, when called, for each tile on the tile board,
     * an ImageCon is created with the face up image url of each Tile object as a parameter, and sets the icon
     * of each JButton in the tileButtons list using the created ImageIcon as a parameter.
     * It displays of a tile board with tiles facing up displaying the face up image.
     */
    private void displayTilesFaceUp()
    {
        ImageIcon faceUpTmage;
        
        if(!tileButtons.isEmpty() && !tiles.isEmpty())
        {
            for(int i = 0; i < NUM_TILES; i++)
            {
                faceUpTmage = new ImageIcon(tiles.get(i).getFaceUpImageURL());
                tileButtons.get(i).setIcon(faceUpTmage);
            }
        }
    }
    
    /**
     * This public method checks if the conditions are right to call the checkMatch method of this class.
     * It returns true if the specified conditions are met.
     * @return true if the number of clicks is multiple of 2 or if it is greater than 2 and not a multiple of 2 and 
     *         the previous two values in the tilesClickes list are equal and the previous tile is not in the tilesMatched list.
     */
    public boolean tilesCanBeMatched()
    {
        return ((numOfTileClicks % 2 == 0 && !tilesMatched.contains(tilesClicked.get(tilesClicked.size() - 2)))|| 
                (numOfTileClicks > 2 && numOfTileClicks % 2 != 0 && 
                (tilesClicked.get(tilesClicked.size() - 2)).equals(tilesClicked.get(tilesClicked.size() - 3)) && 
                !tilesMatched.contains(tilesClicked.get(tilesClicked.size() - 2))));
    }
    
    /**
     * This public method takes in, as parameters, two integer values representing the tileIndices of the two
     * tiles to be matched while the game is in progress. If the face up image urls of both tiles are an exact 
     * match, a boolean true is returned. This is one of the most important methods for the functionality of
     * the game.
     * @param option1Index, of tile 1 to be matched
     * @param option2Index, of tile 2 to be matched
     * @return true if the faceUpImage1URL is equal to faceUpImage2URL.
     */
    public boolean checkMatch(int option1Index, int option2Index)
    {   
        // local variables to store Sting values representing 2 face up image urls.
        String faceUpImage1URL = "";
        String faceUpImage2URL = "";

        // iterates through the array list of tile objects, gets the index of the tile with face down value option1 
        // and assigns the associated face up image url to local variable faceUpImage1URL. Similar operation for option2.
        for(int i = 0; i < this.NUM_TILES; i++)
        {
            if(this.tiles.get(i).getTileIndex()== option1Index)
            {
                faceUpImage1URL = this.tiles.get(i).getFaceUpImageURL();
            }

            if(this.tiles.get(i).getTileIndex() == option2Index)
            {
                faceUpImage2URL = this.tiles.get(i).getFaceUpImageURL();
            }
        }

        return faceUpImage1URL.equals(faceUpImage2URL);  
    }
    
    /**
     * When called, this private method adds the specified MouseListener and ActionListener objects taken as
     * parameters to all JButtons in the tileButtons list, if the list is not empty.
     * @param mL, a MouseListener
     * @param aL, an ActionListener
     * @throws NullPointerException is either of the parameters is null.
     */
    private void addMouseAndActionListenersToAllTiles(MouseListener mL, ActionListener aL) throws NullPointerException
    {
        if(mL == null || aL == null)
            throw new NullPointerException("One or both of the specified values are null");
        
        if(!tileButtons.isEmpty())
        {
            for(JButton newTile : tileButtons)
            {
                newTile.addMouseListener(mL);
                newTile.addActionListener(aL);
            }
        }
    }
    
    /**
     * When called, this private method removes the specified MouseListener and ActionListener objects taken as
     * parameters from all JButtons in the tileButtons list.
     * @param mL, a MouseListener
     * @param aL, an ActionListener
     * @throws NullPointerException is either of the parameters is null.
     */
    private void removeMouseAndActionListenersFromButtons(MouseListener mL, ActionListener aL) throws NullPointerException
    {
        if(mL == null || aL == null)
            throw new NullPointerException("One or both of the specified values are null");
        
        if(!tileButtons.isEmpty())
        {
            for(JButton newTile : tileButtons)
            {
                newTile.removeMouseListener(mL);
                newTile.removeActionListener(aL);
            }
        }
    }
        
    /**
     * When called this public method will clear the tilesMatched and tilesClicked lists, set the number of clicks
     * to 0. It then calls the methods - shuffleTiles, allocateFaceTileIndices, removeMouseAndActionListenersFromButtons  
     * and addMouseAndActionListenerToAllTiles. When the "Play Again" button on the main game board is clicked, it resets 
     * the tile board to the starting conditions 
     * with the tiles shuffled and facing down.
     * @param mL, a MouseListener
     * @param aL, an ActionListener
     * @throws NullPointerException is either of the parameters is null.
     */
    public void handlePlayAgainButtonActionPerformed(MouseListener mL, ActionListener aL) throws NullPointerException
    { 
        if(mL == null || aL == null)
            throw new NullPointerException("One or both of the specified values are null");
                
        this.numOfTileClicks = 0;
        this.tilesMatched.clear();
        this.tilesClicked.clear();
        displayTilesFaceDown();
        shuffleTiles();
        allocateFaceTileIndices();
        removeMouseAndActionListenersFromButtons(mL, aL);   
        addMouseAndActionListenersToAllTiles(mL, aL); 
    }
    
    /**
     * When called this public method will clear the tilesMatched and tilesClicked lists, set the number of clicks
     * to 0. It then calls the methods - displayTilesFaceUp, allocateFaceTileIndices and
     * removeMouseAndActionListenersFromButtons. When the "Flip All Tiles" button on the main game board is clicked,
     * all tiles on the tile board has displayed facing up and all mouse and action listeners removed. 
     * @param mL, a MouseListener
     * @param aL, an ActionListener
     * @throws NullPointerException is either of the parameters is null.
     */
    public void handleFlipAllTilesButtonActionPerformed(MouseListener mL, ActionListener aL) throws NullPointerException
    {
        if(mL == null || aL == null)
            throw new NullPointerException("One or both of the specified values are null");
        
        this.numOfTileClicks = 0;
        this.tilesMatched.clear();
        this.tilesClicked.clear();
        displayTilesFaceUp();
        allocateFaceTileIndices();             
        removeMouseAndActionListenersFromButtons(mL, aL);
    }
    
    /**
     * This public method checks if all the tiles on the tile board have been matched.
     * @return true if number of elements in tilesMatch is equal to the number of tiles of the tile board. 
     */
    public boolean allTilesMatched()
    {
        return (this.tilesMatched.size() == this.NUM_TILES);
    }
    
    /**
     * This public method increases the value stored in this.numOfTileClicks by 1, when called.
     */
    public void handleNumOfMouseTileClicks()
    {
        this.numOfTileClicks++;
    }
   
    /**
     * This method when called, add the tile numbers of both tiles that are evaluated to be a match to tilesMatched list
     * and set the icon of the selected button to the face up image of the selected tile object.
     * @param selectedButton, a JButton
     * @param selectedTile a Tile object
     * @param currentSelectedTileNumber an int value
     * @param previousSelectedTileNumber an int value
     * @throws NullPointerException if the JButton and / or Tile objects are null.
     * @throws IllegalArgumentException if the int value is less than or equal to 0; 
     */
    public void handleTilesMatchedEvent(JButton selectedButton, Tile selectedTile, int currentSelectedTileNumber, int previousSelectedTileNumber)
        throws NullPointerException, IllegalArgumentException
    {
        if(selectedButton == null || selectedTile == null)
            throw new NullPointerException("One or all of the specified parameters are null");
        
        if(currentSelectedTileNumber <= 0)
            throw new IllegalArgumentException("The currentSelectedTileNumber and previousSelectedTileNumber must be greater than or equal to 1");
        
        // Add both matched tiles to the this.tilesMatched list.
        this.tilesMatched.add(previousSelectedTileNumber);
        this.tilesMatched.add(currentSelectedTileNumber);        
        selectedButton.setIcon(new ImageIcon(selectedTile.getFaceUpImageURL()));
    }
    
    /**
     * This public method, when called, removes the Mouse and Action Listeners from the two tiles that have been evaluated
     * as a match. 
     * @param selectedButton, a jButton
     * @param previousSelectedTileNumber, int value
     * @param mL, a MouseListener
     * @param aL, an ActionListener 
     * @throws NullPointerException if the JButton, MouseListener and / or ActionaListener are null.
     * @throws IllegalArgumentException if the int value is less than or equal to 0; 
     */
    public void removeMouseAndActionListenersFromMatchedTiles(JButton selectedButton, int previousSelectedTileNumber, MouseListener mL, ActionListener aL)
            throws NullPointerException, IllegalArgumentException
    {
        if(selectedButton == null || mL == null || aL == null)
            throw new NullPointerException("One or all of the specified parameters are null");
        
        if(previousSelectedTileNumber <= 0)
            throw new IllegalArgumentException("The previousSelectedTileNumber must be greater than or equal to 1");
        
        getTileButton(previousSelectedTileNumber - 1).removeMouseListener(mL);
        getTileButton(previousSelectedTileNumber - 1).removeActionListener(aL);
        selectedButton.removeMouseListener(mL);    
        selectedButton.removeActionListener(aL);    
    }
    
    /**
     * This public method, when called, sets the icon of both selected tiles that do not match to the 
     * Tile object's face down image.
     * @param selectedButton, a JButton
     * @param selectedTile, a Tile object
     * @param previousSelectedTileNumber, an int value
     * @throws NullPointerException if the JButton and / or Tile objects are null.
     * @throws IllegalArgumentException if the int value is less than or equal to 0; 
     */
    public void handleTilesNotMatchedEvent(JButton selectedButton, Tile selectedTile, int previousSelectedTileNumber)
                throws NullPointerException, IllegalArgumentException
    {
        if(selectedButton == null || selectedTile == null)
            throw new NullPointerException("One or all of the specified parameters are null");
        
        if(previousSelectedTileNumber <= 0)
            throw new IllegalArgumentException("The previousSelectedTileNumber must be greater than or equal to 1");
            
        selectedButton.setIcon(new ImageIcon(selectedTile.getFaceDownImageURL()));
        this.tileButtons.get(previousSelectedTileNumber - 1).setIcon(new ImageIcon(selectedTile.getFaceDownImageURL()));
    }
    
     /**
     * This public method takes in, as a parameter, an integer value representing the index of JButton in the tileButtons
     * list and returns the tileButton at that index in the list.
     * @param index
     * @return aTileButton, at index in the tileButtons list.
     */
    public JButton getTileButton(int index){
        JButton aTileButton = tileButtons.get(index);        
        return aTileButton;
    }

    /**
     * This public method takes in, as a parameter, an integer value representing the index of a Tile in the tiles list
     * and returns the Tile object at that index in the list.
     * @param index
     * @return aTileButton, at index in the tileButtons list.
     */
    public Tile getTile(int index){
        Tile thisTile = tiles.get(index);
        return thisTile;
    }  
    
    /**
     * This public method returns a List<Integer> representing the this.tilesClicked variable of this class. It does 
     * not take in any parameters.
     * @return this.tilesClicked
     */
    public List<Integer> getTilesClicked(){
        return this.tilesClicked;
    } 
    
    /**
     * This public method returns a List<Integer> representing the this.tilesMatched variable of this class. It does 
     * not take in any parameters.
     * @return this.tilesMatched
     */
    public List<Integer> getTilesMatched(){
        return this.tilesMatched;
    }
    
    /**
     * This public method returns an int value of this.NUM_TILES representing the number of tiles on the tile board. It 
     * does not take in any parameters.
     * @return this.NUM_TILES, an integer value.
     */
    public int getNumTiles(){
        return this.NUM_TILES;
    }
    
    /**
     * This public method returns an int value of this.numOfTileClicks representing the number of mouse clicks recorded on the
     * tile board. It does not take in any parameters. 
     * @return this.numOfTileClicks, an integer value.
     */
    public int getNumOfTileClicks(){
        return this.numOfTileClicks;
    }
}
