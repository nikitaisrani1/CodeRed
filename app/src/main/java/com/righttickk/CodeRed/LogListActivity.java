package com.righttickk.CodeRed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.righttickk.CodeRed.Adapter.LogAdapter;
import com.righttickk.CodeRed.Model.Logs;
import com.righttickk.CodeRed.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LogListActivity extends AppCompatActivity {


    FirebaseAuth firebaseAuth;
    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();
    List<Map> mapList ;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_list);


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String userid = firebaseUser.getUid();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("HealthLog").child(userid);

        listView = (ListView) findViewById(R.id.lists);
        databaseReference.keepSynced(true);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {



                String value= (dataSnapshot.getValue(Logs.class).getId());
                arrayList.add(value);
                arrayAdapter = new ArrayAdapter<String>(LogListActivity.this,R.layout.log_items,R.id.dateView, arrayList);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String strId = arrayList.get(position);
//                        strId = strId.replaceAll("/","");
                        Intent intent = new Intent(LogListActivity.this, LogDataActivity.class);
                        intent.putExtra("Key",strId);
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}