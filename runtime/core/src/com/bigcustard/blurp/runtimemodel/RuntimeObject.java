package com.bigcustard.blurp.runtimemodel;

import com.bigcustard.blurp.core.*;

/**
 * @param <T> The type of the corresponding model object.
 */
public interface RuntimeObject<T> {

    public abstract void sync(T modelObject, BlurpObjectProvider blurpObjectProvider, boolean newInstance);

    public abstract void dispose();
}
