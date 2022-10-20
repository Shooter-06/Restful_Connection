package com.example.restfulconnection;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomCountryList extends BaseAdapter {

    Activity context;
    ArrayList<Country> countries;

    public CustomCountryList(Activity context, ArrayList<Country> countries) {
        this.context = context;
        this.countries = countries;
    }

    //
    public static class ViewHolder{
        TextView textViewID;
        TextView textViewCountry;
    }

    @Override
    public int getCount() {
        if(countries.size()<=0)
            return 1;
        return countries.size();

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View conertView, ViewGroup parent) {
        View row= conertView;

        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder vh;
        if(conertView ==null){
            vh=new ViewHolder();
            row =inflater.inflate(R.layout.row_child, null, true);
            vh.textViewID= (TextView) row.findViewById(R.id.textView);
            vh.textViewCountry =(TextView) row.findViewById(R.id.textView2);
            row.setTag(vh);
        }else{
            vh=(ViewHolder) conertView.getTag();
        }
        vh.textViewCountry.setText(countries.get(position).getCountryName());
        vh.textViewCountry.setText(""+countries.get(position).getId());
        return row;
    }
}
