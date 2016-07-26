package com.merryapps.tipcalculator.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Calculates the tip based on the bill amount and percentage of tip given.
 * Also splits up the total amount (bill amount + tip amount) amongst individuals.
 * All operations are performed by setting the precision to 2 with {@code BigDecimal.HALF_UP} rounding mode.
 */
public class TipCalculator {

    private static final RoundingMode ROUNDING_MODE_HALF_UP = RoundingMode.HALF_UP;
    private final int SCALE = 2;

    private BigDecimal billAmount;
    private BigDecimal tipPercentage;
    private BigDecimal tipAmount;
    private BigDecimal totalAmount;
    private int numberOfPeople;
    private BigDecimal eachPersonsShare;

    private Rounding rounding;

    /**
     * Creates a new TipCalculator with the given details.
     * @param billAmount the bill amount. Must be non-negative and not null.
     * @param tipPercentage the tip percentage. Must be non-negative and not null.
     * @param numberOfPeople the number of people amongst whom the total will be split. Must be greater than or equal to 1.
     */
    public TipCalculator(BigDecimal billAmount, BigDecimal tipPercentage, int numberOfPeople) {

        if (billAmount == null || tipPercentage == null) {
            throw new IllegalArgumentException("billAmount and/or tipPercentage cannot be null");
        }

        if(numberOfPeople <= 0) {
            throw new IllegalArgumentException("numberOfPeople cannot be <= 0");
        }

        this.billAmount = billAmount.setScale(SCALE, ROUNDING_MODE_HALF_UP);
        this.tipPercentage = tipPercentage.setScale(SCALE, ROUNDING_MODE_HALF_UP);
        this.numberOfPeople = numberOfPeople;
        this.rounding = Rounding.OFF;

        if(this.billAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("billAmount cannot be < 0");
        }

        this.tipAmount = calculateTipAmountFromTipPercentage();
        this.totalAmount = calculateTotalAmount();
        this.eachPersonsShare = calculateEachPersonsShare();


    }

    /**
     * Creates a new TipCalculator with the given details.
     * @param billAmount the bill amount. Must be non-negative and not null.
     * @param numberOfPeople the number of people amongst whom the total will be split. Must be greater than or equal to 1.
     * @param tipAmount the tip amount. Must be non-negative and not null.
     */
    public TipCalculator(BigDecimal billAmount, int numberOfPeople, BigDecimal tipAmount) {

        if (billAmount == null || tipAmount == null) {
            throw new IllegalArgumentException("billAmount and/or tipPercentage cannot be null");
        }

        if(numberOfPeople <= 0) {
            throw new IllegalArgumentException("numberOfPeople cannot be <= 0");
        }

        this. billAmount = billAmount.setScale(SCALE, ROUNDING_MODE_HALF_UP);
        this.tipAmount = tipAmount.setScale(SCALE, ROUNDING_MODE_HALF_UP);
        this.numberOfPeople = numberOfPeople;
        this.rounding = Rounding.OFF;

        if(this.billAmount.compareTo(new BigDecimal("0")) < 0) {
            throw new IllegalArgumentException("billAmount cannot be < 0");
        }

        if(this.billAmount.equals(new BigDecimal("0.00"))) {
            this.tipPercentage = new BigDecimal("0.00");
        } else {
            this.tipPercentage = calculateTipPercentageFromTipAmount();
        }

        this.totalAmount = calculateTotalAmount();
        this.eachPersonsShare = calculateEachPersonsShare();
    }

    public TipCalculator(BigDecimal billAmount) {
        this(billAmount, 1, BigDecimal.ZERO);
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public BigDecimal getTipPercentage() {
        return tipPercentage;
    }

    public BigDecimal getTipAmount() {
        return tipAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public BigDecimal getEachPersonsShare() {
        return eachPersonsShare;
    }

    public Rounding getRounding() {
        return rounding;
    }

    private BigDecimal calculateTipAmountFromTipPercentage() {
        double result = this.billAmount.doubleValue() * this.tipPercentage.doubleValue() / 100;
        return new BigDecimal(result).setScale(SCALE, ROUNDING_MODE_HALF_UP);
    }

    private BigDecimal calculateTipPercentageFromTipAmount() {
        double result = this.tipAmount.doubleValue() / this.billAmount.doubleValue() * 100;
        return new BigDecimal(result).setScale(SCALE, ROUNDING_MODE_HALF_UP);
    }

    private BigDecimal calculateTotalAmount() {
        double result = this.billAmount.doubleValue() + this.tipAmount.doubleValue();
        BigDecimal totalAmount = new BigDecimal(result).setScale(SCALE, ROUNDING_MODE_HALF_UP);

        switch (rounding) {
            case ON: {
                return roundUp(totalAmount);
            }
            case OFF: {
                return totalAmount;
            }
        }

        throw new AssertionError("Rounding not defined");
    }

    private BigDecimal calculateEachPersonsShare() {
        double result = this.totalAmount.doubleValue() / numberOfPeople;
        return new BigDecimal(result).setScale(SCALE, ROUNDING_MODE_HALF_UP);
    }

    /**
     * Edits the tip percentage used for  the tip calculation. New value must be greater than or equal to 0.
     * Dependent values are recalculated.
     * @param newTipPercentage new value.
     */
    public void editTipPercentage(BigDecimal newTipPercentage) {

        if (newTipPercentage.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Tip percentage cannot be < 0. Tip percentage is:" + newTipPercentage);
        }

        this.tipPercentage = newTipPercentage.setScale(SCALE,ROUNDING_MODE_HALF_UP);
        this.tipAmount = calculateTipAmountFromTipPercentage();
        this.totalAmount = calculateTotalAmount();
        this.eachPersonsShare = calculateEachPersonsShare();
    }

    /**
     * Edits the people count. Count must be greater than 1.
     * Dependent values are recalculated.
     * @param newNumberOfPeople new count
     */
    public void editNumberOfPeople(int newNumberOfPeople) {
        if(newNumberOfPeople < 1) {
            throw new IllegalArgumentException("Number of people cannot be < 1. Number of people = " + newNumberOfPeople);
        }

        this.numberOfPeople = newNumberOfPeople;
        this.tipAmount = calculateTipAmountFromTipPercentage();
        //this.totalAmount = calculateTotalAmount();
        this.eachPersonsShare = calculateEachPersonsShare();
    }

    /**
     * Rounds the tip amount to the nearest integer.
     * If the tip amount is less than half the fraction,
     * it is rounded to the highest integer not greater than
     * the current tip amount. Else it is rounded to the next highest integer.
     * Dependent values are recalculated.
     */
    public void setTotalAmountRounding(Rounding rounding) {

        this.rounding = rounding;
        this.totalAmount = calculateTotalAmount();
        this.eachPersonsShare = calculateEachPersonsShare();
    }

    private BigDecimal roundUp(BigDecimal amountToBeRounded) {

        BigInteger integralPart = amountToBeRounded.toBigInteger();

        if(amountToBeRounded.compareTo(new BigDecimal(integralPart.intValue() + 0.5)) >= 0) {
            amountToBeRounded = new BigDecimal(integralPart.intValue() + 1).setScale(SCALE, ROUNDING_MODE_HALF_UP);
        } else {
            amountToBeRounded = new BigDecimal(integralPart).setScale(SCALE, ROUNDING_MODE_HALF_UP);
        }

        return amountToBeRounded;
    }

    public void editBillAmount(BigDecimal newBillAmount) {
        if(newBillAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Bill Amount cannot be < 0. Bill amount found to be " + newBillAmount);
        }
        this.billAmount = newBillAmount.setScale(SCALE, ROUNDING_MODE_HALF_UP);
        this.tipAmount = calculateTipAmountFromTipPercentage();
        this.totalAmount = calculateTotalAmount();
        this.eachPersonsShare = calculateEachPersonsShare();
    }
}
