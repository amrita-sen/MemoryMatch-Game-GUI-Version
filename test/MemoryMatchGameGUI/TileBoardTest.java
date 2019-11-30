/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryMatchGameGUI;

import javax.swing.JButton;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests some methods of the TileBoard class.
 * @author AmritaSen 18048443
 */
public class TileBoardTest {
    
    private TileBoard instance;
    private Tile testTile;
    
    @Before
    public void setUp() {
        instance = new TileBoard();
        testTile = new Tile();        
    }
    
    @After
    public void tearDown() {
        instance = null;
        testTile = null;
    }

    /**
     * Test of handleTilesMatchedEvent method, of class TileBoard.
     */
    @Test
    public void testHandleTilesMatchedEvent() {
        System.out.println("handleTilesMatchedEvent");

        JButton testSelectedButton = testTile.getTileButton();
        int testCurrentSelectedTileNumber = 3;
        int testPreviousSelectedTileNumber = 5;

        instance.handleTilesMatchedEvent(testSelectedButton, testTile, testCurrentSelectedTileNumber, testPreviousSelectedTileNumber);
        assertTrue(instance.getTilesMatched().contains(testCurrentSelectedTileNumber) &&
                   instance.getTilesMatched().contains(testPreviousSelectedTileNumber));
    }  
}
