package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.Colours;
import com.bigcustard.blurp.model.constants.ExistingEffectStrategy;
import com.bigcustard.blurp.model.constants.ScreenLayer;
import com.bigcustard.blurp.model.constants.SpriteEventHandlers;
import com.bigcustard.blurp.model.constants.TargetStyle;
import com.bigcustard.blurp.model.effects.EffectBase;
import com.bigcustard.blurp.model.events.SpriteEventHandler;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/**
 * A sprite is an object that can be put on the screen, moved around, rotated, and generally mucked about with in
 * various ways. For more details, check the subclasses.
 */
@SuppressWarnings("unchecked")
public abstract class Sprite<T> {

    private static final SpriteMouseState NULL_MOUSE_STATE = new SpriteMouseState(false, false, false, false, false,
                                                                                  false, false, false, false, 0, 0, 0);
    /**
     * The position of the sprite, or to be more precise, the position of the <i>center</i> of the sprite.
     * <p>
     * The bottom-left of the world is at x=0, y=0, and the top-right will be determined by the height and width of the
     * world you created. The default world (i.e what you get if you don;t define one yourself) is 800 wide and 600
     * high.
     * <p>
     * You can happily set the coordinates to be outside of the world coordinates, and Blurp won't bat an eyelid.
     * <p>
     * Typically, the whole world is shown on screen, however more advanced programs sometimes use the
     *
     * {@link Camera Camera} class to zoom into an area and pan around the world.
     */
    public double x, y;

    /**
     * The scale of the sprite in the x and y axes. Typically you'd want to set both of these to the same value to
     * make the sprite larger or smaller, which can be done more easily by using the {@link #setScale} convenience method.
     * <p>
     * If you set them individually though, some cool effects can be achieved as only the x or y dimension will be
     * stretched or shrunk. For example, if your sprite was of a rubber ball, then shrinking the y scale a little, and
     * growing the x scale will make it look like it's being squished.
     * </p>
     * Values less than 1 will make the sprite smaller, whereas values greater than 1 will make it grow. Exactly 1 means
     * normal size.
     * <p>
     * Negative values will flip the sprite in that axis as well as growing it or shrinking it.
     * </p>
     */
    public double scaleX, scaleY;

    /**
     * The angle that the sprite is rotated by. A positive value is clockwise, whereas a negative one is anti-clockwise
     * (which is UK English for "counter-clockwise" in case you didn't know).
     * <p>
     * There are 360 degrees in a circle, so setting this to 360 is the same as setting it to 0.
     */
    public double angle;

    /**
     * The colour of the Sprite. This will be used in different ways depending on the type of Sprite. For example
     * ImageSprites will use it as a tint to overlay on the existing image whereas for TextSprites it is simply the
     * colour of the text.
     *
     * @see Colour
     */
    public Colour colour = Colours.White;

    /**
     * The alpha transparency of the sprite. This odd phrase actual just means how transparent it is.
     * <p>
     * A value of 1 means that it's not transparent at all and will look completely solid. Lower values will make it
     * look more and more ghostly, until at zero it will be completely transparent and you won't be able to see it at
     * all.
     * <p>
     * Try it out... You'll soon see what it does.
     */
    public double transparency;

    public boolean hidden;

    public SpriteEventHandler mouseEnterHandler;

    public SpriteEventHandler mouseExitHandler;

    public SpriteEventHandler clickHandler;

    public SpriteEventHandler dragHandler;

    public SpriteEventHandler dragReleasedHandler;

    public SpriteEventHandler mousePressedHandler;

    public SpriteEventHandler mouseReleasedHandler;

    public SpriteMouseState mouse = NULL_MOUSE_STATE;

    public TargetStyle targetStyle;

    public ScreenLayer layer;

    public final Map<Sprite, SpriteEventHandler> collisionEventHandlers = new HashMap<Sprite, SpriteEventHandler>();

    public T setX(double newX) {

        this.x = newX;
        return (T) this;
    }

    public T setY(double newY) {

        this.y = newY;
        return (T) this;
    }

    public T setScaleX(double newScaleX) {

        this.scaleX = newScaleX;
        return (T) this;
    }

    public T setScaleY(double newScaleY) {

        this.scaleY = newScaleY;
        return (T) this;
    }

    public T setAngle(double newRotation) {

        this.angle = newRotation;
        return (T) this;
    }

    public T setColour(Colour newColour) {

        this.colour = newColour;
        return (T) this;
    }

    public T setTransparency(double newAlpha) {

        this.transparency = newAlpha;
        return (T) this;
    }

    public T setHidden(boolean newHidden) {

        this.hidden = newHidden;
        return (T) this;
    }

    public T setTargetStyle(TargetStyle newTargetStyle) {

        this.targetStyle = newTargetStyle;
        return (T) this;
    }

    public T onMouseEnter(SpriteEventHandler eventHandler) {

        this.mouseEnterHandler = eventHandler;
        return (T) this;
    }

    public T onMouseExit(SpriteEventHandler eventHandler) {

        this.mouseExitHandler = eventHandler;
        return (T) this;
    }

    public T onClick(SpriteEventHandler eventHandler) {

        this.clickHandler = eventHandler;
        return (T) this;
    }

    public T onDrag(SpriteEventHandler eventHandler) {

        this.dragHandler = eventHandler;
        return (T) this;
    }

    public T onDragReleased(SpriteEventHandler eventHandler) {

        this.dragReleasedHandler = eventHandler;
        return (T) this;
    }

    public T onMousePressed(SpriteEventHandler eventHandler) {

        this.mousePressedHandler = eventHandler;
        return (T) this;
    }

    public T onMouseReleased(SpriteEventHandler eventHandler) {

        this.mouseReleasedHandler = eventHandler;
        return (T) this;
    }

    public T onCollisionWith(Sprite other, SpriteEventHandler eventHandler) {

        this.collisionEventHandlers.put(other, eventHandler);
        return (T) this;
    }

    public T setLayer(ScreenLayer layerToAppearOn) {

        this.layer = layerToAppearOn;
        return (T) this;
    }

    public T setMouse(SpriteMouseState newMouseState) {

        this.mouse = newMouseState;
        return (T) this;
    }

    /**
     * This method provides a handy way to set both X and Y coordinates in one hit.
     *
     * @param newX The new X coordinate
     * @param newY The new Y coordinate
     * @return The Sprite.
     */
    public T setPosition(double newX, double newY) {

        x = newX;
        y = newY;
        return (T) this;
    }

    /**
     * Rotates the sprite around its center by the specified amount. This is cumulative, which means that if I call it
     * 10 times with a value of 5 (for example) then it'll end up rotated by 50 degrees.
     *
     * @param degrees Degrees to rotate by. See {@link #angle} for more details.
     * @return The Sprite.
     */
    public T rotateBy(double degrees) {

        angle += degrees;
        return (T) this;
    }

    /**
     * Changes the size of the sprite by multiplying its normal size by the factor. Specifying a factor smaller than 1
     * will shrink the sprite whereas a factor greater than 1 will grow it. Exactly 1 will return it to normal size.
     * <p>
     * If the number is negative, then the sprite will also be flipped in both axes - This is the same as rotating it
     * 180 degrees. Can you work out why?
     *
     * @param factor The scaling factor to multiply the size by.
     * @return The Sprite.
     */
    public T setScale(double factor) {

        scaleX = factor;
        scaleY = factor;
        return (T) this;
    }

    public T setScale(double newScaleX, double newScaleY) {

        this.scaleX = newScaleX;
        this.scaleY = newScaleY;
        return (T) this;
    }

    /**
     * Flips the Sprite on the X-axis (i.e. from left-to-right), so it looks like a mirror-image of the original.
     *
     * @return The Sprite.
     */
    public T flipX() {

        scaleX = -scaleX;
        return (T) this;
    }

    /**
     * Flips the Sprite on the Y-axis (i.e. from top-to-bottom), so it looks upside-down.
     *
     * @return The Sprite.
     */
    public T flipY() {

        scaleY = -scaleY;
        return (T) this;
    }

    public T runEffect(EffectBase effectToRun) {

        return runEffect(effectToRun, SpriteEventHandlers.DoNothing);
    }

    public T runEffect(EffectBase effectToRun, SpriteEventHandler whatToDoAtEnd) {

        return runEffect(effectToRun, whatToDoAtEnd, ExistingEffectStrategy.StopExistingEffect);
    }

    public T stopEffects() {

        return runEffect(null, SpriteEventHandlers.DoNothing, ExistingEffectStrategy.StopExistingEffect);
    }

    public T[] multiplyBy(int numberOfTimes) {

        T[] sprites = (T[]) Array.newInstance(getClass(), numberOfTimes);

        sprites[0] = (T) this;
        for(int i = 1; i < numberOfTimes; i++) {
            sprites[i] = copy();
        }
        return sprites;
    }

    public T moveTowards(Sprite otherSprite, double distance) {

        return moveTowards(otherSprite.x, otherSprite.y, distance);
    }

    public T moveTowards(double targetX, double targetY, double distanceToMove) {

        // Total distances to move in X and Y
        double xDistance = targetX - x;
        double yDistance = targetY - y;
        double distanceToTarget = Math.sqrt(xDistance * xDistance + yDistance * yDistance);

        if(distanceToMove > Math.abs(distanceToTarget)) {
            x = targetX;
            y = targetY;
        } else {
            double ratio = distanceToMove / distanceToTarget;
            x += xDistance * ratio;
            y += yDistance * ratio;
        }
        return (T) this;
    }

    public double distanceTo(Sprite otherSprite) {

        return distanceTo(otherSprite.x, otherSprite.y);
    }

    public double distanceTo(double targetX, double targetY) {

        double xDistance = targetX - x;
        double yDistance = targetY - y;
        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    public double angleTo(Sprite otherSprite) {

        return angleTo(otherSprite.x, otherSprite.y);
    }

    public double angleTo(double targetX, double targetY) {

        return Math.toDegrees(Math.atan2(y - targetY, targetX - x));
    }

    public abstract T move(double angle, double distance);

    /**
     * Checks whether this Sprite has collided with the specified Sprite, or to be more precise, it checks whether this
     * Sprite's {@link #targetStyle} overlaps the other Sprite's {@link #targetStyle}
     * <p>
     * Note that collision shapes are initialised on the first call to {@link Screen#update()} after they're created.
     * If either of the Sprites hasn't hasn't been initialised, this will return false.
     *
     * @param other The Sprite to check whether we've collided with
     * @return true If both Sprites have been initialised and are in collision (i.e. their collisionShapes overlap).
     */
    public abstract boolean overlaps(Sprite other);

    /**
     * Overlapped since last call to this method.
     * @param other
     * @return
     */
    public abstract boolean collidedWith(Sprite other);

    public abstract T runEffect(EffectBase effectToRun, SpriteEventHandler whatToDoAtEnd, ExistingEffectStrategy whatIfAlreadyRunningOne);

    /**
     * Removes the Sprite completely from Blurp. It will be destroyed, and no longer appear on screen.
     * <p>
     * Removing things from Blurp when you no longer need them is a really good thing to do, as any resources they use
     * up is freed and available for the rest of your program to use.
     */
    public abstract void remove();

    public abstract boolean isRunningAnEffect();

    public abstract T pushToBack();

    public abstract T pullToFront();

    public abstract T pushBehind(Sprite otherSprite);

    public abstract T pullInFrontOf(Sprite otherSprite);

    public T clone() {
        return copy();
    }

    public abstract T copy();

    public void normaliseAngle() {

        angle = angle % 360;
        if(angle > 180) {
            angle = -360 + angle;
        }
    }

    @Override
    public String toString() {

        return String.format("x=%.1f y=%.1f scaleX=%.2f scaleY=%.2f angle=%.1f colour=%s transparency=%.2f hidden=%s targetStyle=%s layer=%s",
                             x, y, scaleX, scaleY, angle, colour, transparency, hidden, targetStyle, layer);
    }
}
