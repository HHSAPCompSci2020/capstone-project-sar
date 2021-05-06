Beat the Computer
Revision: 4/25/21
Authors: Shachaf Smith, Ayush Saran, Radhika Agarwal
Description: 
Our game is similar to a classic maze game where the avatar navigates through a maze and obstacles, except the computer controls the avatar and the user controls the obstacles and aims to kill the avatar before it reaches the end of the maze. This game, like any captivating and enjoyable videogame, cures the problem of boredom. We decided to create a computer game anyone can play with ease that requires the skills of AI, GUIs, and also Arrays to create. The goal of the user playing the game is to control the obstacles in the game to hinder, injure, and even hurt/kill the avatar so it doesn’t get to the end of the maze as soon as possible. The game will have one avatar that is computer controlled, and multiple obstacles that the user can use at any point in the game, and the game only ends if the avatar reaches the end, or the user successfully kills the avatar. Afterwards, the user can decide to play the same game or choose a different level. Any person familiar with the basics of videogames and wants to have fun is our perfect audience. The primary features are a maze, that has walls, and empty spaces, an avatar that navigates the maze, a health score, that tracks the health of the avatar, and obstacles. The obstacles are a variety of projectiles that can be used to shoot things at the avatar. They include moving walls, water walls, and a collection of projectiles that deal damage to the avatar. In order to pass a level, you just have to Beat the Computer. 


Instructions

* Hotkeys, Abilities and Obstacles 
   * These abilities will be toggled by the users input upon press and have no definite keybind[a]
   * Abilities and obstacles given to the user serve the purpose of stopping the computer and require strategy and will strive on the player’s ability to press the right keys at the right time
 
* Projectile control
   * The player will need to be able to track and shoot the computer, utilizing a cursor and left-clicking their mouse to use the available projectiles
   * The player’s ability to locate and their precision will be tested against the computer’s high speed and manouveurs [b]
* Menus/Gui Navigation
   * A welcome screen that offers a play button and an instructions button. The play button will redirect the user to the first level of the map, and the instructions menu will allow the user to see our list of instructions
   * A store window allowing the user to buy their abilities and obstacles will be available to them as a pop-up towards the bottom of the screen. It is essential for the user to utilize this menu in order to have enough resources to stop the computer *This implementation is only available if a credit system is coded*
   * An ability/obstacle status bar offers the user a visual representation of their abilities laid out horizontally with their perspective keybinds, allowing for the user to easily know what to click. The status bar also informs the user of the availability of their resources
   * An end screen that marks the completion of the program, popping up at the last developed level. The end screen will either display a victory or defeat and prompt the user to quit






Features List

Must-have Features:
[These are features that we agree you will definitely have by the project due date. A good final project would have all of these completed. At least 5 are required. Each feature should be fully described (at least a few full sentences for each)]
* A computer avatar with ‘lives’
   * This would provide a way for the avatar to be ‘injured’ and ‘die’.  When the avatar reaches zero lives, the avatar ‘dies’ and the player wins. If the avatar manages to make its way through the maze with at least one life remaining, the player loses.
* A computer avatar that solves at least one maze we create
   * We would create a maze similar to what we had to solve in the 2D arrays lab. This algorithm would find a path for the computer to go through the maze. It would also have to find paths to updated mazes, as the maze can be changed by the player with obstacles. 
* An obstacle to block the computer avatar
   * We would start with a standard obstacle that blocks the avatar and slows it down (the water wall). While the avatar is passing through this wall, it has decreased speed. 
* A projectile to be fired at the computer avatar
   * We would begin with a standard projectile that would be fired at the avatar. [c]This projectile would deal a set amount of damage to the avatar, decreasing its ‘lives’.
* Functional Graphic User Interface
   * We would create the visual component of the game, complete with art assets and the interactive components. 


Want-to-have Features:
[These are features that you would like to have by the project due date, but you’re unsure whether you’ll hit all of them. A good final project would have perhaps half of these completed. At least 5 are required. Again, fully describe each.]
*  Two additional maze levels
   * These two maze levels would be more difficult to solve than the base level. In addition, there would be more space for customization by the player. This means more places where obstacles and projectile launchers can be placed.
*  A computer avatar that can solve these maze levels[d]
   * More sophisticated mazes means that the pathfinding algorithm would need to be updated. It would also need to be able to find ways around or through the additional obstacles added as part of the “Want-to-have” features.
*  An additional obstacle that can hinder the avatar from moving through the maze
   * This obstacle would be a moving wall. [e]Unlike the water wall, computer avatar would not be able to pass through this wall when it is blocking the path. Instead, it would need to wait until the wall moved out of the way. [f]
*  An additional projectile with different mechanisms
   * The projectile would be a fire arrow. The fire arrow has a different graphic element, and it deals more damage to the avatar.
*  A currency system to buy projectiles and obstacles
   * In this way, there would not be an unlimited amount of obstacles and projectiles that the player can utilize.[g] The currency would be earned when damage is dealt to the avatar, and can be used to buy obstacles and projectiles. 


Stretch Features:
[These are features that we agree a fully complete version of this program would have, but that you probably will not have time to implement. A good final project does not necessarily need to have any of these completed at all. At least 3 are required. Again, fully describe each.]
*  Points system
   * The game would have a final point system. Points are inversely proportional to the time spent until winning (the faster the better). They are, however, directly proportional to how much currency you have remaining at the end of the level. 
*  Artifacts
   * In addition to dealing damage to the avatar, you would also try to destroy certain computer artifacts in the maze. This is not needed in order to finish the level. However, artifacts yield many level points. 
*  An additional projectile with different mechanisms
   * The projectile would be a poison arrow, and it would deal a significant amount of damage over time. This arrow has different graphic elements than the other projectiles. 






Class List
* Setup and User Controls Package
   * Drawing Surface: Creates Drawing Surface for game
   * User Controls: Manages ability to use keyboard and mouse for certain actions
   * Levels: Manages current level
   * Grid Template: Sets Up Maze
   * Main: Runs Game
* Computer Player Package
   * Avatar: Represents the computer avatar and its visual and game components. Also manages the avatar’s life.
   * Maze:  Manages the maze solving component of the computer player
* Obstacle Package
   * Water Wall: Represents a water wall - the avatar slows down when passing through
   * Moving Wall: Represents a moving wall. The avatar cannot pass through this wall
* Projectile Package
   * Standard Projectile: Represents a standard projectile
   * Fire Arrow: Represents a fire arrow - deals more damage than a standard projectile
   * Poison Arrow: Represents a poison arrow - deals damage over time




Credit List
* Radhika Agarwal
   * Coded Computer Player Package
* Shachaf Smith
   * Coded Setup and User Controls Package
   * Coded Obstacle Package
   * Compiled art assets
* Ayush Saran
   * Coded Projectile Package 
   * Created initial Maze Layouts
* Flaticon.com
   * Icons featured in art assets
* Thenounproject.com
   * Additional icons featured in art assets


[a]What is going to be the default keybinds? How many will there be?
[b]How fast is the computer going to traverse the maze? How fast is the player able to shoot projectiles?
[c]Just to clarify, where are the projectiles fired from? Are they shot from specified locations or does the user just click on the avatar to shoot?
[d]This doesn't feel like a 'want to have' feature. This seems more like a requirement in order for this game to function. It's more like in order to create your game your computer avatar MUST be able to solve all of your maze levels.
[e]How would this moving wall interact with the avatar if they are colliding?
[f]If the only thing that the moving wall does is force the avatar to wait, doesn't it just effectively slow it down? Wouldn't that be very similar to the jello wall in practice?
[g]If this system is not implemented, are there any other restrictions that stop the player from spamming obstacles and projectiles?