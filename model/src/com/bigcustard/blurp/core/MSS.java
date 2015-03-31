package com.bigcustard.blurp.core;

import com.bigcustard.blurp.model.*;

/**
 * MSS stands for the Model Singleton Store. It provides a place to store singletons that the model code requires access
 * to. It's not nice, but it is expedient.
 * <p>
 * The shortened name is an attempt to cause minimal visual impact on calling code.
 */
public class MSS {

    private static volatile boolean initialised;

    private static IModelRepository modelRepository;
    private static Canvas canvas;

    private MSS() { }

    public synchronized static void setInstances(IModelRepository modelRepository, Canvas canvas) {

        if(initialised) throw new IllegalStateException("The model singletons can only be set once.");

        MSS.modelRepository = modelRepository;
        MSS.canvas = canvas;

        initialised = true;
    }

    public synchronized static void dispose() {

        modelRepository = null;
        canvas = null;

        initialised = false;
    }

    public static IModelRepository getModelRepository() {

        return modelRepository;
    }

    public static Canvas getCanvas() {

        return canvas;
    }
}
