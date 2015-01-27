package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;


/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * interface.
 */
public class LandResultFragment extends ResultFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.land_result, container, false);

        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onCreateView");
        }

        return view;
    }

    public static ResultFragment newInstance(Bundle extras) {
        LandResultFragment fragment = new LandResultFragment();
        Bundle args = new Bundle();
        args.putBundle(ARG_EXTRAS, extras);
        fragment.setArguments(args);
        return fragment;
    }

    public void sim_battle() {
        if (BuildConfig.DEBUG) {
            Log.d(Constants.LOG, "Calculating land battle");
        }
        Activity activity = getActivity();
        if (activity == null)
            return;

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);
        boolean take_territory = sp.getBoolean(getString(R.string.key_pref_take_territory), true);
        int sim_iters = Integer.parseInt(sp.getString(getString(R.string.key_pref_sim_iters), "100000"));
        LandBattleSimulation battle = new LandBattleSimulation(
                task,
                attacker,
                attacker_wd,
                attacker_hit_order,
                defender,
                defender_wd,
                defender_hit_order,
                sim_iters,
                take_territory);

        if (task.isCancelled())
            return;

        BattleResult result = battle.run();
        attacker_results.put("won", ((double) result.get_attacker_won()) / result.get_sim_iters() * 100);
        defender_results.put("won", ((double) result.get_defender_won()) / result.get_sim_iters() * 100);

        attacker_results.put("infantry_losses", ((double) result.get_attacker().units[Infantry.id]) / result.get_sim_iters());
        attacker_results.put("artillery_losses", ((double) result.get_attacker().units[Artillery.id]) / result.get_sim_iters());
        attacker_results.put("tank_losses", ((double) result.get_attacker().units[Tank.id]) / result.get_sim_iters());
        attacker_results.put("fighter_losses", ((double) result.get_attacker().units[Fighter.id]) / result.get_sim_iters());
        attacker_results.put("bomber_losses", ((double) result.get_attacker().units[Bomber.id]) / result.get_sim_iters());

        defender_results.put("infantry_losses", ((double) result.get_defender().units[Infantry.id]) / result.get_sim_iters());
        defender_results.put("artillery_losses", ((double) result.get_defender().units[Artillery.id]) / result.get_sim_iters());
        defender_results.put("tank_losses", ((double) result.get_defender().units[Tank.id]) / result.get_sim_iters());
        defender_results.put("fighter_losses", ((double) result.get_defender().units[Fighter.id]) / result.get_sim_iters());
        defender_results.put("bomber_losses", ((double) result.get_defender().units[Bomber.id]) / result.get_sim_iters());
    }

    public void setFields() {
        TextView tv = (TextView) getView().findViewById(R.id.a_won);
        tv.setText(Double.toString(attacker_results.get("won")));
        tv = (TextView) getView().findViewById(R.id.a_infantry);
        tv.setText(Double.toString(attacker_results.get("infantry_losses")));
        tv = (TextView) getView().findViewById(R.id.a_artillery);
        tv.setText(Double.toString(attacker_results.get("artillery_losses")));
        tv = (TextView) getView().findViewById(R.id.a_tanks);
        tv.setText(Double.toString(attacker_results.get("tank_losses")));
        tv = (TextView) getView().findViewById(R.id.a_fighters);
        tv.setText(Double.toString(attacker_results.get("fighter_losses")));
        tv = (TextView) getView().findViewById(R.id.a_bombers);
        tv.setText(Double.toString(attacker_results.get("bomber_losses")));

        tv = (TextView) getView().findViewById(R.id.d_won);
        tv.setText(Double.toString(defender_results.get("won")));
        tv = (TextView) getView().findViewById(R.id.d_infantry);
        tv.setText(Double.toString(defender_results.get("infantry_losses")));
        tv = (TextView) getView().findViewById(R.id.d_artillery);
        tv.setText(Double.toString(defender_results.get("artillery_losses")));
        tv = (TextView) getView().findViewById(R.id.d_tanks);
        tv.setText(Double.toString(defender_results.get("tank_losses")));
        tv = (TextView) getView().findViewById(R.id.d_fighters);
        tv.setText(Double.toString(defender_results.get("fighter_losses")));
        tv = (TextView) getView().findViewById(R.id.d_bombers);
        tv.setText(Double.toString(defender_results.get("bomber_losses")));
    }
}
