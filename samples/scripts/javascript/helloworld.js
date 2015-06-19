screen.backgroundColour = DarkBlue;

world = resources.createTextSprite("Hello World");

while(true) {

    if(keyboard.Left.isPressed()) world.x -= 2;
    if(keyboard.Right.isPressed()) world.x += 2;
    if(keyboard.Up.isPressed()) world.y += 2;
    if(keyboard.Down.isPressed()) world.y -= 2;
    screen.update();
}
