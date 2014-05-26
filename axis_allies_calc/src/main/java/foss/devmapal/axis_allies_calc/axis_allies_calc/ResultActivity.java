package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ResultActivity extends Activity implements LandResultFragment.OnFragmentInteractionListener,
                                                        NavalResultFragment.OnFragmentInteractionListener {
    private ResultFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_result);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentManager fm = getFragmentManager();
        frag = (ResultFragment) fm.findFragmentById(R.id.result_container);
        if( frag == null ) {
            frag = ResultFragment.newInstance(extras, extras.getBoolean("land_battle"));
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.result_container, frag);
            ft.commit();
        }
    }

    @Override
    public void onFragmentInteraction(String id) {
        frag.hide_progress();
    }
}
