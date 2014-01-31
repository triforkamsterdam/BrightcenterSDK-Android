package nl.trifork.brightcenter.androidsdk.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import nl.trifork.brightcenter.androidsdk.GlobalVars;
import nl.trifork.brightcenter.androidsdk.R;
import nl.trifork.brightcenter.androidsdk.model.BCGroup;
import nl.trifork.brightcenter.androidsdk.model.BCUser;
import nl.trifork.brightcenter.androidsdk.rest.BCConnect;

import java.util.List;

/**
 * @author Rick Slot
 */
public class LoginActivity extends SherlockActivity {

    EditText etPassword;
    EditText etUsername;
    Button bLogin;
    BCConnect connector = new BCConnect();
    ProgressBar pbLogin;
    GlobalVars vars;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vars = ((GlobalVars) getApplicationContext());

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.titlebar);
        ImageView ivAvatar = (ImageView) findViewById(R.id.ivAvatar);
        ivAvatar.setVisibility(View.GONE);
        setContentView(R.layout.login_view);


        bLogin = (Button) findViewById(R.id.bLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        pbLogin = (ProgressBar) findViewById(R.id.pbLogin);

        etUsername.clearFocus();
        etPassword.clearFocus();


        pbLogin.setVisibility(View.GONE);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                bLogin.setEnabled(false);
                new GetUserTask(username, password).execute();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        vars.resetVars();
    }

    public void spinning(boolean shouldSpin) {
        if (shouldSpin) {
            pbLogin.setVisibility(View.VISIBLE);
        } else {
            pbLogin.setVisibility(View.GONE);
        }

    }

    public void loadGroupSelectView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private class GetUserTask extends AsyncTask<String, String, BCUser> {

        String username;
        String password;

        public GetUserTask(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinning(true);

        }

        @Override
        protected BCUser doInBackground(String... strings) {
            BCConnect connector = new BCConnect();
            BCUser user = null;
            try {
                user = connector.getUser(username, password);
                if (user.getId() != null) {
                    vars.setLoggedInUser(user);
                    List<BCGroup> groups = connector.getGroupsOfTeacher(username, password);
                    GlobalVars vars = ((GlobalVars) getApplicationContext());
                    vars.setGroups(groups);
                    vars.setUsername(username);
                    vars.setPassword(password);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return user;
        }

        @Override
        protected void onPostExecute(BCUser user) {
            spinning(false);
            bLogin.setEnabled(true);
            if (user != null) {
                loadGroupSelectView();
            } else {
                Context context = getApplicationContext();
                CharSequence text = getResources().getText(R.string.login_toast_error);
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM, 0, 150);
                toast.show();
            }
        }
    }
}