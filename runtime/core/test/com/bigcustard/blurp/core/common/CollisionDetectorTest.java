package com.bigcustard.blurp.core.common;

import com.badlogic.gdx.math.*;
import org.junit.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

// Interestingly, it seems that circles that share a tangent are not overlapping, but polygons that share a vertex are!
public class CollisionDetectorTest {

    private Polygon rectangle1;
    private Polygon rectangle2;
    private Circle circle1;
    private Circle circle2;

    @Before
    public void setUp() throws Exception {

        rectangle1 = new Polygon(new float[] { -50, -50,   50, -50,   50, 50,   -50, 50 });
        rectangle2 = new Polygon(new float[] { -50, -50,   50, -50,   50, 50,   -50, 50 });
        circle1 = new Circle(0, 0, 25);
        circle2 = new Circle(0, 0, 25);
    }

    @Test
    public void detectsCircleCircleOverlap() {

        // Identical
        assertThat(CollisionDetector.checkCircleCircle(circle1, circle2), is(true));

        // Just overlapping
        circle2.setPosition(49.9f, 0);
        assertThat(CollisionDetector.checkCircleCircle(circle1, circle2), is(true));

        // Abutted, but not overlapping
        circle2.setPosition(50, 0);
        assertThat(CollisionDetector.checkCircleCircle(circle1, circle2), is(false));

        // Not overlapping
        circle2.setPosition(500, 500);
        assertThat(CollisionDetector.checkCircleCircle(circle1, circle2), is(false));
    }

    @Test
    public void detectsAxisAlignedRectangleRectangleOverlap() {

        // Identical
        assertThat(CollisionDetector.checkRectangleRectangle(rectangle1, rectangle2), is(true));

        // Just overlapping
        rectangle2.setPosition(99.9f, 99.9f);
        assertThat(CollisionDetector.checkRectangleRectangle(rectangle1, rectangle2), is(true));

        // Abutting vertices counts as overlap
        rectangle2.setPosition(100, 100f);
        assertThat(CollisionDetector.checkRectangleRectangle(rectangle1, rectangle2), is(true));

        // Just not overlapping
        rectangle2.setPosition(100.1f, 100.1f);
        assertThat(CollisionDetector.checkRectangleRectangle(rectangle1, rectangle2), is(false));

        // Not overlapping
        rectangle1.setPosition(100, 100);
        rectangle2.setPosition(500, 500);
        assertThat(CollisionDetector.checkRectangleRectangle(rectangle1, rectangle2), is(false));
    }

    @Test
    public void detectsTransformedRectangleRectangleOverlap() {

        // Overlapping
        rectangle1.setPosition(99, 99);
        assertThat(CollisionDetector.checkRectangleRectangle(rectangle1, rectangle2), is(true));

        // Rotate out of overlap
        rectangle2.setRotation(45);
        assertThat(CollisionDetector.checkRectangleRectangle(rectangle1, rectangle2), is(false));

        // Not overlapping
        rectangle1.setPosition(110, 0);
        rectangle2.setRotation(0);
        assertThat(CollisionDetector.checkRectangleRectangle(rectangle1, rectangle2), is(false));

        // Rotate into overlap
        rectangle2.setRotation(45);
        assertThat(CollisionDetector.checkRectangleRectangle(rectangle1, rectangle2), is(true));

        // Not overlapping
        rectangle1.setPosition(150, 150);
        rectangle2.setRotation(0);
        assertThat(CollisionDetector.checkRectangleRectangle(rectangle1, rectangle2), is(false));

        // Scale into overlap
        rectangle2.setScale(5, 5);
        assertThat(CollisionDetector.checkRectangleRectangle(rectangle1, rectangle2), is(true));
    }

    @Test
    public void detectsRectangleCircleVertexOverlap() {

        // Overlapping with center inside the rectangle
        circle1.setPosition(45, 45);
        assertThat(CollisionDetector.checkRectangleCircle(rectangle1, circle1), is(true));

        // Overlapping with center outside the rectangle
        circle1.setPosition(55, 55);
        assertThat(CollisionDetector.checkRectangleCircle(rectangle1, circle1), is(true));

        // No overlap
        circle1.setPosition(100, 100);
        assertThat(CollisionDetector.checkRectangleCircle(rectangle1, circle1), is(false));
    }

    @Test
    public void detectsRectangleContainsCircle() {

        // Overlapping with center inside the rectangle
        assertThat(CollisionDetector.checkRectangleCircle(rectangle1, circle1), is(true));
    }
}
