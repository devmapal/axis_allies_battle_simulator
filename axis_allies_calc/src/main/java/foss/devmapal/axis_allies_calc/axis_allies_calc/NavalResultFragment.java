package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.Assert.assertNotNull;


/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * interface.
 */
public class NavalResultFragment extends ResultFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.naval_result, container, false);

        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onCreateView");
        }

        return view;
    }

    public static ResultFragment newInstance(Bundle extras) {
        NavalResultFragment fragment = new NavalResultFragment();
        Bundle args = new Bundle();
        args.putBundle(ARG_EXTRAS, extras);
        fragment.setArguments(args);
        return fragment;
    }

    public void sim_battle() {
        if (BuildConfig.DEBUG) {
            Log.d(Constants.LOG, "Calculating naval battle");
        }
        Activity activity = getActivity();
        if(activity == null)
            return;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);
        int sim_iters = Integer.parseInt(sp.getString(getString(R.string.key_pref_sim_iters), "100000"));
        NavalBattleSimulation battle = new NavalBattleSimulation(
                task,
                attacker,
                attacker_wd,
                attacker_hit_order,
                defender,
                defender_wd,
                defender_hit_order,
                sim_iters);

        if(task.isCancelled())
            return;

        BattleResult result = battle.run();
        attacker_results.put("won", ((double) result.get_attacker_won()) / result.get_sim_iters() * 100);
        defender_results.put("won", ((double) result.get_defender_won()) / result.get_sim_iters() * 100);

        attacker_results.put("battleship_losses", ((double) result.get_attacker().units[Battleship.id])/result.get_sim_iters());
        attacker_results.put("destroyer_losses", ((double) result.get_attacker().units[Destroyer.id])/result.get_sim_iters());
        attacker_results.put("aircraftcarrier_losses", ((double) result.get_attacker().units[Aircraftcarrier.id])/result.get_sim_iters());
        attacker_results.put("transport_losses", ((double) result.get_attacker().units[Transport.id])/result.get_sim_iters());
        attacker_results.put("submarine_losses", ((double) result.get_attacker().units[Submarine.id])/result.get_sim_iters());
        attacker_results.put("fighter_losses", ((double) result.get_attacker().units[Fighter.id])/result.get_sim_iters());
        attacker_results.put("bomber_losses", ((double) result.get_attacker().units[Bomber.id])/result.get_sim_iters());

        defender_results.put("battleship_losses", ((double) result.get_defender().units[Battleship.id])/result.get_sim_iters());
        defender_results.put("destroyer_losses", ((double) result.get_defender().units[Destroyer.id])/result.get_sim_iters());
        defender_results.put("aircraftcarrier_losses", ((double) result.get_defender().units[Aircraftcarrier.id])/result.get_sim_iters());
        defender_results.put("transport_losses", ((double) result.get_defender().units[Transport.id])/result.get_sim_iters());
        defender_results.put("submarine_losses", ((double) result.get_defender().units[Submarine.id])/result.get_sim_iters());
        defender_results.put("fighter_losses", ((double) result.get_defender().units[Fighter.id])/result.get_sim_iters());
        defender_results.put("bomber_losses", ((double) result.get_defender().units[Bomber.id])/result.get_sim_iters());
    }

    public void setFields() {
        TextView tv = (TextView) getView().findViewById(R.id.a_won);
        tv.setText(Double.toString(attacker_results.get("won")));
        tv = (TextView) getView().findViewById(R.id.a_battleships);
        tv.setText(Double.toString(attacker_results.get("battleship_losses")));
        tv = (TextView) getView().findViewById(R.id.a_destroyers);
        tv.setText(Double.toString(attacker_results.get("destroyer_losses")));
        tv = (TextView) getView().findViewById(R.id.a_aircraft_carriers);
        tv.setText(Double.toString(attacker_results.get("aircraftcarrier_losses")));
        tv = (TextView) getView().findViewById(R.id.a_transports);
        tv.setText(Double.toString(attacker_results.get("transport_losses")));
        tv = (TextView) getView().findViewById(R.id.a_submarines);
        tv.setText(Double.toString(attacker_results.get("submarine_losses")));
        tv = (TextView) getView().findViewById(R.id.a_fighters);
        tv.setText(Double.toString(attacker_results.get("fighter_losses")));
        tv = (TextView) getView().findViewById(R.id.a_bombers);
        tv.setText(Double.toString(attacker_results.get("bomber_losses")));

        tv = (TextView) getView().findViewById(R.id.d_won);
        tv.setText(Double.toString(defender_results.get("won")));
        tv = (TextView) getView().findViewById(R.id.d_battleships);
        tv.setText(Double.toString(defender_results.get("battleship_losses")));
        tv = (TextView) getView().findViewById(R.id.d_destroyers);
        tv.setText(Double.toString(defender_results.get("destroyer_losses")));
        tv = (TextView) getView().findViewById(R.id.d_aircraft_carriers);
        tv.setText(Double.toString(defender_results.get("aircraftcarrier_losses")));
        tv = (TextView) getView().findViewById(R.id.d_transports);
        tv.setText(Double.toString(defender_results.get("transport_losses")));
        tv = (TextView) getView().findViewById(R.id.d_submarines);
        tv.setText(Double.toString(defender_results.get("submarine_losses")));
        tv = (TextView) getView().findViewById(R.id.d_fighters);
        tv.setText(Double.toString(defender_results.get("fighter_losses")));
        tv = (TextView) getView().findViewById(R.id.d_bombers);
        tv.setText(Double.toString(defender_results.get("bomber_losses")));
    }
}
