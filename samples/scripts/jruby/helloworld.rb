world = blurp.createImageSprite "hello-world.png"

while true do
    world.x -= 2 if keyboard.isKeyPressed(Key_Left)
    world.x += 2 if keyboard.isKeyPressed(Key_Right)
    world.y += 2 if keyboard.isKeyPressed(Key_Up)
    world.y -= 2 if keyboard.isKeyPressed(Key_Down)
    blurp.blurpify
end
