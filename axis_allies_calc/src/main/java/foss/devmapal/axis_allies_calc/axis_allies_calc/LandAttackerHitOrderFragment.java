package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by devmapal on 10/2/14.
 */
public class LandAttackerHitOrderFragment extends HitOrderFragment {

    private static final String ARG_LIST = "list";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            restore_state_from_saved_instance_state(savedInstanceState);
        }
        else {
            restore_state_from_preference();
        }

        setListAdapter();
    }

    @Override
    public void setListAdapter() {
        array = getResources().getStringArray(R.array.land_attacker_hit_order);
        if(list == null) {
            list = new ArrayList<String>(Arrays.asList(array));
        }
        else if(list.isEmpty()) {
            list = new ArrayList<String>(Arrays.asList(array));
        }

        adapter = new ArrayAdapter<String>(getActivity(), getItemLayout(), R.id.text, list);
        setListAdapter(adapter);
    }

    private void restore_state_from_saved_instance_state(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "restore_state_from_saved_instance_state");
        }
        list = (ArrayList<String>) savedInstanceState.getSerializable(ARG_LIST);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable(ARG_LIST, list);

        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onSaveInstanceState");
        }

        super.onSaveInstanceState(savedInstanceState);
    }

    private void restore_state_from_preference() {
        SharedPreferences sharedPref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        String defaultJSON = "[]";

        ArrayList<String> attacker_land_hit_order = null;
        String a_land_hit_order_str = null;
        try {
            a_land_hit_order_str = sharedPref.getString("a_land_hit_order", defaultJSON);
            Log.e(Constants.LOG, sharedPref.getAll().toString());
            JSONArray a_land_hit_order_json = new JSONArray(a_land_hit_order_str);
            list = new ArrayList<String>();
            for (int i = 0; i < a_land_hit_order_json.length(); i++) {
                list.add(a_land_hit_order_json.getString(i));
            }
        } catch (JSONException e) {
            if (BuildConfig.DEBUG) {
                Log.e(Constants.LOG,
                        String.format("Error loading attacker land hit order from shared preferences, was [%s]", a_land_hit_order_str));
            }
        }
    }

    private void save_state_to_preference() {
        SharedPreferences sharedPref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        JSONArray a_land_hit_order_json = new JSONArray(list);
        String a_land_hit_order_str = a_land_hit_order_json.toString();
        editor.putString("a_land_hit_order", a_land_hit_order_str);

        editor.commit();
        Log.e(Constants.LOG, sharedPref.getAll().toString());
    }

    @Override
    public void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) {
            Log.e(Constants.LOG, "onStop");
        }
        save_state_to_preference();
    }

    public void clear_fields() {
        array = getResources().getStringArray(R.array.land_attacker_hit_order);
        list = new ArrayList<String>(Arrays.asList(array));
        setListAdapter();
    }

    public interface Constants {
        String LOG = "foss.devmapal.axis_allies_calc.axis_allies_calc.LandAttackerHitOrderFragment";
    }
}
