package foss.devmapal.axis_allies_calc.axis_allies_calc;

/**
 * Created by devmapal on 3/29/14.
 */
public class Transport {
    public final static int attack;
    public final static int defense;
    public final static int cost;
    public final static String name = "transport";
    public static final int id = 8;

    static {
        attack = 0;
        defense = 1;
        cost = 8;
    }

    public static int attack() {
        return 0;
    }

    public static int defend() {
        return Unit.fight(defense);
    }
}
