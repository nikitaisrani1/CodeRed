package com.righttickk.CodeRed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondIntroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner,spinner1;
    ArrayAdapter<String> madapter,madapter2;
    Button nextbtn;
    String cycledata,mdate,lengthdata;

    private FirebaseAuth mAuth;
    DatabaseReference reference;
    Intent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_intro);
        spinner = findViewById(R.id.spinner);
        spinner1 = findViewById(R.id.spinner1);
        nextbtn = findViewById(R.id.nextbtn1);

        intent1 = getIntent();
        mdate = intent1.getStringExtra("date");

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Users");

        madapter = new ArrayAdapter<String >(SecondIntroActivity.this, R.layout.cspinner,getResources().getStringArray(R.array.days));
        madapter.setDropDownViewResource(R.layout.custom_spinner);
        spinner.setAdapter(madapter);
        spinner.setOnItemSelectedListener(this);

        madapter2 = new ArrayAdapter<String >(SecondIntroActivity.this, R.layout.cspinner,getResources().getStringArray(R.array.days2));
        madapter2.setDropDownViewResource(R.layout.custom_spinner);
        spinner1.setAdapter(madapter2);
        spinner1.setOnItemSelectedListener(this);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase(cycledata,lengthdata);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String cycle = parent.getItemAtPosition(position).toString();

        Spinner spinner = (Spinner) parent;

        if(spinner.getId() == R.id.spinner){
            //do this
            String cycle = parent.getSelectedItem().toString();
            cycledata = cycle;
        }
        if(spinner.getId() == R.id.spinner1){
            //do this
            String length = parent.getSelectedItem().toString();
            lengthdata = length;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void saveToDatabase(String cycledata, String lengthdata) {
//        FirebaseUser firebaseUser = mAuth.getCurrentUser();
//        String userid = firebaseUser.getUid();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("CycleLength").child(userid);
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("id", userid);
//        hashMap.put("cyclelength",cycledata);
//
//        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
                    Intent intent = new Intent( SecondIntroActivity.this,BmiActivity.class);
                    intent.putExtra("cycledata",cycledata);
                    intent.putExtra("lenghtdata",lengthdata);
                    intent.putExtra("date",mdate);
                    startActivity(intent);
//                }else{
//                    Toast.makeText(SecondIntroActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }


}