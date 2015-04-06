//package com.bigcustard.blurp.core;
//
//import java.util.*;
//import com.bigcustard.blurp.runtimemodel.*;
//import org.junit.*;
//
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.*;
//
//public class ModelToRuntimeObjectMapTest {
//
//    private ModelToRuntimeObjectMap<ModelClass, RuntimeClass> testCandidate;
//    private ModelClass modelObject;
//
//    @Before
//    public void setUp() throws Exception {
//
//        testCandidate = new ModelToRuntimeObjectMap<ModelClass, RuntimeClass>(ModelClass.class, RuntimeClass.class);
//        modelObject = new ModelClass("Ford");
//        testCandidate.sync(modelObject, blurpObjectProvider);
//    }
//
//    @Test
//    public void syncCreatesRuntimeObjects() throws Exception {
//
//        RuntimeClass runtimeObject = testCandidate.get(modelObject);
//
//        assertThat(runtimeObject, instanceOf(RuntimeClass.class));
//        assertThat(runtimeObject.id, is("Ford"));
//    }
//
//    @Test
//    public void syncUpdatesExistingRuntimeObject() throws Exception {
//
//        RuntimeClass originalRuntimeObject = testCandidate.get(modelObject);
//
//        modelObject.id = "Arthur";
//        testCandidate.sync(modelObject, blurpObjectProvider);
//        RuntimeClass runtimeObject = testCandidate.get(modelObject);
//
//        assertThat(runtimeObject, sameInstance(originalRuntimeObject));
//        assertThat(runtimeObject.id, is("Arthur"));
//    }
//
//    @Test
//    public void syncAllClearsOrphans() throws Exception {
//
//        ModelClass otherModelObject = new ModelClass("Test2");
//        List<ModelClass> modelObjects = Arrays.asList(otherModelObject);
//        testCandidate.syncAll(modelObjects);
//
//        assertThat(testCandidate.get(modelObject), nullValue());
//
//        RuntimeClass runtimeObject = testCandidate.get(otherModelObject);
//        assertThat(runtimeObject, notNullValue());
//        assertThat(runtimeObject.id, is("Test2"));
//    }
//
//    private static class ModelClass {
//
//        private String id;
//
//        private ModelClass(String id) {
//
//            this.id = id;
//        }
//    }
//
//    private static class RuntimeClass implements RuntimeObject<ModelClass> {
//
//        private String id;
//
//        public RuntimeClass(ModelClass modelObject) {
//
//            sync(modelObject);
//        }
//
//        @Override
//        public void sync(ModelClass modelObject) {
//
//            id = modelObject.id;
//        }
//
//        @Override
//        public void dispose() { }
//    }
//
//}
