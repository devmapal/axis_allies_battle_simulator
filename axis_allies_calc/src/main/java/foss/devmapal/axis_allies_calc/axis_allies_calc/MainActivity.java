package foss.devmapal.axis_allies_calc.axis_allies_calc;

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
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


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
        int pos = LAND_POS;
        if(savedInstanceState != null) {
            restore_state_from_saved_instance_state(savedInstanceState);
        } else {
            pos = restore_state_from_preference();
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
        else {
            bar.setSelectedNavigationItem(pos);
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

    private int restore_state_from_preference() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int defaultValue = 0;
        boolean defaultBool = false;
        String defaultJSON = "[]";

        // Restore Land BattleSimulation input
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

        // Restore Naval BattleSimulation input
        attacker = new Army(
                defaultValue,
                defaultValue,
                defaultValue,
                sharedPref.getInt("a_naval_" + Fighter.name, defaultValue),
                sharedPref.getInt("a_naval_" + Bomber.name, defaultValue),
                sharedPref.getInt("a_naval_" + Battleship.name, defaultValue),
                sharedPref.getInt("a_naval_" + Destroyer.name, defaultValue),
                sharedPref.getInt("a_naval_" + Aircraftcarrier.name, defaultValue),
                sharedPref.getInt("a_naval_" + Transport.name, defaultValue),
                sharedPref.getInt("a_naval_" + Submarine.name, defaultValue),
                sharedPref.getInt("a_naval_" + AntiaircraftGun.name, defaultValue)
        );

        attacker_wd = new WeaponsDevelopment(
                sharedPref.getBoolean("a_naval_" + getString(R.string.jet_fighters), defaultBool),
                sharedPref.getBoolean("a_naval_" + getString(R.string.super_submarines), defaultBool),
                defaultBool,
                sharedPref.getBoolean("a_naval_" + getString(R.string.heavy_bombers), defaultBool)
        );

        defender = new Army(
                defaultValue,
                defaultValue,
                defaultValue,
                sharedPref.getInt("d_naval_" + Fighter.name, defaultValue),
                sharedPref.getInt("d_naval_" + Bomber.name, defaultValue),
                sharedPref.getInt("d_naval_" + Battleship.name, defaultValue),
                sharedPref.getInt("d_naval_" + Destroyer.name, defaultValue),
                sharedPref.getInt("d_naval_" + Aircraftcarrier.name, defaultValue),
                sharedPref.getInt("d_naval_" + Transport.name, defaultValue),
                sharedPref.getInt("d_naval_" + Submarine.name, defaultValue),
                sharedPref.getInt("d_naval_" + AntiaircraftGun.name, defaultValue)
        );

        defender_wd = new WeaponsDevelopment(
                sharedPref.getBoolean("d_naval_" + getString(R.string.jet_fighters), defaultBool),
                sharedPref.getBoolean("d_naval_" + getString(R.string.super_submarines), defaultBool),
                defaultBool,
                sharedPref.getBoolean("d_naval_" + getString(R.string.heavy_bombers), defaultBool)
        );

        naval_fragment_data.putSerializable(ARG_ATTACKER, attacker);
        naval_fragment_data.putSerializable(ARG_ATTACKER_WD, attacker_wd);
        naval_fragment_data.putSerializable(ARG_DEFENDER, defender);
        naval_fragment_data.putSerializable(ARG_DEFENDER_WD, defender_wd);

        return sharedPref.getInt("tab", LAND_POS);
    }

    private void save_state_to_preference() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        // Store land battle data
        Army attacker = (Army) land_fragment_data.getSerializable(ARG_ATTACKER);
        editor.putInt("a_land_" + Infantry.name, attacker.get_infantry());
        editor.putInt("a_land_" + Artillery.name, attacker.get_artillery());
        editor.putInt("a_land_" + Tank.name, attacker.get_tanks());
        editor.putInt("a_land_" + Fighter.name, attacker.get_fighters());
        editor.putInt("a_land_" + Bomber.name, attacker.get_bombers());
        editor.putInt("a_land_" + Battleship.name, attacker.get_battleships());
        editor.putInt("a_land_" + Destroyer.name, attacker.get_destroyers());
        editor.putInt("a_land_" + AntiaircraftGun.name, attacker.get_antiaircraftguns());

        WeaponsDevelopment attacker_wd =
                (WeaponsDevelopment) land_fragment_data.getSerializable(ARG_ATTACKER_WD);
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

        WeaponsDevelopment defender_wd =
                (WeaponsDevelopment) land_fragment_data.getSerializable(ARG_DEFENDER_WD);
        editor.putBoolean("d_land_" + getString(R.string.jet_fighters), defender_wd.jet_fighters);
        editor.putBoolean("d_land_" + getString(R.string.combined_bombardment), defender_wd.combined_bombardment);
        editor.putBoolean("d_land_" + getString(R.string.heavy_bombers), defender_wd.heavy_bombers);

        // Store naval battle data
        attacker = (Army) naval_fragment_data.getSerializable(ARG_ATTACKER);
        editor.putInt("a_naval_" + Fighter.name, attacker.get_fighters());
        editor.putInt("a_naval_" + Bomber.name, attacker.get_bombers());
        editor.putInt("a_naval_" + Battleship.name, attacker.get_battleships());
        editor.putInt("a_naval_" + Destroyer.name, attacker.get_destroyers());
        editor.putInt("a_naval_" + Aircraftcarrier.name, attacker.get_aircraftcarriers());
        editor.putInt("a_naval_" + Transport.name, attacker.get_transports());
        editor.putInt("a_naval_" + Submarine.name, attacker.get_submarines());
        editor.putInt("a_naval_" + AntiaircraftGun.name, attacker.get_antiaircraftguns());

        attacker_wd = (WeaponsDevelopment) naval_fragment_data.getSerializable(ARG_ATTACKER_WD);
        editor.putBoolean("a_naval_" + getString(R.string.jet_fighters), attacker_wd.jet_fighters);
        editor.putBoolean("a_naval_" + getString(R.string.super_submarines), attacker_wd.super_submarines);
        editor.putBoolean("a_naval_" + getString(R.string.heavy_bombers), attacker_wd.heavy_bombers);

        defender = (Army) naval_fragment_data.getSerializable(ARG_DEFENDER);
        editor.putInt("d_naval_" + Fighter.name, defender.get_fighters());
        editor.putInt("d_naval_" + Bomber.name, defender.get_bombers());
        editor.putInt("d_naval_" + Battleship.name, defender.get_battleships());
        editor.putInt("d_naval_" + Destroyer.name, defender.get_destroyers());
        editor.putInt("d_naval_" + Aircraftcarrier.name, defender.get_aircraftcarriers());
        editor.putInt("d_naval_" + Transport.name, defender.get_transports());
        editor.putInt("d_naval_" + Submarine.name, defender.get_submarines());
        editor.putInt("d_naval_" + AntiaircraftGun.name, defender.get_antiaircraftguns());

        defender_wd = (WeaponsDevelopment) naval_fragment_data.getSerializable(ARG_DEFENDER_WD);
        editor.putBoolean("d_naval_" + getString(R.string.jet_fighters), defender_wd.jet_fighters);
        editor.putBoolean("d_naval_" + getString(R.string.super_submarines), defender_wd.super_submarines);
        editor.putBoolean("d_naval_" + getString(R.string.heavy_bombers), defender_wd.heavy_bombers);

        editor.putInt("tab", getActionBar().getSelectedNavigationIndex());

        editor.commit();
    }

    public void runAction() {
        Intent i;
        switch(getActionBar().getSelectedNavigationIndex()) {
            case LAND_POS:
                LandFragment land_fragment = (LandFragment) mTabsAdapter.
                                                              getFragment(R.id.viewpager, LAND_POS);
                land_fragment.getFields(land_fragment.getView());
                i = new Intent(this, ResultActivity.class);
                i.putExtra("attacker", land_fragment.getAttacker());
                i.putExtra("attacker_wd", land_fragment.getAttacker_wd());
                i.putExtra("defender", land_fragment.getDefender());
                i.putExtra("defender_wd", land_fragment.getDefender_wd());
                i.putExtra("land_battle", true);

                LandAttackerHitOrderFragment lahof =
                        (LandAttackerHitOrderFragment) getFragmentManager()
                                .findFragmentById(R.id.attacker_land_hit_order);
                i.putIntegerArrayListExtra("attacker_hit_order",
                           MainActivity.get_hit_order(lahof.get_hit_order()));

                LandDefenderHitOrderFragment ldhof =
                        (LandDefenderHitOrderFragment) getFragmentManager()
                                .findFragmentById(R.id.defender_land_hit_order);
                i.putIntegerArrayListExtra("defender_hit_order",
                        MainActivity.get_hit_order(ldhof.get_hit_order()));

                startActivity(i);
                break;
            case NAVAL_POS:
                NavalFragment naval_fragment = (NavalFragment) mTabsAdapter.
                        getFragment(R.id.viewpager, NAVAL_POS);
                naval_fragment.getFields(naval_fragment.getView());
                i = new Intent(this, ResultActivity.class);
                i.putExtra("attacker", naval_fragment.getAttacker());
                i.putExtra("attacker_wd", naval_fragment.getAttacker_wd());
                i.putExtra("defender", naval_fragment.getDefender());
                i.putExtra("defender_wd", naval_fragment.getDefender_wd());
                i.putExtra("land_battle", false);
                startActivity(i);
                break;
        }
    }

    public static ArrayList<Integer> get_hit_order(List<String> hit_order_str) {
        ArrayList<Integer> hit_order = new ArrayList<>();
        for(String hit_type: hit_order_str) {
            switch(hit_type) {
                case "Infantry":
                    hit_order.add(Infantry.id);
                    break;
                case "Artillery":
                    hit_order.add(Artillery.id);
                    break;
                case "Tank":
                    hit_order.add(Tank.id);
                    break;
                case "Fighter":
                    hit_order.add(Fighter.id);
                    break;
                case "Bomber":
                    hit_order.add(Bomber.id);
            }
        }

        return hit_order;
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
                        LandAttackerHitOrderFragment lahof =
                                (LandAttackerHitOrderFragment) getFragmentManager()
                                        .findFragmentById(R.id.attacker_land_hit_order);
                        lahof.clear_fields();
                        LandDefenderHitOrderFragment ldhof =
                                (LandDefenderHitOrderFragment) getFragmentManager()
                                        .findFragmentById(R.id.defender_land_hit_order);
                        ldhof.clear_fields();
                        break;
                    case NAVAL_POS:
                        NavalFragment naval_fragment = (NavalFragment) mTabsAdapter.
                                                        getFragment(R.id.viewpager, NAVAL_POS);
                        naval_fragment.clear_fields();
                }
                return true;
            case R.id.action_run:
                runAction();
                return true;
            case R.id.action_swap:
                switch(getActionBar().getSelectedNavigationIndex()) {
                    case LAND_POS:
                        LandFragment land_fragment = (LandFragment) mTabsAdapter.
                                getFragment(R.id.viewpager, LAND_POS);
                        land_fragment.swap();
                        break;
                    case NAVAL_POS:
                        NavalFragment naval_fragment = (NavalFragment) mTabsAdapter.
                                getFragment(R.id.viewpager, NAVAL_POS);
                        naval_fragment.swap();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveState(String id,
                            Army attacker,
                            WeaponsDevelopment attacker_wd,
                            Army defender,
                            WeaponsDevelopment defender_wd) {
        if(id.equals(getString(R.string.title_land_battle))) {
            land_fragment_data.putSerializable(ARG_ATTACKER, new Army(attacker));
            land_fragment_data.putSerializable(ARG_ATTACKER_WD, new WeaponsDevelopment(attacker_wd));
            land_fragment_data.putSerializable(ARG_DEFENDER, new Army(defender));
            land_fragment_data.putSerializable(ARG_DEFENDER_WD, new WeaponsDevelopment(defender_wd));
        }
        else if(id.equals(getString(R.string.title_naval_battle))) {
            naval_fragment_data.putSerializable(ARG_ATTACKER, new Army(attacker));
            naval_fragment_data.putSerializable(ARG_ATTACKER_WD, new WeaponsDevelopment(attacker_wd));
            naval_fragment_data.putSerializable(ARG_DEFENDER, new Army(defender));
            naval_fragment_data.putSerializable(ARG_DEFENDER_WD, new WeaponsDevelopment(defender_wd));
        }

        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onSaveState");
        }
    }

    public void onCheckboxClicked(View view) {
        LandFragment land_fragment = (LandFragment) mTabsAdapter.
            getFragment(R.id.viewpager, LAND_POS);
        land_fragment.onCheckboxClicked(view);
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
            final InputMethodManager imm = (InputMethodManager) mContext.getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            if(imm != null) {
                Fragment frag = getFragment(R.id.viewpager, position);
                if(frag != null) {
                    View view = frag.getView();
                    if(view != null)
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
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
            String tag = makeFragmentName(viewId, position);
            return fm.findFragmentByTag(tag);
        }
    }

    public interface Constants {
        String LOG = "foss.devmapal.axis_allies_calc.axis_allies_calc.MainActivity";
    }
}
