package foss.devmapal.axis_allies_calc.axis_allies_calc;

/**
 * Created by devmapal on 4/12/14.
 */
public class BattleResult extends Battle {
    public BattleResult(Army attacker,
                      WeaponsDevelopment attacker_wd,
                      Army defender,
                      WeaponsDevelopment defender_wd,
                      int sim_iters) {
        super(attacker, attacker_wd, defender, defender_wd, sim_iters);
    }

    public Army get_attacker() {
        return attacker;
    }

    public WeaponsDevelopment get_attacker_wd() {
        return attacker_wd;
    }

    public Army get_defender() {
        return defender;
    }

    public WeaponsDevelopment get_defender_wd() {
        return defender_wd;
    }

    public int get_sim_iters() {
        return sim_iters;
    }
}
