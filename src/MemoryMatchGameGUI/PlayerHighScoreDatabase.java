/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryMatchGameGUI;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * This class represents an embedded database object to store a player related data. It contains methods to create a table 
 * to store the game date, the player's username and the player's high score. It addition, the class has methods to insert 
 * data, update data, check if a specified table exists in the database, if a specified username currently exists and if a 
 * current player's current score is higher than the one that exists in the database. There are also methods to retrieve a 
 * specified existing player's game date hi,high score, and ten records of players with the highest score in descending order 
 * of scores from the specified table. SQL statements are used in the methods to interact with the database. An instance of 
 * this class is stored in the TileBoardMemoryGameGUI class to enhance the player interaction and assist with the functionality
 * of "View Leadership" button.
 * 
 * @author AmritaSen 18048443
 */
public class PlayerHighScoreDatabase {
    
    private Connection conn = null;
    private String url = "jdbc:derby:PlayerHighScoresDB; create=true";  //url of the DB host.
    private String tableName = "HIGH_SCORE_RECORDS";  // table to store high score data.
    private Statement statement;
    
    /**
     * This public method sets up the database, calls the checkTableExisting method with the specified table name as a parameter
     * and if the table doesn't exist, creates a table based on the SQL statement specified.     * 
     */
    public void dbsetup() {
        try {
            conn = DriverManager.getConnection(url);
            statement = conn.createStatement();

            // SQL create table statment
            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (GAME_DATE DATE NOT NULL,  USERNAME VARCHAR(10) NOT NULL, "
                                        + "TOTAL_SCORE INTEGER NOT NULL, PRIMARY KEY(USERNAME))");
                
                System.out.println("Table \"" + tableName +"\" created.");
            }
            
            statement.close();

        } catch (Throwable e) {
            System.out.println("error" + e);
        }
    }    
    
    /**
     * This private method is called by dbsetup() method. It takes in as a parameter, a String object that represents the name 
     * of a table and checks if the table exists in the database using SQL statements. A true is returned if the specified table
     *  exits. Else, a false is returned.
     * @param newTableName
     * @return flag, of type boolean.
     */
    private boolean checkTableExisting(String newTableName) {
    boolean flag = false;
    
    try {
        System.out.println("check existing tables.... ");
        String[] types = {"TABLE"};
        DatabaseMetaData dbmd = conn.getMetaData();
        ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);//types);

        while (rsDBMeta.next()) {
        String tableName = rsDBMeta.getString("TABLE_NAME");

        if (tableName.compareToIgnoreCase(newTableName) == 0) {
        System.out.println(tableName + "  is there");
        flag = true;
    }
    }
    
    if (rsDBMeta != null) {
        rsDBMeta.close();
    }
    } catch (SQLException ex) {
    }
        return flag;
    }
    
    /**
     * This public method takes in as a parameter, a String object representing the username of a player and checks if 
     * it exists in the specified table using SQL statements. It return a boolean true if the username exists. Else, it 
     * returns false.
     * @param username
     * @return flag, of type boolean.
     */
    public boolean dbCheckIfUsernameExists(String username)
    {
        boolean flag = false;
        String foundUsername = "";
        
        try{
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT USERNAME FROM " + tableName);

            while(rs.next()) 
            {   
                if(rs.getString(1).equals(username))
                {
                    flag = true;
                    foundUsername = rs.getString(1);
                } 
            } 
            
            if(!foundUsername.isEmpty())
            {
                System.out.println("This username exists in the database.");
            }
            else
            {
                System.out.println("This username does not exist in the database.");
            }           
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerHighScoreDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;        
    }
    
    /**
     * This public method takes in as a parameter, a Player object representing the current player playing the game and 
     * inserts the specified player's game date, username, and score as values for date, username and score in the specified 
     * table, using SQL statements. This method is called in the TileBoardMemoryGameGUI class after a new player
     * manages to match all tiles on the tile board and completes the game.
     * @param aPlayer 
     */
    public void dbInsertData(Player aPlayer) 
    {
        try {            
            long millis = System.currentTimeMillis();  
            java.sql.Date date = new java.sql.Date(millis); 
            String username = aPlayer.getUsername();
            int totalScore = aPlayer.getIndividualScore().getPlayerScore();          
            
            // SQL insert statement
            PreparedStatement ps2 = conn.prepareStatement("INSERT INTO " + tableName + 
                                                          "(GAME_DATE, USERNAME, TOTAL_SCORE) VALUES(?, ?, ?)");
            // Set the variable values
            ps2.setDate(1, date);
            ps2.setString(2, username);
            ps2.setInt(3, totalScore); 
            
            // Execute and then close ps2. 
            ps2.executeUpdate(); 
            System.out.println("New player username, game date and score added to the database");
            ps2.close();

        } catch (Throwable e) {
            System.out.println("error" + e);
        }
    } 
    
    /**
     * This public method takes in as a parameter, a Player object representing the current player playing the game and 
     * updates the values in the specified table with the player's game date, and score using specified SQL statements
     * This method is called in the TileBoardMemoryGameGUI class after an existing player manages to match all tiles on
     * on the tile board, completes the game, and gets a score higher than the one existing in the database table against
     * the player's username.
     * @param aPlayer 
     */
    public void dbUpdateDateAndScore(Player aPlayer) 
    {
        try {            
            long millis = System.currentTimeMillis();              
            java.sql.Date date = new java.sql.Date(millis); 
            String username = aPlayer.getUsername();
            int totalScore = aPlayer.getIndividualScore().getPlayerScore();
            
            // SQL update statment
            PreparedStatement ps2 = conn.prepareStatement("UPDATE " + tableName + 
                                                          " SET GAME_DATE = ?, TOTAL_SCORE = ? WHERE USERNAME = '" + username + "'");
            // Set the variable values
            ps2.setDate(1, date);
            ps2.setInt(2, totalScore);
            
            // Execute and then close ps2.
            ps2.executeUpdate(); 
            System.out.println("Player high score and date in the database updated with the player's current score and date.");
            ps2.close();

        } catch (Throwable e) {
            System.out.println("error" + e);
        }
    } 
    
    /**
     * This public method takes in as a parameter, a Player object representing the current player who exists in the database and 
     * checks if the player's current score is higher than the score against the player's username in the database using specified 
     * SQL statements. This method is called in the TileBoardMemoryGameGUI class after an existing player manages to match all tiles on
     * on the tile board, completes the game. It returns a true if the player's score is higher than the score in the database. Else,
     * it returns false.
     * @param aPlayer
     * @return isCurrentScoreHigher, a boolean
     */
    public boolean dbIsCurrentScoreHigherThanRecord(Player aPlayer)
    {
        boolean isCurrentScoreHigher = false;
        int currentScore = 0;
        int scoreInDatabase = 0;
        
        try
        {
            currentScore = aPlayer.getIndividualScore().getPlayerScore();
            scoreInDatabase = 0;
            String username = aPlayer.getUsername();
            statement = conn.createStatement();
            
            // SQL Select statement
            ResultSet rs = statement.executeQuery("SELECT TOTAL_SCORE FROM " + tableName + " WHERE USERNAME = '" + username + "'");

            if(rs.next())
            {
                scoreInDatabase = rs.getInt(1);
            }           
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerHighScoreDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(currentScore > scoreInDatabase)
                {
                    isCurrentScoreHigher = true;
                } 
        
        return isCurrentScoreHigher;        
    }
    
    /**
     * This public method takes in as a parameter, a Player object representing the current player who exists in the database and 
     * retrieves the player's last high score on record using the specified SQL query statement. This method is called in the  
     * TileBoardMemoryGameGUI to display the information after a player whose username exists in the database enters the username 
     * when prompted.
     * @param aPlayer
     * @return playerHighScore, an int value.
     */
    public int dbRetrievePlayerHighScore(Player aPlayer)
    {
        int playerHighScore = 0;
        
        try
        {
            String username = aPlayer.getUsername();
            statement = conn.createStatement();
            
            // SQL Select statement
            ResultSet rs = statement.executeQuery("SELECT TOTAL_SCORE FROM " + tableName + " WHERE USERNAME = '" + username + "'");
            
             // Assign the value stored in the GAME_DATE column for the specified username to the local int variable playerHighScore.
            if(rs.next())
            {
                playerHighScore = rs.getInt(1);
                System.out.println("Last high score: " + playerHighScore);
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerHighScoreDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return playerHighScore;        
    }
    
    /**
     * This public method takes in as a parameter, a Player object representing the current player who exists in the database and 
     * retrieves the player's date against the high score on record using the specified SQL query statement. This method is called
     * in the TileBoardMemoryGameGUI to display the information after a player whose username exists in the database enters the  
     * username when prompted.
     * @param aPlayer
     * @return date
     */
    public java.sql.Date dbRetrievePlayerGameDate(Player aPlayer)
    {
        long millis = System.currentTimeMillis();  
        java.sql.Date date = new java.sql.Date(millis); 
        
        try
        {
            String username = aPlayer.getUsername();
            statement = conn.createStatement();
            
            //SQL select statement
            ResultSet rs = statement.executeQuery("SELECT GAME_DATE FROM " + tableName + " WHERE USERNAME = '" + username + "'");
            
            // Assign the value stored in the GAME_DATE column for the specified username to the local sql Date variable date.
            if(rs.next())
            {
                date = rs.getDate(1);
                System.out.println("Last game date: " + date);
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerHighScoreDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return date;        
    }
    
    
     /**
     * This public method retrieves the first ten records from the HIGH_SCORES table using a SQL query statement and returns the information
     * in descending order of the scores as a JTable object. This method is called when the player mouse clicks the "View Leadership" button.
     * @return resultTable, a JTable
     */
    public JTable dbRetrieveTopTenRecords()
    {        
        int index = 0;
        JTable resultTable = null;
        String username = "";
        int playerHighScore = 0;        
        
        try
        {    
            statement = conn.createStatement();
            
            // SQL select statement
            ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName + " ORDER BY TOTAL_SCORE DESC FETCH FIRST 10 ROWS ONLY");
            Object[][] rowData = {};
            Object[] columnNames = {"Rank","Player","Game Date","High Score"};
            DefaultTableModel listTableModel = new DefaultTableModel(rowData, columnNames);           
            
            while(rs.next())
            {
                index++;
                java.sql.Date gameDate = rs.getDate(1);
                username = rs.getString(2);              
                playerHighScore = rs.getInt(3);        
               
                listTableModel.addRow(new Object[]{index, username,gameDate,playerHighScore});        
            }
            
            resultTable = new JTable(listTableModel);   
            resultTable.setEnabled(false);    
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerHighScoreDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultTable;
    }     
}
