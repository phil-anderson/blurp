package com.bigcustard.blurp.core.common;

import com.badlogic.gdx.math.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.runtimemodel.*;

public class CollisionDetector {

    // flyweights to save frequent instantiation
    private static Vector2 circleCenter = new Vector2();
    private static Vector2 lastVertex = new Vector2();
    private static Vector2 vertex = new Vector2();

    public static boolean detectCollision(Sprite spriteA, Sprite spriteB) {

        RuntimeSprite runtimeSpriteA = BlurpStore.runtimeRepository.getSprite(spriteA);
        RuntimeSprite runtimeSpriteB = BlurpStore.runtimeRepository.getSprite(spriteB);

        if(runtimeSpriteA == null || runtimeSpriteB == null) return false;

        if(spriteA.collisionShape == CollisionShape.BoundaryRectangle) {
            return spriteB.collisionShape == CollisionShape.BoundaryRectangle ? checkRectangleRectangle(runtimeSpriteA.getCollisionRectangle(), runtimeSpriteB.getCollisionRectangle())
                                                                              : checkRectangleCircle(runtimeSpriteA.getCollisionRectangle(), runtimeSpriteB.getCollisionCircle());
        } else {
            return spriteB.collisionShape == CollisionShape.CenterCircle ? checkCircleCircle(runtimeSpriteA.getCollisionCircle(), runtimeSpriteB.getCollisionCircle())
                                                                         : checkRectangleCircle(runtimeSpriteB.getCollisionRectangle(), runtimeSpriteA.getCollisionCircle());
        }
    }

    static boolean checkCircleCircle(Circle circleA, Circle circleB) {

        return circleA.overlaps(circleB);
    }

    static boolean checkRectangleCircle(Polygon rectangle, Circle circle) {

        if(rectangle.contains(circle.x, circle.y)) return true;

        float[] vertices = rectangle.getTransformedVertices();
        float radiusSquared = circle.radius * circle.radius;
        circleCenter.set(circle.x, circle.y);
        lastVertex.set(vertices[vertices.length - 2], vertices[vertices.length - 1]);

        for(int i = 0; i < vertices.length; i += 2) {
            vertex.set(vertices[i], vertices[i + 1]);
            if (Intersector.intersectSegmentCircle(lastVertex, vertex, circleCenter, radiusSquared)) {
                return true;
            }
            lastVertex.set(vertex);
        }
        return false;
    }

    static boolean checkRectangleRectangle(Polygon rectangleA, Polygon rectangleB) {

        return Intersector.overlapConvexPolygons(rectangleA, rectangleB);
    }
}
