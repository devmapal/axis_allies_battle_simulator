package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

    // TODO: Rename and change types of parameters
    public static NavalFragment newInstance(Army attacker,
                                            WeaponsDevelopment attacker_wd,
                                            Army defender,
                                            WeaponsDevelopment defender_wd) {
        NavalFragment fragment = new NavalFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.naval_input, container, false);
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
