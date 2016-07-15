package com.merryapps.tipcalculator.ui.tip;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.merryapps.tipcalculator.R;

public class MainActivity extends AppCompatActivity {

    //TODO No digits after two decimal points
    //TODO Change color schemes
    //TODO opacity of hint text. Lower it
    //TODO add reset icon in actionbar
    //TODO add share icon in actionbar
    //TODO refactor split to numberOfPeople

    private FloatingActionButton settingsFab;

    private static final String TAG = "MainActivity";

    private TipUiHandler tipUiHandler;

    private EditText billAmountEdtTxt;
    private TextView tipAmountTxtVw;
    private TextView tipPercentageTxtVw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initUiVariables();
    }

    private void initUiVariables() {
        Log.i(TAG, "initUiVariables: called");
        this.billAmountEdtTxt = (EditText) findViewById(R.id.activity_main_edtTxt_bill_amount_id);

        initViews();
    }

    private void initViews() {
        settingsFab = (FloatingActionButton) findViewById(R.id.fragment_main_settings_fab);
        settingsFab.setOnClickListener(newSettingsFabOnClickListener());
    }

    private View.OnClickListener newSettingsFabOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsDialogFragment dialogFragment = new SettingsDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "SettingsDialogFragment");
            }
        };
    }

}