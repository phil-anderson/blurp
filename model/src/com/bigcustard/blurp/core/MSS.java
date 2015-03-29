package com.bigcustard.blurp.core;

/**
 * MSS stands for the Model Singleton Store. It provides a place for the runtime to inject singleton objects that the
 * model can subsequently use. It's not nice, but it is expedient.
 * <p>
 * The shortened name is an attempt to cause minimal visual impact on calling code.
 */
public class MSS {

    private static volatile boolean initialised;

    private static IModelRepository modelRepository;

    private MSS() { }

    public synchronized static void setInstances(IModelRepository modelRepository) {

        if(initialised) throw new IllegalStateException("The model singletons can only be set once.");

        MSS.modelRepository = modelRepository;
        initialised = true;
    }

    public synchronized static void dispose() {

        modelRepository = null;
        initialised = false;
    }

    public static IModelRepository getModelRepository() {

        return modelRepository;
    }

    public static boolean isBlurpInitialised() {

        return initialised;
    }
}
