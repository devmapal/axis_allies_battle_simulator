package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by devmapal on 4/12/14.
 */
public class LandBattleSimulation extends Battle {
    private boolean take_territory;
    private List<String> attacker_hit_order;
    private List<String> defender_hit_order;

    public LandBattleSimulation(Army attacker,
                                WeaponsDevelopment attacker_wd,
                                Army defender,
                                WeaponsDevelopment defender_wd,
                                int sim_iters,
                                boolean take_territory) {
        super(attacker, attacker_wd, defender, defender_wd, sim_iters);
        this.take_territory = take_territory;
        
        attacker_hit_order = new ArrayList<String>(5);
        attacker_hit_order.add(Infantry.name);
        attacker_hit_order.add(Artillery.name);
        attacker_hit_order.add(Tank.name);
        attacker_hit_order.add(Fighter.name);
        attacker_hit_order.add(Bomber.name);

        defender_hit_order = new ArrayList<String>(5);
        defender_hit_order.add(Bomber.name);
        defender_hit_order.add(Infantry.name);
        defender_hit_order.add(Artillery.name);
        defender_hit_order.add(Tank.name);
        defender_hit_order.add(Fighter.name);
    }

    public BattleResult run() {
        BattleResult result = new BattleResult(this.sim_iters);

        // Simulate sim_iters battles
        for(int i = 0; i < sim_iters; ++i) {
            sim_battle(result);
        }

        return result;
    }

    private int calc_hits(int num, int strength) {
        int hits = 0;
        for (int i = 0; i < num; ++i) {
            if(Die.roll() <= strength)
                ++hits;
        }

        return hits;
    }

    private int calc_attacker_hits(Army attacker) {
        int hits = calc_hits(attacker.infantry, Infantry.attack);
        hits += calc_hits(attacker.artillery, Artillery.attack);
        hits += calc_hits(attacker.tanks, Tank.attack);
        hits += calc_hits(attacker.fighters, Fighter.attack);
        hits += calc_hits(attacker.bombers, Bomber.attack);
        if(attacker_wd.heavy_bombers)
            hits += calc_hits(attacker.bombers, Bomber.attack);

        return hits;
    }

    private int calc_defender_hits(Army defender) {
        int hits = calc_hits(defender.infantry, Infantry.defense);
        hits += calc_hits(defender.artillery, Artillery.defense);
        hits += calc_hits(defender.tanks, Tank.defense);
        hits += calc_hits(defender.fighters, Fighter.defense);
        hits += calc_hits(defender.bombers, Bomber.defense);

        return hits;
    }

    private void apply_hits_on_attacker(Army attacker, int defender_hits) {
        for(String unit_name : attacker_hit_order) {
            if(defender_hits == 0)
                return;
            Integer attacker_units = attacker.get(unit_name);
            assertNotNull(attacker_units);
            if(attacker_units == 0)
                continue;
            if(attacker_units > defender_hits) {
                attacker_units -= defender_hits;
                attacker.set(unit_name, attacker_units);
                defender_hits = 0;
                break;
            } else {
                defender_hits -= attacker_units;
                int count = attacker.get(unit_name);
                attacker.set(unit_name, 0);
                if(attacker.infantry + attacker.artillery + attacker.tanks == 0 &&
                   take_territory && count == 1) {
                    defender_hits++;
                    attacker.set(unit_name, 1);
                }
            }
        }

        if(defender_hits > 0) {
            int sum = attacker.infantry + attacker.artillery + attacker.tanks;
            if(sum > 0) {
                assertEquals(sum, 1);
                attacker.infantry = 0;
                attacker.artillery = 0;
                attacker.tanks = 0;
            }
        }
    }

    private void apply_hits_on_defender(Army defender, int attacker_hits) {
        for (String unit_name : defender_hit_order) {
            Integer defender_units = defender.get(unit_name);
            assertNotNull(defender_units);
            if (defender_units > attacker_hits) {
                defender_units -= attacker_hits;
                defender.set(unit_name, defender_units);
                return;
            } else {
                attacker_hits -= defender_units;
                defender.set(unit_name, 0);
            }
        }
    }

    private void sim_battle(BattleResult result) {
        Army attacker = new Army(this.attacker);
        Army defender = new Army(this.defender);

        while(attacker.land_battle_units() > 0 &&
                defender.land_battle_units() > 0) {
            int attacker_hits = calc_attacker_hits(attacker);
            int defender_hits = calc_defender_hits(defender);
            apply_hits_on_attacker(attacker, defender_hits);
            apply_hits_on_defender(defender, attacker_hits);
        }
        
        Army result_attacker = result.get_attacker();
        result_attacker.infantry += this.attacker.infantry - attacker.infantry;
        result_attacker.artillery += this.attacker.artillery - attacker.artillery;
        result_attacker.tanks += this.attacker.tanks - attacker.tanks;
        result_attacker.fighters += this.attacker.fighters - attacker.fighters;
        result_attacker.bombers += this.attacker.bombers - attacker.bombers;

        Army result_defender = result.get_defender();
        result_defender.infantry += this.defender.infantry - defender.infantry;
        result_defender.artillery += this.defender.artillery - defender.artillery;
        result_defender.tanks += this.defender.tanks - defender.tanks;
        result_defender.fighters += this.defender.fighters - defender.fighters;
        result_defender.bombers += this.defender.bombers - defender.bombers;

        if(defender.land_battle_units() == 0 &&
           attacker.infantry + attacker.artillery + attacker.tanks > 0)
            result.set_attacker_won(result.get_attacker_won()+1);
        else
            result.set_defender_won(result.get_defender_won()+1);
    }
}
