package com.righttickk.CodeRed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirstIntroActivity extends AppCompatActivity {

    public CalendarView calendarView;
    Button nextbtn;
    String date;
    private FirebaseAuth mAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_intro);
        nextbtn = findViewById(R.id.nextbtn);

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Users");

        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = dayOfMonth +"/"+ (month+1) +"/"+ year;
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
//        FirebaseUser firebaseUser = mAuth.getCurrentUser();
//        String userid = firebaseUser.getUid();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("LastDate").child(userid);
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("id", userid);
//        hashMap.put("lastdate",date);
//
//        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
                    Intent intent = new Intent(FirstIntroActivity.this, SecondIntroActivity.class);
                    intent.putExtra("date",date);
                    startActivity(intent);
//                }else{
//                    Toast.makeText(FirstIntroActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

}