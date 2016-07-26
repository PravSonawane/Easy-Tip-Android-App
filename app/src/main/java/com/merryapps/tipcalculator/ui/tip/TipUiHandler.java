package com.merryapps.tipcalculator.ui.tip;

import com.merryapps.tipcalculator.model.Rounding;
import com.merryapps.tipcalculator.model.TipCalculator;

import java.math.BigDecimal;

/**
 * Created by mephisto on 7/4/16.
 */
public class TipUiHandler {

    private static final String TAG = "TipUiHandler";
    private static final String EMPTY_STRING = "";
    private static final String DECIMAL_0_STRING = "0.00";
    private static final String INTEGER_1_STRING = "1";
    private static final String INTEGER_15_STRING = "15";

    private String billAmount;
    private String tipPercentage;
    private String tipAmount;
    private String totalAmount;
    private String numberOfPeople;
    private String eachPersonsShare;

    private Rounding rounding;

    private TipCalculator currentTipCalculator;

    public TipUiHandler() {
        this.billAmount = EMPTY_STRING;
        this.tipPercentage = INTEGER_15_STRING;
        this.tipAmount = DECIMAL_0_STRING;
        this.numberOfPeople = INTEGER_1_STRING;
        this.eachPersonsShare = DECIMAL_0_STRING;
        this.totalAmount = DECIMAL_0_STRING;

        this.rounding = Rounding.OFF;
        this.currentTipCalculator = new TipCalculator(
                new BigDecimal(DECIMAL_0_STRING),
                new BigDecimal(this.tipPercentage),
                Integer.parseInt(this.numberOfPeople));

    }

    public String getBillAmount() {
        return this.currentTipCalculator.getBillAmount().toString();
    }

    public Rounding getRounding() {
        return rounding;
    }

    void setRounding(Rounding rounding) {
        switch (rounding) {
            case ON:
               this.currentTipCalculator.setTotalAmountRounding(Rounding.ON);
                break;
            case OFF:
                this.currentTipCalculator.setTotalAmountRounding(Rounding.OFF);
                break;
        }
        this.rounding = rounding;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
        this.currentTipCalculator.editBillAmount(new BigDecimal(this.billAmount));
    }

    public String getTipPercentage() {
        return this.currentTipCalculator.getTipPercentage().toString();
    }

    public void setTipPercentage(String tipPercentage) {
        this.tipPercentage = tipPercentage;
        this.currentTipCalculator.editTipPercentage(new BigDecimal(this.tipPercentage));
    }

    public String getTipAmount() {
        return this.currentTipCalculator.getTipAmount().toString();
    }

    public String getTotalAmount() {
        return this.currentTipCalculator.getTotalAmount().toString();
    }

    public String getNumberOfPeople() {
        return Integer.toString(this.currentTipCalculator.getNumberOfPeople());
    }

    public void setNumberOfPeople(String numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        this.currentTipCalculator.editNumberOfPeople(Integer.parseInt(numberOfPeople));
    }

    public String getEachPersonsShare() {
        return this.currentTipCalculator.getEachPersonsShare().toString();
    }

}
