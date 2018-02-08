package com.sire.storage.ModuleEnvironment.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ==================================================
 * All Right Reserved
 * Date:2017/9/26
 * Author:Sire
 * Description:
 * ==================================================
 */
public class CommonUtils {
    public static long generateDateKey(Date date)  {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        long dateSecondes = 0;
        try {
            dateSecondes = simpleDateFormat.parse(format).getTime()/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateSecondes;
    }

    /**
     * 获取时间整数
     * @param date
     * @param times
     * @return
     */
    public static long generateOffsetdayKey(Date date, int times)  {
        return generateDateTime(date) + 24*60*60*1000*times;
    }
    private static long generateDateTime(Date date)  {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String format = simpleDateFormat.format(date);
        long dateSecondes = 0;
        try {
            dateSecondes = simpleDateFormat.parse(format).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateSecondes;
    }
}
