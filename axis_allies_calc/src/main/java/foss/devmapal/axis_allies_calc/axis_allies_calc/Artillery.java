package foss.devmapal.axis_allies_calc.axis_allies_calc;

/**
 * Created by devmapal on 3/29/14.
 */
public class Artillery {
    public static final int attack;
    public static final int defense;
    public static final int cost;
    public final static String name= "artillery";

    static {
        attack = 2;
        defense = 2;
        cost = 4;
    }

    public static int attack() {
        return Unit.fight(attack);
    }

    public static int defend() {
        return Unit.fight(defense);
    }
}
