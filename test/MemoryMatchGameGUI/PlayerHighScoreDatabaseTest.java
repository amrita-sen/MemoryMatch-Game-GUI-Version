/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryMatchGameGUI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests some methods of the PlayerHighScoreDatabase class.
 * @author AmritaSen 18048443
 */
public class PlayerHighScoreDatabaseTest {
    
    private PlayerHighScoreDatabase instance;
    private Player testPlayer;
    
    @Before
    public void setUp() {
        instance = new PlayerHighScoreDatabase();
        instance.dbsetup();
        testPlayer = new Player("test1");
    }
    
    @After
    public void tearDown() {
        instance = null;
        testPlayer = null;
    }


    /**
     * Test of dbCheckIfUsernameExists method, of class PlayerHighScoreDatabase.
     */
    @Test
    public void testDbCheckIfUsernameExists() {
        System.out.println("dbCheckIfUsernameExists");
        String username = "test5";
        boolean expResult = false;
        boolean result = instance.dbCheckIfUsernameExists(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of dbIsCurrentScoreHigherThanRecord method, of class PlayerHighScoreDatabase.
     */
    @Test
    public void testDbIsCurrentScoreHigherThanRecord() {
        System.out.println("dbIsCurrentScoreHigherThanRecord");
        testPlayer.setIsNewPlayer(false);
        testPlayer.getIndividualScore().setPlayerScore(200);        
        boolean expResult = true;
        boolean result = instance.dbIsCurrentScoreHigherThanRecord(testPlayer);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of dbRetrievePlayerHighScore method, of class PlayerHighScoreDatabase.
     */
    @Test
    public void testDbRetrievePlayerHighScore() {
        System.out.println("dbRetrievePlayerHighScore");
        Player aPlayer = new Player("test3");
        int expResult = 145;
        int result = instance.dbRetrievePlayerHighScore(aPlayer);
       
        assertEquals(expResult, result);
    }
    
    /**
     * Test of dbUpdateDateAndScore method, of class PlayerHighScoreDatabase.
     */
    @Test
    public void testDbUpdateDateAndScore(){
        System.out.println("dbUpdateDateAndScore");
        testPlayer.getIndividualScore().setPlayerScore(150);        
        instance.dbUpdateDateAndScore(testPlayer);
        
        assertTrue(instance.dbRetrievePlayerHighScore(testPlayer) == 150);
    }

}