package com.example.android.fragments.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import com.example.android.fragments.CustomGroupAdapter;
import com.example.android.fragments.GlobalVars;
import com.example.android.fragments.R;
import com.example.android.fragments.model.BCGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rick Slot
 */
public class GroupFragment extends ListFragment {

    List<BCGroup> groups = new ArrayList<BCGroup>();
    GlobalVars vars;
    CustomGroupAdapter customGroupAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vars = ((GlobalVars) getActivity().getApplicationContext());
        groups = vars.getGroups();

        final ArrayList<String> list = new ArrayList<String>();
        for (BCGroup group : groups) {
            list.add(group.getGroupName());
        }

        customGroupAdapter = new CustomGroupAdapter(getActivity(), R.layout.custom_group_row, list);

        StudentFragment studentFragment = new StudentFragment();
        getFragmentManager().beginTransaction().add(R.id.article_fragment, studentFragment).commit();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setSelector(R.drawable.custom_selector_group_background);
        getListView().setBackgroundColor(Color.rgb(79, 79, 79));

        getListView().setDivider(null);
        getListView().setDividerHeight(1);
        View headerView = View.inflate(getActivity(), R.layout.custom_group_header, null);
        getListView().addHeaderView(headerView, null, false);
        setListAdapter(customGroupAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (position != 0) {
            StudentFragment studentFragment = new StudentFragment();
            vars.setSelectedGroup(groups.get(position - 1));
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.article_fragment)).add(R.id.article_fragment, studentFragment).commit();
        }
    }
}
