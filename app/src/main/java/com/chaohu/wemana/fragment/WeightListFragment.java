package com.chaohu.wemana.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaohu.wemana.R;

/**
 * Created by chaohu on 2016/3/30.
 */
public class WeightListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_wemana_measure,container,false);
        return view;
    }
}