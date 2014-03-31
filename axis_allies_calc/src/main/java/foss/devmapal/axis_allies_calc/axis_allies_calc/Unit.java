package foss.devmapal.axis_allies_calc.axis_allies_calc;

/**
 * Created by devmapal on 3/29/14.
 */
public class Unit {
    public static int fight(int strength) {
        if(Die.roll() > strength) return 0;
        else return 1;
    }
}
