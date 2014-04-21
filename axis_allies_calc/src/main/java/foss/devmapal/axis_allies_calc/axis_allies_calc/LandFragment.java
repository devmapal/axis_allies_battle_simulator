package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;
import android.app.Fragment;
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

    public static LandFragment newInstance(Army attacker,
                                           WeaponsDevelopment attacker_wd,
                                           Army defender,
                                           WeaponsDevelopment defender_wd) {
        LandFragment fragment = new LandFragment();
        Bundle args = new Bundle();

        args.putSerializable(ARG_ATTACKER, attacker);
        args.putSerializable(ARG_ATTACKER_WD, attacker_wd);
        args.putSerializable(ARG_DEFENDER, defender);
        args.putSerializable(ARG_DEFENDER_WD, defender_wd);
        fragment.setArguments(args);
        return fragment;
    }

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
    }

    private void setFields(View view) {
        // Initialize attacker input fields
        EditText et = (EditText) view.findViewById(R.id.a_infantry);
        et.setText(Integer.toString(attacker.infantry));
        et = (EditText) view.findViewById(R.id.a_artillery);
        et.setText(Integer.toString(attacker.artillery));
        et = (EditText) view.findViewById(R.id.a_tanks);
        et.setText(Integer.toString(attacker.tanks));
        et = (EditText) view.findViewById(R.id.a_fighters);
        et.setText(Integer.toString(attacker.fighters));
        et = (EditText) view.findViewById(R.id.a_bombers);
        et.setText(Integer.toString(attacker.bombers));
        et = (EditText) view.findViewById(R.id.a_battleships);
        et.setText(Integer.toString(attacker.battleships));
        et = (EditText) view.findViewById(R.id.a_destroyers);
        et.setText(Integer.toString(attacker.destroyers));
        CheckBox cb = (CheckBox) view.findViewById(R.id.combined_bombardment);
        cb.setChecked(attacker_wd.combined_bombardment);
        cb = (CheckBox) view.findViewById(R.id.heavy_bombers);
        cb.setChecked(attacker_wd.heavy_bombers);

        // Initialize defender input fields
        et = (EditText) view.findViewById(R.id.d_infantry);
        et.setText(Integer.toString(defender.infantry));
        et = (EditText) view.findViewById(R.id.d_artillery);
        et.setText(Integer.toString(defender.artillery));
        et = (EditText) view.findViewById(R.id.d_tanks);
        et.setText(Integer.toString(defender.tanks));
        et = (EditText) view.findViewById(R.id.d_fighters);
        et.setText(Integer.toString(defender.fighters));
        et = (EditText) view.findViewById(R.id.d_bombers);
        et.setText(Integer.toString(defender.bombers));
        cb = (CheckBox) view.findViewById(R.id.d_antiaircraft);
        cb.setChecked(defender.antiaircraft_gun);
        cb = (CheckBox) view.findViewById(R.id.jet_fighters);
        cb.setChecked(defender_wd.jet_fighters);
    }

    public void getFields(View view) {
        // Save attacker unit count
        EditText et = (EditText) view.findViewById(R.id.a_infantry);
        try {
            attacker.infantry = Integer.parseInt(et.getText().toString());
            if(attacker.infantry < 0) attacker.infantry = 0;
        } catch (NullPointerException e) {
            attacker.infantry = 0;
        }
        et = (EditText) view.findViewById(R.id.a_artillery);
        try {
            attacker.artillery = Integer.parseInt(et.getText().toString());
            if(attacker.artillery < 0) attacker.artillery = 0;
        } catch (NullPointerException e) {
            attacker.artillery = 0;
        }
        et = (EditText) view.findViewById(R.id.a_tanks);
        try {
            attacker.tanks = Integer.parseInt(et.getText().toString());
            if(attacker.tanks < 0) attacker.tanks = 0;
        } catch (NullPointerException e) {
            attacker.tanks = 0;
        }
        et = (EditText) view.findViewById(R.id.a_fighters);
        try {
            attacker.fighters = Integer.parseInt(et.getText().toString());
            if(attacker.fighters < 0) attacker.fighters = 0;
        } catch (NullPointerException e) {
            attacker.fighters = 0;
        }
        et = (EditText) view.findViewById(R.id.a_bombers);
        try {
            attacker.bombers = Integer.parseInt(et.getText().toString());
            if(attacker.bombers < 0) attacker.bombers = 0;
        } catch (NullPointerException e) {
            attacker.bombers = 0;
        }
        et = (EditText) view.findViewById(R.id.a_battleships);
        try {
            attacker.battleships = Integer.parseInt(et.getText().toString());
            if(attacker.battleships < 0) attacker.battleships = 0;
        } catch (NullPointerException e) {
            attacker.battleships = 0;
        }
        et = (EditText) view.findViewById(R.id.a_destroyers);
        try {
            attacker.destroyers = Integer.parseInt(et.getText().toString());
            if(attacker.destroyers < 0) attacker.destroyers = 0;
        } catch (NullPointerException e) {
            attacker.destroyers = 0;
        }

        // Save defender unit count
        et = (EditText) view.findViewById(R.id.d_infantry);
        try {
            defender.infantry = Integer.parseInt(et.getText().toString());
            if(defender.infantry < 0) defender.infantry = 0;
        } catch (NullPointerException e) {
            defender.infantry = 0;
        }
        et = (EditText) view.findViewById(R.id.d_artillery);
        try {
            defender.artillery = Integer.parseInt(et.getText().toString());
            if(defender.artillery < 0) defender.artillery = 0;
        } catch (NullPointerException e) {
            defender.artillery = 0;
        }
        et = (EditText) view.findViewById(R.id.d_tanks);
        try {
            defender.tanks = Integer.parseInt(et.getText().toString());
            if(defender.tanks < 0) defender.tanks = 0;
        } catch (NullPointerException e) {
            defender.tanks = 0;
        }
        et = (EditText) view.findViewById(R.id.d_fighters);
        try {
            defender.fighters = Integer.parseInt(et.getText().toString());
            if(defender.fighters < 0) defender.fighters = 0;
        } catch (NullPointerException e) {
            defender.fighters = 0;
        }
        et = (EditText) view.findViewById(R.id.d_bombers);
        try {
            defender.bombers = Integer.parseInt(et.getText().toString());
            if(defender.bombers < 0) defender.bombers = 0;
        } catch (NullPointerException e) {
            defender.bombers = 0;
        }
    }

    public Army getAttacker() {
        View view = getView();
        getFields(view);
        return new Army(attacker);
    }

    public WeaponsDevelopment getAttacker_wd() {
        View view = getView();
        getFields(view);
        return new WeaponsDevelopment(attacker_wd);
    }

    public Army getDefender() {
        View view = getView();
        getFields(view);
        return new Army(defender);
    }

    public WeaponsDevelopment getDefender_wd() {
        View view = getView();
        getFields(view);
        return new WeaponsDevelopment(defender_wd);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.land_input, container, false);

        CheckBox combined_bombardment = (CheckBox) view.findViewById(R.id.combined_bombardment);
        combined_bombardment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attacker_wd.combined_bombardment = ((CheckBox) v).isChecked();
            }
        });
        CheckBox heavy_bombers = (CheckBox) view.findViewById(R.id.heavy_bombers);
        heavy_bombers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attacker_wd.heavy_bombers = ((CheckBox) v).isChecked();
            }
        });
        CheckBox antiaircraft = (CheckBox) view.findViewById(R.id.d_antiaircraft);
        antiaircraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defender.antiaircraft_gun = ((CheckBox) v).isChecked();
            }
        });
        CheckBox jet_fighters = (CheckBox) view.findViewById(R.id.jet_fighters);
        jet_fighters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defender_wd.jet_fighters = ((CheckBox) v).isChecked();
            }
        });

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
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }
}
