# MemoryMatch-Game-GUI-Version (Academic Project)

**IDE:** NetBeans 8.2<br/>
**DBMS:** Apache Derby DB

This is a Graphical User Interface (GUI) version of an intellectual, yet fun, tile matching game that is intended to train
concentration, patience, and short-term memory as the player attempts to match tiles on a displayed tile board. The 
program consists of 8 classes and uses a text file to allot each tile a "face-up" and "face-down" image. It also includes an 
embedded database component to store the date, player's username, and score. The top ten high scores for the 
leadership board and the last high score, displayed for an existing player, are retrieved from the database. <br/>

**Goal of the game:** With every tile flip, memorise the location of an image, and eventually match all the tiles on the tile board.<br/>

**Gameplay:**<br/>
**=======**<br/>
1. There will be 20 tiles facing down on the displayed tile board.<br/>
2. In each move, the player can turn any 2 tiles by using the mouse to click on the tile.<br/>
3. When a tile, with a face down image is clicked, it will be flipped and the face up image will be displayed.<br/> 
4. The 2 chosen tiles will be considered a "match" if both have the same face up image.<br/>
5. If the tiles do not match, they will flip again to display their face down image. Matched tiles will not flip back.<br/>
6. The game ends when a player successfully matches all the tiles on the tile board or, if he/she chooses to quit the game.<br/>
7. Button options available to a player during the game:<br/>
   **Play Again:**  This will start a new game. If a current game is in progress, all scores will reset to zero.<br/>
   **Game Instructions & Scoring:** Game instructions and scoring information will be displayed.<br/>
   **Flip All Tiles:**  This will end a current game and flip all the tiles on the tile board to display their face up image.<br/>
   **View Leadership Board:** The top 10 players and their high scores will be displayed.<br/>   
8. The game window can be closed by using the mouse to click on the "X" button at the top-right corner of the window.<br/><br/>

**Scoring:**<br/>
**=======**<br/>
1. Every match, will increase score by 20 points and the "number of matches" by 1.<br/>
2. Every miss will reduce score by 5 points and increase the "number of misses" by 1.<br/>
3. If the "Play Again" button or the "Flip All Tiles" button is clicked when a game is in progress, the current game will end 
and the score will be reset to 0 before a new game can begin.<br/>
4. A player's score will be displayed throughout the game, in the panel above the tile board.<br/>
5. At the end of the game, the player's final score will be displayed and the username, score, and date will saved in the database.<br/>
6. If a player has played the game more than once, the highest score and the date, the game was played on, will be saved.<br/><br/>

**Screenshots:**<br/>
**========**<br/>
Introduction panel and username prompt after the “Yes” option is clicked:<br/>
![image](https://user-images.githubusercontent.com/52112568/69907415-099a7e80-143a-11ea-8ee3-586d2c60e00d.png)
<br/><br/>

Welcome message if input username does not exist in the database and message if it exists:<br/>
![image](https://user-images.githubusercontent.com/52112568/69907220-da820e00-1435-11ea-9f58-222c92a73e59.png)
<br/><br/>

Main game panel after player clicks the “OK” button:<br/>
![image](https://user-images.githubusercontent.com/52112568/69907270-d7d3e880-1436-11ea-9c8e-e797a91c409f.png)
<br/><br/>

After a player clicks 2 tiles – chosen tiles will flip and a message will let the player know if the tiles are a match or not (tiles 
that do not match, flip back). Scores are updated after the player clicks the “OK” button:<br/>
![image](https://user-images.githubusercontent.com/52112568/69907291-5fb9f280-1437-11ea-897c-06cbf78cc6bf.png)
<br/><br/>

After all tiles are matched:<br/>
![image](https://user-images.githubusercontent.com/52112568/69907796-11a9ec80-1441-11ea-85b2-a6e394198cf8.png)
<br/><br/>

Leadership Board:<br/>
![image](https://user-images.githubusercontent.com/52112568/69907518-5df22e00-143b-11ea-9998-14f6a4a0f136.png)
<br/><br/>
