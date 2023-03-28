package com.righttickk.CodeRed.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.righttickk.CodeRed.Model.Detail;
import com.righttickk.CodeRed.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CycleFragment extends Fragment {

    String userId, getDate;
    int cLength, pLength;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    ProgressBar progressBaror, progressBargy;
    TextView txtV1, txtV2, txtV3, txtV4, txtV5, txtV6,pLengthV,cLengthV,bmi1,bmi2;
    Date today, FDate, LDate;
    String day,strBmi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        today = new Date();
        day = String.valueOf(today);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        userId = firebaseUser.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();

        reference = firebaseDatabase.getReference("Details").child(userId);
        reference.keepSynced(true);
        setData(reference);
        return inflater.inflate(R.layout.cycle_fragment, container, false);

    }

    private void setData(DatabaseReference reference) {
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Detail detail = snapshot.getValue(Detail.class);
                getDate = detail.getLastDate();
                cLength = Integer.parseInt(detail.getCycleLength());
                pLength = Integer.parseInt(detail.getPeriodLength());
                strBmi = detail.getBMI();

                try {
                    LDate = new SimpleDateFormat("dd/MM/yyyy").parse(getDate);

                    Calendar c = Calendar.getInstance();
                    c.setTime(LDate);
                    c.add(Calendar.DATE, pLength - 1);
                    FDate = c.getTime();

                    List<Date> datesL = getDates(LDate, FDate);

                    Calendar cn1 = Calendar.getInstance();
                    cn1.setTime(FDate);
                    cn1.add(Calendar.DATE, 1);
                    Date btwFDate = cn1.getTime();

                    Calendar cn = Calendar.getInstance();
                    cn.setTime(btwFDate);
                    cn.add(Calendar.DATE, (cLength));
                    Date nLDate = cn.getTime();

                    Calendar cf = Calendar.getInstance();
                    cf.setTime(nLDate);
                    cf.add(Calendar.DATE, pLength - 1);
                    Date nFDate = cf.getTime();

                    List<Date> ndates = new ArrayList<Date>();
                    ndates.add(nLDate);
                    ndates.add(nFDate);

                    List<Date> ndatesL = getNDates(btwFDate, nLDate);


                    DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                    String output = outputFormatter.format(today);


                    for (int i = 0; i < datesL.size(); i++) {
                        DateFormat opf = new SimpleDateFormat("dd/MM/yyyy");
                        String a = opf.format(datesL.get(i));
                        int size = datesL.size() - 1;
                        int valuei = i;
                        if (a.equals(output)) {

                            Handler handle = new Handler() {
                                @SuppressLint("HandlerLeak")
                                @Override
                                public void handleMessage(Message msg) {
                                    super.handleMessage(msg);
                                    progressBaror.incrementProgressBy(1);
                                }
                            };


                            progressBaror.setMax(datesL.size());
//                            progressBaror.setProgress(i);
                            progressBaror.setVisibility(View.VISIBLE);

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                    while (progressBaror.getProgress() <= valuei) {
                                        handle.sendMessage(handle.obtainMessage());
                                        handle.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                // TODO Auto-generated method stub
                                                progressBaror.setProgress(progressBaror.getProgress());

                                                if (progressBaror.getProgress() == valuei) {
                                                    Thread.currentThread().interrupt();

                                                }

                                            }
                                        });
                                        try {
                                            // Sleep for 200 milliseconds.
                                            // Just to display the progress slowly
                                            Thread.sleep(600); //thread will take approx 3 seconds to finish,change its value according to your needs
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }).start();

                            bmi1.setText("BMI = " + strBmi + " kg/m\u00B2");
                            bmi1.setVisibility(View.VISIBLE);
                            pLengthV.setText("Period Length : "+pLength+" Days");
                            pLengthV.setVisibility(View.VISIBLE);
                            txtV1.setText("Day " + (valuei + 1));
                            txtV1.setVisibility(View.VISIBLE);
                            txtV3.setText("First Day : " + opf.format(datesL.get(0)));
                            txtV3.setVisibility(View.VISIBLE);
                            txtV4.setText("Last day : " + opf.format(datesL.get(datesL.size() - 1)));
                            txtV4.setVisibility(View.VISIBLE);
                            break;
                        }
                    }

                    for (int i = 0; i < ndatesL.size(); i++) {
                        DateFormat opf = new SimpleDateFormat("dd/MM/yyyy");
                        String a = opf.format(ndatesL.get(i));
                        if (a.equals(output)) {
//                            if (!output.equals(a)) {

                                Handler handle = new Handler() {
                                    @SuppressLint("HandlerLeak")
                                    @Override
                                    public void handleMessage(Message msg) {
                                        super.handleMessage(msg);
                                        progressBargy.incrementProgressBy(1);
                                    }
                                };


                                progressBargy.setMax(ndatesL.size());
//                            progressBargy.setProgress(i);
                                progressBargy.setVisibility(View.VISIBLE);


                                int finalI = i;
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // TODO Auto-generated method stub
                                        while (progressBargy.getProgress() <= finalI) {
                                            handle.sendMessage(handle.obtainMessage());
                                            handle.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    // TODO Auto-generated method stub
                                                    progressBargy.setProgress(progressBargy.getProgress());

                                                    if (progressBargy.getProgress() == finalI) {
                                                        Thread.currentThread().interrupt();

                                                    }

                                                }
                                            });
                                            try {
                                                // Sleep for 200 milliseconds.
                                                // Just to display the progress slowly
                                                Thread.sleep(100); //thread will take approx 3 seconds to finish,change its value according to your needs
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }).start();

                                bmi2.setText("BMI = " + strBmi + " kg/m\u00B2");
                                bmi2.setVisibility(View.VISIBLE);
                                cLengthV.setText("Cycle Length : "+cLength);
                                cLengthV.setVisibility(View.VISIBLE);
                                txtV2.setText((ndatesL.size() - (i + 1)) + " Days until your next period");
                                txtV2.setVisibility(View.VISIBLE);
                                txtV5.setText("Last Period : " + opf.format(LDate));
                                txtV5.setVisibility(View.VISIBLE);
                                txtV6.setText("Next Period : " + opf.format(nLDate));
                                txtV6.setVisibility(View.VISIBLE);
                                break;
                            }
//                        }
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private List<Date> getNDates(Date btwFDate, Date nLDate) {
        ArrayList<Date> ndatesL = new ArrayList<Date>();

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(btwFDate);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(nLDate);

        while (!cal1.after(cal2)) {

            ndatesL.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return ndatesL;
    }


    private List<Date> getDates(Date lDate, Date fDate) {

        ArrayList<Date> datesL = new ArrayList<Date>();

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(lDate);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(fDate);

        while (!cal1.after(cal2)) {

            datesL.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return datesL;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBaror = view.findViewById(R.id.or_progressBar);
        progressBargy = view.findViewById(R.id.gy_progressBar);
        txtV1 = view.findViewById(R.id.daysOfP);
        txtV2 = view.findViewById(R.id.daysLeftForP);
        txtV3 = view.findViewById(R.id.txtDate1);
        txtV4 = view.findViewById(R.id.txtDate2);
        txtV5 = view.findViewById(R.id.txtDate3);
        txtV6 = view.findViewById(R.id.txtDate4);
        bmi1 = view.findViewById(R.id.bmi1);
        bmi2 = view.findViewById(R.id.bmi2);
        pLengthV = view.findViewById(R.id.pLength);
        cLengthV = view.findViewById(R.id.cLength);

    }
}
