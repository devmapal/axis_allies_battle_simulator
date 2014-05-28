package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.util.Log;

/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * interface.
 */
public class LandFragment extends Fragment {

    private static final String ARG_ATTACKER = "attacker";
    private static final String ARG_ATTACKER_WD = "attacker_wd";
    private static final String ARG_DEFENDER = "defender";
    private static final String ARG_DEFENDER_WD = "defender_wd";

    private Army attacker;
    private WeaponsDevelopment attacker_wd;
    private Army defender;
    private WeaponsDevelopment defender_wd;

    private OnFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LandFragment() {
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

        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onCreate");
        }
    }

    private void setFields(View view) {
        // Initialize attacker input fields
        EditText et = (EditText) view.findViewById(R.id.a_infantry);
        et.setText(Integer.toString(attacker.get_infantry()));
        et = (EditText) view.findViewById(R.id.a_artillery);
        et.setText(Integer.toString(attacker.get_artillery()));
        et = (EditText) view.findViewById(R.id.a_tanks);
        et.setText(Integer.toString(attacker.get_tanks()));
        et = (EditText) view.findViewById(R.id.a_fighters);
        et.setText(Integer.toString(attacker.get_fighters()));
        et = (EditText) view.findViewById(R.id.a_bombers);
        et.setText(Integer.toString(attacker.get_bombers()));
        et = (EditText) view.findViewById(R.id.a_battleships);
        et.setText(Integer.toString(attacker.get_battleships()));
        et = (EditText) view.findViewById(R.id.a_destroyers);
        et.setText(Integer.toString(attacker.get_destroyers()));
        CheckBox cb = (CheckBox) view.findViewById(R.id.combined_bombardment);
        cb.setChecked(attacker_wd.combined_bombardment);
        cb = (CheckBox) view.findViewById(R.id.heavy_bombers);
        cb.setChecked(attacker_wd.heavy_bombers);

        // Initialize defender input fields
        et = (EditText) view.findViewById(R.id.d_infantry);
        et.setText(Integer.toString(defender.get_infantry()));
        et = (EditText) view.findViewById(R.id.d_artillery);
        et.setText(Integer.toString(defender.get_artillery()));
        et = (EditText) view.findViewById(R.id.d_tanks);
        et.setText(Integer.toString(defender.get_tanks()));
        et = (EditText) view.findViewById(R.id.d_fighters);
        et.setText(Integer.toString(defender.get_fighters()));
        et = (EditText) view.findViewById(R.id.d_bombers);
        et.setText(Integer.toString(defender.get_bombers()));
        cb = (CheckBox) view.findViewById(R.id.d_antiaircraft);
        cb.setChecked(defender.get_antiaircraftguns() != 0);
        cb = (CheckBox) view.findViewById(R.id.jet_fighters);
        cb.setChecked(defender_wd.jet_fighters);
    }

    public void getFields(View view) {
        // Save attacker unit count
        EditText et = (EditText) view.findViewById(R.id.a_infantry);
        try {
            attacker.set_infantry(Integer.parseInt(et.getText().toString()));
            if(attacker.get_infantry() < 0) attacker.set_infantry(0);
        } catch (NullPointerException e) {
            attacker.set_infantry(0);
        }
        et = (EditText) view.findViewById(R.id.a_artillery);
        try {
            attacker.set_artillery(Integer.parseInt(et.getText().toString()));
            if(attacker.get_artillery() < 0) attacker.set_artillery(0);
        } catch (NullPointerException e) {
            attacker.set_artillery(0);
        }
        et = (EditText) view.findViewById(R.id.a_tanks);
        try {
            attacker.set_tanks(Integer.parseInt(et.getText().toString()));
            if(attacker.get_tanks() < 0) attacker.set_tanks(0);
        } catch (NullPointerException e) {
            attacker.set_tanks(0);
        }
        et = (EditText) view.findViewById(R.id.a_fighters);
        try {
            attacker.set_fighters(Integer.parseInt(et.getText().toString()));
            if(attacker.get_fighters() < 0) attacker.set_fighters(0);
        } catch (NullPointerException e) {
            attacker.set_fighters(0);
        }
        et = (EditText) view.findViewById(R.id.a_bombers);
        try {
            attacker.set_bombers(Integer.parseInt(et.getText().toString()));
            if(attacker.get_bombers() < 0) attacker.set_bombers(0);
        } catch (NullPointerException e) {
            attacker.set_bombers(0);
        }
        et = (EditText) view.findViewById(R.id.a_battleships);
        try {
            attacker.set_battleships(Integer.parseInt(et.getText().toString()));
            if(attacker.get_battleships() < 0) attacker.set_battleships(0);
        } catch (NullPointerException e) {
            attacker.set_battleships(0);
        }
        et = (EditText) view.findViewById(R.id.a_destroyers);
        try {
            attacker.set_destroyers(Integer.parseInt(et.getText().toString()));
            if(attacker.get_destroyers() < 0) attacker.set_destroyers(0);
        } catch (NullPointerException e) {
            attacker.set_destroyers(0);
        }
        CheckBox cb = (CheckBox) view.findViewById(R.id.combined_bombardment);
        attacker_wd.combined_bombardment = cb.isChecked();
        cb = (CheckBox) view.findViewById(R.id.heavy_bombers);
        attacker_wd.heavy_bombers = cb.isChecked();

        // Save defender unit count
        et = (EditText) view.findViewById(R.id.d_infantry);
        try {
            defender.set_infantry(Integer.parseInt(et.getText().toString()));
            if(defender.get_infantry() < 0) defender.set_infantry(0);
        } catch (NullPointerException e) {
            defender.set_infantry(0);
        }
        et = (EditText) view.findViewById(R.id.d_artillery);
        try {
            defender.set_artillery(Integer.parseInt(et.getText().toString()));
            if(defender.get_artillery() < 0) defender.set_artillery(0);
        } catch (NullPointerException e) {
            defender.set_artillery(0);
        }
        et = (EditText) view.findViewById(R.id.d_tanks);
        try {
            defender.set_tanks(Integer.parseInt(et.getText().toString()));
            if(defender.get_tanks() < 0) defender.set_tanks(0);
        } catch (NullPointerException e) {
            defender.set_tanks(0);
        }
        et = (EditText) view.findViewById(R.id.d_fighters);
        try {
            defender.set_fighters(Integer.parseInt(et.getText().toString()));
            if(defender.get_fighters() < 0) defender.set_fighters(0);
        } catch (NullPointerException e) {
            defender.set_fighters(0);
        }
        et = (EditText) view.findViewById(R.id.d_bombers);
        try {
            defender.set_bombers(Integer.parseInt(et.getText().toString()));
            if(defender.get_bombers() < 0) defender.set_bombers(0);
        } catch (NullPointerException e) {
            defender.set_bombers(0);
        }
        cb = (CheckBox) view.findViewById(R.id.d_antiaircraft);
        int antiaircraft = cb.isChecked() ? 1 : 0;
        defender.set_antiaircraftguns(antiaircraft);
        cb = (CheckBox) view.findViewById(R.id.jet_fighters);
        defender_wd.jet_fighters = cb.isChecked();
    }

    public Army getAttacker() {
        return new Army(attacker);
    }

    public WeaponsDevelopment getAttacker_wd() {
        return new WeaponsDevelopment(attacker_wd);
    }

    public Army getDefender() {
        return new Army(defender);
    }

    public WeaponsDevelopment getDefender_wd() {
        return new WeaponsDevelopment(defender_wd);
    }

    public void clear_fields() {
        attacker = new Army();
        attacker_wd = new WeaponsDevelopment();
        defender = new Army();
        defender_wd = new WeaponsDevelopment();

        setFields(getView());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.land_input, container, false);
        setFields(view);

        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onCreateView");
        }

        return view;
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

        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onAttach");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onDetach");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        getFields(getView());
        mListener.onSaveState(getString(R.string.title_land_battle),
                attacker,
                attacker_wd,
                defender,
                defender_wd);

        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onPause");
        }
    }

    public void swap() {
        getFields(getView());
        Army attacker_old = attacker;
        WeaponsDevelopment attacker_wd_old = attacker_wd;
        attacker = defender;
        attacker_wd = defender_wd;
        defender = attacker_old;
        defender_wd = attacker_wd_old;
        setFields(getView());
    }

    /**
    * This interface must be implemented by activities that contain this
    * fragment to allow an interaction in this fragment to be communicated
    * to the activity and potentially other fragments contained in that
    * activity.
    * <p>
    * See the Android Training lesson <a href=
    * "http://developer.android.com/training/basics/fragments/communicating.html"
    * >Communicating with Other Fragments</a> for more information.
    */
    public interface OnFragmentInteractionListener {
        public void onSaveState(String id, Army attacker, WeaponsDevelopment attacker_wd,
                                           Army defender, WeaponsDevelopment defender_wd);
    }

    public interface Constants {
        String LOG = "foss.devmapal.axis_allies_calc.axis_allies_calc.LandFragment";
    }
}
