# blurp
Beginner's game rendering engine built on libgdx

WORK IN PROGRESS - CHECK BACK LATER

*TODO*
* Refactor to get rid of the syncing maps and just use commands for Sprite creation, plus a normal map?
* Rename EffectBase to Effect, and Effect to something like Atomic Effect (or maybe even merge with TweenEffect)
* Sprite copying / prototyping
* Have two stages, one for HUD. Sprites can be drawn on HUD layer instead. Useful for things like score.
* Allow control over zOrder (relative methods like Sprite.putBehind(otherSprite))
* Provide a proper dispose() method which cleans everything out + Make script restart at end after a tap
* World size?
* Add alpha to console - Do it as a single HUD visibility thing.
* Layers - Background / Screen / HUD -> Operations can select which to work on?

* Don't like the Viewport being passed in. We should change it.
* Almost all examples need redoing / refactoring
* [DV] Sprite mouse left and right clicks
* [DV] Background drawing

* Debug grid overlay
* Look at gradle build

* [DV] Shape Sprites (line, square, rect, circle, triangle, n-agon)

* Language Changes
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
* Renaming
*   blurp, blurpify, collisionShape, many others
* Documentation - Move Javadoc out and turn it into Markdown documentation.


