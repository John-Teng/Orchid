package com.tengo.orchid.View;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by johnteng on 2018-03-10.
 */

public class SinglePhotoFragment extends android.support.v4.app.Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // SHOULDNT BE NEEDED
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
