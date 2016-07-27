package com.merryapps.easytip.ui.tip;


import android.animation.Animator;
import android.animation.LayoutTransition;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.merryapps.easytip.R;
import com.merryapps.easytip.model.tip.Rounding;
import com.merryapps.easytip.model.quote.Quote;
import com.merryapps.easytip.model.quote.QuoteManager;
import com.merryapps.easytip.ui.framework.AbstractFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends AbstractFragment {

    private static final String TAG = "MainFragment";
    private static final int MAX_SEEKBAR_VALUE = 20;
    private static final String EMPTY_STRING = "";
    private static final String ZERO_DECIMAL_STRING = "0.00";
    private static final String ONE_STRING = "1";
    private static final String DEFAULT_PERCENTAGE_DECIMAL_STRING = "15";

    private TextView quoteTxtVw;
    private TextView quoteAuthorTextVw;
    private EditText billAmountEdtTxt;
    private TextView tipAmountTxtVw;
    private TextView tipPercentageTxtVw;
    private TextView eachPersonsShareTxtVw;
    private TextView peopleCountTxtVw;
    private TextView totalTxtVw;
    private TextView percentageSettingsValueTxtVw;
    private TextView peopleCountSettingsValueTxtVw;

    private FloatingActionButton settingsFab;
    private RelativeLayout fragmentParent;
    private RelativeLayout quoteRelLyt;
    private LinearLayout settingsLnrLyt;
    private Button btnDone;
    private Button btnCancel;
    private SeekBar percentageSeekbar;
    private SeekBar peopleCountSeekbar;
    private Switch roundUpSwitch;


    private int finalYCoordinate;
    private int finalXCoordinate;
    private int minPercentageValue;
    private int maxPercentageValue;
    private int minPeopleCountValue;
    private int maxPeopleCountValue;
    private int percentageSeekbarStep;
    private int peopleCountSeekbarStep;
    private Animation settingsFabEnterAnimation;
    private QuoteManager quoteManager;
    private DisplayMetrics displayMetrics;
    private TipUiHandler tipUiHandler;
    private CompoundButton.OnCheckedChangeListener roundUpSwitchListener;

    public MainFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initializeInstanceVariables();
        initViews(view);
        setupAnimations();
        setupListeners(view);
        initDb();
        setQuote();

        startAnimations();
        return view;
    }

    private void initializeInstanceVariables() {
        displayMetrics = new DisplayMetrics();
        MainFragment.this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        finalYCoordinate = (int) (displayMetrics.heightPixels * 0.57);
        finalXCoordinate = displayMetrics.widthPixels / 2;
        tipUiHandler = new TipUiHandler();

        minPercentageValue = 5;
        maxPercentageValue = 25;
        minPeopleCountValue = 1;
        maxPeopleCountValue = 20;
        percentageSeekbarStep = 1;
        peopleCountSeekbarStep = 1;

    }

    private void setupListeners(View view) {
        settingsFab.setOnClickListener(newSettingsFabOnClickListener(view));
        billAmountEdtTxt.setFilters(new InputFilter[] { this.createFilter() });
        billAmountEdtTxt.addTextChangedListener(newBillAmountTextWatcher());
        percentageSeekbar.setOnSeekBarChangeListener(newPercentageSeekbarChangeListener());
        peopleCountSeekbar.setOnSeekBarChangeListener(newPeopleCountSeekbarChangeListener());
        roundUpSwitchListener = newRoundUpSwitchListener();
        roundUpSwitch.setOnCheckedChangeListener(roundUpSwitchListener);
    }

    private CompoundButton.OnCheckedChangeListener newRoundUpSwitchListener() {
        Log.d(TAG, "newRoundUpSwitchListener() called");
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isOn) {
                    Log.d(TAG, "onCheckedChanged() called with: " + "compoundButton = [" + compoundButton + "], isOn = [" + isOn + "]");
                  if(isOn) {
                      tipUiHandler.setRounding(Rounding.ON);
                      tipPercentageTxtVw.setText(tipUiHandler.getTipPercentage());
                      tipAmountTxtVw.setText(tipUiHandler.getTipAmount());
                      totalTxtVw.setText(tipUiHandler.getTotalAmount());
                      peopleCountTxtVw.setText(tipUiHandler.getNumberOfPeople());
                      eachPersonsShareTxtVw.setText(tipUiHandler.getEachPersonsShare());

                  } else {
                      tipUiHandler.setRounding(Rounding.OFF);
                      tipPercentageTxtVw.setText(tipUiHandler.getTipPercentage());
                      tipAmountTxtVw.setText(tipUiHandler.getTipAmount());
                      totalTxtVw.setText(tipUiHandler.getTotalAmount());
                      peopleCountTxtVw.setText(tipUiHandler.getNumberOfPeople());
                      eachPersonsShareTxtVw.setText(tipUiHandler.getEachPersonsShare());
                  }
            }
        };
    }

    private SeekBar.OnSeekBarChangeListener newPeopleCountSeekbarChangeListener() {
        Log.d(TAG, "newPeopleCountSeekbarChangeListener() called");
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d(TAG, "onProgressChanged() called with: " + "seekBar = [" + seekBar + "], i = [" + i + "], b = [" + b + "]");
                int change = minPeopleCountValue + (i * peopleCountSeekbarStep);
                peopleCountSettingsValueTxtVw.setText(Integer.toString(change));

                tipUiHandler.setNumberOfPeople(Integer.toString(change));
                tipUiHandler.setBillAmount(tipUiHandler.getBillAmount());
                tipPercentageTxtVw.setText(tipUiHandler.getTipPercentage());
                tipAmountTxtVw.setText(tipUiHandler.getTipAmount());
                totalTxtVw.setText(tipUiHandler.getTotalAmount());
                peopleCountTxtVw.setText(tipUiHandler.getNumberOfPeople());
                eachPersonsShareTxtVw.setText(tipUiHandler.getEachPersonsShare());

                roundUpSwitch.setOnCheckedChangeListener(null);
                roundUpSwitch.setOnCheckedChangeListener(roundUpSwitchListener);
                roundUpSwitch.setChecked(tipUiHandler.getRounding().equals(Rounding.ON));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch() called with: " + "seekBar = [" + seekBar + "]");

            }

            @Override
             public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch() called with: " + "seekBar = [" + seekBar + "]");

            }
        };
    }

    private SeekBar.OnSeekBarChangeListener newPercentageSeekbarChangeListener() {
        Log.d(TAG, "newPercentageSeekbarChangeListener() called");
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d(TAG, "onProgressChanged() called with: " + "seekBar = [" + seekBar + "], i = [" + i + "], b = [" + b + "]");
                int change = minPercentageValue + (i * percentageSeekbarStep);
                percentageSettingsValueTxtVw.setText(Integer.toString(change));
                tipUiHandler.setTipPercentage(Integer.toString(change));
                tipUiHandler.setBillAmount(tipUiHandler.getBillAmount());
                tipPercentageTxtVw.setText(tipUiHandler.getTipPercentage());
                tipAmountTxtVw.setText(tipUiHandler.getTipAmount());
                totalTxtVw.setText(tipUiHandler.getTotalAmount());
                peopleCountTxtVw.setText(tipUiHandler.getNumberOfPeople());
                eachPersonsShareTxtVw.setText(tipUiHandler.getEachPersonsShare());

                roundUpSwitch.setOnCheckedChangeListener(null);
                roundUpSwitch.setChecked(tipUiHandler.getRounding().equals(Rounding.ON));
                roundUpSwitch.setOnCheckedChangeListener(roundUpSwitchListener);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch() called with: " + "seekBar = [" + seekBar + "]");


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch() called with: " + "seekBar = [" + seekBar + "]");

            }
        };
    }

    private TextWatcher newBillAmountTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String billAmountString = s.toString();
                if (billAmountString.equals("") ||
                        billAmountString.equals("0") ||
                        billAmountString.equals("0.")) {
                    tipUiHandler.setBillAmount("0.00");
                    tipPercentageTxtVw.setText(tipUiHandler.getTipPercentage());
                    tipAmountTxtVw.setText(tipUiHandler.getTipAmount());
                    totalTxtVw.setText(tipUiHandler.getTotalAmount());
                    peopleCountTxtVw.setText(tipUiHandler.getNumberOfPeople());
                    eachPersonsShareTxtVw.setText(tipUiHandler.getEachPersonsShare());

                    roundUpSwitch.setOnCheckedChangeListener(null);
                    roundUpSwitch.setChecked(tipUiHandler.getRounding().equals(Rounding.ON));
                    roundUpSwitch.setOnCheckedChangeListener(roundUpSwitchListener);
                } else if (billAmountString.endsWith(".")) {
                    tipUiHandler.setBillAmount(billAmountString + "00");
                    tipPercentageTxtVw.setText(tipUiHandler.getTipPercentage());
                    tipAmountTxtVw.setText(tipUiHandler.getTipAmount());
                    totalTxtVw.setText(tipUiHandler.getTotalAmount());
                    peopleCountTxtVw.setText(tipUiHandler.getNumberOfPeople());
                    eachPersonsShareTxtVw.setText(tipUiHandler.getEachPersonsShare());

                    roundUpSwitch.setOnCheckedChangeListener(null);
                    roundUpSwitch.setChecked(tipUiHandler.getRounding().equals(Rounding.ON));
                    roundUpSwitch.setOnCheckedChangeListener(roundUpSwitchListener);
                } else if(Double.parseDouble(billAmountString) > 0) {
                    tipUiHandler.setBillAmount(billAmountString);
                    tipPercentageTxtVw.setText(tipUiHandler.getTipPercentage());
                    tipAmountTxtVw.setText(tipUiHandler.getTipAmount());
                    totalTxtVw.setText(tipUiHandler.getTotalAmount());
                    peopleCountTxtVw.setText(tipUiHandler.getNumberOfPeople());
                    eachPersonsShareTxtVw.setText(tipUiHandler.getEachPersonsShare());
                    roundUpSwitch.setOnCheckedChangeListener(null);
                    roundUpSwitch.setChecked(tipUiHandler.getRounding().equals(Rounding.ON));
                    roundUpSwitch.setOnCheckedChangeListener(roundUpSwitchListener);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    private InputFilter createFilter() {
        return new InputFilter() {
            final int maxDigitsBeforeDecimalPoint=9;
            final int maxDigitsAfterDecimalPoint=2;

            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                StringBuilder builder = new StringBuilder(dest);
                builder.replace(dstart, dend, source.subSequence(start, end).toString());

                /*
                    1. Allow only one leading 0.
                    2. Allow 'maxDigitsBeforeDecimalPoint' before decimal
                    3. Allow 'maxDigitsAfterDecimalPoint' after decimal
                 */
                if (!builder.toString().matches(
                        "(" +
                                "([0]{1})?" +
                        ")" +
                        "(" +
                                "([1-9]{1})([0-9]{0,"+(maxDigitsBeforeDecimalPoint-1)+"})?" +
                        ")?" +

                        "(" +
                                "\\.[0-9]{0,"+maxDigitsAfterDecimalPoint+"}" +
                        ")?"

                )) {
                    if(source.length()==0)
                        return dest.subSequence(dstart, dend);
                    return "";
                }

                return null;

            }
        };
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
        quoteRelLyt = (RelativeLayout) view.findViewById(R.id.activity_main_relLyt_quote_id);
        settingsLnrLyt = (LinearLayout) view.findViewById(R.id.activity_main_lnrLyt_settings_id);
        settingsFab = (FloatingActionButton) view.findViewById(R.id.fragment_main_settings_fab);
        billAmountEdtTxt = (EditText) view.findViewById(R.id.activity_main_edtTxt_bill_amount_id);
        tipAmountTxtVw = (TextView) view.findViewById(R.id.activity_main_txtVw_tip_amount_id);
        tipPercentageTxtVw = (TextView) view.findViewById(R.id.activity_main_txtVw_tip_percentage_id);
        eachPersonsShareTxtVw = (TextView) view.findViewById(R.id.activity_main_txtVw_share_id);
        peopleCountTxtVw = (TextView) view.findViewById(R.id.activity_main_txtVw_people_count_id);
        totalTxtVw = (TextView) view.findViewById(R.id.activity_main_txtVw_total_amount_id);
        percentageSeekbar = (SeekBar) view.findViewById(R.id.activity_main_skBr_tip_percentage_id);
        peopleCountSeekbar = (SeekBar) view.findViewById(R.id.activity_main_skBr_people_count_id);
        percentageSettingsValueTxtVw = (TextView) view.findViewById(R.id.activity_main_txtVw_tip_percentage_settings_value_id);
        peopleCountSettingsValueTxtVw = (TextView) view.findViewById(R.id.activity_main_txtVw_people_count_settings_value_id);
        roundUpSwitch = (Switch) view.findViewById(R.id.activity_main_swtch_round_up_id);

        //setting up seekbars
        percentageSeekbar.setMax(MAX_SEEKBAR_VALUE);
        peopleCountSeekbar.setMax(MAX_SEEKBAR_VALUE - minPeopleCountValue);

        //setup amounts, tips and shares
        billAmountEdtTxt.setText(EMPTY_STRING);
        tipAmountTxtVw.setText(ZERO_DECIMAL_STRING);
        tipPercentageTxtVw.setText(DEFAULT_PERCENTAGE_DECIMAL_STRING);
        eachPersonsShareTxtVw.setText(ZERO_DECIMAL_STRING);
        peopleCountTxtVw.setText(ONE_STRING);
        totalTxtVw.setText(ZERO_DECIMAL_STRING);

        int tipPercentage = Integer.parseInt(tipUiHandler.getTipPercentage());
        percentageSeekbar.setProgress(tipPercentage*percentageSeekbarStep - minPercentageValue);
        int percentageSeekbarValue = tipPercentage * percentageSeekbarStep;
        percentageSettingsValueTxtVw.setText(Integer.toString(percentageSeekbarValue));

        int peopleCount = (int) Double.parseDouble(tipUiHandler.getNumberOfPeople());
        peopleCountSeekbar.setProgress(peopleCount*peopleCountSeekbarStep - minPeopleCountValue);
        int peopleCountSeekbarValue = peopleCount * percentageSeekbarStep;
        peopleCountSettingsValueTxtVw.setText(Integer.toString(peopleCountSeekbarValue));
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
                } else {
                    quoteTxtVw.setVisibility(View.GONE);
                    quoteAuthorTextVw.setVisibility(View.GONE);
                    settingsFab.setVisibility(View.GONE);
                    settingsLnrLyt.setVisibility(View.VISIBLE);
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
            anim.setInterpolator(new AccelerateDecelerateInterpolator());
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
