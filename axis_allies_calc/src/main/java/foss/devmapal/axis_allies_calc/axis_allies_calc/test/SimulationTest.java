package foss.devmapal.axis_allies_calc.axis_allies_calc.test;

import android.os.AsyncTask;
import android.test.InstrumentationTestCase;

import foss.devmapal.axis_allies_calc.axis_allies_calc.Army;
import foss.devmapal.axis_allies_calc.axis_allies_calc.BattleResult;
import foss.devmapal.axis_allies_calc.axis_allies_calc.LandBattleSimulation;
import foss.devmapal.axis_allies_calc.axis_allies_calc.NavalBattleSimulation;
import foss.devmapal.axis_allies_calc.axis_allies_calc.WeaponsDevelopment;

/**
 * Created by devmapal on 4/28/14.
 */
public class SimulationTest extends InstrumentationTestCase {
/*    static final double TOLERANCE = 0.05;

    public void test1InfVs1Inf() throws Exception {
        Army attacker = new Army();
        attacker.set_infantry(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_infantry(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./4;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1InfVs1Artillery() throws Exception {
        Army attacker = new Army();
        attacker.set_infantry(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_artillery(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./4;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1InfVs1Tank() throws Exception {
        Army attacker = new Army();
        attacker.set_infantry(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_tanks(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./7;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1InfVs1Fighter() throws Exception {
        Army attacker = new Army();
        attacker.set_infantry(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_fighters(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./13;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1InfVs1JetFighter() throws Exception {
        Army attacker = new Army();
        attacker.set_infantry(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_fighters(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        defender_wd.jet_fighters = true;
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./31;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1InfVs1Bomber() throws Exception {
        Army attacker = new Army();
        attacker.set_infantry(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_bombers(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*5./11;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1ArtilleryVs1Inf() throws Exception {
        Army attacker = new Army();
        attacker.set_artillery(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_infantry(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*.4;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1ArtilleryVs1Artillery() throws Exception {
        Army attacker = new Army();
        attacker.set_artillery(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_artillery(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*.4;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1ArtilleryVs1Tank() throws Exception {
        Army attacker = new Army();
        attacker.set_artillery(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_tanks(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./4;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1ArtilleryVs1Fighter() throws Exception {
        Army attacker = new Army();
        attacker.set_artillery(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_fighters(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./7;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1ArtilleryVs1JetFighter() throws Exception {
        Army attacker = new Army();
        attacker.set_artillery(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_fighters(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        defender_wd.jet_fighters = true;
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./16;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1ArtilleryVs1Bomber() throws Exception {
        Army attacker = new Army();
        attacker.set_artillery(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_bombers(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*5./8;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1TankVs1Inf() throws Exception {
        Army attacker = new Army();
        attacker.set_tanks(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_infantry(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./2;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1TankVs1Artillery() throws Exception {
        Army attacker = new Army();
        attacker.set_tanks(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_artillery(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./2;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1TankVs1Tank() throws Exception {
        Army attacker = new Army();
        attacker.set_tanks(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_tanks(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./3;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1TankVs1Fighter() throws Exception {
        Army attacker = new Army();
        attacker.set_tanks(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_fighters(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./5;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1TankVs1JetFighter() throws Exception {
        Army attacker = new Army();
        attacker.set_tanks(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_fighters(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        defender_wd.jet_fighters = true;
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./11;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1TankVs1Bomber() throws Exception {
        Army attacker = new Army();
        attacker.set_tanks(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_bombers(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*5./7;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void testNoUnits() throws Exception {
        Army attacker = new Army();
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 0;
        assertEquals(attacker_won, expected_result);
    }

    public void testNoDefender() throws Exception {
        Army attacker = new Army();
        attacker.set_tanks(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100;
        assertEquals(attacker_won, expected_result);
    }

    public void testArtilleryInfSupport() throws Exception {
        Army attacker = new Army();
        attacker.set_infantry(1);
        attacker.set_artillery(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_artillery(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;

        attacker.set_infantry(0);
        attacker.set_artillery(2);
        battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000,
                true);

        result = battle.run();
        double attacker_won2 = ((double) result.get_attacker_won())/result.get_sim_iters()*100;

        assertTrue(Math.abs(attacker_won - attacker_won2) < TOLERANCE*100);
    }


    public void test1TransportVs1Transport() throws Exception {
        Army attacker = new Army();
        attacker.set_transports(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_transports(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        NavalBattleSimulation battle = new NavalBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 0.;
        assertTrue(attacker_won == expected_result);
    }

    public void test1TransportVs1Submarine() throws Exception {
        Army attacker = new Army();
        attacker.set_transports(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_submarines(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        NavalBattleSimulation battle = new NavalBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 0.;
        assertTrue(attacker_won == expected_result);
    }

    public void test1TransportVs1Aircraftcarrier() throws Exception {
        Army attacker = new Army();
        attacker.set_transports(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_aircraftcarriers(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        NavalBattleSimulation battle = new NavalBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 0.;
        assertTrue(attacker_won == expected_result);
    }

    public void test1TransportVs1Destroyer() throws Exception {
        Army attacker = new Army();
        attacker.set_transports(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_destroyers(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        NavalBattleSimulation battle = new NavalBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 0.;
        assertTrue(attacker_won == expected_result);
    }

    public void test1DestroyerVs1Destroyer() throws Exception {
        Army attacker = new Army();
        attacker.set_destroyers(1);
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.set_destroyers(1);
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        };
        NavalBattleSimulation battle = new NavalBattleSimulation(
                task,
                attacker,
                attacker_wd,
                defender,
                defender_wd,
                100000);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./3;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }*/
}
