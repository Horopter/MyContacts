package com.horopter.mycontacts;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Horopter on 3/29/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<ViewHolder> implements Filterable{
    public Context mContext;
    public ArrayList<Contact> contacts,filterList;
    public CustomFilter filter;

    MyAdapter(Context context, ArrayList<Contact> list) {
        mContext = context;
        contacts = list;
        filterList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_contact_item, viewGroup, false);
        ViewHolder mh = new ViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ViewHolder currentItem, int i) {
            String contactName = contacts.get(i).getName();
            currentItem.name_id.setText(contactName);
            String uri = contacts.get(i).getUri();
            Picasso.with(mContext).load(uri).fit().placeholder(R.drawable.person_placeholder).into(currentItem.photo);
            currentItem.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(View v, int pos) {
                    Toast.makeText(mContext,"Calling "+contacts.get(pos).getName(),Toast.LENGTH_LONG).show();
                }
            });
            final Uri contactUri = ContactsContract.Contacts.getLookupUri(contacts.get(i).getId(), contacts.get(i).getLookup());
            currentItem.photo.assignContactUri(contactUri);
            currentItem.photo.setMode(ContactsContract.QuickContact.MODE_MEDIUM);
        }
    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new CustomFilter(filterList,this);
        }
        return filter;
    }
}

