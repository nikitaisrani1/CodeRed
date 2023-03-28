package com.righttickk.CodeRed.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.righttickk.CodeRed.LogListActivity;
import com.righttickk.CodeRed.Model.Detail;
import com.righttickk.CodeRed.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class HealthFragment extends Fragment {

    EditText bodyTemp;
    RadioGroup radioGroup1,radioGroup2,radioGroup3;
    RadioButton rB1,rB2,rB3,rB4,rB5,rB6,rB7,rB8;
    private RadioButton getRb1,getRb2,getRb3;
    CheckBox mIrritation,mCramps,mHeadache;
    String strId1,strId2,strId3;
    int radioId1,radioId2,radioId3;
    String bTemp,rGroup1,rGroup2,rGroup3;
    String cB1,cB2,cB3;
    Button submitBtn;
    TextView header, docMsg ;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    String getDate;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        String userId = firebaseUser.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();

        reference = firebaseDatabase.getReference("Details").child(userId);
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Detail detail = snapshot.getValue(Detail.class);
                getDate = detail.getLastDate();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return inflater.inflate(R.layout.health_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        header = view.findViewById(R.id.headerTag);
        docMsg = view.findViewById(R.id.doctorTxt);
        bodyTemp = view.findViewById(R.id.bodyTempEdt);
        radioGroup1 = view.findViewById(R.id.radioGroup1);
        radioGroup2 = view.findViewById(R.id.radioGroup2);
        radioGroup3 = view.findViewById(R.id.radioGroup3);
        rB1 = view.findViewById(R.id.radiobutton1);
        rB2 = view.findViewById(R.id.radiobutton2);
        rB3 = view.findViewById(R.id.radiobutton3);
        rB4 = view.findViewById(R.id.radiobutton4);
        rB5 = view.findViewById(R.id.radiobutton5);
        rB6 = view.findViewById(R.id.radiobutton6);
        rB7 = view.findViewById(R.id.radiobutton7);
        rB8= view.findViewById(R.id.radiobutton8);
        mIrritation = view.findViewById(R.id.cB1);
        mCramps = view.findViewById(R.id.cB2);
        mHeadache = view.findViewById(R.id.cB3);

        submitBtn = view.findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogListActivity.class);
                startActivity(intent);
            }
        });

        bodyTemp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                docMsg.setVisibility(View.GONE);
                submitBtn.setVisibility(View.VISIBLE);
                return false;
            }
        });

        mIrritation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docMsg.setVisibility(View.GONE);
                submitBtn.setVisibility(View.VISIBLE);
            }
        });

        mCramps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docMsg.setVisibility(View.GONE);
                submitBtn.setVisibility(View.VISIBLE);
            }
        });

        mHeadache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docMsg.setVisibility(View.GONE);
                submitBtn.setVisibility(View.VISIBLE);
            }
        });


        rB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docMsg.setVisibility(View.GONE);
                submitBtn.setVisibility(View.VISIBLE);
                checkRb1();
            }
        });

        rB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docMsg.setVisibility(View.GONE);
                submitBtn.setVisibility(View.VISIBLE);
                checkRb1();
            }
        });

        rB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docMsg.setVisibility(View.GONE);
                submitBtn.setVisibility(View.VISIBLE);
                checkRb2();
            }
        });

        rB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docMsg.setVisibility(View.GONE);
                submitBtn.setVisibility(View.VISIBLE);
                checkRb2();
            }
        });

        rB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docMsg.setVisibility(View.GONE);
                submitBtn.setVisibility(View.VISIBLE);
                checkRb3();
            }
        });

        rB6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docMsg.setVisibility(View.GONE);
                submitBtn.setVisibility(View.VISIBLE);
                checkRb3();
            }
        });

        rB7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docMsg.setVisibility(View.GONE);
                submitBtn.setVisibility(View.VISIBLE);
                checkRb3();
            }
        });

        rB8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docMsg.setVisibility(View.GONE);
                submitBtn.setVisibility(View.VISIBLE);
                checkRb3();
            }
        });

    }

    private void checkRb1() {
        radioId1 = radioGroup1.getCheckedRadioButtonId();
        getRb1 = getView().findViewById(radioId1);
        strId1 = String.valueOf(getRb1.getId());

        String strRB1Id = String.valueOf(rB1.getId());
        String strRB2Id = String.valueOf(rB2.getId());

        if(strId1.equals(strRB1Id)){
            rGroup1 = "yes";
        } else if( strId1.equals(strRB2Id)) {
            rGroup1 = "no";
        }


    }
    private void checkRb2() {
        radioId2 = radioGroup2.getCheckedRadioButtonId();
        getRb2 = getView().findViewById(radioId2);
        strId2 = String.valueOf(getRb2.getId());

        String strRB3Id = String.valueOf(rB3.getId());
        String strRB4Id = String.valueOf(rB4.getId());

        if(strId2.equals(strRB3Id)){
            rGroup2 = "yes";
        } else if( strId2.equals(strRB4Id)) {
            rGroup2 = "no";
        }

    }
    private void checkRb3() {
        radioId3 = radioGroup3.getCheckedRadioButtonId();
        getRb3 = getView().findViewById(radioId3);
        strId3 = String.valueOf(getRb3.getId());

        String strRB5Id = String.valueOf(rB5.getId());
        String strRB6Id = String.valueOf(rB6.getId());
        String strRB7Id = String.valueOf(rB7.getId());
        String strRB8Id = String.valueOf(rB8.getId());

        if(strId3.equals(strRB5Id)){
            rGroup3 = "White";
        } else if( strId3.equals(strRB6Id)) {
            rGroup3 = "Clear and Watery";
        } else if( strId3.equals(strRB7Id)) {
            rGroup3 = "Brown and Bloody";
        } else if( strId3.equals(strRB8Id)) {
            rGroup3 = "Yellow / Green";
        }
    }


    private void submit() {

        String strTemp = bodyTemp.getText().toString();
//
//        if(strTemp != null && !"".equals(strTemp) && rGroup3 != null ){
            bTemp = strTemp;
//            float Temp = Float.parseFloat((strTemp));
//
//
//            if(Temp > 99.99 && rGroup3 == "Yellow / Green"){
//                docMsg.setText("Your temperature is high and your vaginal secretion is not normal. Recommended to see a doctor");
//                docMsg.setVisibility(View.VISIBLE);
////                Toast.makeText(getActivity(),"Recommended to see a doctor",Toast.LENGTH_LONG).show();
//            } else if(Temp > 99.99){
//                docMsg.setText("Your temperature is high. Recommended to see a doctor");
//                docMsg.setVisibility(View.VISIBLE);
//            } else if(rGroup3 == "Yellow / Green"){
//                docMsg.setText("Your vaginal secretion is not normal. Recommended to see a doctor");
//                docMsg.setVisibility(View.VISIBLE);
//            }
//
//
//        }

        if(mIrritation.isChecked()){
            cB1 = "yes";
        } else {
            cB1 = "no";
        }

        if(mCramps.isChecked()){
            cB2 = "yes";
        } else {
            cB2 = "no";
        }

        if(mHeadache.isChecked()){
            cB3 = "yes";
        } else {
            cB3 = "no";
        }


        if(bTemp!=null && cB1!=null && cB2!=null && cB3!=null && rGroup1!=null && rGroup2!=null && rGroup3!=null){



            String strToday = getDate;


            String refDate = getDate.replaceAll("/","");

            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            String userid = firebaseUser.getUid();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("HealthLog").child(userid).child(refDate);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id",strToday);
            hashMap.put("lastDate",strToday);
            hashMap.put("BodyTemp", bTemp);
            hashMap.put("CB1", cB1);
            hashMap.put("CB2", cB2);
            hashMap.put("CB3", cB3);
            hashMap.put("RGroup1", rGroup1);
            hashMap.put("RGroup2", rGroup2);
            hashMap.put("RGroup3", rGroup3);
            reference.keepSynced(true);
            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        submitBtn.setVisibility(View.GONE);

                        String strTemp = bodyTemp.getText().toString();

                        if(strTemp != null && !"".equals(strTemp) && rGroup3 != null ){
                            bTemp = strTemp;
                            float Temp = Float.parseFloat((strTemp));


                            if(Temp > 99.99 && rGroup3 == "Yellow / Green"){
                                docMsg.setText("Your temperature is high and your vaginal secretion is not normal. Recommended to see a doctor.");
                                docMsg.setVisibility(View.VISIBLE);
                            } else if(Temp > 99.99){
                                docMsg.setText("Your temperature is high. Recommended to see a doctor.");
                                docMsg.setVisibility(View.VISIBLE);
                            } else if(rGroup3 == "Yellow / Green"){
                                docMsg.setText("Your vaginal secretion is not normal. Recommended to see a doctor.");
                                docMsg.setVisibility(View.VISIBLE);
                            } else {
                                docMsg.setText("You are Normal.");
                                docMsg.setVisibility(View.VISIBLE);
                            }


                        }
//                        Toast.makeText(getActivity(), "Submitted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            Toast.makeText(getActivity(),"Please answer all the question",Toast.LENGTH_LONG).show();
        }
    }


}
