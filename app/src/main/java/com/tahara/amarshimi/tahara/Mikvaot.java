package com.tahara.amarshimi.tahara;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tahara.amarshimi.adapters.ListAdapterMikvaot;
import com.tahata.amarshimi.tahara.R;

/**
 * Created by amarshimi on 2/22/2015.
 */
public class Mikvaot extends Fragment {

    ListView list;
    String str [];
    ListAdapterMikvaot adapterMikvaot;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.mikvaot_list, container, false);
        list =(ListView)view.findViewById(R.id.lvcards);
        str = new String[]{"shimi","amar","noam","yagel","shimi","amar","noam","yagel"};

        adapterMikvaot = new ListAdapterMikvaot(getActivity(), str);

        list.setAdapter(adapterMikvaot);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "dfs", Toast.LENGTH_LONG).show();
            }
        });



        return view;
    }

}
