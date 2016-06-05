package com.example.conga.bmi.adapters.adapter;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.conga.bmi.R;
import com.example.conga.bmi.adapters.viewholders.NoteBookViewHolders;
import com.example.conga.bmi.databases.NoteBookDatabaseHelper;
import com.example.conga.bmi.fragments.DetailsNoteFragmnet;
import com.example.conga.bmi.fragments.EditNoteFragmnet;
import com.example.conga.bmi.models.NoteBook;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class NotebookAdapter extends RecyclerView.Adapter<NoteBookViewHolders>   {
    private static String TAG = NotebookAdapter.class.getSimpleName();
    public List<NoteBook> mListNoteBooks;
    private LayoutInflater mLayoutInflater;
    private Activity mActivity;
    private NoteBookDatabaseHelper mNoteBookDatabaseHelper;


    public NotebookAdapter(List<NoteBook> mListNoteBooks, Activity mActivity) {
       // this.mListener =listener;
        this.mListNoteBooks = mListNoteBooks;
        mLayoutInflater = LayoutInflater.from(mActivity);
        this.mActivity = mActivity;
        mNoteBookDatabaseHelper = new NoteBookDatabaseHelper(mActivity);
        try {
            mNoteBookDatabaseHelper.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    @Override
    public NoteBookViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_item_notebook_frag, parent, false);
        NoteBookViewHolders myViewHolder = new NoteBookViewHolders(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final NoteBookViewHolders holder, final int position) {
        // mListNoteBooks = mNoteBookDatabaseHelper.getAllNoteBook();
        holder.textViewSubject.setText(mListNoteBooks.get(position).getSubject());
        holder.textViewDate.setText(mListNoteBooks.get(position).getDate());
        holder.mFrameLayout.setBackgroundResource(R.drawable.bgnote);
        holder.mFrameLayout.setOnLongClickListener(new View.OnLongClickListener() {
                                                       @Override
                                                       public boolean onLongClick(View view) {
                                                           // Toast.makeText(mActivity.getApplicationContext(), "hahah", Toast.LENGTH_SHORT).show();
                                                           //Creating the instance of PopupMenu
                                                           final PopupMenu popup = new PopupMenu(mActivity, holder.mFrameLayout);
                                                           //Inflating the Popup using xml file
                                                           popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                                                           //registering popup with OnMenuItemClickListener
                                                           popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                                               public boolean onMenuItemClick(MenuItem item) {
                                                                   //   Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                                                                   switch (item.getItemId()) {
                                                                       case R.id.details:
                                                                           // open task to edit
                                                                           Bundle bundle = new Bundle();
                                                                           bundle.putParcelable("details", mListNoteBooks.get(position));
                                                                           Fragment toFragment = new DetailsNoteFragmnet();
                                                                           toFragment.setArguments(bundle);
                                                                           ((FragmentActivity) mActivity).getSupportFragmentManager().beginTransaction()
                                                                                   .replace(R.id.containernotefrg, toFragment, "details")
                                                                                   .addToBackStack("details").commit();
                                                                           break;
                                                                       case R.id.edit:
                                                                           // open task to edit
                                                                           Bundle bundle1 = new Bundle();
                                                                           bundle1.putParcelable("Task", mListNoteBooks.get(position));
                                                                           Fragment toFragment1 = new EditNoteFragmnet();
                                                                           toFragment1.setArguments(bundle1);
                                                                           ((FragmentActivity) mActivity).getSupportFragmentManager().beginTransaction()
                                                                                   .replace(R.id.containernotefrg, toFragment1, "Task")
                                                                                   .addToBackStack("Task").commit();
                                                                           break;
                                                                       case R.id.delete:
                                                                           // open task to edit
                                                                           final AlertDialog.Builder b = new AlertDialog.Builder(mActivity);
                                                                           b.setTitle(R.string.question);
                                                                           b.setMessage(R.string.messageCon);
                                                                           b.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                                                               @Override
                                                                               public void onClick(DialogInterface dialog, int which) {
                                                                                   try {
                                                                                       mNoteBookDatabaseHelper.deleteNotebook(mListNoteBooks.get(position).getId());
                                                                                       // mTaskDatabaseAdapter.closeDB();
                                                                                       mListNoteBooks.remove(position);
                                                                                       notifyItemRemoved(position);
                                                                                       Toast.makeText(mActivity.getApplicationContext(), "Đã xóa", LENGTH_SHORT).show();

                                                                                   } catch (Exception e) {
                                                                                       e.printStackTrace();
                                                                                   }
                                                                                   dialog.dismiss();
                                                                               }
                                                                           });
                                                                           b.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                                                               @Override
                                                                               public void onClick(DialogInterface dialog, int which) {
                                                                                   dialog.cancel();
                                                                               }
                                                                           });
                                                                           b.create().show();

                                                                           break;
                                                                   }
                                                                   return true;
                                                               }
                                                           });

                                                           popup.show();
                                                           return false;
                                                       }

                                                   }
        );
    }


    @Override
    public int getItemCount() {
        if (mListNoteBooks == null) {
            return 0;
        } else {
            return mListNoteBooks.size();
        }
    }

    public void remove(int position) {
        mNoteBookDatabaseHelper.deleteNotebook(mListNoteBooks.get(position).getId());
        mListNoteBooks.remove(position);
        notifyItemRemoved(position);
    }

}
