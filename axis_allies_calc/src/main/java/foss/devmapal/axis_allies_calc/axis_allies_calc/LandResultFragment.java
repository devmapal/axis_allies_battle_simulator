package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ProgressBar;


import foss.devmapal.axis_allies_calc.axis_allies_calc.dummy.DummyContent;

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
        Tuple<String, String>[] items = new Tuple[1];
        items[0] = new Tuple<String, String>("Attacker losses", "50%");
        mAdapter = new ResultItemAdapter(getActivity(), items);

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

    }

    public interface Constants {
        String LOG = "foss.devmapal.axis_allies_calc,axis_allies_calc";
    }

}
