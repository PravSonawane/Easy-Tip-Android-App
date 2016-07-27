package com.merryapps.easytip.ui.tip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.merryapps.easytip.R;

public class MainActivity extends AppCompatActivity {

    //TODO No digits after two decimal points
    //TODO Change color schemes
    //TODO opacity of hint text. Lower it
    //TODO add reset icon in actionbar
    //TODO add share icon in actionbar
    //TODO refactor split to numberOfPeople

    private static final String TAG = "MainActivity";

    private TipUiHandler tipUiHandler;

    private EditText billAmountEdtTxt;

    private FrameLayout fragmentPlaceholder;

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
        this.fragmentPlaceholder = (FrameLayout) findViewById(R.id.activity_main_frmLyt_placeholder_id);

        initViews();
    }

    private void initViews() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_frmLyt_placeholder_id, new MainFragment()).commit();

    }
}