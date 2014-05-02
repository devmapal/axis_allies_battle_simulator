package foss.devmapal.axis_allies_calc.axis_allies_calc.test;

import android.test.InstrumentationTestCase;

import foss.devmapal.axis_allies_calc.axis_allies_calc.Army;
import foss.devmapal.axis_allies_calc.axis_allies_calc.BattleResult;
import foss.devmapal.axis_allies_calc.axis_allies_calc.LandBattleSimulation;
import foss.devmapal.axis_allies_calc.axis_allies_calc.WeaponsDevelopment;

/**
 * Created by devmapal on 4/28/14.
 */
public class SimulationTest extends InstrumentationTestCase {
    static final double TOLERANCE = 0.1;

    public void test1InfVs1Inf() throws Exception {
        Army attacker = new Army();
        attacker.infantry = 1;
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.infantry = 1;
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        LandBattleSimulation battle = new LandBattleSimulation(attacker,
                attacker_wd,
                defender,
                defender_wd,
                1000000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./4;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    public void test1TankVs1Tank() throws Exception {
        Army attacker = new Army();
        attacker.tanks = 1;
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.tanks = 1;
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        LandBattleSimulation battle = new LandBattleSimulation(attacker,
                attacker_wd,
                defender,
                defender_wd,
                1000000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*1./3;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }

    // This is wrong, needs fix
    /*public void test2InfVs1Inf() throws Exception {
        Army attacker = new Army();
        attacker.infantry = 2;
        WeaponsDevelopment attacker_wd = new WeaponsDevelopment();
        Army defender = new Army();
        defender.infantry = 1;
        WeaponsDevelopment defender_wd = new WeaponsDevelopment();
        LandBattleSimulation battle = new LandBattleSimulation(attacker,
                attacker_wd,
                defender,
                defender_wd,
                1000000,
                true);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double expected_result = 100*11./29;
        assertTrue(attacker_won/expected_result > 1 - TOLERANCE);
        assertTrue(attacker_won/expected_result < 1 + TOLERANCE);
    }*/
}
