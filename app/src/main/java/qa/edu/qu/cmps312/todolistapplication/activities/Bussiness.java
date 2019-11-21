package qa.edu.qu.cmps312.todolistapplication.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;

import qa.edu.qu.cmps312.todolistapplication.R;
import qa.edu.qu.cmps312.todolistapplication.adapters.CustomAdapter;
import qa.edu.qu.cmps312.todolistapplication.fragments.DialogFragment;
import qa.edu.qu.cmps312.todolistapplication.model.Tutorial;

public class Bussiness extends AppCompatActivity implements DialogFragment.DialogFragmentInteraction, CustomAdapter.AdapterInteraction{

    private FloatingActionButton addBtn;
    private ArrayList<Tutorial> tutorialsBus;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Boolean mPane = false;
    private int currentEditPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bussinesss);
        if (findViewById(R.id.fragment_container) != null) {
            mPane = true;
        } else
            mPane = false;

        addBtn = findViewById(R.id.add_btn3);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = DialogFragment.newInstance();

                if (mPane) {
                    findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragment_container, dialogFragment)
                            .commit();
                } else
                    dialogFragment.show(getSupportFragmentManager(), "MY_DIALOG");
            }
        });


        adapter = new CustomAdapter(this, getAllTodo());

        recyclerView = findViewById(R.id.busList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void updateTodo(Tutorial todo) {

    }

    public void dismissFragment() {
        if (mPane) {
            findViewById(R.id.fragment_container).setVisibility(View.GONE);
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent.getExtras()!=null) {
            currentEditPosition = intent.getExtras().getInt("position");
        }
        adapter.notifyDataSetChanged();
    }

    // Tutorial-1 - add the newly added tutorial to the database
    @Override
    public void addTutorial(Tutorial tutorial) {
        tutorialsBus.add(tutorial);
        adapter.notifyDataSetChanged();
        dismissFragment();    //this will remove the dialog from screen

    }

    public ArrayList<Tutorial> getAllTodo() {


        tutorialsBus = new ArrayList<>();
        // tutorialsBus.add(new Tutorial("a","a","a","A"));
        layoutManager = new LinearLayoutManager(this);
        return tutorialsBus;
    }

    @Override
    public void deleteTodo(int position) {

    }

    @Override
    public void editToDoList(int position, Tutorial tutorial) {

    }
}
