package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.os.AsyncTask;

import java.util.List;

/**
 * Created by devmapal on 4/12/14.
 */
public abstract class BattleSimulation {
    protected Army attacker;
    protected WeaponsDevelopment attacker_wd;
    protected Army defender;
    protected WeaponsDevelopment defender_wd;
    protected int sim_iters;
    protected List<Integer> attacker_hit_order;
    protected List<Integer> defender_hit_order;
    protected AsyncTask<Void, Void, Void> task;

    public BattleSimulation(AsyncTask<Void, Void, Void> task,
                            Army attacker,
                            WeaponsDevelopment attacker_wd,
                            Army defender,
                            WeaponsDevelopment defender_wd,
                            int sim_iters) {
        this.task = task;
        this.attacker = attacker;
        this.attacker_wd = attacker_wd;
        this.defender = defender;
        this.defender_wd = defender_wd;
        this.sim_iters = sim_iters;
    }

    protected BattleResult aggregate_results(BattleResult[] results) {
        BattleResult result = new BattleResult(results.length * results[0].get_sim_iters());
        for(BattleResult x : results) {
            result.set_attacker_won(result.get_attacker_won() + x.get_attacker_won());
            result.get_attacker().add(x.get_attacker());
            result.set_defender_won(result.get_defender_won() + x.get_defender_won());
            result.get_defender().add(x.get_defender());
        }

        return result;
    }

    public BattleResult run() {
        int cpus = Runtime.getRuntime().availableProcessors();
        int sim_iters = this.sim_iters / cpus;
        if(sim_iters == 0)
            sim_iters = 1;
        BattleResult[] results = new BattleResult[cpus];
        WorkerThread[] threads = new WorkerThread[cpus];

        for(int n = 0; n < cpus; ++n) {
            results[n] = new BattleResult(sim_iters);
            threads[n] = new WorkerThread(results[n]);
            threads[n].start();
        }

        for(int n = 0; n < cpus; ++n) {
            try {
                threads[n].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return aggregate_results(results);
    }

    private class WorkerThread extends Thread {
        private BattleResult result;

        public WorkerThread(BattleResult result) {
            this.result = result;
        }

        @Override
        public void run() {
            for(int i = 0; i < result.get_sim_iters(); ++i) {
                sim_battle(result);
                if (task.isCancelled())
                    break;
            }
        }
    }

    protected int calc_hits(int num, int strength) {
        int hits = 0;
        for (int i = 0; i < num; ++i) {
            if(Die.roll() <= strength)
                ++hits;
        }

        return hits;
    }

    protected abstract void sim_battle(BattleResult result);
}
