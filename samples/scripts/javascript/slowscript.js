world = blurp.imageSprite("world.png");

while(true) {

    // Delay long enough for a non-rendering frame to go through.
    world.x = utils.wave(150, 650, 2500);
    for(var i = 0; i < 10000; i++) {Math.sin(i)}
    blurp.blurpify();
}
