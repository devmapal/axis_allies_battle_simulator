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
public abstract class ResultFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private static final String ARG_ATTACKER = "attacker";
    private static final String ARG_ATTACKER_WD = "attacker_wd";
    private static final String ARG_ATTACKER_HIT_ORDER = "attacker_hit_order";
    private static final String ARG_DEFENDER = "defender";
    private static final String ARG_DEFENDER_WD = "defender_wd";
    private static final String ARG_DEFENDER_HIT_ORDER = "defender_hit_order";
    protected static final String ARG_EXTRAS = "extras";

    protected Army attacker;
    protected WeaponsDevelopment attacker_wd;
    protected ArrayList<Integer> attacker_hit_order;
    protected Army defender;
    protected WeaponsDevelopment defender_wd;
    protected ArrayList<Integer> defender_hit_order;

    protected HashMap<String, Double> attacker_results;
    protected HashMap<String, Double> defender_results;
    protected ComputeBattleTask task;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ResultFragment() {
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

    public abstract void sim_battle();

    public abstract void setFields();

    protected class ComputeBattleTask extends AsyncTask<Void, Void, Void> {
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
