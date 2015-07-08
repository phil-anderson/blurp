# Mile-High View of Blurp

This document is a **WORK IN PROGRESS** - There's a ton of stuff that hasn't been written up yet.

## Usage
    java -jar blurp.jar [Options...] script-name

        script-name    : The filename of the script to run, or in the case of Java, the fully qualified name of the class
                         to run, which must extend BlurpJavaProgram
        -language (-l) : Language may be either "Java" or the name of a JVM scripting language. If omitted, Blurp will
                         infer it from the filename
        -width (-w)    : The width in pixels of the window that Blurp will run your script in (default: 800)
        -height (-h)   : The height in pixels of the window that Blurp will run your script in (default: 600)

The following shortcuts are available whilst Blurp is running...

  * **Ctrl + P** Pause script
  * **Ctrl + S** Stop script
  * **Ctrl + R** Reload and restart script (Ctrl + F5 also works)
  * **Ctrl + Q** Quit Blurp (Ctrl + F4 also works)
  * **Ctrl + D** Toggle debug mode
  * **Ctrl + L** Clear console
  * **Ctrl + C** Copy console contents to clipboard

Note that **Ctrl + R** reloads the script from file, so it will include any changes you have made.

## General
Blurp provides several global objects (or properties if you're using Blurp from Java ) that can be used to build games
and programs. Often they represent real things like the keyboard, the mouse or the screen.

Sometimes they are used to create other Blurp objects that you can use to build your game or program like sprites and
special effects

Objects in Blurp usually have public properties that you can change. Changing these will have an effect. For example...
    myCoolSprite.x = myCoolSprite.x + 10

Will cause the sprite to appear 10 pixels to the right.

In addition, the objects' properties also have setter methods. These do the same thing, but because they return the
original object, they can be used for chaining. Also, additional helpful setter methods are included like setPosition,
which allows you to set both x and y more easily.
Here's an example of chaining setters...

    myCoolSprite.setPosition(100,100).setAngle(45).setScale(1.5)

## Updating the Screen
Arguably the most important method in the whole of Blurp is screen.update(). You can change things around as much as
you like, and nothing will happen on teh screen until you call this method.

Typically, you'd call it in a loop in order to do smooth animation. This is such a common thing that we made
screen.update() return true, so you can put it right there in your loop.
Here's a typical blurp Ruby program that does some animation...

    world = $resources.create_image_sprite "hello-world.png"

    while screen.update do
        world.rotate_by(1);
    }

The screen.update() method is intentionally designed to wait until your computer screen has actually displayed
everything before exiting. This has two key benefits...
    1. It makes animation rock-steady
    2. It imposes a fixed frame-rate on your game

That second point is a big win - Handling varying framerates in games is really complicated, and definitely not
something you want ot be doing whn you're first starting out as a game-writing prodigy!

It also means that your Blurp program will run at the same speed on any computer that can run it.

If you write a REALLY intensive, processor-hungry Golliath of a program, and then try to run it on a computer that's not
powerful enough for it, then Blurp will handle it but the program will run slower than expected.

## Polling vs Event-Driven
A quick word on this important subject. If you're new to programming, you can skip this section and come back to it
another time.

Blurp lets you write games in polling or event-driven styles. You can even mix-and-match both approaches in the same
program. It will handle it all just fine and make sure that everything works correctly.

If teh program you're writing is a game, then it's strongly recommended that you favour polling over events for your
main game code.
At first glance it looks like it'll be much easier to write a game in Blurp using event-driven code, but experience
shows that using event-driven programming for games quickly ends up being a bit of a tangled mess that's hard to work
with.

That said, it's fantastic for UI's etc, so go ahead and use it for menu screens etc if you like. In fact, go ahead and
try it for a game if you want, just don;t say I didn't warn you!

## The Global Objects

### resources ($resources)
The resources object lets you create things like sprites that you'll use for your game or program. Often these will use
files that you've copied into your project folder. For example, you can use the resources object to create a sprite
from an image file which you can then control and change in all sorts of ways, just like the example above.

Currently, resources will let you create ImageSprites, TextSprites and AnimationSprites. As well as all the things you
can do with any sprite, each of these have extra methods that are special to them. For example you can change the text
of a TextSprite or play the animation of an AnimationSprite.

See com.bigcustard.blurp.model.Resources
### keyboard ($keyboard)
The keyboard object lets you find information about the whether keys are pressed or not, whether they've just been
pressed and what letters have been typed.

It also allows you to provide event handlers for all those things too.

Here's some typical keyboard code in Ruby that allows teh user to control a spaceship

    spaceship.x -= 2 if keyboard.Left.is_pressed
    spaceship.x += 2 if keyboard.Right.is_pressed

Note that is_pressed will return true all the time the key is pressed, not just when it is first pressed, so holding
down the Left or Right keys will make the spaceship move smoothly and continuously

*See com.bigcustard.blurp.model.Keyboard*

### mouse ($mouse)
The mouse object does the same sort of thing the keyboard object does, but for - yes, you guessed it - the mouse!

You can check the state of the buttons and the position of the mouse.

*See com.bigcustard.blurp.model.Mouse*

### effects ($effects)
You can use the effects object to define special effects that you can subsequently apply to sprites or the camera.

An effect is a change that happens over time, so you can start one running, then more-or-less forget about it and it'll
keep running.

You have loads of control over the effect - how long it should last, the style of the change (i.e. the easing function),
whether it should
whether it should play once, many times, back-and-forth etc. etc.

Multiple effects can be combined - either in parallel or in sequence - and the resulting combined effects can then be
combined into even more complex effects.

It's fair to say that some quite cool things can be done with effects. Take some time to have a play around with them.

*See com.bigcustard.blurp.model.effects.Effects, com.bigcustard.blurp.model.constants.EffectStyle,
com.bigcustard.blurp.model.constants.ExistingEffectStrategy*

### camera ($camera)
By default, your Blurp program runs in an 800 by 600 window. setting teh position of a sprite to 0, 0 will place it at
the bottom-left of the screen, and likewise setting it to 800, 600 will place it at the top-right.

There's actually nothing to stop you placing it at 1000, 1000. It may not be shown on the screen, but Blurp knows it's
there.

Now... Think of a virtual camera looking at the Blurp world you're creating. It's position is at 400, 300 and it's
zoom level is 1. In this state, it can see a rectangle of 800 by 600 pixels.

Here's the thing though, you can move the camera! If we gradually moved it diagonally up and left, we'd see the sprite
that we
placed at 1000, 1000 come into view.

See how that works?

We could even use an effect to smoothly move the camera to 1000, 1000 and it would do exactly that, ending up exactly
centered on the sprite we placed there. Cool eh?

Not only that, but we can also zoom the camera in and out, and even rotate it! It's like being a hollywood cameraman!

*See com.bigcustard.blurp.model.Camera*

### screen ($screen)
As has already been mentioned, the screen object is the home of the all-important update() method - Arguably the beating
heart of any Blurp program.

You can also use screen to set a background colour and set the window title text.

Additionally, the screen object is used to access the viewport object, which is too advanced a topic for this document,
but in a nutshell controls the width and height of the Blurp world that is displayed in the Blurp window.

Combining control of the viewport, with control of the camera can be quite complicated, but can lead to some
impressive results.

### timer ($timer)
The timer can be used to run bits of code in a scheduled manner. You can tell it to do something after a certain amount
of time, or to do something at a regular interval (e.g. every three seconds)

The timer object also gives you access to the stopwatch object which can be used just like a real stopwatch

*See com.bigcustard.blurp.model.Timer, com.bigcustard.blurp.model.Stopwatch*

### utils ($utils)
This is a selection of miscellaneous methods that might be useful in your Blurp programs.

Personally I find the methods for generating random numbers and random colours come are particularly handy.

*See com.bigcustard.blurp.model.Utils*

### system ($system)


### console ($console)
The console is really a debugging tool. You can use it to display text. Additionally, everything in Blurp has a
reasonable toString() implementation, so using the console to print them can provide useful information.

Regardless of what's happening in your game screen - even if you are panning, zooming and rotating the camera so that
the whole screen is whirling vertiginously around - the console remains absolutely solid, and is always displayed
on-top of any other content.

You can specify a colour and transparency for the console that subsequent text will be printed in.

*See com.bigcustard.blurp.model.Console*

## Sprites
### General
### Layers
### Collision Detection
### Events

## Layers

## The Viewport


# Todo List
* AnimationSprite.dispose needs to deregister texture usages.
* GLITCH - TextSprites have jaggies when tinted, and rendered on non-white background. Rebuild png with extra padding
* BUG - File selector: Selecting class file doesn't run it. Needs changing to a fully qualified class. Hmmmm...
* Ruby doesn't handle field remapping - method set_scale_x => setScaleX, but field scaleX !=> scale_x
* Need a Ruby wrapper that will check for camel-case properties if snake-case not found.
* Need a Ruby wrapper that will check for vanilla properties if ! or ? not found.
* Add sanity checks to model method parameters?
* Warm up script engine (mainly for JRuby)
* Look at gradle build, and add a task to create an executable jar
* Almost all examples need redoing / refactoring
* Documentation

* [DV] Image filename syntax for defining regions "spritesheet.png:x:y:w:h"
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


