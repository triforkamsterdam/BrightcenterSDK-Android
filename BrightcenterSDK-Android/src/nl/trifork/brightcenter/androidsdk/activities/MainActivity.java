
package nl.trifork.brightcenter.androidsdk.activities;

import android.os.Bundle;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import nl.trifork.brightcenter.androidsdk.GlobalVars;
import nl.trifork.brightcenter.androidsdk.R;

/**
 * @author Rick Slot
 */
public class MainActivity extends SherlockFragmentActivity {

    GlobalVars vars;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vars = ((GlobalVars) getApplicationContext());


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.titlebar);
        TextView tvUsernameTitleBar = (TextView) findViewById(R.id.tvUsernameTitlebar);
        tvUsernameTitleBar.setText(vars.getUsername());

        setContentView(R.layout.main_view);

        if (findViewById(R.id.group_fragment) != null) {
            if (savedInstanceState != null) {
                return;
            }

            GroupFragment groupFragment = new GroupFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.group_fragment, groupFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        vars.resetVars();
        finish();
        return true;
    }
}