package Utils;

public class Utils {

    public static double getAbsoluteValue(double num) {
        if (num < 0) {
            return (-1) * num;
        }
        return num;
    }

    public int changeBit(int offset, int num) {
        int mask = 0x1;
        mask = mask << offset - 1;
        return num ^ mask;
    }
}
