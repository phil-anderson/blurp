package com.bigcustard.blurp.model.events;

public interface SimpleEventHandler<T> {

    public void handle(T source);
}
