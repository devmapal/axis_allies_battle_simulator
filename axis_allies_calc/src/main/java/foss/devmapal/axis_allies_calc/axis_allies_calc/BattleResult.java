package foss.devmapal.axis_allies_calc.axis_allies_calc;

/**
 * Created by devmapal on 4/12/14.
 */
public class BattleResult {
    private Army attacker;
    private Army defender;
    private int sim_iters;
    private int attacker_won;
    private int defender_won;

    public BattleResult(Army attacker,
                      Army defender,
                      int sim_iters,
                      int attacker_won,
                      int defender_won) {
        this.attacker = attacker;
        this.defender = defender;
        this.sim_iters = sim_iters;
        this.attacker_won = attacker_won;
        this.defender_won = defender_won;
    }


    public BattleResult(Army attacker,
                        Army defender,
                        int sim_iters) {
        this(attacker, defender, sim_iters, 0, 0);
    }

    public Army get_attacker() {
        return attacker;
    }

    public Army get_defender() {
        return defender;
    }

    public int get_sim_iters() {
        return sim_iters;
    }

    public int get_attacker_won() {
        return attacker_won;
    }

    public int get_defender_won() {
        return defender_won;
    }

    public void set_attacker_won(int attacker_won) {
        this.attacker_won = attacker_won;
    }

    public void set_defender_won(int defender_won) {
        this.defender_won = defender_won;
    }
}
