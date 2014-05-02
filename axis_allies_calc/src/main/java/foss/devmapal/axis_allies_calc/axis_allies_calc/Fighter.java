package foss.devmapal.axis_allies_calc.axis_allies_calc;

/**
 * Created by devmapal on 3/29/14.
 */
public class Fighter {
    public final static int attack;
    public final static int defense;
    public final static int cost;
    public final static String name = "fighter";
    public static final int id = 3;

    static {
        attack = 3;
        defense = 4;
        cost = 10;
    }

    public static int attack() {
        return Unit.fight(attack);
    }

    public static int defend() {
        return Unit.fight(defense);
    }
}
