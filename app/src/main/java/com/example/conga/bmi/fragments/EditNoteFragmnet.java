package com.example.conga.bmi.fragments;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.conga.bmi.R;
import com.example.conga.bmi.abstracts.BaseFragment;
import com.example.conga.bmi.databases.NoteBookDatabaseHelper;
import com.example.conga.bmi.models.NoteBook;
import com.example.conga.bmi.utils.DbBitmapUtility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditNoteFragmnet extends BaseFragment {
    private static String TAG = AddNewNoteBookFargment.class.getSimpleName();
    private EditText subjectEditText;
    private EditText contentEditText;
    // private Button btn_chooseImage;
    private Button btn_save;
    private Button btn_cancel;
    private int id_btn;
    private static final int PICK_IMAGE = 100;
    private Cursor mCursor;
    private String pathString;
    private Bitmap mBitmap;
    private DbBitmapUtility mDbBitmapUtility;
    // private ImageView mImageView;
    private NoteBookDatabaseHelper mNoteBookDatabaseHelper;
    private NoteBook mNoteBook;


    @Override
    protected int setLayoutById() {
        return R.layout.addnewnotebookfrag;
    }

    @Override
    protected void initViews() {

        subjectEditText = (EditText) view.findViewById(R.id.editTextSubject);
        contentEditText = (EditText) view.findViewById(R.id.editTextContent);
        //   btn_chooseImage = (Button) view.findViewById(R.id.btn_image);
        btn_save = (Button) view.findViewById(R.id.save_btn);
        btn_cancel = (Button) view.findViewById(R.id.cancel_btn);
        //  mImageView = (ImageView) view.findViewById(R.id.avatar);
        // mDbBitmapUtility = new DbBitmapUtility();

        mNoteBookDatabaseHelper = new NoteBookDatabaseHelper(getActivity());
        try {
            mNoteBookDatabaseHelper.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //////////////////////////
        Bundle bundle = getArguments();
        mNoteBook= (NoteBook) bundle
                .getParcelable("Task");
        if (mNoteBook == null) {
            Toast.makeText(getActivity().getApplicationContext(), "cmmn", Toast.LENGTH_SHORT).show();
        }
       // Toast.makeText(getActivity().getApplicationContext(), "" + task.getId_task(), Toast.LENGTH_SHORE
        subjectEditText.setText(mNoteBook.getSubject());
        contentEditText.setText(mNoteBook.getContent());

    }

    @Override
    protected void addEvents() {
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Tabitha full.ttf");
        subjectEditText.setTypeface(font);
        contentEditText.setTypeface(font);
        // btn_chooseImage.setTypeface(font);
        btn_cancel.setTypeface(font);
        btn_save.setTypeface(font);

        // handle choose image btn
//        btn_chooseImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //openGallery();
//                openSDCard();
//            }
//
//
//        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

    }

    private void saveData() {
        String subject = subjectEditText.getText().toString();
        String content = contentEditText.getText().toString();
        DateFormat df = new SimpleDateFormat("dd MM yyyy, HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());
        NoteBook _noteBook = new NoteBook(mNoteBook.getId(), subject, content, date);
        try {
            mNoteBookDatabaseHelper.editNotebook(_noteBook);

        } catch (Exception e) {
            e.printStackTrace();
        }
        mNoteBookDatabaseHelper.close();
        Fragment noteBookDetailsFrag = new NoteBookDetailsFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transition = fragmentManager.beginTransaction().replace(R.id.containernotefrg, noteBookDetailsFrag);
        transition.commit();
        Log.d(TAG, date);
    }

//    private void openSDCard() {
//        Intent sdCardIntent = new Intent(Intent.ACTION_PICK);
//        sdCardIntent.setType("image/*");
//        startActivityForResult(sdCardIntent, PICK_IMAGE);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case 1: {
//                if (resultCode == RESULT_OK) {
////                    Uri photoUri = data.getData();
//                    Uri photoUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//                    if (photoUri != null) {
//                        String[] filePathColumn = new String[]{MediaStore.Images.Media.DATA};
//                        mCursor = getActivity().getContentResolver().query(photoUri, filePathColumn, null, null, null);
//                        if (mCursor.moveToFirst()) {
//                            try {
//                                int columnIndex = mCursor.getColumnIndex(filePathColumn[0]);
//                                pathString = mCursor.getString(columnIndex);
//                                mCursor.close();
//                                mBitmap = BitmapFactory.decodeFile(pathString);
//                                Log.d(TAG, pathString);
//                                //mImageView.setImageBitmap(mBitmap);
//                            } catch (Exception e) {
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
    //    private void openGallery() {
//        Intent gallery =
//                new Intent(Intent.ACTION_PICK,
//                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//        startActivityForResult(gallery, PICK_IMAGE);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
//            Uri imageUri = data.getData();
//            mImageView.setImageURI(imageUri);
//        }
//    }

}
