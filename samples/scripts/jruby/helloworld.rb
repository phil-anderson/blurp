world = resources.createImageSprite "hello-world.png"

while screen.update do

    world.x -= 2 if keyboard.Left.isPressed
    world.x += 2 if keyboard.Right.isPressed
    world.y += 2 if keyboard.Up.isPressed
    world.y -= 2 if keyboard.Down.isPressed

    if keyboard.Space.wasJustPressed
        world.runEffect(effects.rotateBy(360).style(Elastic_Stop))
    end
end
