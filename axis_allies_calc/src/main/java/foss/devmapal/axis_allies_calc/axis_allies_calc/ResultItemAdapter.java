package foss.devmapal.axis_allies_calc.axis_allies_calc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by devmapal on 4/11/14.
 */
public class ResultItemAdapter extends ArrayAdapter<Tuple<String, String>> {
    private final Context context;
    private final Tuple<String, String>[] objects;

    public ResultItemAdapter(Context context, Tuple<String, String>[] objects) {
        super(context, R.layout.result_item, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.result_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.var_name);
        textView.setText(objects[position].x);
        textView = (TextView) rowView.findViewById(R.id.value);
        textView.setText(objects[position].y);

        return rowView;
    }
}
