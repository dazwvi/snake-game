-----------------------
Development environment
-----------------------
Java: Java(TM) SE Runtime Environment
Java Version: 1.8.0_91
OS Name: Mac OS X
OS Version: 10.11.4
OS Architecture: x86_64
Total Memory (MB): 61
Max Memory (MB): 910

----------------------
Command Line Arguments
----------------------
Compile: javac Main.java Snake.java SplashScreen.java StartMenu.java GameBoard.java Dot.java
Run the game: java main <fps> <speed> (The range of fps is 20 to 65, and the range of speed is 1 to 10)

Makefile: make - compile all java files
          make run - run the program with fps=25 and speed=4

--------------
Overall Design
--------------
Main: taking arguments from the console and running the program.
SplashScreen: Jframe to control all panels on it.
StartMenu: the first panel shown after lunching the game, description of the game, my name, userid and etc.
GameBoard: the game is drawing on this Jpanel with all functions and event-handlers for playing.
Dot: coordinate class for position.
Snake.java: define the snake object and its functions. e.g move, is_food, is_collision.

-------------
Game Controls
-------------
Start: click the play icon on the SplashScreen
Move: the arrow keys
Pause/Resume: space
Restart: R

------------
Enhancements
------------
1. Adding sound effect when the snack eats food (eat.wav) or dies (gameover.wav).
2. Using texture graphics for the splash screen (background.jpg), the snake (snake.png) and the food (apple.png).