package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.CheckBox;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
                   LandFragment.OnFragmentInteractionListener,
                   NavalFragment.OnFragmentInteractionListener {

    private static final String ARG_ATTACKER = "attacker";
    private static final String ARG_ATTACKER_WD = "attacker_wd";
    private static final String ARG_DEFENDER = "defender";
    private static final String ARG_DEFENDER_WD = "defender_wd";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private LandFragment land_fragment;
    private NavalFragment naval_fragment;

    /**
     * Used to store the current nav position (LandBattle / NavalBattle)
     */
    private int nav_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            land_fragment = LandFragment.newInstance(
                    (Army) savedInstanceState.getSerializable(ARG_ATTACKER),
                    (WeaponsDevelopment) savedInstanceState.getSerializable(ARG_ATTACKER_WD),
                    (Army) savedInstanceState.getSerializable(ARG_DEFENDER),
                    (WeaponsDevelopment) savedInstanceState.getSerializable(ARG_DEFENDER_WD));
            naval_fragment = NavalFragment.newInstance();
        } else {
            land_fragment = LandFragment.newInstance(new Army(),
                    new WeaponsDevelopment(),
                    new Army(),
                    new WeaponsDevelopment());
            naval_fragment = NavalFragment.newInstance();
        }

        nav_pos = 0;

        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        //mTitle = getTitle();
        mTitle = getString(R.string.title_land_battle);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        View view = land_fragment.getView();
        land_fragment.getFields(view);
        savedInstanceState.putSerializable(ARG_ATTACKER, land_fragment.getAttacker());
        savedInstanceState.putSerializable(ARG_ATTACKER_WD, land_fragment.getAttacker_wd());
        savedInstanceState.putSerializable(ARG_DEFENDER, land_fragment.getDefender());
        savedInstanceState.putSerializable(ARG_DEFENDER_WD, land_fragment.getDefender_wd());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentTransaction ft;
        nav_pos = position;
        switch(position) {
            case 0:
                mTitle = getString(R.string.title_land_battle);
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, land_fragment);
                ft.commit();
                break;
            case 1:
                mTitle = getString(R.string.title_naval_battle);
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, naval_fragment);
                ft.commit();
                break;
        }
    }

    @Override
    public void onRunAction() {
        switch(nav_pos) {
            case 0:
                Intent i = new Intent(this, ResultActivity.class);
                i.putExtra("attacker", land_fragment.getAttacker());
                i.putExtra("attacker_wd", land_fragment.getAttacker_wd());
                i.putExtra("defender", land_fragment.getDefender());
                i.putExtra("defender_wd", land_fragment.getDefender_wd());
                startActivity(i);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                return true;
            case R.id.action_clear:
                switch(nav_pos) {
                    case 0:
                        land_fragment.clear_fields();
                        break;
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(String id) {
    }
}
