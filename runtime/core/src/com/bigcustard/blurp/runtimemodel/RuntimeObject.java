package com.bigcustard.blurp.runtimemodel;

/**
 * @param <T> The type of the corresponding model object.
 */
public interface RuntimeObject<T> {

    void sync(T modelObject);
}
