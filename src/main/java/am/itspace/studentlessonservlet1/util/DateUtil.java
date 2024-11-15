package am.itspace.studentlessonservlet1.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat TIME =  new SimpleDateFormat("HH:mm");

    public static Date timeForDate(String time){
        try {
            return TIME.parse(time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String dateForTime(Date date){
        return (String) TIME.format(date);
    }
}
