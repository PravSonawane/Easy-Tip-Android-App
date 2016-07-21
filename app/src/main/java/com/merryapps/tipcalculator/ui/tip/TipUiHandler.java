package com.merryapps.tipcalculator.ui.tip;

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

    private RoundMode roundMode;

    private TipCalculator currentTipCalculator;
    private TipCalculator preRoundingTipCalculator;

    public TipUiHandler() {
        this.billAmount = EMPTY_STRING;
        this.tipPercentage = INTEGER_15_STRING;
        this.tipAmount = DECIMAL_0_STRING;
        this.numberOfPeople = INTEGER_1_STRING;
        this.eachPersonsShare = DECIMAL_0_STRING;
        this.totalAmount = DECIMAL_0_STRING;

        this.roundMode = RoundMode.NOT_ROUNDED;
        this.currentTipCalculator = new TipCalculator(
                new BigDecimal(DECIMAL_0_STRING),
                new BigDecimal(this.tipPercentage),
                Integer.parseInt(this.numberOfPeople));

    }

    public String getBillAmount() {
        return this.currentTipCalculator.getBillAmount().toString();
    }

    public RoundMode getRoundMode() {
        return roundMode;
    }

    void setRoundMode(RoundMode roundMode) {
        this.roundMode = roundMode;
        switch (roundMode) {
            case ROUNDED:
                //take a backup before rounding
                this.preRoundingTipCalculator = new TipCalculator(this.currentTipCalculator.getBillAmount(),
                        this.currentTipCalculator.getTipPercentage(),
                        this.currentTipCalculator.getNumberOfPeople());
                this.currentTipCalculator.roundTotalAmount();
                break;
            case NOT_ROUNDED:
                if (this.preRoundingTipCalculator == null) {
                    throw new IllegalStateException("No pre rounding data found! " +
                            "This probably occurred because you were trying to remove rounding when its already not rounded!");
                }
                this.currentTipCalculator = preRoundingTipCalculator;
                this.preRoundingTipCalculator = null;
                break;
        }
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
        if (this.getRoundMode().equals(RoundMode.ROUNDED)) {
            this.setRoundMode(RoundMode.NOT_ROUNDED);
        }
        this.currentTipCalculator.editBillAmount(new BigDecimal(this.billAmount));
    }

    public String getTipPercentage() {
        return this.currentTipCalculator.getTipPercentage().toString();
    }

    public void setTipPercentage(String tipPercentage) {
        this.tipPercentage = tipPercentage;
        if (this.getRoundMode().equals(RoundMode.ROUNDED)) {
            this.setRoundMode(RoundMode.NOT_ROUNDED);
        }
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
        if (this.getRoundMode().equals(RoundMode.ROUNDED)) {
            this.setRoundMode(RoundMode.NOT_ROUNDED);
        }
        this.currentTipCalculator.editNumberOfPeople(Integer.parseInt(numberOfPeople));
    }

    public String getEachPersonsShare() {
        return this.currentTipCalculator.getEachPersonsShare().toString();
    }

}
