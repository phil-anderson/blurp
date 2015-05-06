package com.bigcustard.blurp.runtimemodel;

/**
 * @param <T> The type of the corresponding model object.
 */
public interface RuntimeObject<T> {

    public abstract void sync(T modelObject, boolean newInstance);

    public abstract void dispose();
}
