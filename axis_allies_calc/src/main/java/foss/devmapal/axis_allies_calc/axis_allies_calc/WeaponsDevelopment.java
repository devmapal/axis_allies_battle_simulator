package foss.devmapal.axis_allies_calc.axis_allies_calc;

import java.io.Serializable;

/**
 * Created by devmapal on 4/4/14.
 */
public class WeaponsDevelopment implements Serializable {
    public boolean jet_fighters,
                   super_submarines,
                   combined_bombardment,
                   heavy_bombers;

    public WeaponsDevelopment(boolean jet_fighters,
                              boolean super_submarines,
                              boolean combined_bombardment,
                              boolean heavy_bombers) {
        this.jet_fighters = jet_fighters;
        this.super_submarines = super_submarines;
        this.combined_bombardment = combined_bombardment;
        this.heavy_bombers = heavy_bombers;
    }

    public WeaponsDevelopment() {
        this(false, false, false, false);
    }

    public WeaponsDevelopment(WeaponsDevelopment wd) {
        this.jet_fighters = wd.jet_fighters;
        this.super_submarines = wd.super_submarines;
        this.combined_bombardment = wd.combined_bombardment;
        this.heavy_bombers = wd.heavy_bombers;
    }
}
