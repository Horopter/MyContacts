package com.horopter.mycontacts;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by Horopter on 4/1/2016.
 */
public class CustomFilter extends Filter {

    MyAdapter adapter;
    ArrayList<Contact> filterList;


    public CustomFilter(ArrayList<Contact> filterList,MyAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
            constraint=constraint.toString().toUpperCase();
            ArrayList<Contact> filteredPlayers=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                if(filterList.get(i).getName().toUpperCase().contains(constraint))
                {
                    filteredPlayers.add(filterList.get(i));
                }
            }

            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }


        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.contacts= (ArrayList<Contact>) results.values;
        adapter.notifyDataSetChanged();
    }
}

