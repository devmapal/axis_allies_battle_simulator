package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.preference.PreferenceManager;

/**
 * Created by devmapal on 4/12/14.
 */
public class LandBattle extends Battle {
    private boolean take_territory;

    public LandBattle(Army attacker,
                      WeaponsDevelopment attacker_wd,
                      Army defender,
                      WeaponsDevelopment defender_wd,
                      int sim_iters,
                      boolean take_territory) {
        super(attacker, attacker_wd, defender, defender_wd, sim_iters);
        this.take_territory = take_territory;
    }
}
