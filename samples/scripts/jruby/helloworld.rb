world = $resources.create_image_sprite "hello-world.png"

while $screen.update do

    world.x -= 2 if $keyboard.Left.is_pressed
    world.x += 2 if $keyboard.Right.is_pressed
    world.y += 2 if $keyboard.Up.is_pressed
    world.y -= 2 if $keyboard.Down.is_pressed

    world.angle += 2 if $keyboard.Space.is_pressed

    if $keyboard.Space.was_just_released
        world.run_effect($effects.rotate_to(0).with_style(Elastic_Stop))
    end
end
