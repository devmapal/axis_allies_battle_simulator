package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ProgressBar;


/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * interface.
 */
public class LandResultFragment extends ListFragment {

    private ProgressBar myProgressBar;
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

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ArrayAdapter mAdapter;


    public static LandResultFragment newInstance(Bundle extras) {
        LandResultFragment fragment = new LandResultFragment();
        Bundle args = new Bundle();
        args.putBundle(ARG_EXTRAS, extras);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LandResultFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        result_items = new Tuple[2];

        if (getArguments() != null) {
            Bundle extras = (Bundle) getArguments().getBundle(ARG_EXTRAS);
            attacker = (Army) extras.getSerializable(ARG_ATTACKER);
            attacker_wd = (WeaponsDevelopment) extras.getSerializable(ARG_ATTACKER_WD);
            defender = (Army) extras.getSerializable(ARG_DEFENDER);
            defender_wd = (WeaponsDevelopment) extras.getSerializable(ARG_DEFENDER_WD);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new ResultItemAdapter(getActivity(), result_items);

        ComputeBattleTask task = new ComputeBattleTask();
        task.execute();
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
    }

    public void sim_battle() {
        if (BuildConfig.DEBUG) {
            Log.d(Constants.LOG, "Calculating battle");
        }
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean take_territory = sp.getBoolean(getString(R.string.key_pref_take_territory), true);
        int sim_iters = Integer.parseInt(sp.getString(getString(R.string.key_pref_sim_iters), "100000"));
        LandBattleSimulation battle = new LandBattleSimulation(attacker,
                attacker_wd,
                defender,
                defender_wd,
                sim_iters,
                take_territory);

        BattleResult result = battle.run();
        double attacker_won = ((double) result.get_attacker_won())/result.get_sim_iters()*100;
        double defender_won = ((double) result.get_defender_won())/result.get_sim_iters()*100;

        //result_items = new Tuple[2];
        result_items[0] = new Tuple<String, String>("Attacker won", Double.toString(attacker_won) + "%");
        result_items[1] = new Tuple<String, String>("Defender won", Double.toString(defender_won) + "%");
    }

    private class ComputeBattleTask extends AsyncTask<Void, Void, Void> {

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
            mListener.onFragmentInteraction("");
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

    public interface Constants {
        String LOG = "foss.devmapal.axis_allies_calc,axis_allies_calc";
    }
}
