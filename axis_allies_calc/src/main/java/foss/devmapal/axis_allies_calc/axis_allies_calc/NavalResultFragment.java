package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;

import static junit.framework.Assert.assertNotNull;


/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * interface.
 */
public class NavalResultFragment extends ListFragment {

    private OnFragmentInteractionListener mListener;

    private static final String ARG_ATTACKER = "attacker";
    private static final String ARG_ATTACKER_WD = "attacker_wd";
    private static final String ARG_DEFENDER = "defender";
    private static final String ARG_DEFENDER_WD = "defender_wd";
    private static final String ARG_EXTRAS = "extras";

    private Army attacker;
    private WeaponsDevelopment attacker_wd;
    private Army defender;
    private WeaponsDevelopment defender_wd;

    private Tuple<String, String>[] result_items;

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
        result_items = new Tuple[16];

        if (getArguments() != null) {
            Bundle extras = getArguments().getBundle(ARG_EXTRAS);
            attacker = (Army) extras.getSerializable(ARG_ATTACKER);
            attacker_wd = (WeaponsDevelopment) extras.getSerializable(ARG_ATTACKER_WD);
            defender = (Army) extras.getSerializable(ARG_DEFENDER);
            defender_wd = (WeaponsDevelopment) extras.getSerializable(ARG_DEFENDER_WD);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter mAdapter;
        mAdapter = new ResultItemAdapter(getActivity(), result_items);

        if(task == null) {
            task = new ComputeBattleTask();
            task.execute();
        }
        else if(task.finished) {
            mListener.onFragmentInteraction("");
        }
        setListAdapter(mAdapter);
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
                defender,
                defender_wd,
                sim_iters);

        if(task.isCancelled())
            return;

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double defender_won = ((double) result.get_defender_won())/result.get_sim_iters()*100;

        double a_battleship_losses = ((double) result.get_attacker().units[Battleship.id])/result.get_sim_iters();
        double a_destroyer_losses = ((double) result.get_attacker().units[Destroyer.id])/result.get_sim_iters();
        double a_aircraftcarrier_losses = ((double) result.get_attacker().units[Aircraftcarrier.id])/result.get_sim_iters();
        double a_transport_losses = ((double) result.get_attacker().units[Transport.id])/result.get_sim_iters();
        double a_submarine_losses = ((double) result.get_attacker().units[Submarine.id])/result.get_sim_iters();
        double a_fighter_losses = ((double) result.get_attacker().units[Fighter.id])/result.get_sim_iters();
        double a_bomber_losses = ((double) result.get_attacker().units[Bomber.id])/result.get_sim_iters();

        double d_battleship_losses = ((double) result.get_defender().units[Battleship.id])/result.get_sim_iters();
        double d_destroyer_losses = ((double) result.get_defender().units[Destroyer.id])/result.get_sim_iters();
        double d_aircraftcarrier_losses = ((double) result.get_defender().units[Aircraftcarrier.id])/result.get_sim_iters();
        double d_transport_losses = ((double) result.get_defender().units[Transport.id])/result.get_sim_iters();
        double d_submarine_losses = ((double) result.get_defender().units[Submarine.id])/result.get_sim_iters();
        double d_fighter_losses = ((double) result.get_defender().units[Fighter.id])/result.get_sim_iters();
        double d_bomber_losses = ((double) result.get_defender().units[Bomber.id])/result.get_sim_iters();

        result_items[0] = new Tuple<>("Attacker won", Double.toString(attacker_won) + "%");
        result_items[1] = new Tuple<>("Transport losses", Double.toString(a_transport_losses));
        result_items[2] = new Tuple<>("Submarine losses", Double.toString(a_submarine_losses));
        result_items[3] = new Tuple<>("Aircraftcarrier losses", Double.toString(a_aircraftcarrier_losses));
        result_items[4] = new Tuple<>("Destroyer losses", Double.toString(a_destroyer_losses));
        result_items[5] = new Tuple<>("Battleship losses", Double.toString(a_battleship_losses));
        result_items[6] = new Tuple<>("Fighter losses", Double.toString(a_fighter_losses));
        result_items[7] = new Tuple<>("Bomber losses", Double.toString(a_bomber_losses));

        result_items[8] = new Tuple<>("Defender won", Double.toString(defender_won) + "%");
        result_items[9] = new Tuple<>("Transport losses", Double.toString(d_transport_losses));
        result_items[10] = new Tuple<>("Submarine losses", Double.toString(d_submarine_losses));
        result_items[11] = new Tuple<>("Aircraftcarrier losses", Double.toString(d_aircraftcarrier_losses));
        result_items[12] = new Tuple<>("Destroyer losses", Double.toString(d_destroyer_losses));
        result_items[13] = new Tuple<>("Battleship losses", Double.toString(d_battleship_losses));
        result_items[14] = new Tuple<>("Fighter losses", Double.toString(d_fighter_losses));
        result_items[15] = new Tuple<>("Bomber losses", Double.toString(d_bomber_losses));
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
