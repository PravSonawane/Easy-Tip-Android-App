package com.merryapps.easytip.ui.tip;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;

import com.merryapps.easytip.R;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by mephisto on 7/29/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    private int minPercentageValue = 5;
    private int maxPercentageValue = 25;
    private int minPeopleCountValue = 1;
    private int maxPeopleCountValue = 20;
    private int percentageSeekbarStep = 1;
    private int peopleCountSeekbarStep = 1;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testViewsWhenFirstBillAmountDigitIsTyped() {

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(typeText("1"));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("0.15")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("1.15")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("1.15")));
    }

    @Test
    public void testViewsWhenTwoBillAmountDigitsAreTyped() {

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(typeText("10"));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));
    }

    @Test
    public void testViewsWhenFirstCharacterIsDecimal() {

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(typeText("."));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText(".")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("0.00")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("0.00")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("0.00")));
    }

    @Test
    public void testViewsWhenFirstCharacterIsZero() {

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(typeText("0"));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("0")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("0.00")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("0.00")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("0.00")));
    }

    @Test
    public void testViewsWhenRoundModeIsTurnedOn() {

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(typeText("103"));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("103")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("15.45")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("118.45")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("118.45")));

        onView(withId(R.id.activity_main_swtch_round_up_id))
                .perform(click());

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("103")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("15.45")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("118.00")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("118.00")));
    }

    @Test
    public void testViewsWhenDeleteKeyIsPressedForBillAmount() {

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(typeText("103"));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("103")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("15.45")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("118.45")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("118.45")));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(pressKey(KeyEvent.KEYCODE_DEL));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));
    }

    @Test
    public void testViewsWhenRoundModeIsTurnedOnAndOff() {

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(typeText("103"));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("103")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("15.45")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("118.45")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("118.45")));

        onView(withId(R.id.activity_main_swtch_round_up_id))
                .perform(click());

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("103")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("15.45")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("118.00")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("118.00")));

        onView(withId(R.id.activity_main_swtch_round_up_id))
                .perform(click());

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("103")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("15.45")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("118.45")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("118.45")));
    }

    @Test
    public void testUiWhenSettingsFabIsClicked() {

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(typeText("10"));
        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.fragment_main_settings_fab_id))
                .perform(click());

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_quote_id))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.activity_main_txtVw_quote_author_id))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.activity_main_lnrLyt_settings_id))
                .check(matches(isDisplayed()));


    }

    @Test
    public void testUiWhenDoneButtonIsClicked() {

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(typeText("10"));
        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.fragment_main_settings_fab_id))
                .perform(click());

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_quote_id))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.activity_main_txtVw_quote_author_id))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.activity_main_lnrLyt_settings_id))
                .check(matches(isDisplayed()));

        onView(withId(R.id.fragment_dialog_settings_btn_done_id))
                .perform(click());

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_quote_id))
                .check(matches(isDisplayed()));
        onView(withId(R.id.activity_main_txtVw_quote_author_id))
                .check(matches(isDisplayed()));
        onView(withId(R.id.activity_main_lnrLyt_settings_id))
                .check(matches(not(isDisplayed())));

    }

    @Test
    public void testTextViewsWhenPercentageSeekbarIsIncreased() {

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(typeText("10"));
        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.fragment_main_settings_fab_id))
                .perform(click());

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_quote_id))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.activity_main_txtVw_quote_author_id))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.activity_main_lnrLyt_settings_id))
                .check(matches(isDisplayed()));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_settings_value_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_people_count_settings_value_id))
                .check(matches(withText("01")));

        onView(withId(R.id.activity_main_skBr_tip_percentage_id))
                .perform(setPercentageProgress(20));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("20")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("2.00")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("12.00")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("12.00")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_settings_value_id))
                .check(matches(withText("20")));
        onView(withId(R.id.activity_main_txtVw_people_count_settings_value_id))
                .check(matches(withText("01")));
    }

    @Test
    public void testTextViewsWhenPercentageSeekbarIsDecreased() {

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(typeText("10"));
        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.fragment_main_settings_fab_id))
                .perform(click());

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_quote_id))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.activity_main_txtVw_quote_author_id))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.activity_main_lnrLyt_settings_id))
                .check(matches(isDisplayed()));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_settings_value_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_people_count_settings_value_id))
                .check(matches(withText("01")));

        onView(withId(R.id.activity_main_skBr_tip_percentage_id))
                .perform(setPercentageProgress(10));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.00")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.00")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.00")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_settings_value_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_people_count_settings_value_id))
                .check(matches(withText("01")));
    }

    @Test
    public void testTextViewsWhenPeopleCountSeekbarIsIncreased() {

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(typeText("10"));
        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.fragment_main_settings_fab_id))
                .perform(click());

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_quote_id))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.activity_main_txtVw_quote_author_id))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.activity_main_lnrLyt_settings_id))
                .check(matches(isDisplayed()));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_settings_value_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_people_count_settings_value_id))
                .check(matches(withText("01")));

        onView(withId(R.id.activity_main_skBr_people_count_id))
                .perform(setPeopleProgress(2));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("2")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("5.75")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_settings_value_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_people_count_settings_value_id))
                .check(matches(withText("02")));
    }

    @Test
    public void testTextViewsWhenPeopleCountSeekbarIsDecreased() {

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(typeText("10"));
        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.fragment_main_settings_fab_id))
                .perform(click());

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_quote_id))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.activity_main_txtVw_quote_author_id))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.activity_main_lnrLyt_settings_id))
                .check(matches(isDisplayed()));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_settings_value_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_people_count_settings_value_id))
                .check(matches(withText("01")));

        onView(withId(R.id.activity_main_skBr_people_count_id))
                .perform(setPeopleProgress(2));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("2")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("5.75")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_settings_value_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_people_count_settings_value_id))
                .check(matches(withText("02")));

        onView(withId(R.id.activity_main_skBr_people_count_id))
                .perform(setPeopleProgress(1));

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_quote_id))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.activity_main_txtVw_quote_author_id))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.activity_main_lnrLyt_settings_id))
                .check(matches(isDisplayed()));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_settings_value_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_people_count_settings_value_id))
                .check(matches(withText("01")));
    }

    @Test
    public void testUiWhenResetButtonIsClicked() {

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .perform(typeText("10"));
        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("10")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("1.50")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("11.50")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("11.50")));

        onView(withId(R.id.action_reset))
                .perform(click());

        onView(withId(R.id.activity_main_edtTxt_bill_amount_id))
                .check(matches(withText("")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_tip_amount_id))
                .check(matches(withText("0.00")));
        onView(withId(R.id.activity_main_txtVw_people_count_id))
                .check(matches(withText("1")));
        onView(withId(R.id.activity_main_txtVw_share_id))
                .check(matches(withText("0.00")));
        onView(withId(R.id.activity_main_txtVw_total_amount_id))
                .check(matches(withText("0.00")));
        onView(withId(R.id.activity_main_txtVw_tip_percentage_settings_value_id))
                .check(matches(withText("15")));
        onView(withId(R.id.activity_main_txtVw_people_count_settings_value_id))
                .check(matches(withText("01")));
    }

    private ViewAction setPercentageProgress(final int progress) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(SeekBar.class);
            }

            @Override
            public String getDescription() {
                return "Set a progress value";
            }

            @Override
            public void perform(UiController uiController, View view) {
                SeekBar seekBar = (SeekBar) view;
                seekBar.setProgress((progress * percentageSeekbarStep) - minPercentageValue);
            }
        };
    }

    private ViewAction setPeopleProgress(final int progress) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(SeekBar.class);
            }

            @Override
            public String getDescription() {
                return "Set a progress value";
            }

            @Override
            public void perform(UiController uiController, View view) {
                SeekBar seekBar = (SeekBar) view;
                seekBar.setProgress((progress * peopleCountSeekbarStep) - minPeopleCountValue);
            }
        };
    }


}
