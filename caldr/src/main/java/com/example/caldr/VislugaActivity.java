package com.example.caldr;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Home on 28.02.2017.
 */

public class VislugaActivity extends Activity {
    int   day;
    int   month;
    int   year;
    TextView mTextDate;
    Button ok;
    Date d,n;
    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visluga);
        ok = (Button) findViewById(R.id.ok);
        mTextDate = (TextView) findViewById(R.id.textViewDate);
        datePicker = (DatePicker)findViewById(R.id.datePicker);}


//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        String formatedDate = sdf.format(new Date(year, month, day));
//
//        Date d,n; d = new Date(year,month,day);mTextDate.setText(formatedDate);
//        Calendar.getInstance();}
        //Calendar.getTime();
public void onclick11 (View v33){
        day  = datePicker.getDayOfMonth();
        month= datePicker.getMonth();
        year = datePicker.getYear();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formatedDate = sdf.format(new Date(year, month, day));

     d = new Date(year,month,day);
    mTextDate.setText(formatedDate+"   " +d);
    }

}
