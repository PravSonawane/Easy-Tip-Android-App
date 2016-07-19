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
        Animation animation = AnimationUtils.loadAnimation(this.getActivity(), R.anim.anim_settings_fab_enter);
        settingsFab.startAnimation(animation);
        initDb();
        setQuote();

        DisplayMetrics metrics = new DisplayMetrics();
        MainFragment.this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        finalYCoordinate = (int) (metrics.heightPixels * 0.57);
        finalXCoordinate = metrics.widthPixels / 2;
        return view;
    }

    private View.OnClickListener newSettingsFabOnClickListener(final View view) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ViewGroup.LayoutParams oririnalParams = settingsRelLyt.getLayoutParams();
                RelativeLayout.LayoutParams newParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                newParams.addRule(RelativeLayout.BELOW, R.id.activity_main_relLyt_total_amount_id);
                settingsRelLyt.setLayoutParams(newParams);*/
                //settingsRelLyt.setVisibility(View.VISIBLE);
                //SettingsDialogFragment dialogFragment = new SettingsDialogFragment();
                //dialogFragment.show(MainFragment.this.getActivity().getSupportFragmentManager(), "SettingsDialogFragment");

                //settingsLnrLyt.setVisibility(View.VISIBLE);
                //RelativeLayout.LayoutParams newParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //newParams1.addRule(RelativeLayout.BELOW, R.id.activity_main_lnrLyt_settings_id);
                //quoteRelLyt.setLayoutParams(newParams1);
                btnDone = (Button) view.findViewById(R.id.fragment_dialog_settings_btn_done_id);
                btnCancel = (Button) view.findViewById(R.id.fragment_dialog_settings_btn_cancel_id);
                btnDone.setOnClickListener(newDoneButtonOnClickListener());
                btnCancel.setOnClickListener(newCancelButtonOnClickListener());


                if(Build.VERSION.SDK_INT >= 21) {
                    final ViewGroup.LayoutParams originalParams = settingsFab.getLayoutParams();
                    Transition arcMotion = TransitionInflater.from(MainFragment.this.getActivity()).inflateTransition(R.transition.arc_motion);

                    arcMotion.addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {

                            quoteTxtVw.setVisibility(View.GONE);
                            quoteAuthorTextVw.setVisibility(View.GONE);
                            //tipPercentageSettingsRelLyt.setVisibility(View.VISIBLE);
                            //peopleCountSettingsRelLyt.setVisibility(View.VISIBLE);
                            //quoteRelLyt.setVisibility(View.GONE);
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
                            animateRevealColorFromCoordinates(quoteRelLyt, R.color.colorSettingsBackground, finalXCoordinate, 0);
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
                    //newParams.addRule(RelativeLayout.CENTER_IN_PARENT);


                    /*newParams.topMargin = (int)(metrics.heightPixels * 0.75);
                    newParams.leftMargin = (int)(metrics.widthPixels * 0.50);*/


                    newParams.topMargin = finalYCoordinate;
                    newParams.leftMargin = finalXCoordinate;
                    settingsFab.setLayoutParams(newParams);
                }

            }
        };
    }

    private View.OnClickListener newCancelButtonOnClickListener() {
        return null;
    }

    private View.OnClickListener newDoneButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT >= 21) {
                    final ViewGroup.LayoutParams originalParams = settingsFab.getLayoutParams();
                    Transition arcMotion = TransitionInflater.from(MainFragment.this.getActivity()).inflateTransition(R.transition.arc_motion);

                    arcMotion.addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {


                            //tipPercentageSettingsRelLyt.setVisibility(View.VISIBLE);
                            //peopleCountSettingsRelLyt.setVisibility(View.VISIBLE);
                            //quoteRelLyt.setVisibility(View.GONE);

                            settingsFab.setVisibility(View.VISIBLE);
                            settingsLnrLyt.setVisibility(View.GONE);
                        }

                        @Override
                        public void onTransitionEnd(Transition transition) {
                            //settingsFab.setLayoutParams(originalParams);
                            quoteTxtVw.setVisibility(View.VISIBLE);
                            quoteAuthorTextVw.setVisibility(View.VISIBLE);

                            Log.d(TAG, "onTransitionEnd: finalXCoordinate:" + finalXCoordinate);
                            Log.d(TAG, "onTransitionEnd: finalYCoordinate:" + finalYCoordinate);

                            int[] outLocation = new int[2];
                            tipPercentangeSettingsLnrLyt.getLocationInWindow(outLocation);
                            Rect rect = new Rect();
                            tipPercentangeSettingsLnrLyt.getGlobalVisibleRect(rect);
                            Log.d(TAG, "onTransitionEnd: center x:" + rect.exactCenterX());
                            Log.d(TAG, "onTransitionEnd: center y:" + rect.exactCenterY());
                            Log.d(TAG, "onTransitionEnd: quoteRelLyt location[0]:" + outLocation[0] + " location[1]:" + outLocation[1]);
                            animateRevealColorFromCoordinates(quoteRelLyt, R.color.colorSettingsBackground, finalXCoordinate, 0);
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
                    //newParams.addRule(RelativeLayout.CENTER_IN_PARENT);


                    /*newParams.topMargin = (int)(metrics.heightPixels * 0.75);
                    newParams.leftMargin = (int)(metrics.widthPixels * 0.50);*/


                    newParams.topMargin = finalYCoordinate;
                    newParams.leftMargin = finalXCoordinate;
                    settingsFab.setLayoutParams(newParams);
                }
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

    private void setQuote() {
        QuoteManager quoteManager = managerFactory().quoteManager();
        Quote quote = quoteManager.getRandomQuote();
        quoteTxtVw.setText(quote.getQuote());
        quoteAuthorTextVw.setText(quote.getAuthor());
    }

    private void initDb() {
        managerFactory().seedDataManager().initializeDb();
    }
}
