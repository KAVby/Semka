package com.example.caldr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import android.R.color;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import static java.util.Calendar.YEAR;


public class MainActivity extends FragmentActivity {
	Date previousDate = null;
	Date userSelectedDate = null;
	Date f1 = new Date(); // текущая дата
	 public static final String APP_PREFERENCES = "mysettings"; //название файла для хранения настроек
	public static final String APP_PREFERENCES_COUNTER = "counter"; // параметр, кот. сохраняем в настройках отвечает за подсветку смены
	public static final String APP_PREFERENCES_str1 = "str1";
	public static final String APP_PREFERENCES_str2 = "str2";
	public static final String APP_PREFERENCES_str3 = "str3";// параметр, кот. сохраняем в настройках отвечает за состав смены
	public static  String APP_PREFERENCES_str1_ = "str1_";
	private SharedPreferences mSettings; //переменная экземпляра класса, кот отвечает за настройки
	int nsm;  //для выбора смены
	String string1, string2, string3, string_date;
    Calendar c1,c2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Button BtnNow;
		setContentView(R.layout.activity_main);
		BtnNow = (Button) findViewById(R.id.BtnNow);
		final TextView mText3 = (TextView) findViewById(R.id.TextView1) ;
		final TextView mText = (TextView) findViewById(R.id.textView) ;
		final TextView mText4 = (TextView) findViewById(R.id.TextView2) ;
        final TextView textVisluga = (TextView) findViewById(R.id.textVisluga) ;

		 mText4.setMovementMethod(new ScrollingMovementMethod());
		final CaldroidFragment caldroidFragment = new CaldroidFragment();
		Bundle args = new Bundle();
		final Calendar cal = Calendar.getInstance();
		final Calendar cal2 = Calendar.getInstance();
		final Calendar cal3 = Calendar.getInstance();
		final Calendar cal4 = Calendar.getInstance();
		args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
		args.putInt(CaldroidFragment.YEAR, cal.get(YEAR));
		args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY);
		caldroidFragment.setArguments(args);
		FragmentTransaction t = getSupportFragmentManager().beginTransaction();
		t.replace(R.id.calendar1, caldroidFragment);
		t.commit();

		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);   //инициализирую файл настроек
		if (mSettings.contains(APP_PREFERENCES_COUNTER)) //если параметр номер смены в файле уже создан, то берем его
			// Получаем число из настроек
			nsm = mSettings.getInt(APP_PREFERENCES_COUNTER, 0);
		if (mSettings.contains(APP_PREFERENCES_str1)) //если параметр список смены в файле уже создан, то берем его
			// Получаем число из настроек
			 string1 = mSettings.getString(APP_PREFERENCES_str1, "none");
		else string1 ="Кононов \nБуланкин \nМинин \nи др. ";

		if (mSettings.contains(APP_PREFERENCES_str2)) //если параметр в файле уже создан, то берем его
			// Получаем число из настроек
			string2 = mSettings.getString(APP_PREFERENCES_str2, "none");
		else string2 ="Шибеко \nМинец \nТочилов \nСойко";
		if (mSettings.contains(APP_PREFERENCES_str1)) //если параметр в файле уже создан, то берем его
			// Получаем число из настроек
			string3 = mSettings.getString(APP_PREFERENCES_str3, "none");
		else string3 ="Рослик \nРак \nФесенко \nХадосевич \nКурейчик \nСупронович \nВоробьева";

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
            c2 = Calendar.getInstance();
            textVisluga.setText("Выслуга:" + "\nЛет  " + DifDate.diferenceDate(Calendar.YEAR, c1, c2) + "\nМесяцев  " + DifDate.diferenceDate(Calendar.MONTH, c1, c2) + "\nДней  " + DifDate.diferenceDate(Calendar.DAY_OF_MONTH, c1, c2));
        }


        else {string_date ="08-03-2017"; textVisluga.setText("Заполните\n для расчета\n выслуги");}




		final CaldroidListener listener = new CaldroidListener() {

			public void alg(Date date){

				Date j;
					cal2.setTime(date);
					j=cal2.getTime();
				int w=cal2.get(Calendar.WEEK_OF_YEAR);
				if ((w%2)==0)
					mText.setText("1");
				else mText.setText("2"); //первая или вторая смена у жены взависимости от четности недели
					double i = j.getTime()/100000/864;
				double src = i/3;
				int res = (int)src; //целая часть
				double res2 = src - res; //дробная часть
				double src2 = (i+1)/3;
				int res3 = (int)src2; //целая часть
				double res4 = src2 - res3; //дробная часть
				SimpleDateFormat formattedDate = new SimpleDateFormat("dd.MM.yyyy");
				if (res2==0) {
					mText3.setText("Третья смена" +"\n"+formattedDate.format(cal2.getTime()));	
				    mText4.setText(string3) ;
				   }
				else 
					if ((res2)>0 & (res4)>0) {
					 mText3.setText("Первая смена" +"\n"+formattedDate.format(cal2.getTime())) ;
					 mText4.setText(string1) ;
					}
				      else {
					    mText3.setText("Вторая смена  " +"\n"+formattedDate.format(cal2.getTime())) ;
					    mText4.setText(string2) ;
					  }

				
			}
			 public void onChangeMonth(int month, int year) {
				 //SimpleDateFormat formattedDate = new SimpleDateFormat("dd.MM.yyyy");  // формат даты для отображения

				 if (userSelectedDate != null ){
					    caldroidFragment.setBackgroundResourceForDate(R.color.caldroid_white, userSelectedDate);
					    caldroidFragment.refreshView();
					}
				 caldroidFragment.setBackgroundResourceForDate(R.color.caldroid_lighter_gray, f1);
				    caldroidFragment.refreshView();
				 cal.set(year, (month-1), 1,0,0,0);
				 Date d1 = new Date();
		     		d1=cal4.getTime();
     		Date d = new Date();
     		d=cal.getTime();
     		double i = d.getTime()/100000/864;
     		mText3.setText("Выберите дату");
     		mText4.setText("") ;
     		//mText3.setText("  "+month+"  "+year+"   "+cal.get(Calendar.YEAR)+"  " +(formattedDate.format(d.getTime()))+"  "+(cal.getTime()));
			double src = i/3;
			int res = (int)src; //целая часть
			double res2 = src - res; //дробная часть			
			double src2 = (i+1)/3;
			int res3 = (int)src2; //целая часть
			double res4 = src2 - res3; //дробная часть	
			int j;
			if (res2==0) {cal.add(cal.DAY_OF_MONTH, 1-nsm);
				 for (j=1; j<50; j=j+1){
				        caldroidFragment.setTextColorForDate(color.holo_red_light, cal.getTime());
				        cal.add(cal.DAY_OF_MONTH, 3);}
				}
			else 
				if ((res2)>0 & (res4)>0) {cal.add(cal.DAY_OF_MONTH, -nsm);
		     for (j=1; j<50; j=j+1){
		        caldroidFragment.setTextColorForDate(color.holo_red_light, cal.getTime());
		        cal.add(cal.DAY_OF_MONTH, 3);}
					}
			      else {
			    	   cal.add(cal.DAY_OF_MONTH, 2-nsm);
						 for (j=1; j<50; j=j+1){
						        caldroidFragment.setTextColorForDate(color.holo_red_light, cal.getTime());
						        cal.add(cal.DAY_OF_MONTH, 3);}
			      }

			mText3.setText("Выбери дату   ");
			
   //  		alg(d1);
		     
             }
			 
			 
			 
			 
		@SuppressWarnings("unused")
		@Override
			public void onSelectDate(Date date, View view) {
				// TODO Auto-generated method stub
			
			alg(date);
			if (previousDate != null   ){
			caldroidFragment.setBackgroundResourceForDate(R.color.caldroid_white, previousDate);
			caldroidFragment.setBackgroundResourceForDate(R.color.caldroid_lighter_gray, f1);
    		caldroidFragment.refreshView();
				}
			caldroidFragment.setBackgroundResourceForDate(R.color.caldroid_holo_blue_dark, date);
			previousDate = date;
			caldroidFragment.refreshView();
			userSelectedDate = date;

			}
		
		};
		
		caldroidFragment.setCaldroidListener(listener);	
		
		OnClickListener BtnNowClc = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub 

				if (userSelectedDate != null ){
				    caldroidFragment.setBackgroundResourceForDate(R.color.caldroid_white, userSelectedDate);
				    caldroidFragment.refreshView();
				}
				
				 Date f = new Date();



				 caldroidFragment.moveToDate(f);
				 caldroidFragment.refreshView();

				cal3.set(cal3.get(Calendar.YEAR),cal3.get(Calendar.MONTH),cal3.get(Calendar.DAY_OF_MONTH),0,0,0 );  // убираем время (мешает при расчете смен)
		     		f=cal3.getTime();
		     		caldroidFragment.setBackgroundResourceForDate(R.color.caldroid_holo_blue_dark, f);
		     		double i = f.getTime()/100000/864;
					double src = i/3;
					int res = (int)src; //целая часть
					double res2 = src - res; //дробная часть
					double src2 = (i+1)/3;
					int res3 = (int)src2; //целая часть
					double res4 = src2 - res3; //дробная часть
					SimpleDateFormat formattedDate = new SimpleDateFormat("dd.MM.yyyy");
					if (res2==0) {
						mText3.setText("Третья смена" +"\n"+formattedDate.format(cal3.getTime()));
					    mText4.setText(string3) ;
					}
					else 
						if ((res2)>0 & (res4)>0) {
						 mText3.setText("Первая смена"+"\n"+formattedDate.format(cal3.getTime())) ;
						 mText4.setText(string1) ;
						}
					      else {
						    mText3.setText("Вторая смена"+"\n"+formattedDate.format(cal3.getTime()));
						    mText4.setText(string2) ;
					      }
			

			}
			
		
		};	
	   
		BtnNow.setOnClickListener(BtnNowClc);
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.smena1) {

			SharedPreferences.Editor editor = mSettings.edit();
			editor.putInt(APP_PREFERENCES_COUNTER, 6);
			editor.apply();
			Intent intent = getIntent();
			finish();
			startActivity(intent);
	//		onCreate(Bundle);
			return true;
		}
		if (id == R.id.smena2) {

			SharedPreferences.Editor editor = mSettings.edit();
			editor.putInt(APP_PREFERENCES_COUNTER, 5);
			editor.apply();
			Intent intent = getIntent();
			finish();
			startActivity(intent);
	//		onCreate(Bundle);
			return true;
		}
		if (id == R.id.smena3) {

			SharedPreferences.Editor editor = mSettings.edit();
			editor.putInt(APP_PREFERENCES_COUNTER, 7);
			editor.apply();
			Intent intent = getIntent();
			finish();
			startActivity(intent);
	//		onCreate(Bundle );
			return true;
		}
		if (id == R.id.sostav) {


			Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
			intent.putExtra("sm1", string1);
			intent.putExtra("sm2", string2);
			intent.putExtra("sm3", string3);
			MainActivity.this.finish();
			startActivity(intent);

			return true;
		}
		if (id == R.id.visluga) {


			Intent intent = new Intent(MainActivity.this, VislugaActivity.class);
			MainActivity.this.finish();
			startActivity(intent);

			return true;
		}
		if (id == R.id.exit) {

			this.finish();
			android.os.Process.killProcess(android.os.Process.myPid());
			//System.exit(0);
		}
		return super.onOptionsItemSelected(item);
	}
}
