package com.merryapps.easytip.ui.framework;


import android.support.v4.app.Fragment;

import com.merryapps.easytip.EasyTipApp;
import com.merryapps.easytip.model.framework.ManagerFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class AbstractFragment extends Fragment {


    public AbstractFragment() {
        // Required empty public constructor
    }

    public ManagerFactory managerFactory() {
        return ((EasyTipApp) getActivity().getApplication()).getManagerFactory();
    }

    public String getProperty(String property, String file) {
        return ((EasyTipApp) getActivity().getApplication()).getProperty(property,file);
    }

}
