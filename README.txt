=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: natjolie
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.
    I changed my game from my proposal.

  1. SubTyping
  I have a bomb class that extends the apple class. It overrides the draw feature
  and the get and set functions of the apple class and implements its own changePosition
  function which changes the placement of the bomb by a random variable.

  2. Complicated Game Logic
  I had to implement several features of the snake game, including my own take which involves
  adding bombs to the grid that randomize their location every few frames and shrinking the snake
  when the snake hits them. These features include: making sure the game ends, resetting, accounting
  for the snake running into itself, running out of bounds, running into an apple or a bomb and growing or
  shrinking, incrementing the score, changing the screens, making sure the bomb and apple are not on the same
  space or the same space as the snakes body and making sure the tiles were not in the corner.

  3. Collections and Maps
  I used a Map to track the space and then the Coordinate for the grid in the game play class and then
  for the snake I used a Map tracking the body index and then the Coordinate of the snake. I used several
  of the Treemap functions to transform the data it stored and used for each loops or for loops to do this.

  4. JUnit Test
  I tested all of the functions of my Snake class to make sure it was inbounds, and colliding with
  the other objects properly as well shrinking and growing. I also tested what would happen depending
  on the size of the snake and if the snake was ending properly.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  Coordinates
  - the coordinate class is the building block of the game. It draws a square and takes in the
  x and y coordinate to draw it.

  Apple
  - this is another building block, takes in the x and y coordinate where it should be drawn and
  draws it in red.

  Snake
  - the snake is a map of Coordinates, starting off with 3 coordinates. The snake game has Strings
   to find what key is being pressed and this updates the string direction. From there
  I have functions, move that adds a Coordinate at the beginning of the Map and shifts the rest down.
  Snake also has functions that take in a bomb or apple and check if the head of the snake is at the
   same location, grows and shrinks the Map and keeps track of if the snake is inbounds.

   Bomb
   Bomb is an extension of Apple and it draws the shape differently and randomizes how long it stays
   in that location. It implements a new function called change location that randomizes the location
   of the bomb and checks its location against the snake and body.

  GamePlay
  GamePlay is where everything comes together. I implement my snake, apple, and bomb. I draw all of them
  and then in my rule class I either shrink or expand the snake depending on its collision. I also keep track
  of the state of the game by seeing if any of the snakes endgame provisions occur in a function called checkHead.
  if it does then it switches to the replay function and I basically toggle between the three different screens,
  startScreen, endScreen or the regular playing one depending on the gameState.

   Screen
   This displays the JComponents, panels and frames. At the top I include my play and replay buttons
   and the bottom keeps track of the score. The middle holds the gameboard. it has to event listeners to check
   if the buttons are hit and either resets the screen or switches off the start screen.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  I thought of my design for a while but with the snake class I had trouble adding, shrinking and moving.
  I originally used the remove function for shrink to take off the last key in the Map and I got a AWT
  out of bounds error so I had to make sure my functions checked to make sure most things were not null
  that I was acessing and ending up recopying the collection to a new collection with one less value. Towards
  the end when implementing the bomb and apple collisions I wasn't sure if I shoudl have the apple class take in a
  snake, a snake class take in an apple, or do everything in the gameboard which is why in the bomb function
  I have change position and I take in five paramters but for the move apple function I do that in the
  game class.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
I think I have good seperation of functionality. I wrote down all the functions I needed before hand
(shrink, grow, collide, reset, startgame, endgame, getpositions, changepositions, etc). However,
if I could do it again I would standardize how I wanted to implement the movement and collisions of objects
that take in another object as parameters. I would also probably make an interface because of the similiarities between
my snake, apple, and bomb class (or at least apple and bomb) rather than do an extension of bomb from apple.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
