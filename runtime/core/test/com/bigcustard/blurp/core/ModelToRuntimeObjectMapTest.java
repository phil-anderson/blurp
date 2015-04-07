package com.bigcustard.blurp.core;

import java.util.*;
import com.bigcustard.blurp.runtimemodel.*;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ModelToRuntimeObjectMapTest {

    private ModelToRuntimeObjectMap<ModelClass, RuntimeClass> testCandidate;
    private ModelClass modelObject;

    @Before
    public void setUp() {

        testCandidate = new ModelToRuntimeObjectMap<ModelClass, RuntimeClass>(RuntimeClass.class);
        modelObject = new ModelClass("Ford");
        testCandidate.sync(modelObject, null);
    }

    @Test
    public void syncCreatesRuntimeObjects() {

        RuntimeClass runtimeObject = testCandidate.get(modelObject);

        assertThat(runtimeObject, instanceOf(RuntimeClass.class));
        assertThat(runtimeObject.id, is("Ford"));
    }

    @Test
    public void syncUpdatesExistingRuntimeObject() {

        RuntimeClass originalRuntimeObject = testCandidate.get(modelObject);

        modelObject.id = "Arthur";
        testCandidate.sync(modelObject, null);

        RuntimeClass runtimeObject = testCandidate.get(modelObject);
        assertThat(runtimeObject, sameInstance(originalRuntimeObject));
        assertThat(runtimeObject.id, is("Arthur"));
    }

    @Test
    public void newInstanceOnlyTrueOnInstantiatingSync()  {

        assertThat(testCandidate.get(modelObject).isNew, is(true));

        modelObject.id = "Arthur";
        testCandidate.sync(modelObject, null);

        assertThat(testCandidate.get(modelObject).isNew, is(false));
    }

    @Test
    public void syncAllClearsOrphans() {

        ModelClass otherModelObject = new ModelClass("Test2");
        List<ModelClass> modelObjects = Arrays.asList(otherModelObject);
        testCandidate.syncAll(modelObjects, null);

        assertThat(testCandidate.get(modelObject), nullValue());

        RuntimeClass runtimeObject = testCandidate.get(otherModelObject);
        assertThat(runtimeObject, notNullValue());
        assertThat(runtimeObject.id, is("Test2"));
    }

    private static class ModelClass {

        private String id;

        private ModelClass(String id) {

            this.id = id;
        }
    }

    private static class RuntimeClass implements RuntimeObject<ModelClass> {

        private String id;
        private boolean isNew;

        public RuntimeClass() {}

        @Override
        public void sync(ModelClass modelObject, BlurpObjectProvider blurpObjectProvider, boolean newInstance) {

            id = modelObject.id;
            isNew = newInstance;
        }

        @Override
        public void dispose() { }
    }

}
