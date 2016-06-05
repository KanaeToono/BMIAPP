package com.example.conga.bmi.adapters.viewholders;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.conga.bmi.R;


public class NoteBookViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
   public CardView mCardView;
    public FrameLayout mFrameLayout;
    public TextView textViewSubject;
    public TextView textViewDate;

    public NoteBookViewHolders(View itemView) {
        super(itemView);
        mFrameLayout = (FrameLayout) itemView.findViewById(R.id.frame_item_rss_layout);
        textViewSubject = (TextView) itemView.findViewById(R.id.textViewSubject);
        textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
    }

    @Override
    public void onClick(View view) {

    }
}
