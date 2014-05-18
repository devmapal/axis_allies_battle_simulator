package foss.devmapal.axis_allies_calc.axis_allies_calc;

/**
 * Created by devmapal on 3/29/14.
 */
public class Submarine {
    public final static int attack;
    public final static int defense;
    public final static int cost;
    public final static String name = "submarine";
    public static final int id = 9;

    static {
        attack = 3;
        defense = 3;
        cost = 8;
    }

    public static int attack() {
        return Unit.fight(attack);
    }

    public static int defend() {
        return Unit.fight(defense);
    }
}
