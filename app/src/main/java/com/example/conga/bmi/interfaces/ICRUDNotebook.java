package com.example.conga.bmi.interfaces;


import com.example.conga.bmi.models.NoteBook;

import java.util.ArrayList;

public interface  ICRUDNotebook {
    public void addNewNoteBook(NoteBook noteBook);
    public ArrayList<NoteBook> getAllNoteBook();
    public void editNotebook(NoteBook noteBook);
    public void deleteNotebook(int id_note);
}
