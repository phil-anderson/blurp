# blurp
Beginner's game rendering engine built on libgdx

WORK IN PROGRESS - CHECK BACK LATER

*TODO*
* Keyboard - onKeyDown(), onKeyUp()
* Mouse - onMouseMoved, onLeftButtonPressed, onRightButtonPressed, onLeftButtonReleased, onRightButtonReleased
* NamedColour = Add to colourTag map on create, then user can create named colours too
* Reference counting for images?
* BUG Tall, narrow viewport causes completion message to be truncated.
* Add toString() methods to model objects
* Add display of exceptions to the script complete code
* Add pause and resume methods to runtime - Use a flag in blurpifier (NOT BlurpScreen).
* Blurp runtime should be a singleton.
* IMPORTANT: Need to inject MouseWindowChecker when hosted. TELL SPENCE!
* Look at gradle build, and add a task to create an executable jar
* Review, restructure and lots of renaming required in model API
* Almost all examples need redoing / refactoring
* Documentation

* [DV] Provide a way to disable system shortcuts maybe?
* [DV] Sprite mouse left and right clicks
* [DV] Background drawing
* [DV] Shape Sprites (line, square, rect, circle, triangle, n-agon)
* [DV] Consider a SpriteList object that is a Sprite but changes propagate (D3 selector style)
* [DV] Move Viewport out of configuration and into BlurpStore.
* [DV] Have interfaces for properties like IPosition, IScale, IRotation. Then anything that has those can be used as a value in a setter. Mad idea?


