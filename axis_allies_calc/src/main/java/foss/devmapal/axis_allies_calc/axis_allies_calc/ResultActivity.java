package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ResultActivity extends Activity implements ResultFragment.OnFragmentInteractionListener {

    private LinearLayout linlaComputeProgress;
    private Fragment result_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        boolean land_battle = extras.getBoolean("land_battle");
        setContentView(R.layout.activity_result);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        linlaComputeProgress = (LinearLayout) findViewById(R.id.linlaComputeProgress);
        linlaComputeProgress.setVisibility(View.VISIBLE);
        FragmentManager fm = getFragmentManager();
        result_fragment = fm.findFragmentById(R.id.result_container);

        if (BuildConfig.DEBUG) {
            Log.d(ResultActivity.Constants.LOG, Boolean.toString(land_battle));
        }

        if(land_battle == true) {
            if(!(result_fragment instanceof LandResultFragment)) {
                result_fragment = LandResultFragment.newInstance(extras);
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.result_container, result_fragment);
                ft.commit();
            }
        }
        else {
            if(!(result_fragment instanceof NavalResultFragment)) {
                result_fragment = NavalResultFragment.newInstance(extras);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.result_container, result_fragment);
                ft.commit();
            }
        }
    }

    @Override
    public void onFragmentInteraction(String id) {
        hide_progress();
        if(result_fragment instanceof LandResultFragment) {
            ((LandResultFragment) result_fragment).setFields();
        }
        else if(result_fragment instanceof NavalResultFragment) {
            ((NavalResultFragment) result_fragment).setFields();
        }
    }

    public void hide_progress() {
        linlaComputeProgress.setVisibility(View.GONE);
        findViewById(R.id.result_layout).setVisibility(View.VISIBLE);
    }

    public interface Constants {
        String LOG = "foss.devmapal.axis_allies_calc.axis_allies_calc.ResultActivity";
    }
}
