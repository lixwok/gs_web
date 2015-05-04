package cn.com.wonder.xlab.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/4/28.
 */
public class Test {

    public static void main(String args[]){
        String str ="20150423073000";
        str = dateToyyyymmddhhmmss(str);
        System.out.println("str = " + str);

    }
    public static String dateToyyyymmddhhmmss(String str) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddhhmmss");
        Date date = null;
        SimpleDateFormat sdf2 = null;
        try {
            date = (Date) sdf1.parse(str);
            sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.print(sdf2.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf2.format(date);
    }
}
