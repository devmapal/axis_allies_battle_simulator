package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;

import android.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity
        implements LandFragment.OnFragmentInteractionListener,
                   NavalFragment.OnFragmentInteractionListener {

    private static final String LAND_BATTLE = "land_battle";
    private static final String NAVAL_BATTLE = "naval_battle";
    private static final String ARG_ATTACKER = "attacker";
    private static final String ARG_ATTACKER_WD = "attacker_wd";
    private static final String ARG_DEFENDER = "defender";
    private static final String ARG_DEFENDER_WD = "defender_wd";
    private static final int LAND_POS = 0;
    private static final int NAVAL_POS = 1;

    private ViewPager mViewPager;
    private TabsAdapter mTabsAdapter;

    private Bundle land_fragment_data;
    private Bundle naval_fragment_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        land_fragment_data = new Bundle();
        naval_fragment_data = new Bundle();
        if(savedInstanceState != null) {
            restore_state_from_saved_instance_state(savedInstanceState);
        } else {
            restore_state_from_preference();
        }

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewpager);
        setContentView(mViewPager);

        final ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

        mTabsAdapter = new TabsAdapter(this, mViewPager);
        mTabsAdapter.addTab(bar.newTab().setText(R.string.title_land_battle),
                LandFragment.class, land_fragment_data);
        mTabsAdapter.addTab(bar.newTab().setText(R.string.title_naval_battle),
                NavalFragment.class, naval_fragment_data);

        if (savedInstanceState != null) {
            bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", LAND_POS));
        }
        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onCreate");
        }
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBundle(LAND_BATTLE, land_fragment_data);
        savedInstanceState.putBundle(NAVAL_BATTLE, naval_fragment_data);
        savedInstanceState.putInt("tab", getActionBar().getSelectedNavigationIndex());

        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onSaveInstanceState");
        }

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onStop");
        }
        save_state_to_preference();
        mTabsAdapter = null;
        mViewPager = null;
    }

    private void restore_state_from_saved_instance_state(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "restore_state_from_saved_instance_state");
        }
        land_fragment_data = savedInstanceState.getBundle(LAND_BATTLE);
        naval_fragment_data = savedInstanceState.getBundle(NAVAL_BATTLE);
    }

    private void restore_state_from_preference() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int defaultValue = 0;
        boolean defaultBool = false;

        Army attacker = new Army(
                sharedPref.getInt("a_land_" + Infantry.name, defaultValue),
                sharedPref.getInt("a_land_" + Artillery.name, defaultValue),
                sharedPref.getInt("a_land_" + Tank.name, defaultValue),
                sharedPref.getInt("a_land_" + Fighter.name, defaultValue),
                sharedPref.getInt("a_land_" + Bomber.name, defaultValue),
                sharedPref.getInt("a_land_" + Battleship.name, defaultValue),
                sharedPref.getInt("a_land_" + Destroyer.name, defaultValue),
                defaultValue,
                defaultValue,
                sharedPref.getInt("a_land_" + AntiaircraftGun.name, defaultValue)
        );

        WeaponsDevelopment attacker_wd = new WeaponsDevelopment(
                sharedPref.getBoolean("a_land_" + getString(R.string.jet_fighters), defaultBool),
                defaultBool,
                sharedPref.getBoolean("a_land_" + getString(R.string.combined_bombardment), defaultBool),
                sharedPref.getBoolean("a_land_" + getString(R.string.heavy_bombers), defaultBool)
        );

        Army defender = new Army(
                sharedPref.getInt("d_land_" + Infantry.name, defaultValue),
                sharedPref.getInt("d_land_" + Artillery.name, defaultValue),
                sharedPref.getInt("d_land_" + Tank.name, defaultValue),
                sharedPref.getInt("d_land_" + Fighter.name, defaultValue),
                sharedPref.getInt("d_land_" + Bomber.name, defaultValue),
                sharedPref.getInt("d_land_" + Battleship.name, defaultValue),
                sharedPref.getInt("d_land_" + Destroyer.name, defaultValue),
                defaultValue,
                defaultValue,
                sharedPref.getInt("d_land_" + AntiaircraftGun.name, defaultValue)
        );

        WeaponsDevelopment defender_wd = new WeaponsDevelopment(
                sharedPref.getBoolean("d_land_" + getString(R.string.jet_fighters), defaultBool),
                defaultBool,
                sharedPref.getBoolean("d_land_" + getString(R.string.combined_bombardment), defaultBool),
                sharedPref.getBoolean("d_land_" + getString(R.string.heavy_bombers), defaultBool)
        );

        land_fragment_data.putSerializable(ARG_ATTACKER, attacker);
        land_fragment_data.putSerializable(ARG_ATTACKER_WD, attacker_wd);
        land_fragment_data.putSerializable(ARG_DEFENDER, defender);
        land_fragment_data.putSerializable(ARG_DEFENDER_WD, defender_wd);
    }

    private void save_state_to_preference() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Army attacker = (Army) land_fragment_data.getSerializable(ARG_ATTACKER);
        editor.putInt("a_land_" + Infantry.name, attacker.get_infantry());
        editor.putInt("a_land_" + Artillery.name, attacker.get_artillery());
        editor.putInt("a_land_" + Tank.name, attacker.get_tanks());
        editor.putInt("a_land_" + Fighter.name, attacker.get_fighters());
        editor.putInt("a_land_" + Bomber.name, attacker.get_bombers());
        editor.putInt("a_land_" + Battleship.name, attacker.get_battleships());
        editor.putInt("a_land_" + Destroyer.name, attacker.get_destroyers());
        editor.putInt("a_land_" + AntiaircraftGun.name, attacker.get_antiaircraftguns());

        WeaponsDevelopment attacker_wd = (WeaponsDevelopment) land_fragment_data.getSerializable(ARG_ATTACKER_WD);
        editor.putBoolean("a_land_" + getString(R.string.jet_fighters), attacker_wd.jet_fighters);
        editor.putBoolean("a_land_" + getString(R.string.combined_bombardment), attacker_wd.combined_bombardment);
        editor.putBoolean("a_land_" + getString(R.string.heavy_bombers), attacker_wd.heavy_bombers);

        Army defender = (Army) land_fragment_data.getSerializable(ARG_DEFENDER);
        editor.putInt("d_land_" + Infantry.name, defender.get_infantry());
        editor.putInt("d_land_" + Artillery.name, defender.get_artillery());
        editor.putInt("d_land_" + Tank.name, defender.get_tanks());
        editor.putInt("d_land_" + Fighter.name, defender.get_fighters());
        editor.putInt("d_land_" + Bomber.name, defender.get_bombers());
        editor.putInt("d_land_" + Battleship.name, defender.get_battleships());
        editor.putInt("d_land_" + Destroyer.name, defender.get_destroyers());
        editor.putInt("d_land_" + AntiaircraftGun.name, defender.get_antiaircraftguns());

        WeaponsDevelopment defender_wd = (WeaponsDevelopment) land_fragment_data.getSerializable(ARG_DEFENDER_WD);
        editor.putBoolean("d_land_" + getString(R.string.jet_fighters), defender_wd.jet_fighters);
        editor.putBoolean("d_land_" + getString(R.string.combined_bombardment), defender_wd.combined_bombardment);
        editor.putBoolean("d_land_" + getString(R.string.heavy_bombers), defender_wd.heavy_bombers);

        editor.commit();
    }

    public void runAction() {
        switch(getActionBar().getSelectedNavigationIndex()) {
            case LAND_POS:
                LandFragment land_fragment = (LandFragment) mTabsAdapter.
                                                              getFragment(R.id.viewpager, LAND_POS);
                Intent i = new Intent(this, ResultActivity.class);
                i.putExtra("attacker", land_fragment.getAttacker());
                i.putExtra("attacker_wd", land_fragment.getAttacker_wd());
                i.putExtra("defender", land_fragment.getDefender());
                i.putExtra("defender_wd", land_fragment.getDefender_wd());
                startActivity(i);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onOptionsItemSelected");
        }
        switch(item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                return true;
            case R.id.action_clear:
                switch(getActionBar().getSelectedNavigationIndex()) {
                    case LAND_POS:
                        LandFragment land_fragment = (LandFragment) mTabsAdapter.
                                                        getFragment(R.id.viewpager, LAND_POS);
                        land_fragment.clear_fields();
                        break;
                }
                return true;
            case R.id.action_run:
                runAction();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(String id) {
    }

    @Override
    public void onSaveState(String id, Army attacker, WeaponsDevelopment attacker_wd, Army defender, WeaponsDevelopment defender_wd) {
        if(id.equals(getString(R.string.title_land_battle))) {
            land_fragment_data.putSerializable(ARG_ATTACKER, new Army(attacker));
            land_fragment_data.putSerializable(ARG_ATTACKER_WD, new WeaponsDevelopment(attacker_wd));
            land_fragment_data.putSerializable(ARG_DEFENDER, new Army(defender));
            land_fragment_data.putSerializable(ARG_DEFENDER_WD, new WeaponsDevelopment(defender_wd));
        }
        else if(id.equals(getString(R.string.title_naval_battle))) {

        }

        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onSaveState");
        }
    }

    public static class TabsAdapter extends FragmentPagerAdapter
            implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
        private final FragmentActivity mContext;
        private final ActionBar mActionBar;
        private final ViewPager mViewPager;
        private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

        static final class TabInfo {
            private final Class<?> clss;
            private final Bundle args;

            TabInfo(Class<?> _class, Bundle _args) {
                clss = _class;
                args = _args;
            }
        }

        public TabsAdapter(FragmentActivity activity, ViewPager pager) {
            super(activity.getSupportFragmentManager());
            mContext = activity;
            mActionBar = activity.getActionBar();
            mViewPager = pager;
            mViewPager.setAdapter(this);
            mViewPager.setOnPageChangeListener(this);
        }
        public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) {
            TabInfo info = new TabInfo(clss, args);
            tab.setTag(info);
            tab.setTabListener(this);
            mTabs.add(info);
            mActionBar.addTab(tab);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }

        @Override
        public Fragment getItem(int position) {
            TabInfo info = mTabs.get(position);
            return Fragment.instantiate(mContext, info.clss.getName(), info.args);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            mActionBar.setSelectedNavigationItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            Object tag = tab.getTag();
            for (int i=0; i<mTabs.size(); i++) {
                if (mTabs.get(i) == tag) {
                    mViewPager.setCurrentItem(i);
                }
            }
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        }

        private String makeFragmentName(int viewId, int position) {
            return "android:switcher:" + viewId + ":" + position;
        }

        public Fragment getFragment(int viewId, int position) {
            FragmentManager fm = mContext.getSupportFragmentManager();
            String tag = makeFragmentName(R.id.viewpager, LAND_POS);
            return fm.findFragmentByTag(tag);
        }
    }

    public interface Constants {
        String LOG = "foss.devmapal.axis_allies_calc.axis_allies_calc.MainActivity";
    }
}
