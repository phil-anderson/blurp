package com.bigcustard.blurp.core;

import java.util.*;
import com.bigcustard.blurp.runtimemodel.*;

public class ImageCache {

    private final Map<String, RuntimeImage> cache = new HashMap<String, RuntimeImage>();

    public RuntimeImage useImage(String name) {

        RuntimeImage image = cache.get(name);
        if(image == null) {
            image = new RuntimeImage(name);
            cache.put(name, image);
        }
        image.registerUsage();
        return image;
    }

    public void stopUsingImage(RuntimeImage image) {

        image.deregisterUsage();
        if(!image.isInUse()) {
            cache.remove(image.getName());
            image.dispose();
        }
    }

    public void clear() {

        for(RuntimeImage image : cache.values()) {
            image.dispose();
        }
        cache.clear();
    }
}
