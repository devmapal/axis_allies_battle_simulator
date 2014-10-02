package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by devmapal on 10/2/14.
 */
public class LandDefenderHitOrderFragment extends HitOrderFragment {

    @Override
    public void setListAdapter() {
        array = getResources().getStringArray(R.array.land_defender_hit_order);
        list = new ArrayList<String>(Arrays.asList(array));

        adapter = new ArrayAdapter<String>(getActivity(), getItemLayout(), R.id.text, list);
        setListAdapter(adapter);
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
}
