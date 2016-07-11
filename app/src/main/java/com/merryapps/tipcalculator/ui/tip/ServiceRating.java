package com.merryapps.tipcalculator.ui.tip;

/**
 * Created by mephisto on 7/10/16.
 */
public enum ServiceRating {

    OK,
    GOOD,
    VERY_GOOD;

    /**
     * Get the number of service ratings possible.
     */
    public static int getServiceRatingCount() {
        return 3;
    }
}
