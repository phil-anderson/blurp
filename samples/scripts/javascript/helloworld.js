screen.backgroundColour = DarkBlue;

world = resources.createImageSprite("hello-world.png");

while(true) {

    if(keyboard.isKeyPressed(Left_Key)) world.x -= 2;
    if(keyboard.isKeyPressed(Right_Key)) world.x += 2;
    if(keyboard.isKeyPressed(Up_Key)) world.y += 2;
    if(keyboard.isKeyPressed(Down_Key)) world.y -= 2;
    screen.update();
    sdfsdfsdf
}
