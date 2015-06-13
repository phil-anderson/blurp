screen.backgroundColour = DarkBlue;

world = system.createImageSprite("hello-world.png");

while(true) {

    if(keyboard.isKeyPressed(Key_Left)) world.x -= 2;
    if(keyboard.isKeyPressed(Key_Right)) world.x += 2;
    if(keyboard.isKeyPressed(Key_Up)) world.y += 2;
    if(keyboard.isKeyPressed(Key_Down)) world.y -= 2;
    screen.update();
}
