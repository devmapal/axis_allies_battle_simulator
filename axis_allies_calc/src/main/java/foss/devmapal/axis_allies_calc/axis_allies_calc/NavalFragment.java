package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * interface.
 */
public class NavalFragment extends Fragment {

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
    public NavalFragment() {
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

    private void setFields(View view) {
        // Initialize attacker input fields
        EditText et = (EditText) view.findViewById(R.id.a_battleships);
        et.setText(Integer.toString(attacker.get_battleships()));
        et = (EditText) view.findViewById(R.id.a_destroyers);
        et.setText(Integer.toString(attacker.get_destroyers()));
        et = (EditText) view.findViewById(R.id.a_aircraft_carriers);
        et.setText(Integer.toString(attacker.get_aircraftcarriers()));
        et = (EditText) view.findViewById(R.id.a_transports);
        et.setText(Integer.toString(attacker.get_transports()));
        et = (EditText) view.findViewById(R.id.a_submarines);
        et.setText(Integer.toString(attacker.get_submarines()));
        et = (EditText) view.findViewById(R.id.a_fighters);
        et.setText(Integer.toString(attacker.get_fighters()));
        et = (EditText) view.findViewById(R.id.a_bombers);
        et.setText(Integer.toString(attacker.get_bombers()));
        CheckBox cb = (CheckBox) view.findViewById(R.id.super_submarines);
        cb.setChecked(attacker_wd.super_submarines);
        cb = (CheckBox) view.findViewById(R.id.heavy_bombers);
        cb.setChecked(attacker_wd.heavy_bombers);

        // Initialize defender input fields
        et = (EditText) view.findViewById(R.id.d_battleships);
        et.setText(Integer.toString(attacker.get_battleships()));
        et = (EditText) view.findViewById(R.id.d_destroyers);
        et.setText(Integer.toString(attacker.get_destroyers()));
        et = (EditText) view.findViewById(R.id.d_aircraft_carriers);
        et.setText(Integer.toString(attacker.get_aircraftcarriers()));
        et = (EditText) view.findViewById(R.id.d_transports);
        et.setText(Integer.toString(attacker.get_transports()));
        et = (EditText) view.findViewById(R.id.d_submarines);
        et.setText(Integer.toString(attacker.get_submarines()));
        et = (EditText) view.findViewById(R.id.d_fighters);
        et.setText(Integer.toString(attacker.get_fighters()));
        et = (EditText) view.findViewById(R.id.d_bombers);
        et.setText(Integer.toString(attacker.get_bombers()));
        cb = (CheckBox) view.findViewById(R.id.jet_fighters);
        cb.setChecked(defender_wd.jet_fighters);
    }

    public void getFields(View view) {
        // Save attacker unit count
        EditText et = (EditText) view.findViewById(R.id.a_battleships);
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
        et = (EditText) view.findViewById(R.id.a_aircraft_carriers);
        try {
            attacker.set_aircraftcarriers(Integer.parseInt(et.getText().toString()));
            if(attacker.get_aircraftcarriers() < 0) attacker.set_aircraftcarriers(0);
        } catch (NullPointerException e) {
            attacker.set_aircraftcarriers(0);
        }
        et = (EditText) view.findViewById(R.id.a_transports);
        try {
            attacker.set_transports(Integer.parseInt(et.getText().toString()));
            if(attacker.get_transports() < 0) attacker.set_transports(0);
        } catch (NullPointerException e) {
            attacker.set_transports(0);
        }
        et = (EditText) view.findViewById(R.id.a_submarines);
        try {
            attacker.set_submarines(Integer.parseInt(et.getText().toString()));
            if(attacker.get_submarines() < 0) attacker.set_submarines(0);
        } catch (NullPointerException e) {
            attacker.set_submarines(0);
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
        CheckBox cb = (CheckBox) view.findViewById(R.id.super_submarines);
        attacker_wd.super_submarines = cb.isChecked();
        cb = (CheckBox) view.findViewById(R.id.heavy_bombers);
        attacker_wd.heavy_bombers = cb.isChecked();

        // Save defender unit count
        et = (EditText) view.findViewById(R.id.d_battleships);
        try {
            defender.set_battleships(Integer.parseInt(et.getText().toString()));
            if(defender.get_battleships() < 0) defender.set_battleships(0);
        } catch (NullPointerException e) {
            defender.set_battleships(0);
        }
        et = (EditText) view.findViewById(R.id.d_destroyers);
        try {
            defender.set_destroyers(Integer.parseInt(et.getText().toString()));
            if(defender.get_destroyers() < 0) defender.set_destroyers(0);
        } catch (NullPointerException e) {
            defender.set_destroyers(0);
        }
        et = (EditText) view.findViewById(R.id.d_aircraft_carriers);
        try {
            defender.set_aircraftcarriers(Integer.parseInt(et.getText().toString()));
            if(defender.get_aircraftcarriers() < 0) defender.set_aircraftcarriers(0);
        } catch (NullPointerException e) {
            defender.set_aircraftcarriers(0);
        }
        et = (EditText) view.findViewById(R.id.d_transports);
        try {
            defender.set_transports(Integer.parseInt(et.getText().toString()));
            if(defender.get_transports() < 0) defender.set_transports(0);
        } catch (NullPointerException e) {
            defender.set_transports(0);
        }
        et = (EditText) view.findViewById(R.id.d_submarines);
        try {
            defender.set_submarines(Integer.parseInt(et.getText().toString()));
            if(defender.get_submarines() < 0) defender.set_submarines(0);
        } catch (NullPointerException e) {
            defender.set_submarines(0);
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
        View view = inflater.inflate(R.layout.naval_input, container, false);
        setFields(view);

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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        getFields(getView());
        mListener.onSaveState(getString(R.string.title_naval_battle),
                attacker,
                attacker_wd,
                defender,
                defender_wd);
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

}
