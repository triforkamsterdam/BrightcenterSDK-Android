package nl.trifork.brightcenter.androidsdk.activities;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import nl.trifork.brightcenter.androidsdk.CustomStudentAdapter;
import nl.trifork.brightcenter.androidsdk.GlobalVars;
import nl.trifork.brightcenter.androidsdk.R;
import nl.trifork.brightcenter.androidsdk.model.BCGroup;
import nl.trifork.brightcenter.androidsdk.model.BCStudent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rick Slot
 */
public class StudentFragment extends ListFragment {
    ListView listview;
    List<BCGroup> groups = new ArrayList<BCGroup>();
    BCGroup selectedGroup = new BCGroup();
    CustomStudentAdapter arrayAdapter;
    GlobalVars vars;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> studentNames = new ArrayList<String>();
        vars = ((GlobalVars) getActivity().getApplicationContext());
        selectedGroup = vars.getSelectedGroup();
        if (selectedGroup != null) {
            for (BCStudent student : selectedGroup.getBCStudents()) {
                studentNames.add(student.getFirstName() + " " + student.getLastName());
            }
        }

        arrayAdapter = new CustomStudentAdapter(getActivity(), R.layout.custom_student_row, studentNames);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(Color.GRAY));
        getListView().setDividerHeight(1);
        view.setBackgroundColor(Color.WHITE);

        View headerView = View.inflate(getActivity(), R.layout.custom_student_header, null);
        getListView().addHeaderView(headerView, null, false);
        setListAdapter(arrayAdapter);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (position != 0) {
            vars.setSelectedStudent(selectedGroup.getBCStudents().get(position - 1));
            if (vars.getIntent() != null) {
                startActivity(vars.getIntent());
            } else {
                Context context = getActivity().getApplicationContext();
                CharSequence text = "I don't know which activity to load. Check the readme!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM, 0, 150);
                toast.show();
            }
        }
    }
}
