# blurp
Beginner's game rendering engine built on libgdx

WORK IN PROGRESS - CHECK BACK LATER

*TODO*
* Sprite copying / prototyping
* Don't like the Viewport being passed in. We should change it.
* Have two stages, one for HUD. Sprites can be drawn on HUD layer instead. Useful for things like score.
* More effect samples
* Add Sprite methods queueEffect? loopEffect?
* Allow control over zOrder (relative methods like Sprite.putBehind(otherSprite))
* Provide a proper dispose() method which cleans everything out
* Make script dispose() and then restart at end after a tap
* Sprite mouse left and right clicks
* World size?
* Background drawing
* Debug grid overlay
* Look at gradle build
* Ditch Android (for now)
* Shape Sprites (line, square, rect, circle, triangle, n-agon)
* Sprite copying (allows prototyping)
* Add alpha to console
* Layers - Background / Screen / HUD -> Operations can select which to work on?
* Language Changes
*   God class a-la gdx?
*   createSprite -> Determines type from parameter. Means image file name one
* Move Viewport out of configuration and into BlurpStore.
* Have interfaces for properties like IPosition, IScale, IRotation. Then anything that has those can be used as a value in a setter. Mad idea?
* Ditch Android (for now)
* Shape Sprites (line, square, rect, circle, triangle, n-agon)
* Sprite copying (allows prototyping)
* Add alpha to console
* Layers - Background / Screen / HUD -> Operations can select which to work on?  Score when using camera.
* Language Changes?
*   God class a-la gdx?
*   createSprite -> Determines type from parameter. Means image file name one?
* Move Viewport out of configuration and into BlurpStore.
* Have interfaces for properties like IPosition, IScale, IRotation. Then anything that has those can be used as a value in a setter. Mad idea?
* Documentation - Move Javadoc out and turn it into Markdown documentation.


