package com.niesens.soccersubhelper.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.niesens.R;
import com.niesens.soccersubhelper.engine.Player;
import java.util.ArrayList;
import java.util.List;

public class SoccerSubHelper extends Activity {

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHelper.getInstance(this);
        setContentView(R.layout.main);

        ListView listView = (ListView) findViewById(R.id.listView);
        List<String> roster = new ArrayList<String>();
        for (Player player : RosterDAO.getPlayersFromRoster()) {
            roster.add(player.getName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, roster);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView listView = (ListView) findViewById(R.id.listView);
        List<String> roster = new ArrayList<String>();
        for (Player player : RosterDAO.getPlayersFromRoster()) {
            roster.add(player.getName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, roster);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(com.niesens.R.menu.main, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_team_roster:
                startActivity(new Intent(this, TeamRosterActivity.class));
                return true;
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
           default:
                return super.onOptionsItemSelected(item);
        }
    }
}

