package com.bigcustard.blurp.core;

import java.lang.reflect.*;
import java.util.*;
import com.bigcustard.blurp.runtimemodel.*;

/**
 * Map of model objects to runtime objects. Can handle syncing with model objects, either by copying state in the case
 * of a pre-existing runtime object, or by instantiating a runtime object. It will then remove any orphaned runtime
 * objects.
 *
 * The runtime class must have a monadic constructor that takes an instance of the model class.
 *
 * @param <K> The model object type
 * @param <V> The runtime model object type
 */
public class ModelToRuntimeObjectMap<K, V extends RuntimeObject<K>> {

    private Map<K, V> store;
    private Constructor runtimeObjectConstructor;

    // Wouldn't need the class parameters if Java had proper generics. Sigh.
    public ModelToRuntimeObjectMap(Class<K> modelClass, Class<V> runtimeClass) {

        store = new HashMap<K, V>();
        try {
            runtimeObjectConstructor = runtimeClass.getConstructor(modelClass);
        } catch(NoSuchMethodException e) {
            throw new RuntimeException("The runtime class doesn't have a constructor that takes the model class", e);
        }
    }

    /**
     * Syncs existing runtime objects from their corresponding model objects, and instantiates any new runtime objects
     * that may be required. After which it removes any orphaned runtime objects.
     *
     * @param modelObjects
     */
    public void syncAll(List<K> modelObjects) {

        for(K modelObject : modelObjects) {
            sync(modelObject);
        }
        clearOrphans(modelObjects);
    }

    void sync(K modelObject) {

        V runtimeObject = store.get(modelObject);
        if(runtimeObject == null) {
            try {
                runtimeObject = (V) runtimeObjectConstructor.newInstance(modelObject);
                store.put(modelObject, runtimeObject);
            } catch(Exception e) {
                throw new RuntimeException("Error instantiating the runtime object", e);
            }
        } else {
            runtimeObject.sync(modelObject);
        }
    }

    private void clearOrphans(List<K> modelObjects) {

        for(K storedModelObject : new ArrayList<K>(store.keySet())) {
            if(!modelObjects.contains(storedModelObject)) {
                store.remove(storedModelObject);
            }
        }
    }

    public V get(K modelObject) {

        return store.get(modelObject);
    }
}
