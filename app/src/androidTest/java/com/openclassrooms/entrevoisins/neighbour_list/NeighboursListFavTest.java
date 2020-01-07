
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListFavTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);


    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

    }




    @Test
    public void FavoriteNeighourList_NotNull(){
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.buttFav)).perform(click());
        Espresso.pressBack();
        onView(withId(R.id.list_neighboursFav)).check(matches(hasMinimumChildCount(1)));

    }

    @Test
    public void FavoriteNeighourList_Delete_FavDeleteButNotNeighbour(){
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.buttFav)).perform(click());
        Espresso.pressBack();
        onView(withId(R.id.container)).perform(scrollRight());
        onView(withId(R.id.list_neighboursFav)).check(withItemCount(1));
        onView(withId(R.id.list_neighboursFav))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        onView(withId(R.id.list_neighboursFav)).check(withItemCount(0));

    }

    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.buttFav)).perform(click());
        Espresso.pressBack();
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(--ITEMS_COUNT));
        onView(withId(R.id.container)).perform(scrollRight());
        onView(withId(R.id.list_neighboursFav)).check(matches(hasChildCount(0)));
    }
}