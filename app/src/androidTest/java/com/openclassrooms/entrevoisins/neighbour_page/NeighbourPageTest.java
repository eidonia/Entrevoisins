package com.openclassrooms.entrevoisins.neighbour_page;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

public class NeighbourPageTest {

    private NeighbourPage mNeighbourPage;

    @Rule
    public ActivityTestRule<NeighbourPage> mNeighbourPageRule = new ActivityTestRule<NeighbourPage>(NeighbourPage.class) {
        @Override
        protected Intent getActivityIntent() {
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Neighbour neighbour = new Neighbour(1, "Roger", "http://i.pravatar.cc/150?u=a042581f4e29026704d");
            Intent intent = new Intent(context, NeighbourPage.class);
            intent.putExtra("ID", 10);
            return intent;
        }
    };
    public static Matcher<Object> withCollapsibleToolbarTitle(final Matcher<String> textMatcher) {
        return new BoundedMatcher<Object, CollapsingToolbarLayout>(CollapsingToolbarLayout.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with toolbar title: ");
                textMatcher.describeTo(description);
            }
            @Override
            protected boolean matchesSafely(CollapsingToolbarLayout toolbarLayout) {
                return textMatcher.matches(toolbarLayout.getTitle());
            }
        };
    }

    @Before
    public void setUp(){
        mNeighbourPage = mNeighbourPageRule.getActivity();
        assertThat(mNeighbourPage, notNullValue());
    }

    @Test
    public void textNameNotNull() {
        onView(isAssignableFrom(CollapsingToolbarLayout.class)).check(matches(withCollapsibleToolbarTitle(is(mNeighbourPage.neighbour.getName()))));
    }

    @Test
    public void CheckIfNeighbourIsFavorite(){
        onView(withId(R.id.buttFav)).perform(click());
        assertTrue(mNeighbourPage.neighbour.isFavorite());
        onView(withId(R.id.buttFav)).perform(click());
        assertFalse(mNeighbourPage.neighbour.isFavorite());
    }
}