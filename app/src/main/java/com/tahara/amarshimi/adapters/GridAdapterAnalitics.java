package com.tahara.amarshimi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.tahata.amarshimi.tahara.R;


/**
 * Created by amarshimi on 2/22/2015.
 */

public class GridAdapterAnalitics extends BaseAdapter {

    LayoutInflater layoutInflater;
    String[] list;



    public GridAdapterAnalitics( Context context ,String[] list) {
        super();
        this.list=list;
        layoutInflater = LayoutInflater.from(context);
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
            convertView = layoutInflater.inflate(R.layout.grid_analitics_item,
                    null);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //holder.image.setImageResource(R.drawable.pin);

        return convertView;
    }

    class ViewHolder {

        ImageView image;
    }

}
