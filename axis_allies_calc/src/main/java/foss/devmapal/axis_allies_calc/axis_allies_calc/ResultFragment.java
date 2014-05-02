package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class ResultFragment extends Fragment {
    private static final String ARG_LAND_BATTLE = "land_battle";
    private static final String ARG_EXTRAS = "extras";

    private boolean land_battle;
    private Bundle extras;

    private LandResultFragment result_fragment;
    private LinearLayout linlaComputeProgress;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ResultFragment.
     */
    public static ResultFragment newInstance(Bundle extras, boolean land_battle) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_LAND_BATTLE, land_battle);
        args.putBundle(ARG_EXTRAS, extras);
        fragment.setArguments(args);
        return fragment;
    }
    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            land_battle = getArguments().getBoolean(ARG_LAND_BATTLE);
            extras = getArguments().getBundle(ARG_EXTRAS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        linlaComputeProgress = (LinearLayout) view.findViewById(R.id.linlaComputeProgress);
        linlaComputeProgress.setVisibility(View.VISIBLE);
        if(land_battle == true) {
            FragmentManager fm = getChildFragmentManager();
            result_fragment = (LandResultFragment) fm.findFragmentById(R.id.result_fragment_container);
            if(result_fragment == null) {
                result_fragment = LandResultFragment.newInstance(extras);
                FragmentTransaction ft_child = getChildFragmentManager().beginTransaction();
                ft_child.replace(R.id.result_fragment_container, result_fragment);
                ft_child.commit();
            }
        }
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void hide_progress() {
        linlaComputeProgress.setVisibility(View.GONE);
    }

    public void show_progress() {
    }
}
