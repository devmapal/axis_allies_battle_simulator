package foss.devmapal.axis_allies_calc.axis_allies_calc;

/**
 * Created by devmapal on 4/12/14.
 */
public class Battle {
    protected Army attacker;
    protected WeaponsDevelopment attacker_wd;
    protected Army defender;
    protected WeaponsDevelopment defender_wd;
    protected int sim_iters;

    public Battle(Army attacker,
                      WeaponsDevelopment attacker_wd,
                      Army defender,
                      WeaponsDevelopment defender_wd,
                      int sim_iters) {
        this.attacker = attacker;
        this.attacker_wd = attacker_wd;
        this.defender = defender;
        this.defender_wd = defender_wd;
        this.sim_iters = sim_iters;
    }
}
