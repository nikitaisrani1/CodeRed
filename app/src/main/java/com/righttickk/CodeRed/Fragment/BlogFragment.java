package com.righttickk.CodeRed.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.righttickk.CodeRed.Data.Constants;
import com.righttickk.CodeRed.Model.Blog;
import com.righttickk.CodeRed.Model.Quiz;
import com.righttickk.CodeRed.R;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class BlogFragment extends Fragment {

    private RadioGroup rbGroup1;
    private RadioGroup rbGroup2;
    private RadioGroup rbGroup3;
    private RadioGroup rbGroup4;
    private RadioGroup rbGroup5;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;
    private RadioButton rb6;
    private RadioButton rb7;
    private RadioButton rb8;
    private RadioButton rb9;
    private RadioButton rb10;
    private RadioButton getRb1;
    private RadioButton getRb2;
    private RadioButton getRb3;
    private RadioButton getRb4;
    private RadioButton getRb5;
    int radioId1,radioId2,radioId3,radioId4,radioId5;
    String strId1,strId2,strId3,strId4,strId5;
    Button submitBtn;
    String gAnswer1,gAnswer2,gAnswer3,gAnswer4,gAnswer5;
    int total,total1,total2,total3,total4,total5;
    TextView result;
    String dataSaved;

    FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        sharedPreferences = this.getActivity().getSharedPreferences(Constants.MyPREFERENCES, MODE_PRIVATE);

        return inflater.inflate(R.layout.blog_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rbGroup1 = view.findViewById(R.id.radiogroup1);
        rbGroup2 = view.findViewById(R.id.radiogroup2);
        rbGroup3 = view.findViewById(R.id.radiogroup3);
        rbGroup4 = view.findViewById(R.id.radiogroup4);
        rbGroup5 = view.findViewById(R.id.radiogroup5);
        rb1 = view.findViewById(R.id.radioButton1);
        rb2 = view.findViewById(R.id.radioButton2);
        rb3 = view.findViewById(R.id.radioButton3);
        rb4 = view.findViewById(R.id.radioButton4);
        rb5 = view.findViewById(R.id.radioButton5);
        rb6 = view.findViewById(R.id.radioButton6);
        rb7 = view.findViewById(R.id.radioButton7);
        rb8 = view.findViewById(R.id.radioButton8);
        rb9 = view.findViewById(R.id.radioButton9);
        rb10 = view.findViewById(R.id.radioButton10);
        submitBtn = view.findViewById(R.id.submitbtn);
        result = view.findViewById(R.id.resultTxt);



        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRb1();
                submitBtn.setVisibility(View.VISIBLE);
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRb1();
                submitBtn.setVisibility(View.VISIBLE);
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRb2();
                submitBtn.setVisibility(View.VISIBLE);
            }
        });
        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRb2();
                submitBtn.setVisibility(View.VISIBLE);
            }
        });
        rb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRb3();
                submitBtn.setVisibility(View.VISIBLE);
            }
        });
        rb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRb3();
                submitBtn.setVisibility(View.VISIBLE);
            }
        });
        rb7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRb4();
                submitBtn.setVisibility(View.VISIBLE);
            }
        });
        rb8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRb4();
                submitBtn.setVisibility(View.VISIBLE);
            }
        });
        rb9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRb5();
                submitBtn.setVisibility(View.VISIBLE);
            }
        });
        rb10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRb5();
                submitBtn.setVisibility(View.VISIBLE);
            }
        });

        checkResult1();
    }


    private void checkResult1() {

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String userid = firebaseUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Blog").child(userid);
        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Blog blog = snapshot.getValue(Blog.class);
                String saved = blog.getIsSaved();
                dataSaved = saved;
                if(saved.equals("yes")){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Constants.IsDataSaved,true);
                    editor.commit();

                    if (getActivity().getSharedPreferences(Constants.MyPREFERENCES, MODE_PRIVATE).getBoolean(Constants.IsDataSaved, false)) {

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Quiz").child(userid);
                        reference.keepSynced(true);
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Quiz quiz = snapshot.getValue(Quiz.class);
                                String group1 = quiz.getRbGroup1();
                                String group2 = quiz.getRbGroup2();
                                String group3 = quiz.getRbGroup3();
                                String group4 = quiz.getRbGroup4();
                                String group5 = quiz.getRbGroup5();

                                if(group1.equals("yes")){
                                    rb1.setChecked(true);
                                    total1 = 20;
                                } else if(group1.equals("no")){
                                    rb2.setChecked(true);
                                    total1 = 0;
                                }

                                if(group2.equals("yes")){
                                    rb3.setChecked(true);
                                    total2 = 20;
                                } else if(group2.equals("no")){
                                    rb4.setChecked(true);
                                    total2 = 0;
                                }

                                if(group3.equals("yes")){
                                    rb5.setChecked(true);
                                    total3 = 20;
                                } else if(group3.equals("no")){
                                    rb6.setChecked(true);
                                    total3 = 0;
                                }

                                if(group4.equals("yes")){
                                    rb7.setChecked(true);
                                    total4 = 20;
                                } else if(group4.equals("no")){
                                    rb8.setChecked(true);
                                    total4 = 0;
                                }

                                if(group5.equals("yes")){
                                    rb9.setChecked(true);
                                    total5 = 20;
                                } else if(group5.equals("no")){
                                    rb10.setChecked(true);
                                    total5 = 0;
                                }

                                total = (total1+total2+total3+total4+total5);
                                result.setText("There is "+total+"% chance that you may have PCOS");
                                result.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    private void submit() {
        if(strId1!=null && strId2!=null && strId3!=null &&strId4!=null &&strId5!=null){

            firebaseAuth = FirebaseAuth.getInstance();


            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            String userid = firebaseUser.getUid();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Quiz").child(userid);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id",userid);
            hashMap.put("rbGroup1", gAnswer1);
            hashMap.put("rbGroup2", gAnswer2);
            hashMap.put("rbGroup3", gAnswer3);
            hashMap.put("rbGroup4", gAnswer4);
            hashMap.put("rbGroup5", gAnswer5);
            reference.keepSynced(true);
            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(getActivity(), "Submitted", Toast.LENGTH_SHORT).show();

                        submitBtn.setVisibility(View.GONE);


                        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Blog").child(userid);
                        HashMap<String, String> hashMap1 = new HashMap<>();
                        hashMap1.put("id", userid);
                        hashMap1.put("isSaved", "yes");

                        reference1.keepSynced(true);
                        reference1.setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });

                        checkResult1();

                    } else {
                        Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            Toast.makeText(getActivity(),"Please answer all the question",Toast.LENGTH_LONG).show();
        }
    }

    private void checkRb1() {
        radioId1 = rbGroup1.getCheckedRadioButtonId();
        getRb1 = getView().findViewById(radioId1);
        strId1 = String.valueOf(getRb1.getId());

        String strRB1Id = String.valueOf(rb1.getId());
        String strRB2Id = String.valueOf(rb2.getId());

        if(strId1.equals(strRB1Id)){
            gAnswer1 = "yes";
        } else if( strId1.equals(strRB2Id)) {
            gAnswer1 = "no";
        }
    }

    private void checkRb2() {
        radioId2 = rbGroup2.getCheckedRadioButtonId();
        getRb2 = getView().findViewById(radioId2);
        strId2 = String.valueOf(getRb2.getId());

        String strRB3Id = String.valueOf(rb3.getId());
        String strRB4Id = String.valueOf(rb4.getId());

        if(strId2.equals(strRB3Id)){
            gAnswer2 = "yes";
        } else if( strId2.equals(strRB4Id)) {
            gAnswer2 = "no";
        }
    }

    private void checkRb3() {
        radioId3 = rbGroup3.getCheckedRadioButtonId();
        getRb3 = getView().findViewById(radioId3);
        strId3 = String.valueOf(getRb3.getId());

        String strRB5Id = String.valueOf(rb5.getId());
        String strRB6Id = String.valueOf(rb6.getId());

        if(strId3.equals(strRB5Id)){
            gAnswer3 = "yes";
        } else if( strId3.equals(strRB6Id)) {
            gAnswer3 = "no";
        }
    }

    private void checkRb4() {
        radioId4 = rbGroup4.getCheckedRadioButtonId();
        getRb4 = getView().findViewById(radioId4);
        strId4 = String.valueOf(getRb4.getId());

        String strRB7Id = String.valueOf(rb7.getId());
        String strRB8Id = String.valueOf(rb8.getId());

        if( strId4.equals(strRB7Id)) {
            gAnswer4 = "yes";
        } else if( strId4.equals(strRB8Id)) {
            gAnswer4 = "no";
        }
    }

    private void checkRb5() {
        radioId5 = rbGroup5.getCheckedRadioButtonId();
        getRb5 = getView().findViewById(radioId5);
        strId5 = String.valueOf(getRb5.getId());

        String strRB9Id = String.valueOf(rb9.getId());
        String strRB10Id = String.valueOf(rb10.getId());

        if( strId5.equals(strRB9Id)) {
            gAnswer5 = "yes";
        } else if( strId5.equals(strRB10Id)) {
            gAnswer5 = "no";
        }
    }
}
