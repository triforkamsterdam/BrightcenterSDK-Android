package nl.trifork.brightcenter.androidsdk;

import android.app.Application;
import android.content.Intent;
import nl.trifork.brightcenter.androidsdk.model.BCGroup;
import nl.trifork.brightcenter.androidsdk.model.BCStudent;
import nl.trifork.brightcenter.androidsdk.model.BCUser;


import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to save some global variables
 * @author Rick Slot
 */
public class GlobalVars extends Application {

    private String username = "";
    private String password = "";
    private List<BCGroup> groups = new ArrayList<BCGroup>();
    private BCGroup selectedGroup;
    private BCStudent selectedStudent;
    private BCUser loggedInUser;
    private Intent intent = null;

    public void resetVars(){
        username = "";
        password = "";
        groups = new ArrayList<BCGroup>();
        selectedGroup = null;
        selectedStudent = null;
        loggedInUser = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BCGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<BCGroup> groups) {
        this.groups = groups;
    }

    public BCGroup getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(BCGroup selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public BCStudent getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(BCStudent selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public BCUser getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(BCUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntentForStudentSelected(Intent intent) {
        this.intent = intent;
    }
}
