package com.merryapps.tipcalculator.ui.tip;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.merryapps.tipcalculator.R;
import com.merryapps.tipcalculator.model.core.Quote;
import com.merryapps.tipcalculator.model.core.QuoteManager;
import com.merryapps.tipcalculator.ui.framework.AbstractFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends AbstractFragment {

    private TextView quoteTxtVw;
    private TextView quoteAuthorTextVw;
    private FloatingActionButton settingsFab;

    public MainFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        quoteTxtVw = (TextView) view.findViewById(R.id.activity_main_txtVw_quote_id);
        quoteAuthorTextVw = (TextView) view.findViewById(R.id.activity_main_txtVw_quote_author_id);
        settingsFab = (FloatingActionButton) view.findViewById(R.id.fragment_main_settings_fab);
        settingsFab.setOnClickListener(newSettingsFabOnClickListener());
        Animation animation = AnimationUtils.loadAnimation(this.getActivity(), R.anim.anim_settings_fab_enter);
        settingsFab.startAnimation(animation);
        initDb();
        setQuote();
        return view;
    }

    private View.OnClickListener newSettingsFabOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsDialogFragment dialogFragment = new SettingsDialogFragment();
                dialogFragment.show(MainFragment.this.getActivity().getSupportFragmentManager(), "SettingsDialogFragment");
            }
        };
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
