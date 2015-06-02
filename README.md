# blurp
Beginner's game rendering engine built on libgdx

WORK IN PROGRESS - CHECK BACK LATER

*TODO*
* IMPORTANT: Need to inject MouseWindowChecker when hosted. TELL SPENCE!
* Change everything to milliseconds. Seconds are driving me nuts!
*       Effects, Utils.Rest
* Rename EffectBase to Effect, and Effect to something like AtomicEffect (or maybe even merge with TweenEffect)
* Provide a proper dispose() method which cleans everything out + Make script restart at end after a tap

* [DV?] Don't like the Viewport being passed in. We should change it.
* Almost all examples need redoing / refactoring
* [DV] Sprite mouse left and right clicks
* [DV] Background drawing

* Look at gradle build

* [DV] Shape Sprites (line, square, rect, circle, triangle, n-agon)

* [DV] Consider a SpriteList object that is a Sprite but changes propagate (D3 selector style)
* Language Changes
*   createSprite -> Determines type from parameter. Means image file name one
* Move Viewport out of configuration and into BlurpStore.
* [DV] Have interfaces for properties like IPosition, IScale, IRotation. Then anything that has those can be used as a value in a setter. Mad idea?
* Renaming
*   blurp, blurpify, collisionShape, many others
*   Documentation - Move Javadoc out and turn it into Markdown documentation.


