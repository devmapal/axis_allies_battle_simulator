package foss.devmapal.axis_allies_calc.axis_allies_calc;

import java.util.Random;

/**
 * Created by devmapal on 3/29/14.
 */
public class Die {
    private static Random rand;

    static {
        rand = new Random();
    }

    public static int roll() {
        return rand.nextInt(6) + 1;
    }
}
