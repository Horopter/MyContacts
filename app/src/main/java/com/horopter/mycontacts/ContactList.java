package com.horopter.mycontacts;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller;

/**
 * Created by Horopter on 3/29/2016.
 */
public class ContactList extends AppCompatActivity implements View.OnClickListener{
    private Context cntx;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Cursor cursor;
    private SearchView sv;
    public MyAdapter mAdapter;
    public ArrayList<Contact> mContacts;
    private Map<String, Integer> mapIndex;
    private int f,l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        sv= (SearchView) findViewById(R.id.mSearch);
        cntx = getApplicationContext();
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
        cursor = cntx.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, sortOrder);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        VerticalRecyclerViewFastScroller fastScroller = (VerticalRecyclerViewFastScroller)findViewById(R.id.fast_scroller);
        fastScroller.setRecyclerView(mRecyclerView);
        mRecyclerView.addOnScrollListener(fastScroller.getOnScrollListener());
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Integer contactsCount = cursor.getCount();
        mContacts = new ArrayList<>();
        if (contactsCount > 0) {
            for (int i = 0; i < contactsCount; i++) {
                cursor.moveToPosition(i);
                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String curi = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));
                Integer cid = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String clookup = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                Contact c = new Contact(contactName,curi,cid,clookup);
                mContacts.add(c);
            }
        }
        mAdapter = new MyAdapter(this,mContacts);
        mRecyclerView.setAdapter(mAdapter);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        //DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        //float dpH = displayMetrics.heightPixels/displayMetrics.density;
        //int p = (int)((dpH-225)/50);
        //int p = 8;
        //getIndexList(mContacts,p);
        getIndexList(mContacts);
        displayIndex();
    }

    private void getIndexList(ArrayList<Contact> fruits){//,int p) {
        mapIndex = new LinkedHashMap<>();
        mapIndex.put("*",0);
        if(fruits==null)
        {
            Log.d("Crash report","fruits is null");
            Toast.makeText(getApplicationContext(),"App crashes due to fetch of contacts not possible",Toast.LENGTH_LONG).show();
        }
        for (int i=0;i<fruits.size();i++) {
            String index = fruits.get(i).getName().substring(0, 1);
            index = index.toUpperCase();
            if (mapIndex.get(index) == null)
                mapIndex.put(index,i);//+Math.abs(p));
        }
    }

    private void displayIndex() {
        LinearLayout indexLayout = (LinearLayout) findViewById(R.id.side_index);
        TextView textView;
        List<String> indexList = new ArrayList<>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) getLayoutInflater().inflate(
                    R.layout.side_index_item, null);
            textView.setText(index);
            textView.setOnClickListener(this);
            indexLayout.addView(textView);
        }
    }

    public void onClick(View view) {
        TextView selectedIndex = (TextView) view;
        mRecyclerView.scrollToPosition(mapIndex.get(selectedIndex.getText()));
    }
}
