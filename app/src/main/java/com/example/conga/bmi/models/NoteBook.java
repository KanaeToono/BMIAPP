package com.example.conga.bmi.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.conga.bmi.utils.DbBitmapUtility;


public class NoteBook implements Parcelable {
    public int id;
    public String subject;
    public String content;
//    public byte[] imageArrays;
    private DbBitmapUtility mDbBitmapUtility;
    public String date;

    public NoteBook(int id ,String subject, String content, String date) {
        this.id =id;
        this.subject = subject;
        this.content = content;
//        this.imageArrays = imageArrays;

        this.date = date;
    }

    public NoteBook() {

    }

    public NoteBook( String subject, String content,  String date) {

        this.subject = subject;
        this.content = content;

        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public byte[] getImageArrays() {
//        return imageArrays;
//    }
//
//    public void setImageArrays(byte[] imageArrays) {
//        this.imageArrays = imageArrays;
//    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
//                ", imageArrays=" + Arrays.toString(imageArrays) +
//                ", mDbBitmapUtility=" + mDbBitmapUtility +
                ", date='" + date + '\'' +
                '}';
    }

    public static final Parcelable.Creator<NoteBook> CREATOR = new Creator<NoteBook>() {
        public NoteBook createFromParcel(Parcel source) {
            NoteBook mNoteBook = new NoteBook();
            mNoteBook.id = source.readInt();
            mNoteBook.subject = source.readString();
            mNoteBook.content = source.readString();
            mNoteBook.date = source.readString();

            return mNoteBook;
        }

        public NoteBook[] newArray(int size) {
            return new NoteBook[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(subject);
        parcel.writeString(content);
        parcel.writeString(date);

    }
}
