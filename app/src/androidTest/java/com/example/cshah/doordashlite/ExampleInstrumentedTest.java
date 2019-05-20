package com.example.cshah.doordashlite;

import android.content.res.Resources;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.cshah.doordashlite.Controller.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private Resources resources;

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        resources = activityRule.getActivity().getResources();
    }
    
    @Test
    public void testSampleRecyclerVisible() {

        Espresso.onView(ViewMatchers.withId(R.id.my_recycler_view))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testCaseForRecyclerClick() {

        Espresso.onView(ViewMatchers.withId(R.id.my_recycler_view))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void testCaseForRecyclerScroll() {

        // Get total item of RecyclerView
        RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.my_recycler_view);

        // Scroll to end of page with position
        Espresso.onView(ViewMatchers.withId(R.id.my_recycler_view))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .perform(scrollToPosition(8));
    }


    @Test
    public void testCaseForRecyclerItemImageView() {

        onView(withId(R.id.my_recycler_view))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .check(matches(hasDescendant(withId(R.id.imageView))));
    }

    @Test
    public void testCaseForRecyclerItemNameTextView() {

        onView(withId(R.id.my_recycler_view))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .check(matches(hasDescendant(withId(R.id.nameText))));
    }

    @Test
    public void testCaseForRecyclerItemDescriptionView() {

        onView(withId(R.id.my_recycler_view))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .check(matches(hasDescendant(withId(R.id.descriptionText))));
    }

    @Test
    public void testCaseForRecyclerItemStatusView() {

        onView(withId(R.id.my_recycler_view))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .check(matches(hasDescendant(withId(R.id.statusText))));
    }

    public Matcher<View> withViewAtPosition(final int position, final Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                final RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView);
            }
        };
    }
}
