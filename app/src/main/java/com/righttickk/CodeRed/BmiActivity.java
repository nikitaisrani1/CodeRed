package com.righttickk.CodeRed;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BmiActivity extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private TextView result;

    Button calculateBtn, nextBtn;
    Intent intent, intent1, intent2;
    String mdate, mcycledata, mlengthdata;
    String strBmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        height = (EditText) findViewById(R.id.heightEdt);
        weight = (EditText) findViewById(R.id.weightEdt);
        result = (TextView) findViewById(R.id.bmiResult);

        calculateBtn = findViewById(R.id.calculate);
        nextBtn = findViewById(R.id.next);

        intent = getIntent();
        mdate = intent.getStringExtra("date");
        intent1 = getIntent();
        mcycledata = intent1.getStringExtra("cycledata");
        intent2 = getIntent();
        mlengthdata = intent2.getStringExtra("lenghtdata");

        height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBtn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.GONE);
            }
        });
        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBtn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.GONE);
            }
        });

    }

    public void calculateBmi(View view) {

        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null  &&  !"".equals(weightStr)) {
            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue / (heightValue * heightValue);
            strBmi = String.valueOf(bmi);
            result.setText("BMI = " + strBmi + " kg/m\u00B2");
            result.setVisibility(View.VISIBLE);
            calculateBtn.setVisibility(View.GONE);
            nextBtn.setVisibility(View.VISIBLE);
        }

    }

    public void nextBtn(View view) {
        Intent intent = new Intent(BmiActivity.this, ThirdIntroActivity.class);
        intent.putExtra("cycledata", mcycledata);
        intent.putExtra("lenghtdata", mlengthdata);
        intent.putExtra("date", mdate);
        intent.putExtra("bmi",strBmi );
        startActivity(intent);
    }
}