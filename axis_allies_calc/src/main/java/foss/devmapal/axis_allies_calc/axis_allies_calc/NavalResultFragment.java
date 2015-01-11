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
public class NavalResultFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private static final String ARG_ATTACKER = "attacker";
    private static final String ARG_ATTACKER_WD = "attacker_wd";
    private static final String ARG_ATTACKER_HIT_ORDER = "attacker_hit_order";
    private static final String ARG_DEFENDER = "defender";
    private static final String ARG_DEFENDER_WD = "defender_wd";
    private static final String ARG_DEFENDER_HIT_ORDER = "defender_hit_order";
    private static final String ARG_EXTRAS = "extras";

    private Army attacker;
    private WeaponsDevelopment attacker_wd;
    private ArrayList<Integer> attacker_hit_order;
    private Army defender;
    private WeaponsDevelopment defender_wd;
    private ArrayList<Integer> defender_hit_order;

    private HashMap<String, Double> attacker_results;
    private HashMap<String, Double> defender_results;
    private ComputeBattleTask task;

    public static NavalResultFragment newInstance(Bundle extras) {
        NavalResultFragment fragment = new NavalResultFragment();
        Bundle args = new Bundle();
        args.putBundle(ARG_EXTRAS, extras);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NavalResultFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        attacker_results = new HashMap<>();
        defender_results = new HashMap<>();

        if (getArguments() != null) {
            Bundle extras = getArguments().getBundle(ARG_EXTRAS);
            attacker = (Army) extras.getSerializable(ARG_ATTACKER);
            attacker_wd = (WeaponsDevelopment) extras.getSerializable(ARG_ATTACKER_WD);
            attacker_hit_order = extras.getIntegerArrayList(ARG_ATTACKER_HIT_ORDER);
            defender = (Army) extras.getSerializable(ARG_DEFENDER);
            defender_wd = (WeaponsDevelopment) extras.getSerializable(ARG_DEFENDER_WD);
            defender_hit_order = extras.getIntegerArrayList(ARG_DEFENDER_HIT_ORDER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.naval_result, container, false);

        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onCreateView");
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(task == null) {
            task = new ComputeBattleTask();
            task.execute();
        }
        else if(task.finished) {
            mListener.onFragmentInteraction("");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(task != null) {
            task.cancel(true);
        }
    }

    public void sim_battle() {
        if (BuildConfig.DEBUG) {
            Log.d(Constants.LOG, "Calculating battle");
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

    private class ComputeBattleTask extends AsyncTask<Void, Void, Void> {
        public boolean finished = false;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {
            sim_battle();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(mListener != null)
                mListener.onFragmentInteraction("");
            finished = true;
        }
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }

    public interface Constants {
        String LOG = "foss.devmapal.axis_allies_calc.axis_allies_calc";
    }
}
