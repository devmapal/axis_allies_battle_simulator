package foss.devmapal.axis_allies_calc.axis_allies_calc;

import foss.devmapal.axis_allies_calc.axis_allies_calc.Unit;

/**
 * Created by devmapal on 3/29/14.
 */
public class Infantry {
    public static final int attack;
    public static final int defense;
    public static final int cost;
    public static final String name = "infantry";

    static {
        attack = 1;
        defense = 2;
        cost = 3;
    }

    public static int attack() {
        return Unit.fight(attack);
    }

    public static int defend() {
        return Unit.fight(defense);
    }
}
