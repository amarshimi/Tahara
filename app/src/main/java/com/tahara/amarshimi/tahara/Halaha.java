package com.tahara.amarshimi.tahara;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.tahara.amarshimi.adapters.GridAdapterAnalitics;
import com.tahata.amarshimi.tahara.R;

/**
 * Created by amarshimi on 2/22/2015.
 */
public class Halaha extends Fragment {

    GridView grid;
    String str[];
    GridAdapterAnalitics adapterAnalitics;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.grid_analitics, container, false);

        str = new String[]{"a","a","a"};
        grid = (GridView)view.findViewById(R.id.gvanalitics);

        adapterAnalitics = new GridAdapterAnalitics(getActivity(), str);
        grid.setAdapter(adapterAnalitics);

        return view;
    }

}
