package com.righttickk.CodeRed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.righttickk.CodeRed.Fragment.BlogFragment;
import com.righttickk.CodeRed.Fragment.CalendarFragment;
import com.righttickk.CodeRed.Fragment.CycleFragment;
import com.righttickk.CodeRed.Fragment.FaqFragment;
import com.righttickk.CodeRed.Fragment.HealthFragment;

public class ManageActivity extends AppCompatActivity  {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        firebaseAuth = FirebaseAuth.getInstance();

        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, new CalendarFragment()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.healthLog:
                Intent intent = new Intent(ManageActivity.this, LogListActivity.class);
                startActivity(intent);
                return (true);

            case R.id.logOut:
                firebaseAuth.getCurrentUser();
                firebaseAuth.signOut();
                finish();
                Intent i = new Intent(ManageActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                return (true);
        }
        return true;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = new CalendarFragment();

            switch (item.getItemId()) {
                case R.id.nav_cycle:
                    selectedFragment = new CycleFragment();
                    break;
                case R.id.nav_calendar:
                    selectedFragment = new CalendarFragment();
                    break;
                case R.id.nav_health:
                    selectedFragment = new HealthFragment();
                    break;
                case R.id.nav_faq:
                    selectedFragment = new FaqFragment();
                    break;
                case R.id.nav_blog:
                    selectedFragment = new BlogFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, selectedFragment).commit();
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        finishAffinity();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}