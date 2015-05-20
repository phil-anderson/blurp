var spin = effects.rotateBy(4 * 360).effectStyle(SmoothStop).duration(2);
var shrinkAndFade = effects.combine(effects.scaleTo(0), effects.alpha(0));

for(var x = 0; x < 8; x++) {
    for(var y = 0; y < 6; y++) {
        makeWorld(50 + x * 100, 50 + y * 100);
    }
}

while(true) {
    blurp.blurpify();
}

function makeWorld(x, y) {

    return blurp.createImageSprite("hello-world.png")
                .collisionShape(CenterCircle)
                .scale(0.33)
                .position(x, y)
                .whenMouseEnters(spin)
                .whenClicked(remove);
}

function remove(sprite) {

    sprite.runEffectThenRemove(shrinkAndFade);
}
