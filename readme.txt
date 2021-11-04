                          ---------------------------Dungeons of doom readme----------------------------------
PREQUISITES:
System must have a version of JDK installed to run java files

INSTALLATION:
To compile the project use Command Prompt.
Once you have reached the directory of project files, to compile call:

Javac gameLogic.java

java gameLogic

SETUP:
When the game is first run user is prompted to either enter a map file location or leave blank. To enter a file location type the entire file directory 
eg. C:\\Users\\FiercePC\\Documents\\map.txt. double backslashes (\\) should be used due to the way strings are handled in java.  

USER INSTRUCTIONS:
After entering a map file location, the map file stored there will be loaded into the game. If no map is entered the standard default map is used. 
The user and bot are spawned in random locations, they cannot spawn in a wall or in each other, the player cannot spawn on gold. 
The aim of the game is to collect enough gold to reach the exit before the bot catches the player.

When the main game starts the user has several commands that can be entered in this format:
“HELLO” : Displays how much Gold player has left to collect.
“LOOK” : Displays a 5 by 5 grid around the player showing their surrounding area.
“PICKUP” : If the player is over some gold PICKUP will add the gold to the players gold count , if no gold is present the action will fail.
“MOVE N” : Moves the player one space up on the map, will output if the move has succeeded or failed (due to player moving to an invalid location).
“MOVE S” : Moves the player one space down on the map, will output if the move has succeeded or failed (due to player moving to an invalid location).
“MOVE E” : Moves the player one space right on the map, will output if the move has succeeded or failed (due to player moving to an invalid location).
“MOVE W” : Moves the player one space left on the map, will output if the move has succeeded or failed (due to player moving to an invalid location).

The map contains several icons for different objects in the game world
“.” : empty space
“G” : gold
“P” : player
“B” : bot
“E” : exit
“#” : wall

A bot attempts to chase the player around the map, the bot and player take turns to make moves. The bot can LOOK in order to try and find the player in
a 5 by 5 area around it or MOVE. If the bot can see the player it will move towards them, if not it will move randomly. If the bot catches the player before
they reach the exit the game is over.

CONTACT INFO:
Programmer contact email: ac2424@bath.ac.uk

KNOWN BUGS:
Invalid map file locations will crash the code

Bot sometimes walks into walls when making a random move


                                 _A_
                                / | \
                               |.-=-.| GOOD LUCK ADVENTURER!
                               )\_|_/(
                            .=='\   /`==.
                          .'\   (`:')   /`.
                        _/_ |_.-' : `-._|__\_
                       <___>'\    :   / `<___>
                       /  /   >=======<  /  /
                     _/ .'   /  ,-:-.  \/=,'
                    / _/    |__/v^v^v\__) \
                    \(\)     |V^V^V^V^V|\_/
                     (\\     \`---|---'/
                       \\     \-._|_,-/
                        \\     |__|__|
                         \\   <___X___>
                          \\   \..|../
                           \\   \ | /
                            \\  /V|V\
                             \|/  |  \
                              '--' `--`   