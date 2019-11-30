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
 * This class tests some methods of the Tile class.
 * @author AmritaSen 18048443
 */
public class TileTest {
    
    private Tile instance;
    private String testImageURL;
    
    @Before
    public void setUp() {
        instance = new Tile();
        testImageURL = "beaming-face-with-smiling-eyes-icon.png";
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of getTileButton method, of class Tile.
     */
    @Test
    public void testGetTileButton() {
        System.out.println("getTileButton");
        JButton result = instance.getTileButton();              
        assertTrue(result instanceof JButton);
    }

    /**
     * Test of ceateAndSetTileButtonIcon method, of class Tile.
     */
    @Test
    public void testCreateAndSetTileButtonIcon() {
        System.out.println("createAndSetTileButtonIcon");
        instance.createAndSetTileButtonIcon(testImageURL);
        assertTrue(instance.getTileButton().getIcon() != null &&
                   (testImageURL.equals(instance.getFaceUpImageURL())|| testImageURL.equals(instance.getFaceDownImageURL())));
    }

   /**
    * Test of getFaceDownImageURL method, of class Tile.
    */
    @Test
    public void testGetFaceDownImageURL() {
        System.out.println("getFaceDownImageURL");
        String result = instance.getFaceDownImageURL();
        instance.setFaceUpImageURL(result);
        assertTrue(instance.getFaceUpImageURL().equals(result));
    }
    
    /**
     * Test of getFaceUpImageURL method, of class Tile.
     */
    @Test
    public void testGetFaceUpImageURL() {
        System.out.println("getFaceUpImageURL");
        instance.setFaceUpImageURL(testImageURL);
        String result = testImageURL;
        assertTrue(result != null && instance.getFaceUpImageURL().equals(result));
    }
    
    /**
     * Test of setFaceUpImageURL method, of class Tile.
     */
    @Test
    public void testSetFaceUpImageURL() {
        System.out.println("setFaceUpImageURL");
        String result = testImageURL;
        instance.setFaceUpImageURL(testImageURL);
        assertTrue(instance.getFaceUpImageURL().equals(testImageURL));
    }
    
    /**
     * Test of getTileIndex method, of class Tile.
     */
    @Test
    public void testGetTileIndex() {
        System.out.println("getTileIndex");
        int result = instance.getTileIndex();
        assertTrue(result >= 1);
    }
    
    /**
     * Test of setTileIndex method, of class Tile.
     */
    @Test
    public void testSetTileIndex() {
        System.out.println("setTileIndex");
        int tileIndex = 1;
        instance.setTileIndex(tileIndex);
        assertTrue(tileIndex >= 1 && instance.getTileIndex() == tileIndex);
    }
    
}
