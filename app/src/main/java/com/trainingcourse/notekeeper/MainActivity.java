package com.trainingcourse.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.trainingcourse.notekeeper.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener
{

    private NoteRecyclerAdapter mNoteRecyclerAdapter;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private RecyclerView mRecyclerItems;
    private LinearLayoutManager mNotesLayoutManager;
    private CourseRecyclerAdapter mCourseRecyclerAdapter;
    private GridLayoutManager mCoursesLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(findViewById(R.id.toolbar));

        FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NoteActivity.class));
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        ActionBarDrawerToggle toogle=new ActionBarDrawerToggle(this,drawer,
                findViewById(R.id.toolbar),
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.setDrawerListener(toogle);
        toogle.syncState();
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navigationView.setNavigationItemSelectedListener(this);
        initializeDisplayContent();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
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

        mRecyclerItems = (RecyclerView) findViewById(R.id.list_items);
        mNotesLayoutManager = new LinearLayoutManager(this);
        List<NoteInfo> notes=DataManager.getInstance().getNotes();
        mNoteRecyclerAdapter = new NoteRecyclerAdapter(this,notes);


        mCoursesLayoutManager = new GridLayoutManager(this,getResources().getInteger(R.integer.course_grid_span));
        List<CourseInfo> courses=DataManager.getInstance().getCourses();
        mCourseRecyclerAdapter = new CourseRecyclerAdapter(this,courses);

        displayNotes();

    }
    
    private void displayCourses() {
        mRecyclerItems.setLayoutManager(mCoursesLayoutManager);
        mRecyclerItems.setAdapter(mCourseRecyclerAdapter);

        selectNavigationMenuItem(R.id.nav_courses);

    }

    private void displayNotes() {
        mRecyclerItems.setLayoutManager(mNotesLayoutManager);
        mRecyclerItems.setAdapter(mNoteRecyclerAdapter);

        selectNavigationMenuItem(R.id.nav_notes);
    }

    private void selectNavigationMenuItem(int id) {
        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(id).setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.nav_courses) {
            displayCourses();
        }
        else if(id==R.id.nav_notes) {
            displayNotes();

        }

        DrawerLayout drawer=(DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void handleSelection(String message) {
        View view=findViewById(R.id.list_items);
        Snackbar.make(view,message,Snackbar.LENGTH_LONG).show();
    }
}