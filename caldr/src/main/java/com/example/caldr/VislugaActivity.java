package com.example.caldr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.DatePicker;

import java.text.ParseException;
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
    String string_date;
    DatePicker datePicker;
    Calendar c1,c2,gc1,gc2;
    public static final String APP_PREFERENCES = "mysettings"; //название файла для хранения настроек
    public static final String APP_PREFERENCES_COUNTER = "counter"; // параметр, кот. сохраняем в настройках отвечает за подсветку смены
    public static final String APP_PREFERENCES_str1 = "str1";
    public static final String APP_PREFERENCES_str2 = "str2";
    public static final String APP_PREFERENCES_str3 = "str3";
    public static  String APP_PREFERENCES_str1_ = "str1_"; //
    private SharedPreferences mSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visluga);
        ok = (Button) findViewById(R.id.ok);
        mTextDate = (TextView) findViewById(R.id.textViewDate);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
//    day_now= datePicker.getDayOfMonth();
//    month_now= datePicker.getMonth();
//    year_now = datePicker.getYear();

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains(APP_PREFERENCES_str1_)) //если параметр дата начала службы в файле уже создан, то берем его (дата)
        // Получаем число из настроек
        {
            string_date = mSettings.getString(APP_PREFERENCES_str1_, "none");
            SimpleDateFormat stringtodate = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            try {
                date = stringtodate.parse(string_date);
            } catch (ParseException ex) {
                System.out.println("Это не должно произойти");
            }
            c1 = Calendar.getInstance();
            c1.setTime(date);

//            textVisluga.setText("Выслуга:" + "\nЛет  " + DifDate.diferenceDate(Calendar.YEAR, c1, c2) + "\nМесяцев  " + DifDate.diferenceDate(Calendar.MONTH, c1, c2) + "\nДней  " + DifDate.diferenceDate(Calendar.DAY_OF_MONTH, c1, c2));
            datePicker.updateDate(c1.get(Calendar.YEAR),c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
        }


        //else {string_date ="08-03-2017"; textVisluga.setText("Заполните\n для расчета\n выслуги");}


    }


public void onclick11 (View v33){
        day  = datePicker.getDayOfMonth();
        month= datePicker.getMonth();
        year = datePicker.getYear();



    c1= Calendar.getInstance();
    c2= Calendar.getInstance();
    c1.set(year,month,day); //дата которую выбрали
//    c2.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH); //сегодняшняя дата

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);//закидываем выбранную дату в файл
    SharedPreferences.Editor editor = mSettings.edit();
    editor.putString(APP_PREFERENCES_str1_, sdf.format(c1.getTime()));
    editor.apply();



    mTextDate.setText(sdf.format(c1.getTime())+"\nВыслуга:"+"\nЛет  " +DifDate.diferenceDate(Calendar.YEAR, c1, c2) +"\nМесяцев  "+DifDate.diferenceDate(Calendar.MONTH, c1, c2)+"\nДней  "+DifDate.diferenceDate(Calendar.DAY_OF_MONTH, c1, c2));
             }
    public void onclick12 (View v34){
        Intent intent = new Intent(VislugaActivity.this,MainActivity.class);
        finish();
        startActivity(intent);
    }




}
