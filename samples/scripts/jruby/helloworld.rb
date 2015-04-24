world = blurp.imageSprite "hello-world.png"

while true do
    world.x -= 2 if keyboard.isKeyPressed(keys.LEFT)
    world.x += 2 if keyboard.isKeyPressed(keys.RIGHT)
    world.y += 2 if keyboard.isKeyPressed(keys.UP)
    world.y -= 2 if keyboard.isKeyPressed(keys.DOWN)
    blurp.blurpify
end
