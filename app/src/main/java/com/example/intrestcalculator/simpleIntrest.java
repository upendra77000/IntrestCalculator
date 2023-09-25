package com.example.intrestcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class simpleIntrest extends AppCompatActivity {

    private EditText p;
    private EditText r;
    private EditText sDate;
    private EditText eDate;
    private Button sub;
    private TextView pMonth;
    private TextView pYear;
    private TextView pDay;
    private TextView tIntrest;
    private TextView fAmount;
    DatePickerDialog.OnDateSetListener setListener1,setListener2;
    String date1,date2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_intrest);

        p=(EditText)findViewById(R.id.principle);
        r=(EditText)findViewById(R.id.rate);
        sDate=(EditText)findViewById(R.id.startDate);
        eDate=(EditText)findViewById(R.id.endDate);
        sub=(Button)findViewById(R.id.submit);

        pMonth=(TextView)findViewById(R.id.perMonth);
        pYear=(TextView)findViewById(R.id.perYear);
        pDay=(TextView)findViewById(R.id.perDay);
        tIntrest=(TextView)findViewById(R.id.totIntrest);
        fAmount=(TextView)findViewById(R.id.finAmount);

        final Calendar c = Calendar.getInstance();
        int year1 = c.get(Calendar.YEAR);
        int month1 = c.get(Calendar.MONTH);
        int day1 = c.get(Calendar.DAY_OF_MONTH);

        final Calendar c1 = Calendar.getInstance();
        int year2 = c1.get(Calendar.YEAR);
        int month2 = c1.get(Calendar.MONTH);
        int day2= c1.get(Calendar.DAY_OF_MONTH);

        sDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        simpleIntrest.this, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth
                        ,setListener1,year1,month1,day1);
                datePickerDialog.show();
            }
        });
        setListener1 =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month=month+1;
                date1=dayOfMonth+"/"+month+"/"+year;
                sDate.setText(date1);
            }
        };

        eDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        simpleIntrest.this, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth
                        ,setListener2,year2,month2,day2);
                datePickerDialog.show();
            }
        });
        setListener2 =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month=month+1;
                date2=dayOfMonth+"/"+month+"/"+year;
                eDate.setText(date2);
            }
        };

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int P = 0;
                float R = 0;
                if (checkEmptyFileds(p, r,sDate,eDate)) {
                    P = Integer.parseInt(p.getText().toString());
                    R = Float.parseFloat(r.getText().toString());
                }
                else
                    Toast.makeText(simpleIntrest.this, "Please enter all the values", Toast.LENGTH_SHORT).show();
                float i = P * R / 100;
                String s = Float.toString(i);
                pMonth.setText(s);
                float j = 12 * i;
                String s1 = Float.toString(j);
                pYear.setText(s1);
                float k = i / 30;
                String s2 = Float.toString(k);
                pDay.setText(s2);
                    int[] a = substract(date1, date2);
                    float l = a[0] * k + a[1] * i + a[2] * j;
                    String s3 = Float.toString(l);
                    tIntrest.setText(s3);
                    float m = l + (float) P;
                    String s4 = Float.toString(m);
                    fAmount.setText(s4);
            }
        });

    }
    boolean checkEmptyFileds(EditText p, EditText r,EditText sDate,EditText eDate) {
        boolean b = true;
        if (p.getText().toString().equals("") || r.getText().toString().equals("") || sDate.equals(null) || eDate.equals(null)) {
            b = false;
        }
        return b;
    }
    int[] substract(String date1, String date2) {
        String[] s1= date1.split("/");
        String[] s2=date2.split("/");
        int days1=Integer.parseInt(s1[0]);
        int days2=Integer.parseInt(s2[0]);
        int month1=Integer.parseInt(s1[1]);
        int month2=Integer.parseInt(s2[1]);
        int year1=Integer.parseInt(s1[2]);
        int year2=Integer.parseInt(s2[2]);
        int fDays,fMonths,fYears;
        if(days2>days1){
            fDays=days2-days1;
        }
        else{
            fDays=30+days2-days1;
            month2=month2-1;
        }
        if(month2>month1){
            fMonths=month2-month1;
        }
        else{
            fMonths=12+month2-month1;
            year2=year2-1;
        }
        fYears=year2-year1;
        int[] k =new int[3];
        k[0]=fDays;
        k[1]=fMonths;
        k[2]=fYears;
        return k;
    }
}
