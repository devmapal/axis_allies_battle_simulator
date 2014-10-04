package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by devmapal on 10/2/14.
 */
public class NavalDefenderHitOrderFragment extends HitOrderFragment {

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
        array = getResources().getStringArray(R.array.naval_hit_order);
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

        ArrayList<String> defender_naval_hit_order = null;
        String d_naval_hit_order_str = null;
        try {
            d_naval_hit_order_str = sharedPref.getString("d_naval_hit_order", defaultJSON);
            Log.e(Constants.LOG, sharedPref.getAll().toString());
            JSONArray d_naval_hit_order_json = new JSONArray(d_naval_hit_order_str);
            list = new ArrayList<String>();
            for (int i = 0; i < d_naval_hit_order_json.length(); i++) {
                list.add(d_naval_hit_order_json.getString(i));
            }
        } catch (JSONException e) {
            if (BuildConfig.DEBUG) {
                Log.e(Constants.LOG,
                        String.format("Error loading defender naval hit order from shared preferences, was [%s]", d_naval_hit_order_str));
            }
        }
    }

    private void save_state_to_preference() {
        SharedPreferences sharedPref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        JSONArray d_naval_hit_order_json = new JSONArray(list);
        String d_naval_hit_order_str = d_naval_hit_order_json.toString();
        editor.putString("d_naval_hit_order", d_naval_hit_order_str);

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

    public interface Constants {
        String LOG = "foss.devmapal.axis_allies_calc.axis_allies_calc.NavalDefenderHitOrderFragment";
    }

    /**
     * Return list item layout resource passed to the ArrayAdapter.
     */
    @Override
    protected int getItemLayout() {
        /*if (removeMode == DragSortController.FLING_LEFT_REMOVE || removeMode == DragSortController.SLIDE_LEFT_REMOVE) {
            return R.layout.list_item_handle_right;
        } else */
        return R.layout.list_item_handle_right;
    }

    public void clear_fields() {
        array = getResources().getStringArray(R.array.naval_hit_order);
        list = new ArrayList<String>(Arrays.asList(array));
        setListAdapter();
    }
}
