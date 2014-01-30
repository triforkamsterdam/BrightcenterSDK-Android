package nl.trifork.brightcenter.androidsdk;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author Rick Slot
 */
public class CustomStudentAdapter extends ArrayAdapter<String> {

    Context context;
    int resource;
    List<String> objects = null;

    public CustomStudentAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);


        }
        String studentName = objects.get(position);
        TextView tvGroupId = (TextView) row.findViewById(R.id.tvStudenName);
        tvGroupId.setText(studentName);

        return row;
    }
}
