package com.kbear.noknok;

import android.support.test.espresso.assertion.ViewAssertions;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.kbear.noknok.ui.activities.LauncherActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by allen on 4/6/15.
 */
@LargeTest
public class HelloWorldEspressoTest extends ActivityInstrumentationTestCase2<LauncherActivity> {

    public HelloWorldEspressoTest() {
        super(LauncherActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void test() {
        onView(withText("hello world")).check(ViewAssertions.matches(isDisplayed()));
    }

}
