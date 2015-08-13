package com.bigcustard.blurp.model.events;

import com.bigcustard.blurp.model.*;

public interface CameraEventHandler extends SimpleEventHandler<Camera> {

    public static CameraEventHandler NULL = new CameraEventHandler() {
        @Override
        public void handle(Camera camera) {
            // Do nothing
        }
    };
}
