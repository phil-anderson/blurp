effects.defaultDuration = 250;
worldImage = resources.loadImage("hello-world.png");

world1 = resources.createImageSprite(worldImage).setPosition(150, 300)
world2 = resources.createImageSprite(worldImage).setPosition(650, 300)

world1.onCollisionWith(world2, function () {
    world1.runEffect(effects.moveTo(150, 300));
    world2.runEffect(effects.moveTo(650, 300));
})

while(screen.update()) {
    world1.x += 2;
    world2.x -= 2;
}