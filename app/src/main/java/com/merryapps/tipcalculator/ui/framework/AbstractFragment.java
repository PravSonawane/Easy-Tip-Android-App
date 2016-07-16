package com.merryapps.tipcalculator.ui.framework;


import android.support.v4.app.Fragment;

import com.merryapps.tipcalculator.TipCalculatorApp;
import com.merryapps.tipcalculator.model.core.ManagerFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class AbstractFragment extends Fragment {


    public AbstractFragment() {
        // Required empty public constructor
    }

    public ManagerFactory managerFactory() {
        return ((TipCalculatorApp) getActivity().getApplication()).getManagerFactory();
    }

    public String getProperty(String property, String file) {
        return ((TipCalculatorApp) getActivity().getApplication()).getProperty(property,file);
    }

}
