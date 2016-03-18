package com.zhuinden.firsttestingsetup.tests;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.zhuinden.firsttestingsetup.*;
import com.zhuinden.firsttestingsetup.util.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void before() {
        Intent intent = new Intent();
        mActivityTestRule.launchActivity(intent); //lazy launch if needed
        Espresso.registerIdlingResources(mActivityTestRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void initialTest() {
        Espresso.onView(ViewMatchers.withId(com.zhuinden.firsttestingsetup.R.id.hello_world)).check(ViewAssertions.matches(ViewMatchers.withText(
                com.zhuinden.firsttestingsetup.R.string.hello_world)));
    }

    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(mActivityTestRule.getActivity().getCountingIdlingResource());
    }
}