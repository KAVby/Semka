package com.example.caldr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
    int   year, year_now,yotno;
    TextView mTextDate;
    Button ok;
    String string_date,sY,sM,sD;
    DatePicker datePicker;
    Calendar c1,c2,gc1,gc2;
    EditText editTextY;
    EditText editTextM;
    EditText editTextD;
    CheckBox checkBox;
    Boolean YorNo;


    public static final String APP_PREFERENCES = "mysettings"; //название файла для хранения настроек
    public static final String APP_PREFERENCES_COUNTER = "counter"; // параметр, кот. сохраняем в настройках отвечает за подсветку смены
    public static final String APP_PREFERENCES_str1 = "str1";
    public static final String APP_PREFERENCES_str2 = "str2";
    public static final String APP_PREFERENCES_str3 = "str3";// параметр, кот. сохраняем в настройках отвечает за состав смены
    public static  String APP_PREFERENCES_str1_ = "str1_"; //дата
    public static  String APP_PREFERENCES_str1_y = "str1_Y";
    public static  String APP_PREFERENCES_str1_m = "str1_M";
    public static  String APP_PREFERENCES_str1_d = "str1_D";
    public static  String APP_PREFERENCES_str1_yotno = "yotno";
    private SharedPreferences mSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visluga);
        ok = (Button) findViewById(R.id.ok);
        mTextDate = (TextView) findViewById(R.id.textViewDate);
        editTextY = (EditText) findViewById(R.id.editTextY);
        editTextM = (EditText) findViewById(R.id.editTextM);
        editTextD = (EditText) findViewById(R.id.editTextD);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
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
        }else      c1 = Calendar.getInstance();
        if (mSettings.contains(APP_PREFERENCES_str1_y))
        {
            sY = mSettings.getString(APP_PREFERENCES_str1_y, "none");
            editTextY.setText(sY);
        } else  editTextY.setText("0");
        if (mSettings.contains(APP_PREFERENCES_str1_m))
        {
            sM = mSettings.getString(APP_PREFERENCES_str1_m, "none");
            editTextM.setText(sM);
        }else  editTextM.setText("0");
        if (mSettings.contains(APP_PREFERENCES_str1_d))
        {
            sD = mSettings.getString(APP_PREFERENCES_str1_d,"none");
            editTextD.setText(sD);
        }else  editTextD.setText("0");
        if (mSettings.contains(APP_PREFERENCES_str1_yotno))
        {
            yotno = mSettings.getInt(APP_PREFERENCES_str1_yotno,0);
            if (yotno==1) checkBox.setChecked(true);else  checkBox.setChecked(false);
        }

        //else {string_date ="08-03-2017"; textVisluga.setText("Заполните\n для расчета\n выслуги");}


    }


public void onclick11 (View v33){
        day  = datePicker.getDayOfMonth();
        month= datePicker.getMonth();
        year = datePicker.getYear();


    if (checkBox.isChecked()) yotno=1;// для записи состояния в файл
    else yotno=2;

//    sY=Integer.parseInt(editTextY.getText().toString());
//    sM=Integer.parseInt(editTextM.getText().toString());
//    sD=Integer.parseInt(editTextD.getText().toString());


    c1= Calendar.getInstance();
    c2= Calendar.getInstance();

//    c2.add(Calendar.YEAR, sY);
//    c2.add(Calendar.MONTH, sM);
//    c2.add(Calendar.DAY_OF_MONTH, sD);

//    gc3= Calendar.getInstance();

//    c2.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH); //сегодняшняя дата
    if (editTextY.getText().length()==0)
        editTextY.setText("0");//sY="0";
    if (editTextM.getText().length()==0)
        editTextM.setText("0");//sM="0";
    if (editTextD.getText().length()==0)
        editTextD.setText("0");//sD="0";

    sY=editTextY.getText().toString();
    sM=editTextM.getText().toString();
    sD=editTextD.getText().toString();



    c1.set(year,month,day); //дата которую выбрали

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);//закидываем выбранную дату в файл
    SharedPreferences.Editor editor = mSettings.edit();
    editor.putString(APP_PREFERENCES_str1_, sdf.format(c1.getTime()));
    editor.putString(APP_PREFERENCES_str1_y, sY);
    editor.putString(APP_PREFERENCES_str1_m, sM);
    editor.putString(APP_PREFERENCES_str1_d, sD);
    editor.putInt(APP_PREFERENCES_str1_yotno, yotno);
    editor.apply();
gc1=(Calendar)c1.clone();
    gc2=(Calendar)c2.clone();

//    gc3.add(Calendar.YEAR,-gc1.get(Calendar.YEAR));
//    gc3.add(Calendar.MONTH,-gc1.get(Calendar.MONTH)-1);
//    gc3.add(Calendar.DAY_OF_MONTH,-gc1.get(Calendar.DAY_OF_MONTH));


    mTextDate.setText(sdf.format(c1.getTime())+"\nВыслуга:"+"\nЛет  " +DifDate.diferenceDate(Calendar.YEAR, c1, c2, sY, sM, sD) +"\nМесяцев  "+DifDate.diferenceDate(Calendar.MONTH, c1, c2, sY, sM, sD)+"\nДней  "+DifDate.diferenceDate(Calendar.DAY_OF_MONTH, c1, c2, sY, sM, sD));
             }
    public void onclick12 (View v34){
        Intent intent = new Intent(VislugaActivity.this,MainActivity.class);
        finish();
        startActivity(intent);
    }




}
