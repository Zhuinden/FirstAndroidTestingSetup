package com.zhuinden.firsttestingsetup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Mock
    Something something;

    Something realSomething;

    @Captor
    ArgumentCaptor<Something.SomeCallback> someCallbackCaptor;

    @Before
    public void init() {
        System.out.println("Initialization ran successfully.");
        MockitoAnnotations.initMocks(this);

        this.realSomething = new Something();
    }

    @Test
    public void addition_isCorrect()
            throws Exception {
        Assert.assertEquals(4, 2 + 2);
    }

    @Test
    public void doMock() {
        Mockito.when(something.someMethod()).thenReturn("Mock");
        Assert.assertEquals("Mock", something.someMethod());
    }

    @Test
    public void testVerifyMethodCall() {
        Mockito.when(something.otherMethod()).thenCallRealMethod();
        something.otherMethod();
        Mockito.verify(something).someMethod(); //verify it was called
    }

    @Test
    public void testSomeMethodWithRealObject() {
        Assert.assertEquals("Real", realSomething.someMethod());
    }

    @Test
    public void testRealThroughMock() {
        Mockito.when(something.someMethod()).thenCallRealMethod();
        Assert.assertEquals("Real", something.someMethod());
    }

    @Test //TODO: make real captor example
    public void testAsyncMethodCall() {
        something.someAsyncMethod(new Something.SomeCallback() {
            @Override
            public void onFinished(Something.Result result) {
                System.out.println("Testing: " + result.getCurrentTime());
            }
        });
        Mockito.verify(something).someAsyncMethod(someCallbackCaptor.capture());
        someCallbackCaptor.getValue().onFinished(new Something.Result(1000L));
        //verify side effects of callback
        //the callback must be defined elsewhere, not in the test.
    }
}