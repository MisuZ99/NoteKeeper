package com.trainingcourse.notekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {


    private NoteRecyclerAdapter mNoteRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoteListActivity.this,NoteActivity.class));
            }
        });

        initializeDisplayContent();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mNoteRecyclerAdapter.notifyDataSetChanged();
        //mAdapterNotes.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {
      /*  final ListView listNotes=findViewById(R.id.list_notes);

        List<NoteInfo> notes=DataManager.getInstance().getNotes();
        mAdapterNotes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,notes);
        listNotes.setAdapter(mAdapterNotes);
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                Intent intent=new Intent(NoteListActivity.this,NoteActivity.class);
                //NoteInfo note=(NoteInfo)listNotes.getItemAtPosition(position);
                intent.putExtra(NoteActivity.NOTE_POSITION,position);
                Log.d("Debug",String.valueOf(position));
                startActivity(intent);
            }
        });*/

        final RecyclerView recyclerNotes=(RecyclerView) findViewById(R.id.list_notes);
        final LinearLayoutManager notesLayoutManager=new LinearLayoutManager(this);
        recyclerNotes.setLayoutManager(notesLayoutManager);

        List<NoteInfo> notes=DataManager.getInstance().getNotes();
        mNoteRecyclerAdapter = new NoteRecyclerAdapter(this,notes);
        recyclerNotes.setAdapter(mNoteRecyclerAdapter);

    }
}