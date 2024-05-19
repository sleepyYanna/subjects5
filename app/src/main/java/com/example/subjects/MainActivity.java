package com.example.subjects;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addsub, floatsub;
    Animation fabOpen, fabClose, fabForward, fabBackward;
    TextView txtsub;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Floating Action Buttons and animations
        addsub = findViewById(R.id.addsub);
        floatsub = findViewById(R.id.floatsub);
        txtsub = findViewById(R.id.txtsub);

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        fabForward = AnimationUtils.loadAnimation(this, R.anim.fab_forward);
        fabBackward = AnimationUtils.loadAnimation(this, R.anim.fab_backward);

        addsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
            }
        });

        floatsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
                openNewActivity();
            }
        });

        // Initialize Spinner and its functionality
        Spinner spinner = findViewById(R.id.spintermss);

        List<String> categories = new ArrayList<>();
        categories.add("All Terms");
        categories.add("2nd Term");
        categories.add("3rd Term");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Selected: " + categories.get(position),
                       Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });
    }

    private void animateFab() {
        if (isOpen) {
            addsub.startAnimation(fabBackward);
            floatsub.startAnimation(fabClose);
            txtsub.startAnimation(fabClose);
            floatsub.setClickable(false);
            isOpen = false;
        } else {
            addsub.startAnimation(fabForward);
            floatsub.startAnimation(fabOpen);
            txtsub.startAnimation(fabOpen);
            floatsub.setClickable(true);
            isOpen = true;
        }
    }

    public void openNewActivity() {
        Intent intent = new Intent(this, create.class);
        startActivity(intent);
    }
}
