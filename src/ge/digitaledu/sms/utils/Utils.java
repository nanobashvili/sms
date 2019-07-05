package ge.digitaledu.sms.utils;

public class Utils {

    public static String trimRightToLeft(String str, int count) {
        if (str != null && str.length() > count) {
            str = str.substring(0, str.length() - count);
        }
        return str;
    }
}
