package com.righttickk.CodeRed;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class ThirdIntroActivity extends AppCompatActivity {

    public CalendarView calendarView;
    Button nextbtn;
    String date, mDate, mCycleData, mLengthData,mBmi;
    Intent intent, intent1,intent2,intent3;
    private FirebaseAuth mAuth;
    DatabaseReference reference;
    private DatePicker datePicker;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_intro);
        nextbtn = findViewById(R.id.nextbtn2);
        datePicker = findViewById(R.id.datePicker);
        intent = getIntent();
        mDate = intent.getStringExtra("date");
//        intent1 = getIntent();
        mCycleData = intent.getStringExtra("cycledata");
//        intent2 = getIntent();
        mLengthData = intent.getStringExtra("lenghtdata");
//        intent3 = getIntent();
        mBmi = intent.getStringExtra("bmi");

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Users");


        Calendar today = Calendar.getInstance();

        datePicker.init(2000, 0, 1, new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date = (dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                        nextbtn.setVisibility(View.VISIBLE);

                    }
                });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase(date);
            }
        });

    }

    private void saveToDatabase(String date) {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String userid = firebaseUser.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Details").child(userid);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", userid);
        hashMap.put("lastDate", mDate);
        hashMap.put("periodLength", mLengthData);
        hashMap.put("cycleLength", mCycleData);
        hashMap.put("DOB", date);
        hashMap.put("BMI", mBmi);

        reference.keepSynced(true);
        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Blog").child(userid);
                    HashMap<String, String> hashMap1 = new HashMap<>();
                    hashMap1.put("id", userid);
                    hashMap1.put("isSaved", "no");

                    reference1.keepSynced(true);
                    reference1.setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                    Intent intent = new Intent(ThirdIntroActivity.this, ManageActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ThirdIntroActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}