package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.ListFragment;
import android.preference.PreferenceManager;
import android.util.Log;
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

    private static final String ARG_ATTACKER = "attacker";
    private static final String ARG_ATTACKER_WD = "attacker_wd";
    private static final String ARG_DEFENDER = "defender";
    private static final String ARG_DEFENDER_WD = "defender_wd";

    private Army attacker;
    private WeaponsDevelopment attacker_wd;
    private Army defender;
    private WeaponsDevelopment defender_wd;

    private Tuple<String, String>[] result_items;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;


    public static LandResultFragment newInstance(Bundle extras) {
        LandResultFragment fragment = new LandResultFragment();
        fragment.setArguments(extras);
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
        result_items = new Tuple[0];

        if (getArguments() != null) {
            attacker = (Army) getArguments().getSerializable(ARG_ATTACKER);
            attacker_wd = (WeaponsDevelopment) getArguments().getSerializable(ARG_ATTACKER_WD);
            defender = (Army) getArguments().getSerializable(ARG_DEFENDER);
            defender_wd = (WeaponsDevelopment) getArguments().getSerializable(ARG_DEFENDER_WD);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new ResultItemAdapter(getActivity(), result_items);

        setListAdapter(mAdapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
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
        LandBattle battle = new LandBattle(attacker,
                attacker_wd,
                defender,
                defender_wd,
                sim_iters,
                take_territory);

        BattleResult result = new BattleResult(attacker, defender, sim_iters);
        double attacker_won = result.get_attacker_won()/result.get_sim_iters();
        double defender_won = result.get_defender_won()/result.get_sim_iters();

        result_items = new Tuple[2];
        result_items[0] = new Tuple<String, String>("Attacker won", Double.toString(attacker_won) + "%");
        result_items[1] = new Tuple<String, String>("Defender won", Double.toString(defender_won) + "%");
        for(int i = 0 ; i < 1000000000; i++);
    }

    public interface Constants {
        String LOG = "foss.devmapal.axis_allies_calc,axis_allies_calc";
    }

}