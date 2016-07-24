package com.merryapps.tipcalculator.model;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Tests TipCalculator.
 */
public class TipCalculatorTest {

    @Test
    public void testConstructor1WhenBillAmountIsNotNull() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.145"), new BigDecimal("25.381"), 1);
        assertNotNull(tipCalculator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor1WhenBillAmountIsNull() {
        new TipCalculator(null, new BigDecimal("25.381"), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor1WhenBillAmountIsLe0() {
        new TipCalculator(new BigDecimal("-1"), new BigDecimal("25.381"), 1);
    }

    @Test
    public void testConstructor1WhenTipPercentageIsNotNull() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.145"), new BigDecimal("25.381"), 1);
        assertNotNull(tipCalculator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor1WhenTipPercentageIsNull() {
        new TipCalculator(new BigDecimal("25.381"), null, 1);
    }

    @Test
    public void testConstructor1WhenNumberOfPeopleIsGt0() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.145"), new BigDecimal("25.381"), 1);
        assertNotNull(tipCalculator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor1WhenNumberOfPeopleIs0() {
        new TipCalculator(new BigDecimal("30.145"), new BigDecimal("25.381"), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor1WhenNumberOfPeopleIsLe0() {
        new TipCalculator(new BigDecimal("30.145"), new BigDecimal("25.381"), -1);
    }

    @Test
    public void testConstructor2WhenBillAmountIsNotNull() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.145"), 1, new BigDecimal("25.381"));
        assertNotNull(tipCalculator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2WhenBillAmountIsNull() {
        new TipCalculator(null, 1, new BigDecimal("25.381"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2WhenBillAmountIsLe0() {
        new TipCalculator(new BigDecimal("-1"), 1, new BigDecimal("25.381"));
    }

    @Test
    public void testConstructor2WhenTipAmountIsNotNull() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.145"), 1, new BigDecimal("25.381"));
        assertNotNull(tipCalculator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2WhenTipAmountIsNull() {
        new TipCalculator(new BigDecimal("25.381"), 1, null);
    }

    @Test
    public void testConstructor2WhenNumberOfPeopleIsGt0() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.145"), 1, new BigDecimal("25.381"));
        assertNotNull(tipCalculator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2WhenNumberOfPeopleIs0() {
        new TipCalculator(new BigDecimal("30.145"), 0, new BigDecimal("25.381"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2WhenNumberOfPeopleIsLe0() {
        new TipCalculator(new BigDecimal("30.145"), -1, new BigDecimal("25.381"));
    }

    @Test
    public void testConstructor1WhenBillAmountRoundingOffToFloor() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.141"), new BigDecimal("25.381"), 1);
        assertEquals(new BigDecimal("30.14"),tipCalculator.getBillAmount());
    }

    @Test
    public void testConstructor1WhenBillAmountRoundingOffToCeiling() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.145"), new BigDecimal("25.381"), 1);
        assertEquals(new BigDecimal("30.15"),tipCalculator.getBillAmount());
    }

    @Test
    public void testConstructor1WhenTipPercentageRoundingOffToFloor() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.141"), new BigDecimal("25.381"), 1);
        assertEquals(new BigDecimal("25.38"),tipCalculator.getTipPercentage());
    }

    @Test
    public void testConstructor1WhenTipPercentageRoundingOffToCeiling() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.145"), new BigDecimal("25.386"), 1);
        assertEquals(new BigDecimal("25.39"),tipCalculator.getTipPercentage());
    }

    @Test
    public void testConstructor1WhenBillAmountPrecisionIsLessThan2() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.1"), new BigDecimal("25.381"), 1);
        assertEquals(new BigDecimal("30.10"),tipCalculator.getBillAmount());
    }

    @Test
    public void testConstructor1WhenBillAmountPrecisionIsGreaterThan2() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.14123"), new BigDecimal("25.381"), 1);
        assertEquals(new BigDecimal("30.14"),tipCalculator.getBillAmount());
    }

    @Test
    public void testConstructor1WhenTipPercentagePrecisionIsLessThan2() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.141"), new BigDecimal("25.3"), 1);
        assertEquals(new BigDecimal("25.30"),tipCalculator.getTipPercentage());
    }

    @Test
    public void testConstructor1WhenTipPercentagePrecisionIsGreaterThan2() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.145"), new BigDecimal("25.31236"), 1);
        assertEquals(new BigDecimal("25.31"),tipCalculator.getTipPercentage());
    }

    @Test
    public void testConstructor2WhenBillAmountRoundingOffToFloor() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.141"), 1, new BigDecimal("25.381"));
        assertEquals(new BigDecimal("30.14"),tipCalculator.getBillAmount());
    }

    @Test
    public void testConstructor2WhenBillAmountRoundingOffToCeiling() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.145"), 1, new BigDecimal("25.381"));
        assertEquals(new BigDecimal("30.15"),tipCalculator.getBillAmount());
    }

    @Test
    public void testConstructor2WhenTipPercentageRoundingOffToFloor() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.141"), 1, new BigDecimal("25.381"));
        assertEquals(new BigDecimal("25.38"),tipCalculator.getTipAmount());
    }

    @Test
    public void testConstructor2WhenTipPercentageRoundingOffToCeiling() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.145"), 1, new BigDecimal("25.386"));
        assertEquals(new BigDecimal("25.39"),tipCalculator.getTipAmount());
    }

    @Test
    public void testConstructor2WhenBillAmountPrecisionIsLessThan2() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.1"), 1, new BigDecimal("25.381"));
        assertEquals(new BigDecimal("30.10"),tipCalculator.getBillAmount());
    }

    @Test
    public void testConstructor2WhenBillAmountPrecisionIsGreaterThan2() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.14123"), 1, new BigDecimal("25.381"));
        assertEquals(new BigDecimal("30.14"),tipCalculator.getBillAmount());
    }

    @Test
    public void testConstructor2WhenTipAmountPrecisionIsLessThan2() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.141"), 1, new BigDecimal("25.3"));
        assertEquals(new BigDecimal("25.30"),tipCalculator.getTipAmount());
    }

    @Test
    public void testConstructor2WhenTipAmountPrecisionIsGreaterThan2() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.145"), 1, new BigDecimal("25.31236"));
        assertEquals(new BigDecimal("25.31"),tipCalculator.getTipAmount());
    }

    @Test
    public void testGetBillAmount() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.1"), 1, new BigDecimal("25.381"));
        assertEquals(new BigDecimal("30.10"),tipCalculator.getBillAmount());
    }

    @Test
    public void testGetTipPercentage() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.1"), new BigDecimal("25.381"), 1);
        assertEquals(new BigDecimal("25.38"),tipCalculator.getTipPercentage());
    }

    @Test
    public void testGetTipAmount() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.1"), 1, new BigDecimal("25.381"));
        assertEquals(new BigDecimal("25.38"),tipCalculator.getTipAmount());
    }

    @Test
    public void testGetNumberOfPeople() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30.1"), 1, new BigDecimal("25.381"));
        assertEquals(1,tipCalculator.getNumberOfPeople());
    }

    @Test
    public void testConstructor1CheckTipAmountIsCorrect() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("30"), new BigDecimal("10"), 1);
        assertEquals(new BigDecimal("3.00"), tipCalculator.getTipAmount());
    }

    @Test
    public void testConstructor2CheckTipPercentageIsCorrect() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("40"), 1, new BigDecimal("5"));
        assertEquals(new BigDecimal("12.50"), tipCalculator.getTipPercentage());
    }

    @Test
    public void testConstructor1CheckTotalAmountIsCorrect() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("45.23"), new BigDecimal("13.34"), 1);
        assertEquals(new BigDecimal("51.26"), tipCalculator.getTotalAmount());
    }

    @Test
    public void testConstructor2CheckTotalAmountIsCorrect() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("40"), 1, new BigDecimal("5"));
        assertEquals(new BigDecimal("45.00"), tipCalculator.getTotalAmount());
    }

    @Test
    public void testConstructor1CheckEachPersonShareIsCorrect() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("45.23"), new BigDecimal("13.34"), 2);
        assertEquals(new BigDecimal("25.63"), tipCalculator.getEachPersonsShare());
    }

    @Test
    public void testConstructor2CheckEachPersonsShareIsCorrect() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("40"), 2, new BigDecimal("5"));
        assertEquals(new BigDecimal("22.50"), tipCalculator.getEachPersonsShare());
    }

    @Test
    public void testConstructor1WhenBillAmountIs0AndTipPercentageIs0() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("0"), new BigDecimal("0"), 1);
        assertEquals(new BigDecimal("0.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getEachPersonsShare());
    }

    @Test
    public void testConstructor2WhenBillAmountIs0AndTipAmountIs0() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("0"), 1, new BigDecimal("0"));
        assertEquals(new BigDecimal("0.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getEachPersonsShare());
    }

    @Test
    public void testEditTipPercentageWhenTipPercentageIsValid() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("40"), new BigDecimal("5"), 2);

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("5.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("21.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("42.00"), tipCalculator.getTotalAmount());

        //round total amount
        tipCalculator.editTipPercentage(new BigDecimal("10.00"));

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("10.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("4.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("22.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("44.00"), tipCalculator.getTotalAmount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testEditTipPercentageWhenTipPercentageIsInvalid() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("40"), new BigDecimal("5"), 2);
        //round total amount
        tipCalculator.editTipPercentage(new BigDecimal("-10.00"));
    }

    @Test
    public void testEditTipPercentageWhenTipPercentageIs0() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("40"), new BigDecimal("5"), 2);

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("5.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("21.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("42.00"), tipCalculator.getTotalAmount());

        //round total amount
        tipCalculator.editTipPercentage(new BigDecimal("0.00"));

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("20.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("40.00"), tipCalculator.getTotalAmount());
    }

    @Test
    public void testEditTipPercentageWhenTipPercentageHasMorePrecision() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("40"), new BigDecimal("5"), 2);

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("5.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("21.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("42.00"), tipCalculator.getTotalAmount());

        //round total amount
        tipCalculator.editTipPercentage(new BigDecimal("0.00495"));

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("20.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("40.00"), tipCalculator.getTotalAmount());
    }

    @Test
    public void testEditNumberOfPeopleWhenNumberIsValid() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("40"), new BigDecimal("5"), 2);

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("5.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("21.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("42.00"), tipCalculator.getTotalAmount());

        //round total amount
        tipCalculator.editNumberOfPeople(3);

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("5.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("14.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("42.00"), tipCalculator.getTotalAmount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEditNumberOfPeopleWhenNumberIsInvalid() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("40"), new BigDecimal("5"), 2);
        //modify the number of people
        tipCalculator.editNumberOfPeople(-1);
    }

    @Test
    public void testEditNumberOfPeopleWhenNumberIsSame() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("40"), new BigDecimal("5"), 2);

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("5.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("21.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("42.00"), tipCalculator.getTotalAmount());

        //round total amount
        tipCalculator.editNumberOfPeople(2);

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("5.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("21.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("42.00"), tipCalculator.getTotalAmount());
    }

    @Test
    public void testRoundTipAmountWhenTipAmountIsFractional() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("40"), new BigDecimal("6"), 2);

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("6.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.40"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("21.20"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("42.40"), tipCalculator.getTotalAmount());

        //round total amount
        tipCalculator.roundTotalAmount();

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("5.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("21.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("42.00"), tipCalculator.getTotalAmount());

    }

    @Test
    public void testRoundTotalAmountWhenTipAmountBecomesZero() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("1"), new BigDecimal("15"), 1);

        assertEquals(new BigDecimal("1.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("15.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("0.15"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("1.15"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("1.15"), tipCalculator.getTotalAmount());

        //round total amount
        tipCalculator.roundTotalAmount();

        assertEquals(new BigDecimal("1.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("1.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("1.00"), tipCalculator.getTotalAmount());
    }

    @Test
    public void testRoundTipAmountWhenTipAmountIsWhole() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("40"), new BigDecimal("5"), 2);

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("5.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("21.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("42.00"), tipCalculator.getTotalAmount());

        //round total amount
        tipCalculator.roundTotalAmount();

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("5.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("21.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("42.00"), tipCalculator.getTotalAmount());

    }

    @Test
    public void testRoundTotalAmountWhenTotalAmountIsCloseToNextWholeNumber() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("40"), new BigDecimal("7"), 2);

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("7.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.80"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("21.40"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("42.80"), tipCalculator.getTotalAmount());

        //round total amount
        tipCalculator.roundTotalAmount();

        assertEquals(new BigDecimal("40.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("7.50"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("3.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("21.50"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("43.00"), tipCalculator.getTotalAmount());

    }

    @Test
    public void testRoundTotalAmountWhenTotalAmountIsHalfAFraction() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("50"), new BigDecimal("5"), 2);

        assertEquals(new BigDecimal("50.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("5.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.50"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("26.25"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("52.50"), tipCalculator.getTotalAmount());

        //round total amount
        tipCalculator.roundTotalAmount();

        assertEquals(new BigDecimal("50.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("6.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("3.00"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("26.50"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("53.00"), tipCalculator.getTotalAmount());

    }

    @Test
    public void testRoundTotalAmountWhenTotalAmountIsFractional() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("37.43"), new BigDecimal("6"), 2);

        assertEquals(new BigDecimal("37.43"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("6.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.25"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("19.84"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("39.68"), tipCalculator.getTotalAmount());

        //round total amount
        tipCalculator.roundTotalAmount();

        assertEquals(new BigDecimal("37.43"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("6.87"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.57"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("20.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("40.00"), tipCalculator.getTotalAmount());

    }

    @Test
    public void testConstructor3WhenBillAmountIsValid() {

        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("50.00"));

        assertEquals(new BigDecimal("50.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getTipAmount());
        assertEquals(1, tipCalculator.getNumberOfPeople());
        assertEquals(new BigDecimal("50.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("50.00"), tipCalculator.getTotalAmount());

    }

    @Test
    public void testConstructor3WhenBillAmountIsZero() {

        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("00.00"));

        assertEquals(new BigDecimal("00.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("0.00"), tipCalculator.getTipAmount());
        assertEquals(1, tipCalculator.getNumberOfPeople());
        assertEquals(new BigDecimal("00.00"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("00.00"), tipCalculator.getTotalAmount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor3WhenBillAmountIsInvalid() {
        new TipCalculator(new BigDecimal("-10.00"));
    }

    @Test
    public void testEditBillAmountWhenBillAmountIsValid() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("50"), new BigDecimal("5"), 2);

        assertEquals(new BigDecimal("50.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("5.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.50"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("26.25"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("52.50"), tipCalculator.getTotalAmount());

        //round total amount
        tipCalculator.editBillAmount(new BigDecimal("55.00"));

        assertEquals(new BigDecimal("55.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("5.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.75"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("28.88"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("57.75"), tipCalculator.getTotalAmount());
    }

    @Test
    public void testEditBillAmountWhenBillAmountHasMorePrecision() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("50"), new BigDecimal("5"), 2);

        assertEquals(new BigDecimal("50.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("5.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.50"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("26.25"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("52.50"), tipCalculator.getTotalAmount());

        //round total amount
        tipCalculator.editBillAmount(new BigDecimal("55.00123"));

        assertEquals(new BigDecimal("55.00"), tipCalculator.getBillAmount());
        assertEquals(new BigDecimal("5.00"), tipCalculator.getTipPercentage());
        assertEquals(new BigDecimal("2.75"), tipCalculator.getTipAmount());
        assertEquals(new BigDecimal("28.88"), tipCalculator.getEachPersonsShare());
        assertEquals(new BigDecimal("57.75"), tipCalculator.getTotalAmount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEditBillAmountWhenBillAmountIsInvalid() {
        TipCalculator tipCalculator = new TipCalculator(new BigDecimal("50"));
        tipCalculator.editBillAmount(new BigDecimal("-55.00"));
    }


}
