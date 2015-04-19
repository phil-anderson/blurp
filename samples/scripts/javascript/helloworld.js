screen.backgroundColour = blurp.colours.darkBlue;

world = blurp.imageSprite("hello-world.png");

while(true) {

    if(keyboard.isKeyPressed(keys.LEFT)) world.x -= 2;
    if(keyboard.isKeyPressed(keys.RIGHT)) world.x += 2;
    if(keyboard.isKeyPressed(keys.UP)) world.y += 2;
    if(keyboard.isKeyPressed(keys.DOWN)) world.y -= 2;
    blurp.blurpify();
}
