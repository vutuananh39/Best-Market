package com.caocao.bestmarket.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.caocao.bestmarket.R;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ArrayList<Note> notes = new ArrayList<Note>();
        notes.add(new Note(R.drawable.ic_launcher_background ,"nam da gui tin nhan cho ban do doc di cuc cung a ah ihafafadf", "12h"));
        notes.add(new Note(R.drawable.ic_launcher_background ,"nam da gui tin nhan cho ban do doc di cuc cung a ah ihafafadf", "12h"));
        notes.add(new Note(R.drawable.ic_launcher_background ,"nam da gui tin nhan cho ban do doc di cuc cung a ah ihafafadf", "12h"));
        notes.add(new Note(R.drawable.ic_launcher_background ,"nam da gui tin nhan cho ban do doc di cuc cung a ah ihafafadf", "12h"));
        notes.add(new Note(R.drawable.ic_launcher_background ,"nam da gui tin nhan cho ban do doc di cuc cung a ah ihafafadf", "12h"));
        notes.add(new Note(R.drawable.ic_launcher_background ,"nam da gui tin nhan cho ban do doc di cuc cung a ah ihafafadf", "12h"));
        notes.add(new Note(R.drawable.ic_launcher_background ,"nam da gui tin nhan cho ban do doc di cuc cung a ah ihafafadf", "12h"));



        NoteAdapter adapter = new NoteAdapter(this, notes);

        ListView  listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

    }
}
