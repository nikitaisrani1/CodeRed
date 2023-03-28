package com.righttickk.CodeRed.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.righttickk.CodeRed.Model.Detail;
import com.righttickk.CodeRed.R;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CalendarFragment extends Fragment {

    String userId, getDate, dob,mBmi;
    String strToday,strnLdate;
    int cLength, pLength;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    CalendarPickerView calendarPickerView;
    Date FDate, LDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        userId = firebaseUser.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();

        reference = firebaseDatabase.getReference("Details").child(userId);
        reference.keepSynced(true);
        setData(reference);

        return inflater.inflate(R.layout.calendar_fragment, container, false);
    }

    private void setData(DatabaseReference reference) {
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                /*get last date from database
                 * get cyclelenght from database*/
                Detail detail = snapshot.getValue(Detail.class);
                getDate = detail.getLastDate();
                cLength = Integer.parseInt(detail.getCycleLength());
                dob = detail.getDOB();
                mBmi = detail.getBMI();
                pLength = Integer.parseInt(detail.getPeriodLength());
//                cLength = cLength+1;

                // convert string to date format
                try {
                    LDate = new SimpleDateFormat("dd/MM/yyyy").parse(getDate);
                    Log.d(TAG, String.valueOf(LDate));  //to check converted or not


                    Calendar c = Calendar.getInstance();   //getting last date of period from last date
                    c.setTime(LDate);
                    c.add(Calendar.DATE, (pLength - 1));
                    FDate = c.getTime();
                    Calendar nextYear = Calendar.getInstance();  //getting year to initialize calendar
                    nextYear.add(Calendar.YEAR, 1);

                    Calendar getLMonth = Calendar.getInstance();  //getting pevious month to initialize calendar
                    getLMonth.setTime(LDate);
                    getLMonth.add(Calendar.MONTH, -1);


                    List<Date> datesL = getDates(LDate, FDate);  // last peroid dates

                    Calendar cn = Calendar.getInstance();
                    cn.setTime(FDate);
                    cn.add(Calendar.DATE, (cLength + 1));
                    Date nLDate = cn.getTime();

                    Calendar cf = Calendar.getInstance();
                    cf.setTime(nLDate);
                    cf.add(Calendar.DATE, (pLength - 1));
                    Date nFDate = cf.getTime();

//                    List<Date> ndates = new ArrayList<Date>();
//                    ndates.add(nLDate);
//                    ndates.add(nFDate);

                    List<Date> ndates1 = getDates1(nLDate, nFDate);  // future prediction

                    Calendar cn1 = Calendar.getInstance();
                    cn1.setTime(nFDate);
                    cn1.add(Calendar.DATE, (cLength + 1));
                    Date nLDate1 = cn1.getTime();

                    Calendar cf1 = Calendar.getInstance();
                    cf1.setTime(nLDate1);
                    cf1.add(Calendar.DATE, (pLength - 1));
                    Date nFDate1 = cf1.getTime();

                    List<Date> ndates2 = getDates2(nLDate1, nFDate1);  // next future prediction

                    Calendar cn2 = Calendar.getInstance();
                    cn2.setTime(nFDate1);
                    cn2.add(Calendar.DATE, (cLength + 1));
                    Date nLDate2 = cn2.getTime();

                    Calendar cf2 = Calendar.getInstance();
                    cf2.setTime(nLDate2);
                    cf2.add(Calendar.DATE, (pLength - 1));
                    Date nFDate2 = cf2.getTime();

                    List<Date> ndates3 = getDates3(nLDate2, nFDate2);   //and next future prediction

                    Calendar getNMonth = Calendar.getInstance();  //getting month to initialize calendar
                    getNMonth.setTime(nFDate2);
                    getNMonth.add(Calendar.MONTH, 1);


                    Date today = new Date();        // get todays date


                    for (int i = 0; i < ndates1.size(); i++){
                        DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                    strToday = outputFormatter.format(today);
                    strnLdate = outputFormatter.format(nLDate);
                    String a = outputFormatter.format(ndates1.get(i));

                    if (strToday.equals(a)) {                                           // setting future predicted date to database lastDate
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        String userid = firebaseUser.getUid();

                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate = dateFormat.format(nLDate);

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Details").child(userid);
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("id", userid);
                        hashMap.put("lastDate", strDate);
                        hashMap.put("periodLength", String.valueOf(pLength));
                        hashMap.put("cycleLength", String.valueOf(cLength));
                        hashMap.put("DOB", dob);
                        hashMap.put("BMI", mBmi);

                        reference.keepSynced(true);
                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Successfull", Toast.LENGTH_SHORT);
                                } else {
                                    Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                        break;
                    }

                }


                    calendarPickerView.init(getLMonth.getTime(), getNMonth.getTime())    // putting all the dates in calendar
                            .inMode(CalendarPickerView.SelectionMode.MULTIPLE)
                            .withHighlightedDates(ndates1)
                            .withHighlightedDates(ndates2)
                            .withHighlightedDates(ndates3)
                            .displayOnly()
                            .withSelectedDates(datesL);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private List<Date> getDates3(Date nLDate2, Date nFDate2) {
        ArrayList<Date> datesL = new ArrayList<Date>();

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(nLDate2);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(nFDate2);

        while (!cal1.after(cal2)) {
            datesL.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return datesL;
    }

    private List<Date> getDates2(Date nLDate1, Date nFDate1) {
        ArrayList<Date> datesL = new ArrayList<Date>();

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(nLDate1);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(nFDate1);

        while (!cal1.after(cal2)) {
            datesL.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return datesL;
    }

    private List<Date> getDates1(Date nLDate, Date nFDate) {
        ArrayList<Date> datesL = new ArrayList<Date>();

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(nLDate);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(nFDate);

        while (!cal1.after(cal2)) {
            datesL.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return datesL;
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

        calendarPickerView = view.findViewById(R.id.calendar);

        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        Calendar lastMonth = Calendar.getInstance();
        lastMonth.add(Calendar.MONTH, -1);

        calendarPickerView.init(lastMonth.getTime(), nextYear.getTime());

    }
}
