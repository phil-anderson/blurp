screen.backgroundColour = DarkBlue

world = resources.createTextSprite("Hello Python")

while True:
#   NB. Cannot write code like world.x =- 2 as Python does not support function overloading
    if keyboard.Left.isPressed(): world.moveTowards(10, 300, 7)
    if keyboard.Right.isPressed(): world.moveTowards(1000, 300, 7)
    if keyboard.Up.isPressed(): world.moveTowards(400, 600, 7)
    if keyboard.Down.isPressed(): world.moveTowards(400, 10, 7)
    screen.update()

