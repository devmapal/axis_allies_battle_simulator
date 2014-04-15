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

    private int nav_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        land_fragment = LandFragment.newInstance();
        naval_fragment = NavalFragment.newInstance();

        nav_pos = 0;

        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentTransaction ft;
        nav_pos = position;
        switch(position) {
            case 0:
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, land_fragment);
                ft.commit();
                break;
            case 1:
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
                i.putExtra(getString(R.string.attacker), land_fragment.getAttacker());
                i.putExtra(getString(R.string.attacker_wd), land_fragment.getAttacker_wd());
                i.putExtra(getString(R.string.defender), land_fragment.getDefender());
                i.putExtra(getString(R.string.defender_wd), land_fragment.getDefender_wd());
                startActivity(i);
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_land_battle);
                break;
            case 2:
                mTitle = getString(R.string.title_naval_battle);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(String id) {
    }
}
