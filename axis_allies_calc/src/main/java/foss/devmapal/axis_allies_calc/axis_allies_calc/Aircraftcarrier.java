package foss.devmapal.axis_allies_calc.axis_allies_calc;

/**
 * Created by devmapal on 3/29/14.
 */
public class Aircraftcarrier {
    public final static int attack;
    public final static int defense;
    public final static int cost;

    static {
        attack = 1;
        defense = 3;
        cost = 16;
    }

    public static int attack() {
        return Unit.fight(attack);
    }

    public static int defend() {
        return Unit.fight(defense);
    }
}
