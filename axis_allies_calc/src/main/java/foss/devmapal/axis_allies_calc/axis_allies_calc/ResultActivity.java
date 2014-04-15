package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_result);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ResultFragment frag = ResultFragment.newInstance(extras, true);
        ft.add(R.id.result_container, frag);
        ft.commit();
    }
}
