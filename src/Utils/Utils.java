package Utils;

/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/****************
 * Class Utils
 * **************/
public class Utils {

    /**
     * getAbsoluteValue
     * get absolute value of given number
     *
     * @param num number
     * @return value
     */

    public static double getAbsoluteValue(double num) {
        if (num < 0) {
            return (-1) * num;
        }
        return num;
    }
}
