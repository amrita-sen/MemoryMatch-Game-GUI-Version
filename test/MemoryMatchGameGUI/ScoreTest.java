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
 * This class tests some methods of the Score class.
 * @author AmritaSen 18048443
 */
public class ScoreTest {
    private Score instance;
    private int testNumberOfMatches;
    private int testNumberOfMisses;
    private int testPlayerScore;
    
    @Before
    public void setUp() {
        instance = new Score();
        testNumberOfMatches = 0;
        testNumberOfMisses = 0;
        testPlayerScore = 0;
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of handleMisses method, of class Score.
     */
    @Test
    public void testHandleMisses() {
        System.out.println("handleMisses");
        
        int expectedNumberOfMisses = testNumberOfMisses + 1;
        int expectedPlayerScore = testPlayerScore - instance.getPointsLostPerMiss();
        instance.handleMisses();
        
        assertTrue(instance.getNumOfMisses() == expectedNumberOfMisses && instance.getPlayerScore() == expectedPlayerScore);
    }

    /**
     * Test of handleMatches method, of class Score.
     */
    @Test
    public void testHandleMatches() {
        System.out.println("handleMatches");
        int expectedNumberOfMatches = testNumberOfMatches + 1;
        int expectedPlayerScore = testPlayerScore + instance.getPointsScoredPerMatch();
        instance.handleMatches();
        
        assertTrue(instance.getNumOfMatches() == expectedNumberOfMatches && instance.getPlayerScore() == expectedPlayerScore);

    }

    /**
     * Test of resetCurrentScore method, of class Score.
     */
    @Test
    public void testResetCurrentScore() {
        System.out.println("resetCurrentScore");
        instance.setNumOfMatches(10);
        instance.setNumOfMisses(10);
        instance.setPlayerScore(10);
        
        if(instance.getNumOfMatches() == 10 && instance.getNumOfMisses() == 10 &&  instance.getPlayerScore() == 10)
        {
            instance.resetCurrentScore();
        }
       
        assertTrue(instance.getNumOfMatches() == 0 && instance.getNumOfMisses() == 0 && instance.getPlayerScore() == 0);
    }
}
