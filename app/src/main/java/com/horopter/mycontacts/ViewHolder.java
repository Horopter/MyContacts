package com.horopter.mycontacts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

/**
 * Created by Horopter on 3/29/2016.
 */
class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected QuickContactBadge photo;
    protected TextView name_id;
    protected ItemClickListener listener;

    public ViewHolder(View view) {
        super(view);
        this.photo = (QuickContactBadge) view.findViewById(R.id.imageView_photo);
        this.name_id = (TextView) view.findViewById(R.id.name_id);
        this.name_id.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener ic)
    {
        this.listener=ic;
    }

    @Override
    public void onClick(View v) {
        this.listener.onItemClick(v,getLayoutPosition());

    }
}

