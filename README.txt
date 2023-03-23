=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: 6039222
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- The four concepts used.

  1. 2D Arrays - The standard 2048 board is a 4x4 board, so I used a 4x4 2D array that saved
  the location of the blocks as well as the value of each block. Initially, the value of each
  block is 0 and the 0 is not shown on the board. There would be a randomly generated 2 block
  that is also on the board. After each arrow key press, if the blocks moved, another random
  2 would be generated, and if equal values were next to each other after the arrow key press
  they would merge. If the blocks did not move, no random 2 should be generated.
  * I changed from the previous project proposal to only use iteration and not use recursion
  while implementing the moves. The new concept I instead incorporated is File I/O.

  2. File I/O - I implemented File I/O by loading and unloading the game. I saved all my data in
  my StoredData.csv file so that each time the Save button is pressed, the data in my game is
  stored into that file. When the load data is pressed, even if the game was exited then reentered,
  the game can be loaded from the last save instance. The data that was saved was the board, all
  moves, current move, all high scores, current high score, all scores, and current score.

  3. Collection - I use linked lists to store all the moves of every game so that I can undo the moves if
  needed. The moves need to be ordered and the values can be duplicated, so Linked Lists would be the best
  type of Collection. I can not store it with an array because there's technically an unlimited amount of moves
  so there's no size that I can define for the array. I can undo continuously . I also use linked lists to store all the current scores and high scores
  so that when I undo the moves, the scores also undo at the same time.

  4. JUnit tests - I tested different functions that was implemented in my code. First, I checked
  my win and loss conditions. I win when I have a tile that's either 2048 or higher, I lost when
  all the tiles are full and I can't merge two blocks together. These conditions are shown at the
  bottom of the board. My second test checks the score at different states of the game and I also
  check if it works with the undo function. My third test tests whether the board reset function
  resets the board to its initial values. My fifth test tests whether my generate random works at
  different instances, and my sixth test tests my undo function through different edge cases.

=========================
=: Your Implementation :=
=========================

- Overview of the claases in my code
  GameBoard - define button functions and changes how the board looks when needed. Connected
  RunTwentyFortyEight class with TwentyFortyEight class.
  RunTwentyFortyEight - adds buttons onto the board, starts the game.
  TwentyFortyEight - defines the functions needed when an arroow key or button is pressed.
  GameTest - tests edge cases and functions in TwentyFortyEight


- Stumbling blocks
  I was stuck specifically on Collections and File I/O. For collections, at one point my board's
  undo functions wasn't working because it would undo all the way work correctly the first time.
  Then when you play some more and undo my board, it would undo incorrectly. The board that I had
  without undoing would appear at the end. I think it was just some parameter/adding mistake.
  I also couldn't figure out which class I needed to implement my load and save functions in.


- Evaluation of design.
  I do think I have a good separation of functionality amongst my classes. You can't access
  any variables outside those classes, but the values of the variables can be accessed
  through various get methods. If given the chance, I would figure out how to make the
  variables in TwentyFortyEight non static (I needed it for save and load).



========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

  n/a
