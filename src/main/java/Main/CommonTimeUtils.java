package Main;

import java.util.concurrent.TimeUnit;

public class CommonTimeUtils {
    public static final int PREFFERED_WIDHT = 70;

    /**
     * Will give you the time in the following format mm:ss
     *
     * @param timeInMillisec the time to convert
     * @return the formatted time
     */
    public static String getFormattedTime(long timeInMillisec){
         return String.format("%s : %s",
                addZeroIf(TimeUnit.MILLISECONDS.toMinutes(timeInMillisec)),
                addZeroIf(TimeUnit.MILLISECONDS.toSeconds(timeInMillisec) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeInMillisec)))
        );
    }

    private static String addZeroIf(long time){
        String longToString = String.valueOf(time);
        return (time < 10) ? "0" + longToString : longToString;
    }
}
