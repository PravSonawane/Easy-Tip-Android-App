package com.merryapps.easytip.ui.tip;

import android.util.Log;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.merryapps.easytip.model.tip.Rounding;
import com.merryapps.easytip.model.tip.TipCalculator;

import java.math.BigDecimal;
import java.util.Locale;

/**
 * Created by mephisto on 7/4/16.
 */
public class TipUiHandler {

    private static final String TAG = "TipUiHandler";

    //references to views of the UI
    private EditText billAmountEdtTxt;
    private TextView tipAmountTxtVw;
    private TextView tipPercentageTxtVw;
    private TextView eachPersonsShareTxtVw;
    private TextView peopleCountTxtVw;
    private TextView totalTxtVw;
    private Switch roundUpSwitch;
    private TextView percentageSettingsValueTxtVw;
    private TextView peopleCountSettingsValueTxtVw;

    private static final String EMPTY_STRING = "";
    private static final String DECIMAL_0_STRING = "0.00";
    private static final String INTEGER_1_STRING = "1";
    private static final String INTEGER_15_STRING = "15";
    private static final String SEEKBAR_VALUE_FORMAT_STRING = "%1$02d";

    private String billAmount;
    private String tipPercentage;
    private String numberOfPeople;

    private Rounding rounding;

    private TipCalculator tipCalculator;

    public TipUiHandler() {
        Log.d(TAG, "TipUiHandler() called");

        this.billAmount = EMPTY_STRING;
        this.tipPercentage = INTEGER_15_STRING;
        this.numberOfPeople = INTEGER_1_STRING;

        this.rounding = Rounding.OFF;
        this.tipCalculator = new TipCalculator(
                new BigDecimal(DECIMAL_0_STRING),
                new BigDecimal(this.tipPercentage),
                Integer.parseInt(this.numberOfPeople));

    }

    public String getBillAmount() {
        return this.tipCalculator.getBillAmount().toString();
    }

    public Rounding getRounding() {
        return rounding;
    }

    void setRounding(Rounding rounding) {
        Log.d(TAG, "setRounding() called with: " + "rounding = [" + rounding + "]");

        switch (rounding) {
            case ON:
               this.tipCalculator.setTotalAmountRounding(Rounding.ON);
                break;
            case OFF:
                this.tipCalculator.setTotalAmountRounding(Rounding.OFF);
                break;
        }
        this.rounding = rounding;

        Log.v(TAG, "setRounding: updating views");
        totalTxtVw.setText(this.getTotalAmount());
        eachPersonsShareTxtVw.setText(this.getEachPersonsShare());
    }

    public void setBillAmount(String billAmount) {
        Log.d(TAG, "setBillAmount() called with: " + "billAmount = [" + billAmount + "]");

        this.billAmount = billAmount;
        this.tipCalculator.editBillAmount(new BigDecimal(this.billAmount));

        Log.v(TAG, "setBillAmount: updating views");
        tipAmountTxtVw.setText(this.getTipAmount());
        totalTxtVw.setText(this.getTotalAmount());
        eachPersonsShareTxtVw.setText(this.getEachPersonsShare());
    }

    public String getTipPercentage() {
        return this.tipCalculator.getTipPercentage().toBigInteger().toString();
    }

    public void setTipPercentage(String tipPercentage) {
        Log.d(TAG, "setTipPercentage() called with: " + "tipPercentage = [" + tipPercentage + "]");

        this.tipPercentage = tipPercentage;
        this.tipCalculator.editTipPercentage(new BigDecimal(this.tipPercentage));

        Log.v(TAG, "setTipPercentage: updating views");
        tipPercentageTxtVw.setText(this.getTipPercentage());
        tipAmountTxtVw.setText(this.getTipAmount());
        totalTxtVw.setText(this.getTotalAmount());
        eachPersonsShareTxtVw.setText(this.getEachPersonsShare());
        percentageSettingsValueTxtVw.setText(String.format(Locale.getDefault(),
                SEEKBAR_VALUE_FORMAT_STRING,Integer.valueOf(tipPercentage)));
    }

    public String getTipAmount() {
        return this.tipCalculator.getTipAmount().toString();
    }

    public String getTotalAmount() {
        return this.tipCalculator.getTotalAmount().toString();
    }

    public String getNumberOfPeople() {
        return Integer.toString(this.tipCalculator.getNumberOfPeople());
    }

    public void setNumberOfPeople(String numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        this.tipCalculator.editNumberOfPeople(Integer.parseInt(numberOfPeople));

        Log.v(TAG, "setNumberOfPeople: updating views");
        peopleCountTxtVw.setText(this.getNumberOfPeople());
        eachPersonsShareTxtVw.setText(this.getEachPersonsShare());
        peopleCountSettingsValueTxtVw.setText(String.format(Locale.getDefault(),
                SEEKBAR_VALUE_FORMAT_STRING,Integer.valueOf(numberOfPeople)));
    }

    public String getEachPersonsShare() {
        return this.tipCalculator.getEachPersonsShare().toString();
    }

    public void setBillAmountEdtTxt(EditText billAmountEdtTxt) {
        this.billAmountEdtTxt = billAmountEdtTxt;
    }

    public void setTipAmountTxtVw(TextView tipAmountTxtVw) {
        this.tipAmountTxtVw = tipAmountTxtVw;
    }

    public void setTipPercentageTxtVw(TextView tipPercentageTxtVw) {
        this.tipPercentageTxtVw = tipPercentageTxtVw;
    }

    public void setEachPersonsShareTxtVw(TextView eachPersonsShareTxtVw) {
        this.eachPersonsShareTxtVw = eachPersonsShareTxtVw;
    }

    public void setPeopleCountTxtVw(TextView peopleCountTxtVw) {
        this.peopleCountTxtVw = peopleCountTxtVw;
    }

    public void setTotalTxtVw(TextView totalTxtVw) {
        this.totalTxtVw = totalTxtVw;
    }

    public void setRoundUpSwitch(Switch roundUpSwitch) {
        this.roundUpSwitch = roundUpSwitch;
    }

    public void setPercentageSettingsValueTxtVw(TextView percentageSettingsValueTxtVw) {
        this.percentageSettingsValueTxtVw = percentageSettingsValueTxtVw;
    }

    public void setPeopleCountSettingsValueTxtVw(TextView peopleCountSettingsValueTxtVw) {
        this.peopleCountSettingsValueTxtVw = peopleCountSettingsValueTxtVw;
    }

    public void reset() {
        tipCalculator = new TipCalculator(BigDecimal.ZERO, new BigDecimal(INTEGER_15_STRING), 1);
        billAmountEdtTxt.setText(EMPTY_STRING);
        tipPercentageTxtVw.setText(this.getTipPercentage());
        tipAmountTxtVw.setText(this.getTipAmount());
        peopleCountTxtVw.setText(this.getNumberOfPeople());
        eachPersonsShareTxtVw.setText(this.getEachPersonsShare());
        totalTxtVw.setText(this.getTotalAmount());
        percentageSettingsValueTxtVw.setText(String.format(Locale.getDefault(),
                SEEKBAR_VALUE_FORMAT_STRING, Integer.valueOf(this.getTipPercentage())));
        peopleCountSettingsValueTxtVw.setText(String.format(Locale.getDefault(),
                SEEKBAR_VALUE_FORMAT_STRING, Integer.valueOf(this.getNumberOfPeople())));
    }
}
