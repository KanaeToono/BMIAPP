package com.example.conga.bmi.fragments;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conga.bmi.R;
import com.example.conga.bmi.abstracts.BaseFragment;
import com.example.conga.bmi.models.NoteBook;


public class DetailsNoteFragmnet extends BaseFragment {
    private TextView mTextViewSubject;
    private TextView mTextViewContent;
    @Override
    protected int setLayoutById() {
        return R.layout.detailsnotebookfragment;
    }

    @Override
    protected void initViews() {
        mTextViewContent = (TextView) view.findViewById(R.id.textViewContent);
        mTextViewSubject = (TextView) view.findViewById(R.id.textViewSubject);
        //////////////////////////
        Bundle bundle = getArguments();
       NoteBook mNoteBook= (NoteBook) bundle
                .getParcelable("details");
        if (mNoteBook == null) {
            Toast.makeText(getActivity().getApplicationContext(), "cmmn", Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(getActivity().getApplicationContext(), "" + task.getId_task(), Toast.LENGTH_SHORE
        mTextViewSubject.setText(mNoteBook.getSubject());
        mTextViewContent.setText(mNoteBook.getContent());

    }

    @Override
    protected void addEvents() {

    }
}
