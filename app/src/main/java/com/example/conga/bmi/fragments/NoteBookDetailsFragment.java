package com.example.conga.bmi.fragments;


import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Toast;

import com.example.conga.bmi.R;
import com.example.conga.bmi.abstracts.BaseFragment;
import com.example.conga.bmi.adapters.adapter.NotebookAdapter;
import com.example.conga.bmi.databases.NoteBookDatabaseHelper;
import com.example.conga.bmi.interfaces.IOnItemClickListener;
import com.example.conga.bmi.models.NoteBook;
import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.adapter.RecyclerViewAdapter;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class NoteBookDetailsFragment extends BaseFragment {
    private static final Object TIME_TO_AUTOMATICALLY_DISMISS_ITEM = 3000;
    private static String TAG = NoteBookDetailsFragment.class.getSimpleName();
    private FloatingActionButton mFloatingActionButton;
    private List<NoteBook> mListNoteBooks;
    private NoteBookDatabaseHelper mNoteBookDatabaseHelper;
    private GridLayoutManager mGridLayoutManager;
    private NotebookAdapter mNotebookAdapter;
    private IOnItemClickListener mListener;
    private static final long DELAY_MILLIS = 100;

    private RecyclerView mRecyclerView;
    private GestureDetector mGestureDetector;
    private boolean mIsPrepressed = false;
    private boolean mIsShowPress = false;
    private View mPressedView = null;


    @Override
    protected int setLayoutById() {
        return R.layout.notebookdetailsfragment;
    }

    @Override
    protected void initViews() {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerListNotebook);
        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.floattingactionbtn);
        mNoteBookDatabaseHelper = new NoteBookDatabaseHelper(getActivity());
        try {
            mNoteBookDatabaseHelper.open();
        }catch (Exception e){
            e.printStackTrace();
        }
        mListNoteBooks = mNoteBookDatabaseHelper.getAllNoteBook();
        mGridLayoutManager= new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mNotebookAdapter = new NotebookAdapter( mListNoteBooks, getActivity());
        mRecyclerView.setAdapter(mNotebookAdapter);
        mNotebookAdapter.notifyDataSetChanged();

    }

    @Override
    protected void addEvents() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment addNewNoteBookfrag = new AddNewNoteBookFargment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.containernotefrg, addNewNoteBookfrag);
                fragmentTransaction.commit();
            }
        });
        final SwipeToDismissTouchListener<RecyclerViewAdapter> touchListener =
                new SwipeToDismissTouchListener<>(
                        new RecyclerViewAdapter(mRecyclerView),
                        new SwipeToDismissTouchListener.DismissCallbacks<RecyclerViewAdapter>() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }




                            public void onPendingDismiss(RecyclerViewAdapter recyclerView, int position) {

                            }

                            @Override
                            public void onDismiss(RecyclerViewAdapter view, final int position) {
                                // adapter.remove(position);
                                final AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
                                b.setTitle(R.string.question);
                                b.setMessage(R.string.messageCon);
                                b.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            mNoteBookDatabaseHelper.deleteNotebook(mListNoteBooks.get(position).getId());
                                            // mTaskDatabaseAdapter.closeDB();
                                            mListNoteBooks.remove(position);
                                            mNotebookAdapter.notifyDataSetChanged();
                                            Toast.makeText(getActivity().getApplicationContext(), "Đã xóa", LENGTH_SHORT).show();

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


                                }
                        });


       // touchListener.setDismissDelay(TIME_TO_AUTOMATICALLY_DISMISS_ITEM);
        mRecyclerView.setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
        mRecyclerView.setOnScrollListener((RecyclerView.OnScrollListener) touchListener.makeScrollListener());
//        mRecyclerView.addOnItemTouchListener(new SwipeableItemClickListener(this,
//                new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
////                        if (view.getId() == R.id.txt_delete) {
//                            touchListener.processPendingDismisses();
//                        } else if (view.getId() == R.id.txt_undo) {
//                            touchListener.undoPendingDismiss();
//                        } else { // R.id.txt_data
//                            Toast.makeText(getActivity(), "Position " + position, LENGTH_SHORT).show();
//
//}}
            //    }));
//        SwipeDismissRecyclerViewTouchListener touchListener =
//                new SwipeDismissRecyclerViewTouchListener(
//                        mRecyclerView,
//                        new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
//                            @Override
//                            public boolean canDismiss(int position) {
//                                return true;
//                            }
//
//                            @Override
//                            public void onDismiss(RecyclerView recyclerView, int[] reverseSortedPositions) {
//                                for (final int position : reverseSortedPositions) {
//                                    //mItems.remove(position);
//                                    //delete  task
//                                    final AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
//                                    b.setTitle(R.string.question);
//                                    b.setMessage(R.string.messageCon);
//                                    b.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            try {
//                                                mNoteBookDatabaseHelper.deleteNotebook(mListNoteBooks.get(position).getId());
//                                                // mTaskDatabaseAdapter.closeDB();
//                                                mListNoteBooks.remove(position);
//                                                mNotebookAdapter.notifyDataSetChanged();
//                                                Toast.makeText(getActivity().getApplicationContext(), "Đã xóa", Toast.LENGTH_SHORT).show();
//
//                                            } catch (Exception e) {
//                                                e.printStackTrace();
//                                            }
//                                            dialog.dismiss();
//                                        }
//                                    });
//                                    b.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            dialog.cancel();
//                                        }
//                                    });
//                                    b.create().show();
//                                }
//
//
//                            }
//                        });
//        mRecyclerView.setOnTouchListener(touchListener);
//        mGestureDetector = new GestureDetector(mRecyclerView.getContext(), new GestureDetector.SimpleOnGestureListener() {
//            @Override
//            public boolean onDown(MotionEvent e) {
//                mIsPrepressed = true;
//                mPressedView = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
//                super.onDown(e);
//                return false;
//            }
//
//            @Override
//            public void onShowPress(MotionEvent e) {
//                if (mIsPrepressed && mPressedView != null) {
//                    mPressedView.setPressed(true);
//                    mIsShowPress = true;
//                }
//                super.onShowPress(e);
//            }
//
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                if (mIsPrepressed && mPressedView != null) {
//                    mPressedView.setPressed(true);
//                    final View pressedView = mPressedView;
//                    pressedView.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            pressedView.setPressed(false);
//                        }
//                    }, DELAY_MILLIS);
//                    mIsPrepressed = false;
//                    mPressedView = null;
//                }
//                return true;
//            }
//
//        });
//
//    }
//
//    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {

   }
}
