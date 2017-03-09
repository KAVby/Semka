package com.example.caldr;

import java.util.Calendar;

/**
 * Created by Home on 07.03.2017.
 */

public class DifDate {

public static Calendar gc1,gc2;
    public static int diferenceDate(int field, Calendar c1, Calendar c2){
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
            if (gc1.get(Calendar.MONTH)>gc2.get(Calendar.MONTH)){
                gc2.set(Calendar.YEAR,gc1.get(Calendar.YEAR)+1);
                for (gc1.add(field, 1); gc1.compareTo(gc2) <= 0; gc1.add(field, 1)) {
                    count++;
                }
            }
            if (gc1.get(Calendar.MONTH)<gc2.get(Calendar.MONTH)){
                gc1.clear(Calendar.YEAR);
                gc2.clear(Calendar.YEAR);
                for (gc1.add(field, 1); gc1.compareTo(gc2) <=0; gc1.add(field, 1))
                {
                    count++;
                }
            }
            if (gc1.get(Calendar.MONTH)==gc2.get(Calendar.MONTH)){
              if (gc1.get(Calendar.DAY_OF_MONTH)<=gc2.get(Calendar.DAY_OF_MONTH))

                count=0;
else
                 count=11;
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
//                gc1.clear(Calendar.MONTH);
//                gc2.clear(Calendar.MONTH);
                gc1.clear(Calendar.YEAR);
                gc2.clear(Calendar.YEAR);
                for (gc1.add(field, 1); gc1.compareTo(gc2) <= 0; gc1.add(field, 1)) {
                    count++;
                }
            }
        }
        if (c1.compareTo(c2) >0) count=0;
        return count;

    }

























}
