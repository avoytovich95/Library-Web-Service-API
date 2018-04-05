package utility;

/**
 * Created by Alex on 3/7/2018.
 */
public class SimpleRandomID {
    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = upper.toLowerCase();
    private static final String digits = "0123456789";
    private static final String alphanum = upper + lower + digits;

    public static String nextString(int size) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; ++i)
            str.append(alphanum.charAt((int) (Math.random() * alphanum.length())));
        return str.toString();
    }
}
