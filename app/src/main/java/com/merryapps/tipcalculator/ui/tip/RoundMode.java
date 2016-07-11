package com.merryapps.tipcalculator.ui.tip;

/**
 * Created by mephisto on 7/10/16.
 */
public enum RoundMode {

    ROUNDED(true),
    NOT_ROUNDED(false);

    private boolean isRounded;

    RoundMode(boolean isRounded) {
        this.isRounded = isRounded;
    }

    public boolean isRounded() {
        return isRounded;
    }

    public static RoundMode getRoundMode(boolean isRounded) {
        if (isRounded) {
            return ROUNDED;
        } else {
            return NOT_ROUNDED;
        }
    }
}
