package com.merryapps.tipcalculator.ui.tip;


import android.animation.Animator;
import android.animation.LayoutTransition;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.merryapps.tipcalculator.R;
import com.merryapps.tipcalculator.model.core.Quote;
import com.merryapps.tipcalculator.model.core.QuoteManager;
import com.merryapps.tipcalculator.ui.framework.AbstractFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends AbstractFragment {

    private static final String TAG = "MainFragment";

    private TextView quoteTxtVw;
    private TextView quoteAuthorTextVw;
    private FloatingActionButton settingsFab;
    //private RelativeLayout settingsRelLyt;
    //private LinearLayout settingsWrapperLnrLyt;
    private RelativeLayout fragmentParent;

    private RelativeLayout quoteRelLyt;
    private LinearLayout settingsLnrLyt;
    private RelativeLayout tipPercentageSettingsRelLyt;
    private RelativeLayout peopleCountSettingsRelLyt;
    private LinearLayout tipPercentangeSettingsLnrLyt;
    private LinearLayout peopleCountSettingsLnrLyt;
    private ScrollView scrollView;

    private Button btnDone;
    private Button btnCancel;



    private int finalYCoordinate;
    private int finalXCoordinate;
    private Animation settingsFabEnterAnimation;
    private QuoteManager quoteManager;
    private DisplayMetrics displayMetrics;

    public MainFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        fragmentParent = (RelativeLayout) view.findViewById(R.id.fragment_main_relLyt_parent_id);
        quoteTxtVw = (TextView) view.findViewById(R.id.activity_main_txtVw_quote_id);
        quoteAuthorTextVw = (TextView) view.findViewById(R.id.activity_main_txtVw_quote_author_id);
        tipPercentageSettingsRelLyt = (RelativeLayout) view.findViewById(R.id.activity_main_relLyt_settings_tip_percentage_id);
        peopleCountSettingsRelLyt = (RelativeLayout) view.findViewById(R.id.activity_main_relLyt_settings_people_count_id);
        tipPercentangeSettingsLnrLyt = (LinearLayout) view.findViewById(R.id.fragment_dialog_settings_lnrlyt_percentage_id);
        peopleCountSettingsLnrLyt = (LinearLayout) view.findViewById(R.id.fragment_dialog_settings_lnrlyt_people_id);
        scrollView = (ScrollView) view.findViewById(R.id.activity_main_parent_scrllVw_id);

        //test
        quoteRelLyt = (RelativeLayout) view.findViewById(R.id.activity_main_relLyt_quote_id);
        settingsLnrLyt = (LinearLayout) view.findViewById(R.id.activity_main_lnrLyt_settings_id);
        //test

        //settingsWrapperLnrLyt = (LinearLayout) view.findViewById(R.id.activity_main_lnrLyt_settings_wrapper_id);
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setInterpolator(LayoutTransition.CHANGE_APPEARING, new DecelerateInterpolator());
        layoutTransition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 400);

        //layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
        quoteRelLyt.setLayoutTransition(layoutTransition);

        //settingsRelLyt = (LinearLayout) view.findViewById(R.id.activity_main_lnrLyt_settings_id);
        settingsFab = (FloatingActionButton) view.findViewById(R.id.fragment_main_settings_fab);
        settingsFab.setOnClickListener(newSettingsFabOnClickListener(view));
        settingsFabEnterAnimation = AnimationUtils.loadAnimation(this.getActivity(), R.anim.anim_settings_fab_enter);
        settingsFab.startAnimation(settingsFabEnterAnimation);
        initDb();
        setQuote();

        displayMetrics = new DisplayMetrics();
        MainFragment.this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        finalYCoordinate = (int) (displayMetrics.heightPixels * 0.57);
        finalXCoordinate = displayMetrics.widthPixels / 2;
        return view;
    }

    private View.OnClickListener newSettingsFabOnClickListener(final View view) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDone = (Button) view.findViewById(R.id.fragment_dialog_settings_btn_done_id);
                btnCancel = (Button) view.findViewById(R.id.fragment_dialog_settings_btn_cancel_id);
                btnDone.setOnClickListener(newDoneButtonOnClickListener());
                btnCancel.setOnClickListener(newCancelButtonOnClickListener());


                if(Build.VERSION.SDK_INT >= 21) {
                    Transition arcMotion = TransitionInflater.from(MainFragment.this.getActivity()).inflateTransition(R.transition.arc_motion);

                    arcMotion.addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {

                            quoteTxtVw.setVisibility(View.GONE);
                            quoteAuthorTextVw.setVisibility(View.GONE);
                        }

                        @Override
                        public void onTransitionEnd(Transition transition) {
                            //settingsFab.setLayoutParams(originalParams);

                            settingsFab.setVisibility(View.GONE);
                            settingsLnrLyt.setVisibility(View.VISIBLE);
                            Log.d(TAG, "onTransitionEnd: finalXCoordinate:" + finalXCoordinate);
                            Log.d(TAG, "onTransitionEnd: finalYCoordinate:" + finalYCoordinate);

                            int[] outLocation = new int[2];
                            tipPercentangeSettingsLnrLyt.getLocationInWindow(outLocation);
                            Rect rect = new Rect();
                            tipPercentangeSettingsLnrLyt.getGlobalVisibleRect(rect);
                            Log.d(TAG, "onTransitionEnd: center x:" + rect.exactCenterX());
                            Log.d(TAG, "onTransitionEnd: center y:" + rect.exactCenterY());
                            Log.d(TAG, "onTransitionEnd: quoteRelLyt location[0]:" + outLocation[0] + " location[1]:" + outLocation[1]);
                            animateRevealColorFromCoordinates(quoteRelLyt, R.color.colorSettingsBackground, finalXCoordinate/2, 0);
                            quoteRelLyt.setBackgroundResource(R.drawable.background_rounded_corners_500_yellow);
                            MainFragment.this.scrollView.post(new Runnable() {
                                @Override
                                public void run() {
                                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                                }
                            });
                        }

                        @Override
                        public void onTransitionCancel(Transition transition) {

                        }

                        @Override
                        public void onTransitionPause(Transition transition) {

                        }

                        @Override
                        public void onTransitionResume(Transition transition) {

                        }
                    });
                    TransitionManager.beginDelayedTransition(fragmentParent, arcMotion);

                    //new params
                    RelativeLayout.LayoutParams newParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    newParams.topMargin = finalYCoordinate;
                    newParams.leftMargin = finalXCoordinate/2;
                    settingsFab.setLayoutParams(newParams);
                }

            }
        };
    }

    private View.OnClickListener newCancelButtonOnClickListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT >= 21) {


                    int[] outLocation = new int[2];
                    quoteRelLyt.getLocationInWindow(outLocation);
                    Rect rect = new Rect();
                    quoteRelLyt.getGlobalVisibleRect(rect);
                    Log.d(TAG, "newDoneButtonOnClickListener: center x:" + rect.exactCenterX());
                    Log.d(TAG, "newDoneButtonOnClickListener: center y:" + rect.exactCenterY());
                    Log.d(TAG, "newDoneButtonOnClickListener: quoteRelLyt location[0]:" + outLocation[0] + " location[1]:" + outLocation[1]);

                    Animator animator = animateRevealHide(quoteRelLyt, R.color.colorPrimaryColorSettings,
                            (int)(displayMetrics.widthPixels - displayMetrics.widthPixels * 0.40),
                            (int)(finalYCoordinate - finalYCoordinate * 0.60));
                    if(animator != null) {
                        animator.addListener(newOnRevealHideListener());
                        animator.start();
                    }
                }
            }
        };
    }

    private View.OnClickListener newDoneButtonOnClickListener() {

        return new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT >= 21) {


                    int[] outLocation = new int[2];
                    quoteRelLyt.getLocationInWindow(outLocation);
                    Rect rect = new Rect();
                    quoteRelLyt.getGlobalVisibleRect(rect);
                    Log.d(TAG, "newDoneButtonOnClickListener: center x:" + rect.exactCenterX());
                    Log.d(TAG, "newDoneButtonOnClickListener: center y:" + rect.exactCenterY());
                    Log.d(TAG, "newDoneButtonOnClickListener: quoteRelLyt location[0]:" + outLocation[0] + " location[1]:" + outLocation[1]);

                    Animator animator = animateRevealHide(quoteRelLyt, R.color.colorPrimaryColorSettings,
                            (int)(displayMetrics.widthPixels - displayMetrics.widthPixels * 0.20),
                            (int)(finalYCoordinate - finalYCoordinate * 0.60));
                    if(animator != null) {
                        animator.addListener(newOnRevealHideListener());
                        animator.start();
                    }
                }
            }
        };
    }

    private Animator.AnimatorListener newOnRevealHideListener() {
        Log.d(TAG, "newOnRevealHideListener() called");
        return new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                settingsLnrLyt.setVisibility(View.GONE);
                quoteTxtVw.setVisibility(View.VISIBLE);
                quoteAuthorTextVw.setVisibility(View.VISIBLE);
                quoteRelLyt.setBackgroundResource(R.drawable.background_rounded_corners_12percent_black);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

                //new params
                RelativeLayout.LayoutParams newParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                newParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                newParams.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
                settingsFab.setLayoutParams(newParams);
                settingsFab.setVisibility(View.VISIBLE);
                settingsFab.startAnimation(settingsFabEnterAnimation);

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        };
    }

    private void animateRevealColorFromCoordinates(ViewGroup viewRoot, @ColorRes int color, int x, int y) {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        if(Build.VERSION.SDK_INT >= 21) {
            Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
            viewRoot.setBackgroundColor(getResources().getColor(color));
            anim.setDuration(300);
            anim.setInterpolator(new DecelerateInterpolator());
            anim.start();
        } else {
            viewRoot.setBackgroundColor(getResources().getColor(color));
        }

    }

    /**
     * Animates a reveal hide on the given viewRoot at the given coordinates
     * @param viewRoot the view group to animate in.
     * @param color the color of the animation
     * @param cx the x coordinate of center
     * @param cy the y coordinate of center
     * @return the animator if Build.VERSION.SDK_INT >= 21, else null.
     */
    private Animator animateRevealHide(final ViewGroup viewRoot, @ColorRes int color, int cx, int cy) {
        Log.d(TAG, "animateRevealHide() called with: " + "viewRoot = [" + viewRoot + "], color = [" + color + "], cx = [" + cx + "], cy = [" + cy + "]");

        if(Build.VERSION.SDK_INT >= 21) {
            Log.i(TAG, "animateRevealHide: SDK_INT >= 21");

            float initialRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

            Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, initialRadius, 0);
            anim.setInterpolator(new DecelerateInterpolator());
            anim.setDuration(300);
            return anim;
        } else {
            Log.i(TAG, "animateRevealHide: SDK_INT < 21");
            viewRoot.setBackgroundColor(getResources().getColor(color));
            return null;
        }

    }

    private void setQuote() {
        quoteManager = managerFactory().quoteManager();
        Quote quote = quoteManager.getRandomQuote();
        quoteTxtVw.setText(quote.getQuote());
        quoteAuthorTextVw.setText(quote.getAuthor());
    }

    private void initDb() {
        managerFactory().seedDataManager().initializeDb();
    }
}
