package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by devmapal on 4/12/14.
 */
public class NavalBattleSimulation extends Battle {
    private List<Integer> attacker_hit_order;
    private List<Integer> defender_hit_order;
    private AsyncTask<Void, Void, Void> task;

    public NavalBattleSimulation(AsyncTask<Void, Void, Void> task,
                                 Army attacker,
                                 WeaponsDevelopment attacker_wd,
                                 Army defender,
                                 WeaponsDevelopment defender_wd,
                                 int sim_iters) {
        super(attacker, attacker_wd, defender, defender_wd, sim_iters);
        this.task = task;

        attacker_hit_order = new ArrayList<Integer>(7);
        attacker_hit_order.add(Transport.id);
        attacker_hit_order.add(Submarine.id);
        attacker_hit_order.add(Fighter.id);
        attacker_hit_order.add(Destroyer.id);
        attacker_hit_order.add(Bomber.id);
        attacker_hit_order.add(Aircraftcarrier.id);
        attacker_hit_order.add(Battleship.id);

        defender_hit_order = new ArrayList<Integer>(7);
        defender_hit_order.add(Transport.id);
        defender_hit_order.add(Submarine.id);
        defender_hit_order.add(Fighter.id);
        defender_hit_order.add(Destroyer.id);
        defender_hit_order.add(Bomber.id);
        defender_hit_order.add(Aircraftcarrier.id);
        defender_hit_order.add(Battleship.id);
    }

    public BattleResult run() {
        BattleResult result = new BattleResult(this.sim_iters);

        // Simulate sim_iters battles
        for(int i = 0; i < sim_iters; ++i) {
            sim_battle(result);
            if(task.isCancelled())
                break;
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
        int hits = calc_hits(attacker.get_transports(), Transport.attack);
        if(attacker_wd.super_submarines)
            hits += calc_hits(attacker.get_submarines(), Submarine.super_submarine_attack);
        else
            hits += calc_hits(attacker.get_submarines(), Submarine.attack);
        hits += calc_hits(attacker.get_fighters(), Fighter.attack);
        hits += calc_hits(attacker.get_destroyers(), Destroyer.attack);
        hits += calc_hits(attacker.get_bombers(), Bomber.attack);
        if(attacker_wd.heavy_bombers)
            hits += calc_hits(attacker.get_bombers(), Bomber.attack);
        hits += calc_hits(attacker.get_aircraftcarriers(), Aircraftcarrier.attack);
        hits += calc_hits(attacker.get_battleships(), Battleship.attack);

        return hits;
    }

    private int calc_defender_hits(Army defender) {
        int hits = calc_hits(defender.get_transports(), Transport.defense);
        hits += calc_hits(defender.get_submarines(), Submarine.defense);
        if(defender_wd.jet_fighters)
            hits += calc_hits(defender.get_fighters(), Fighter.jet_fighter_defense);
        else
            hits += calc_hits(defender.get_fighters(), Fighter.defense);
        hits += calc_hits(defender.get_destroyers(), Destroyer.defense);
        hits += calc_hits(defender.get_bombers(), Bomber.defense);
        hits += calc_hits(defender.get_aircraftcarriers(), Aircraftcarrier.defense);
        hits += calc_hits(defender.get_battleships(), Battleship.defense);

        return hits;
    }

    private int calc_attacker_opening_fire(Army attacker, Army defender) {
        int hits = 0;
        if(defender.get_destroyers() == 0) {
            if(attacker_wd.super_submarines)
                hits += calc_hits(attacker.get_submarines(), Submarine.super_submarine_attack);
            else
                hits += calc_hits(attacker.get_submarines(), Submarine.attack);
        }

        return hits;
    }

    private int calc_defender_opening_fire(Army defender, Army attacker) {
        int hits = 0;
        if(attacker.get_destroyers() == 0)
            hits += calc_hits(defender.get_submarines(), Submarine.defense);

        return hits;
    }

    private void apply_hits(List<Integer> hit_order, Army army, int hits, boolean sea_only) {
        for (int unit_id : hit_order) {
            if((unit_id == Fighter.id || unit_id == Bomber.id) && sea_only)
                continue;
            Integer units = army.get(unit_id);
            assertNotNull(units);
            if (units > hits) {
                units -= hits;
                army.set(unit_id, units);
                return;
            } else {
                hits -= units;
                army.set(unit_id, 0);
            }
        }
    }

    private void sim_battle(BattleResult result) {
        Army attacker = new Army(this.attacker);
        Army defender = new Army(this.defender);
        int attacker_battleships = attacker.get_battleships();
        int defender_battleships = defender.get_battleships();

        while(attacker.naval_battle_units() > 0 && defender.naval_battle_units() > 0) {
            int attacker_hits_subs = calc_attacker_opening_fire(attacker, defender);
            int defender_hits_subs = calc_defender_opening_fire(defender, attacker);
            if(attacker.get_destroyers() == 0) {
                if (attacker_battleships >= defender_hits_subs)
                    attacker_battleships -= defender_hits_subs;
                else {
                    defender_hits_subs -= attacker_battleships;
                    attacker_battleships = 0;
                    apply_hits(attacker_hit_order, attacker, defender_hits_subs, true);
                }
                defender_hits_subs = 0;
            }
            if(defender.get_destroyers() == 0) {
                if (defender_battleships >= attacker_hits_subs)
                    defender_battleships -= attacker_hits_subs;
                else {
                    attacker_hits_subs -= defender_battleships;
                    defender_battleships = 0;
                    apply_hits(defender_hit_order, defender, attacker_hits_subs, true);
                }
                attacker_hits_subs = 0;
            }

            int attacker_hits = calc_attacker_hits(attacker);
            int defender_hits = calc_defender_hits(defender);
            if (attacker_battleships >= defender_hits_subs)
                attacker_battleships -= defender_hits_subs;
            else {
                defender_hits_subs -= attacker_battleships;
                attacker_battleships = 0;
                apply_hits(attacker_hit_order, attacker, defender_hits_subs, true);
            }
            if (defender_battleships >= attacker_hits_subs)
                defender_battleships -= attacker_hits_subs;
            else {
                attacker_hits_subs -= defender_battleships;
                defender_battleships = 0;
                apply_hits(defender_hit_order, defender, attacker_hits_subs, true);
            }
            apply_hits(attacker_hit_order, attacker, defender_hits, false);
            apply_hits(defender_hit_order, defender, attacker_hits, false);
        }

        Army result_attacker = result.get_attacker();
        result_attacker.set_transports(result_attacker.get_transports() + this.attacker.get_transports() - attacker.get_transports());
        result_attacker.set_submarines(result_attacker.get_submarines() + this.attacker.get_submarines() - attacker.get_submarines());
        result_attacker.set_fighters(result_attacker.get_fighters() + this.attacker.get_fighters() - attacker.get_fighters());
        result_attacker.set_destroyers(result_attacker.get_destroyers() + this.attacker.get_destroyers() - attacker.get_destroyers());
        result_attacker.set_bombers(result_attacker.get_bombers() + this.attacker.get_bombers() - attacker.get_bombers());
        result_attacker.set_aircraftcarriers(result_attacker.get_aircraftcarriers() + this.attacker.get_aircraftcarriers() - attacker.get_aircraftcarriers());
        result_attacker.set_battleships(result_attacker.get_battleships() + this.attacker.get_battleships() - attacker.get_battleships());

        Army result_defender = result.get_defender();
        result_defender.set_transports(result_defender.get_transports() + this.defender.get_transports() - defender.get_transports());
        result_defender.set_submarines(result_defender.get_submarines() + this.defender.get_submarines() - defender.get_submarines());
        result_defender.set_fighters(result_defender.get_fighters() + this.defender.get_fighters() - defender.get_fighters());
        result_defender.set_destroyers(result_defender.get_destroyers() + this.defender.get_destroyers() - defender.get_destroyers());
        result_defender.set_bombers(result_defender.get_bombers() + this.defender.get_bombers() - defender.get_bombers());
        result_defender.set_aircraftcarriers(result_defender.get_aircraftcarriers() + this.defender.get_aircraftcarriers() - defender.get_aircraftcarriers());
        result_defender.set_battleships(result_defender.get_battleships() + this.defender.get_battleships() - defender.get_battleships());

        if(defender.naval_battle_units() == 0 && attacker.naval_battle_units() > 0)
            result.set_attacker_won(result.get_attacker_won()+1);
        else if(attacker.naval_battle_units() == 0 && defender.naval_battle_units() > 0)
            result.set_defender_won(result.get_defender_won()+1);
    }
}
