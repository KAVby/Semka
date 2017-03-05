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
    int   day, day_now;
    int   month, month_now;
    int   year, year_now;
    TextView mTextDate;
    Button ok;
    Date d,d1;
    DatePicker datePicker;
    Calendar c1,c2,gc1,gc2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visluga);
        ok = (Button) findViewById(R.id.ok);
        mTextDate = (TextView) findViewById(R.id.textViewDate);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
    day_now= datePicker.getDayOfMonth();
    month_now= datePicker.getMonth();
    year_now = datePicker.getYear();
    }

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
    String formatedDate2 = sdf.format(new Date(year_now, month_now, day_now));
    d = new Date(year,month,day); //дата которую выбрали
    long t = d.getTime();
    d1 = new Date(year_now, month_now, day_now); //сегодняшняя дата
    long t1 = d1.getTime();
    long t2=t1-t;
    d1.setTime(t2);
    long t3 = t2/(1000*60*60*24);//количество дней с выбранной даты

    c1= Calendar.getInstance();
    c2= Calendar.getInstance();
    c1.set(year,month,day); //дата которую выбрали
    c2.set(year_now, month_now, day_now); //сегодняшняя дата






    mTextDate.setText(diferenceDate(Calendar.YEAR, c1, c2) +"\n  "+diferenceDate(Calendar.MONTH, c1, c2)+"\n  "+diferenceDate(Calendar.DAY_OF_MONTH, c1, c2));
            //+" \n  "+ sdf.format(c1.getTime())+" \n  "+sdf.format(c2.getTime())+" \n  "+formatedDate+"   " +d+"   "+formatedDate2);
            //+"\n"+d1+"\n милисек"+t3+"\n год"+(year_now-year)+"\n месяц"+(month_now-month)+"\n день"+(day_now-day));
    }

public  int diferenceDate(int field, Calendar c1, Calendar c2 ){
    int count = 0;
    gc1 = (Calendar)c1.clone();
    gc2 = (Calendar)c2.clone();
    for (gc1.add(field, 1); gc1.compareTo(gc2) <=0; gc1.add(field, 1))
    {
        count++;
    }
    return count;

}



}
