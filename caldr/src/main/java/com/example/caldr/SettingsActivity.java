package com.example.caldr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by kureichyk on 18.02.2017.
 */

public class SettingsActivity extends Activity {

    public static final String APP_PREFERENCES = "mysettings"; //название файла для хранения настроек
    public static final String APP_PREFERENCES_str1 = "str1"; //
    public static final String APP_PREFERENCES_str2 = "str2";
    public static final String APP_PREFERENCES_str3 = "str3";
    public static final String APP_PREFERENCES_COUNTER = "counter";
    public static  String APP_PREFERENCES_str1_ = "str1_";
    private SharedPreferences mSettings;

    EditText sm_1;
    EditText sm_2;
    EditText sm_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button save = (Button)findViewById(R.id.Btnsave);
        Button cancel = (Button)findViewById(R.id.Btncancel);
        sm_1 = (EditText)findViewById(R.id.editText1);
        sm_1.setText(getIntent().getStringExtra("sm1"));
        sm_2 = (EditText)findViewById(R.id.editText2);
        sm_2.setText(getIntent().getStringExtra("sm2"));
        sm_3 = (EditText)findViewById(R.id.editText3);
        sm_3.setText(getIntent().getStringExtra("sm3"));

    }
public void cancelactivity(View v){
    {InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);// убираем клаву после нажатия кнопки, что бы не смещался mainLayout
							    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);}
    Intent intent = new Intent(SettingsActivity.this,MainActivity.class);
    finish();
    startActivity(intent);
}
    public void saveData(View v){
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_str1, sm_1.getText().toString());
        editor.putString(APP_PREFERENCES_str2, sm_2.getText().toString());
        editor.putString(APP_PREFERENCES_str3, sm_3.getText().toString());
        editor.apply();
    }




}
