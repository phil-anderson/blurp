# blurp
Beginner's game rendering engine built on libgdx

WORK IN PROGRESS - CHECK BACK LATER

*TODO*
* Program Complete message should be at start of line if following a print and full white
* Go back to being able to run script form a string
* BUG - Once got a malloc invalid checksum when doing a Ctrl+Q
* BUG - Stop and Start unresponsive at end of an errored script - not parse errors though
* Sprite moveAtAngle
* Add sanity checks to model method parameters?
* Warm up script engine (mainly for JRuby)
* Finish adding idiomatic language support
* Reduce size of "error" image
* Sprite.onCollision events
* Look at gradle build, and add a task to create an executable jar
* Reference counting for images?
* Almost all examples need redoing / refactoring
* Documentation

* [DV] Mouse - onMouseMoved, onLeftButtonPressed, onRightButtonPressed, onLeftButtonReleased, onRightButtonReleased
* [DV] NamedColour = Add to colourTag map on create, then user can create named colours too
* [DV] Blurp runtime should be a singleton.
* [DV] Provide a way to disable system shortcuts maybe?
* [DV] Sprite mouse left and right clicks
* [DV] Background drawing
* [DV] Shape Sprites (line, square, rect, circle, triangle, n-agon)
* [DV] Consider a SpriteList object that is a Sprite but changes propagate (D3 selector style)
* [DV] Move Viewport out of configuration and into BlurpStore.
* [DV] Have interfaces for properties like IPosition, IScale, IRotation. Then anything that has those can be used as a value in a setter. Mad idea?


