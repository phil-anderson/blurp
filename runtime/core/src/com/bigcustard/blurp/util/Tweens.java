package com.bigcustard.blurp.util;

import aurelienribon.tweenengine.*;

public class Tweens {

    public static float[] getTweenAccessorValues(Object target, int tweenType) {

        Class<?> targetClass = findTargetClass(target);
        TweenAccessor tweenAccessor = Tween.getRegisteredAccessor(targetClass);
        float[] initialValues = new float[3]; // Change this if you raise the Tween combined attribute limit
        tweenAccessor.getValues(target, tweenType, initialValues);
        return initialValues;
    }

    // Pilfered from Tween and then butchered.
    private static Class<?> findTargetClass(Object target) {

        if (target instanceof TweenAccessor) return target.getClass();
        if (Tween.getRegisteredAccessor(target.getClass()) != null) return target.getClass();

        Class<?> parentClass = target.getClass().getSuperclass();
        while (parentClass != null && Tween.getRegisteredAccessor(parentClass) == null)
            parentClass = parentClass.getSuperclass();

        return parentClass;
    }
}
