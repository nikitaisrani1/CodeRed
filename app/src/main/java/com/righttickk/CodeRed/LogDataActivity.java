package com.righttickk.CodeRed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.righttickk.CodeRed.Model.Logs;

public class LogDataActivity extends AppCompatActivity {

    TextView header,bodyTemp,pms,pills,vs,result;
    CheckBox CB1,CB2,CB3;
    String cb1,cb2,cb3,VS;
    Float bTemp;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_data);

        header = findViewById(R.id.HeaderTag);
        bodyTemp = findViewById(R.id.BodyTempValue);
        pms = findViewById(R.id.PmsValue);
        pills = findViewById(R.id.PillsValue);
        vs = findViewById(R.id.VSValue);
        CB1 = findViewById(R.id.cBox1);
        CB2 = findViewById(R.id.cBox2);
        CB3 = findViewById(R.id.cBox3);
        result = findViewById(R.id.resultTxt);

        Intent intent = getIntent();
        String title = intent.getStringExtra("Key");
        String id = title.replaceAll("/","");

        header.setText("Log "+title);
        vs.setText(id);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String userid = firebaseUser.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("HealthLog").child(userid).child(id);
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Logs logs = snapshot.getValue(Logs.class);
                bodyTemp.setText(logs.getBodyTemp());
                pms.setText(logs.getRGroup1());
                pills.setText(logs.getRGroup2());
                vs.setText(logs.getRGroup3());

                cb1 = logs.getCB1();
                cb2 = logs.getCB2();
                cb3 = logs.getCB3();

                if(cb1.equals("yes")){
                    CB1.setChecked(true);
                }
                if(cb2.equals("yes")){
                    CB2.setChecked(true);
                }
                if(cb3.equals("yes")){
                    CB3.setChecked(true);
                }

                bTemp = Float.parseFloat(logs.getBodyTemp());
                VS = logs.getRGroup3();

                if(bTemp > 99.99 && VS == "Yellow / Green"){
                    result.setText("Your temperature is high and your vaginal secretion is not normal. Recommended to see a doctor.");
                } else if(bTemp > 99.99){
                    result.setText("Your temperature is high. Recommended to see a doctor.");
                } else if(VS == "Yellow / Green"){
                    result.setText("Your vaginal secretion is not normal. Recommended to see a doctor.");
                } else {
                    result.setText("You are Normal.");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}