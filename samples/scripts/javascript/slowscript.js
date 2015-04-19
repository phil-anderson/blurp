world = blurp.imageSprite("hello-world.png");

while(true) {

    // Delay long enough for a non-rendering frame to go through.
    world.x = utils.wave(150, 650, 2500);
    for(var i = 0; i < 30000; i++) {Math.sin(i)}
    blurp.blurpify();
}
