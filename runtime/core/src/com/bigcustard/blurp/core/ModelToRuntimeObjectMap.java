package com.bigcustard.blurp.core;

import java.lang.reflect.*;
import java.util.*;
import com.bigcustard.blurp.runtimemodel.*;

/**
 * Map of model objects to runtime objects. Can handle syncing with model objects, either by copying state in the case
 * of a pre-existing runtime object, or by instantiating a runtime object. It will then remove any orphaned runtime
 * objects.
 *
 * The runtime class must have a no-params constructor.
 *
 * @param <K> The model object type
 * @param <V> The runtime model object type
 */
public class ModelToRuntimeObjectMap<K, V extends RuntimeObject<K>> implements Iterable<V> {

    private Map<K, V> store;
    private Constructor<V> runtimeObjectConstructor;

    // Wouldn't need the class parameters if Java had proper generics. Sigh.
    public ModelToRuntimeObjectMap(Class<V> runtimeClass) {

        store = new HashMap<K, V>();
        try {
            runtimeObjectConstructor = runtimeClass.getConstructor();
        } catch(NoSuchMethodException e) {
            throw new BlurpException("The runtime class doesn't have a no-args constructor", e);
        }
    }

    /**
     * Syncs existing runtime objects from their corresponding model objects, and instantiates any new runtime objects
     * that may be required. After which it removes any orphaned runtime objects.
     *
     * @param modelObjects The list of model objects to sync with.
     */
    public void syncAll(List<K> modelObjects) {

        for(K modelObject : modelObjects) {
            sync(modelObject);
        }
        clearOrphans(modelObjects);
    }

    void sync(K modelObject) {

        boolean newInstance = false;
        V runtimeObject = store.get(modelObject);
        if(runtimeObject == null) {
            try {
                runtimeObject = runtimeObjectConstructor.newInstance();
                newInstance = true;
                store.put(modelObject, runtimeObject);
            } catch(Exception e) {
                throw new BlurpException("Error instantiating the runtime object", e);
            }
        }

        runtimeObject.sync(modelObject, newInstance);
    }

    private void clearOrphans(List<K> modelObjects) {

        for(Map.Entry<K, V> entry : new ArrayList<Map.Entry<K, V>>(store.entrySet())) {

            K storedModelObject = entry.getKey();
            V runtimeObject = entry.getValue();

            if(!modelObjects.contains(storedModelObject)) {
                runtimeObject.dispose();
                store.remove(storedModelObject);
            }
        }
    }

    public V get(K modelObject) {

        return store.get(modelObject);
    }

    public Iterator<V> iterator() {

        return store.values().iterator();
    }

    public void clear() {

        for(V runtimeObject : store.values()) {
            runtimeObject.dispose();
        }
        store.clear();
    }
}
