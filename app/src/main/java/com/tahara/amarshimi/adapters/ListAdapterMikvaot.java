package com.tahara.amarshimi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tahata.amarshimi.tahara.R;

/**
 * Created by amarshimi on 2/22/2015.
 */

public class ListAdapterMikvaot extends BaseAdapter {

    private LayoutInflater layoutInflater;
    String[] list;

    public ListAdapterMikvaot(Context context, String[] list) {
        super();
        layoutInflater = LayoutInflater.from(context);
        this.list = list;

        // TODO Auto-generated constructor stub
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.length;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.mikvaot_list_item,
                    null);
            holder.txt1 = (TextView) convertView.findViewById(R.id.textView1);
            holder.txt2 = (TextView) convertView.findViewById(R.id.textView3);
            holder.txt3 = (TextView) convertView.findViewById(R.id.textView4);

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt1.setText(list[0]);
        holder.txt2.setText(list[1]);
        holder.txt3.setText(list[2]);

        return convertView;
    }

    class ViewHolder {
        public TextView txt1;
        public TextView txt2;
        public TextView txt3;

    }

}

