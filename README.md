# blurp
Beginner's game rendering engine built on libgdx

WORK IN PROGRESS - CHECK BACK LATER

*TODO*
* BUG Tall, narrow viewport causes completion message to be truncated.
* Handle Ctrl+C terminate better.
* Add toString() methods to model objects
*     Colour constants should know their name for the toString method (NamedColour extends Colour)
* Key combinations for various control functions (e.g. debug on, quit, restart).
* Add display of exceptions to the script complete code
* Add Stop, pause and resume methods to runtime.
* Blurp runtime should be a singleton.
* IMPORTANT: Need to inject MouseWindowChecker when hosted. TELL SPENCE!
* Look at gradle build, and add a task to create an executable jar
* Review, restructure and lots of renaming required in model API
* Almost all examples need redoing / refactoring
* Documentation

* [DV] Sprite mouse left and right clicks
* [DV] Background drawing
* [DV] Shape Sprites (line, square, rect, circle, triangle, n-agon)
* [DV] Consider a SpriteList object that is a Sprite but changes propagate (D3 selector style)
* [DV] Move Viewport out of configuration and into BlurpStore.
* [DV] Have interfaces for properties like IPosition, IScale, IRotation. Then anything that has those can be used as a value in a setter. Mad idea?


