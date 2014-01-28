package com.example.android.fragments.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rick Slot
 */
public class BCGroup {
    String groupId;
    String groupName;
    List<BCStudent> BCStudents = new ArrayList<BCStudent>();

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<BCStudent> getBCStudents() {
        return BCStudents;
    }

    public void setBCStudents(List<BCStudent> BCStudents) {
        this.BCStudents = BCStudents;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
