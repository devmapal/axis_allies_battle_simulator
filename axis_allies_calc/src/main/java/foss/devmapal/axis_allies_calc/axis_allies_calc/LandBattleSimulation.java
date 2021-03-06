package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by devmapal on 4/12/14.
 */
public class LandBattleSimulation extends BattleSimulation {
    private boolean take_territory;

    public LandBattleSimulation(AsyncTask<Void, Void, Void> task,
                                Army attacker,
                                WeaponsDevelopment attacker_wd,
                                ArrayList<Integer> attacker_hit_order,
                                Army defender,
                                WeaponsDevelopment defender_wd,
                                ArrayList<Integer> defender_hit_order,
                                int sim_iters,
                                boolean take_territory) {
        super(task, attacker, attacker_wd, defender, defender_wd, sim_iters);

        this.take_territory = take_territory;
        
        this.attacker_hit_order = attacker_hit_order;
        this.defender_hit_order = defender_hit_order;

        if(attacker.land_battle_units() == 0 || defender.land_battle_units() == 0)
            this.sim_iters = 1;
    }

    private int calc_attacker_hits(Army attacker) {
        int infantry = Math.max(attacker.get_infantry() - attacker.get_artillery(), 0);
        int artillery = attacker.get_artillery() +
                        Math.min(attacker.get_artillery(), attacker.get_infantry());
        int hits = calc_hits(infantry, Infantry.attack);
        hits += calc_hits(artillery, Artillery.attack);
        hits += calc_hits(attacker.get_tanks(), Tank.attack);
        hits += calc_hits(attacker.get_fighters(), Fighter.attack);
        hits += calc_hits(attacker.get_bombers(), Bomber.attack);
        if(attacker_wd.heavy_bombers)
            hits += calc_hits(attacker.get_bombers(), Bomber.attack);

        return hits;
    }

    private int calc_defender_hits(Army defender) {
        int hits = calc_hits(defender.get_infantry(), Infantry.defense);
        hits += calc_hits(defender.get_artillery(), Artillery.defense);
        hits += calc_hits(defender.get_tanks(), Tank.defense);
        if(defender_wd.jet_fighters)
            hits += calc_hits(defender.get_fighters(), Fighter.defense + 1);
        else
            hits += calc_hits(defender.get_fighters(), Fighter.defense);
        hits += calc_hits(defender.get_bombers(), Bomber.defense);

        return hits;
    }

    private int calc_attacker_opening_fire(Army attacker, Army defender) {
        int hits = calc_hits(attacker.get_battleships(), Battleship.attack);
        if(attacker_wd.combined_bombardment)
            hits += calc_hits(attacker.get_destroyers(), Destroyer.attack);

        return hits;
    }

    private int calc_defender_opening_fire(Army attacker, Army defender) {
        int hits = 0;
        if(defender.get_antiaircraftguns() > 0) {
            hits += calc_hits(attacker.get_fighters() + attacker.get_bombers(),
                              AntiaircraftGun.defense);
        }

        return hits;
    }

    private void apply_hits_on_attacker(Army attacker, int defender_hits) {
        for(int unit_id : attacker_hit_order) {
            if(defender_hits == 0)
                return;
            int attacker_units = attacker.get(unit_id);
            if(attacker_units == 0)
                continue;
            if(attacker_units > defender_hits) {
                attacker_units -= defender_hits;
                attacker.set(unit_id, attacker_units);
                defender_hits = 0;
                break;
            } else {
                defender_hits -= attacker_units;
                int count = attacker.get(unit_id);
                attacker.set(unit_id, 0);
                if(attacker.get_infantry() + attacker.get_artillery() + attacker.get_tanks() == 0 &&
                   take_territory && count == 1) {
                    defender_hits++;
                    attacker.set(unit_id, 1);
                }
            }
        }

        if(defender_hits > 0) {
            int sum = attacker.get_infantry() + attacker.get_artillery() + attacker.get_tanks();
            if(sum > 0) {
                attacker.set_infantry(0);
                attacker.set_artillery(0);
                attacker.set_tanks(0);
            }
        }
    }

    private void apply_antiaircraft_hits(Army attacker, int defender_hits) {
        for(int unit_id : attacker_hit_order) {
            if(defender_hits == 0)
                return;
            if(unit_id == Fighter.id || unit_id == Bomber.id) {
                int attacker_units = attacker.get(unit_id);
                if (attacker_units > defender_hits) {
                    attacker_units -= defender_hits;
                    attacker.set(unit_id, attacker_units);
                    return;
                } else {
                    defender_hits -= attacker_units;
                    attacker.set(unit_id, 0);
                }
            }
        }
    }

    private void apply_hits_on_defender(Army defender, int attacker_hits) {
        for (int unit_id : defender_hit_order) {
            int defender_units = defender.get(unit_id);
            if (defender_units > attacker_hits) {
                defender_units -= attacker_hits;
                defender.set(unit_id, defender_units);
                return;
            } else {
                attacker_hits -= defender_units;
                defender.set(unit_id, 0);
            }
        }
    }

    @Override
    protected void sim_battle(BattleResult result) {
        Army attacker = new Army(this.attacker);
        Army defender = new Army(this.defender);

        while(attacker.land_battle_units() > 0 &&
                defender.land_battle_units() > 0) {
            int attacker_hits = calc_attacker_opening_fire(attacker, defender);
            int defender_hits = calc_defender_opening_fire(attacker, defender);
            apply_antiaircraft_hits(attacker, defender_hits);
            apply_hits_on_defender(defender, attacker_hits);

            attacker_hits = calc_attacker_hits(attacker);
            defender_hits = calc_defender_hits(defender);
            apply_hits_on_attacker(attacker, defender_hits);
            apply_hits_on_defender(defender, attacker_hits);
        }
        
        Army result_attacker = result.get_attacker();
        result_attacker.set_infantry(result_attacker.get_infantry() + this.attacker.get_infantry() - attacker.get_infantry());
        result_attacker.set_artillery(result_attacker.get_artillery() + this.attacker.get_artillery() - attacker.get_artillery());
        result_attacker.set_tanks(result_attacker.get_tanks() + this.attacker.get_tanks() - attacker.get_tanks());
        result_attacker.set_fighters(result_attacker.get_fighters() + this.attacker.get_fighters() - attacker.get_fighters());
        result_attacker.set_bombers(result_attacker.get_bombers() + this.attacker.get_bombers() - attacker.get_bombers());

        Army result_defender = result.get_defender();
        result_defender.set_infantry(result_defender.get_infantry() + this.defender.get_infantry() - defender.get_infantry());
        result_defender.set_artillery(result_defender.get_artillery() + this.defender.get_artillery() - defender.get_artillery());
        result_defender.set_tanks(result_defender.get_tanks() + this.defender.get_tanks() - defender.get_tanks());
        result_defender.set_fighters(result_defender.get_fighters() + this.defender.get_fighters() - defender.get_fighters());
        result_defender.set_bombers(result_defender.get_bombers() + this.defender.get_bombers() - defender.get_bombers());

        if(defender.land_battle_units() == 0 &&
           attacker.get_infantry() + attacker.get_artillery() + attacker.get_tanks() > 0)
            result.set_attacker_won(result.get_attacker_won()+1);
        else
            result.set_defender_won(result.get_defender_won()+1);
    }

    public interface Constants {
        String LOG = "foss.devmapal.axis_allies_calc.axis_allies_calc.LandBattleSimulation";
    }
}
