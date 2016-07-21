package com.merryapps.tipcalculator.ui.tip;


import android.animation.Animator;
import android.animation.LayoutTransition;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
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
    private RelativeLayout fragmentParent;

    private RelativeLayout quoteRelLyt;
    private LinearLayout settingsLnrLyt;
    private LinearLayout tipPercentangeSettingsLnrLyt;
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

        initViews(view);
        setupAnimations();
        setupListeners(view);
        startAnimations();
        initDb();
        setQuote();
        initializeInstanceVariables();

        return view;
    }

    private void initializeInstanceVariables() {
        displayMetrics = new DisplayMetrics();
        MainFragment.this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        finalYCoordinate = (int) (displayMetrics.heightPixels * 0.57);
        finalXCoordinate = displayMetrics.widthPixels / 2;
    }

    private void setupListeners(View view) {
        settingsFab.setOnClickListener(newSettingsFabOnClickListener(view));
    }

    private void startAnimations() {
        settingsFab.startAnimation(settingsFabEnterAnimation);
    }

    private void setupAnimations() {
        quoteRelLyt.setLayoutTransition(createQuoteRelLytLayoutTransition());
        settingsFabEnterAnimation = AnimationUtils.loadAnimation(this.getActivity(), R.anim.anim_settings_fab_enter);
    }

    private void initViews(View view) {
        fragmentParent = (RelativeLayout) view.findViewById(R.id.fragment_main_relLyt_parent_id);
        quoteTxtVw = (TextView) view.findViewById(R.id.activity_main_txtVw_quote_id);
        quoteAuthorTextVw = (TextView) view.findViewById(R.id.activity_main_txtVw_quote_author_id);
        tipPercentangeSettingsLnrLyt = (LinearLayout) view.findViewById(R.id.fragment_dialog_settings_lnrlyt_percentage_id);
        scrollView = (ScrollView) view.findViewById(R.id.activity_main_parent_scrllVw_id);
        quoteRelLyt = (RelativeLayout) view.findViewById(R.id.activity_main_relLyt_quote_id);
        settingsLnrLyt = (LinearLayout) view.findViewById(R.id.activity_main_lnrLyt_settings_id);
        settingsFab = (FloatingActionButton) view.findViewById(R.id.fragment_main_settings_fab);
    }

    @NonNull
    private LayoutTransition createQuoteRelLytLayoutTransition() {
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setInterpolator(LayoutTransition.CHANGE_APPEARING, new DecelerateInterpolator());
        layoutTransition.setStartDelay(LayoutTransition.CHANGE_APPEARING, getResources().getInteger(R.integer.layoutTransitionDuration));
        return layoutTransition;
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
                            settingsFab.setVisibility(View.GONE);
                            settingsLnrLyt.setVisibility(View.VISIBLE);

                            animateRevealColorFromCoordinates(quoteRelLyt, R.color.colorSettingsBackground, finalXCoordinate/2, 0);
                            quoteRelLyt.setBackgroundResource(R.drawable.background_rounded_corners_500_yellow);
                        }

                        @Override
                        public void onTransitionCancel(Transition transition) {}

                        @Override
                        public void onTransitionPause(Transition transition) {}

                        @Override
                        public void onTransitionResume(Transition transition) {}
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

                    //values adjusted to get best results
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

                    //values adjusted to get best results
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
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        };
    }

    private void animateRevealColorFromCoordinates(ViewGroup viewRoot, @ColorRes int color, int x, int y) {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        if(Build.VERSION.SDK_INT >= 21) {
            Log.i(TAG, "animateRevealColorFromCoordinates: SDK_INT >= 21");
            Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
            viewRoot.setBackgroundColor(getResources().getColor(color));
            anim.setDuration(getResources().getInteger(R.integer.animateRevealDuration));
            anim.setInterpolator(new DecelerateInterpolator());
            anim.start();
        } else {
            Log.i(TAG, "animateRevealColorFromCoordinates: SDK_INT < 21");
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
            anim.setDuration(getResources().getInteger(R.integer.animateRevealHideDuration));
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
