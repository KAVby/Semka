package com.example.caldr;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateUtils;
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
    DatePicker datePicker;
    Calendar c1,c2,gc1,gc2;
    public static final String APP_PREFERENCES = "mysettings"; //название файла для хранения настроек сохраненной даты
    public static  String APP_PREFERENCES_str1_ = "str1_"; //
    private SharedPreferences mSettings;
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


public void onclick11 (View v33){
        day  = datePicker.getDayOfMonth();
        month= datePicker.getMonth();
        year = datePicker.getYear();



    c1= Calendar.getInstance();
    c2= Calendar.getInstance();
    c1.set(year,month,day); //дата которую выбрали
    c2.set(year_now, month_now, day_now); //сегодняшняя дата

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//    APP_PREFERENCES_str1_=sdf.format(c1);
//    mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = mSettings.edit();
//    editor.putString(APP_PREFERENCES_str1_, sdf.format(c1));
//    editor.apply();



    mTextDate.setText(c1+"Выслуга:\n"+"\nЛет  " +diferenceDate(Calendar.YEAR, c1, c2) +"\nМесяцев  "+diferenceDate(Calendar.MONTH, c1, c2)+"\nДней  "+diferenceDate(Calendar.DAY_OF_MONTH, c1, c2));
             }

public  int diferenceDate(int field, Calendar c1, Calendar c2){
    int count = 0;
    gc1 = (Calendar)c1.clone();
    gc2 = (Calendar)c2.clone();

           // очищаем поля, которые мы не будем учитывать при сравнении дат
if (field==Calendar.YEAR) {
    for (gc1.add(field, 1); gc1.compareTo(gc2) <=0; gc1.add(field, 1))
    {
        count++;
    }
}
    if (field==Calendar.MONTH){

       if (gc1.get(Calendar.MONTH)<=gc2.get(Calendar.MONTH)){
           gc1.clear(Calendar.YEAR);
           gc2.clear(Calendar.YEAR);
           for (gc1.add(field, 1); gc1.compareTo(gc2) <=0; gc1.add(field, 1))
           {
               count++;
           }
    }
        else {//(gc1.get(Calendar.MONTH)>gc2.get(Calendar.MONTH))
            gc2.set(Calendar.YEAR,gc1.get(Calendar.YEAR)+1);
            for (gc1.add(field, 1); gc1.compareTo(gc2) <= 0; gc1.add(field, 1)) {
                count++;
            }
        }
    }

    if (field==Calendar.DAY_OF_MONTH){

        if (gc1.get(Calendar.DAY_OF_MONTH)<=gc2.get(Calendar.DAY_OF_MONTH)){
            gc1.clear(Calendar.MONTH);
            gc2.clear(Calendar.MONTH);
            gc1.clear(Calendar.YEAR);
            gc2.clear(Calendar.YEAR);
            for (gc1.add(field, 1); gc1.compareTo(gc2) <=0; gc1.add(field, 1))
            {
                count++;
            }
        }
        else {
            gc2.set(Calendar.MONTH,gc1.get(Calendar.MONTH)+1);
            for (gc1.add(field, 1); gc1.compareTo(gc2) <= 0; gc1.add(field, 1)) {
                count++;
            }
        }
    }
if (c1.compareTo(c2) >0) count=0;
        return count;

}



}
