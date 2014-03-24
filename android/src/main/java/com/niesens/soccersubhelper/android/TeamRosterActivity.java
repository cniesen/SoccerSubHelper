package com.niesens.soccersubhelper.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.niesens.R;
import com.niesens.soccersubhelper.engine.Player;
import java.util.ArrayList;
import java.util.List;

public class TeamRosterActivity extends Activity {
    ArrayAdapter<String> arrayAdapter;
    List<String> roster;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_roster);

        setTitle(getResources().getString(R.string.app_name) + " - " +
                getResources().getString(R.string.edit_roster_titleSuffix));

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                TextView textView = (TextView) findViewById(R.id.editText);
                RosterDAO.addPlayerToRoster(new Player(textView.getText().toString()));
                displayUpdatedTeamRoster();
                textView.setText("");

            }
        });


        displayUpdatedTeamRoster();
    }



    private void displayUpdatedTeamRoster() {
        ListView listView = (ListView) findViewById(R.id.listView);

        roster = new ArrayList<String>();
        for (Player player : RosterDAO.getPlayersFromRoster()) {
            roster.add(player.getName());
        }
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, roster);
        listView.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {

                final String selectedPlayer=roster.get(position);

                Context context = v.getContext();
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete " + selectedPlayer)
                        .setMessage("Are you sure you want to remove the player from the team?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RosterDAO.removePlayerFromRoster(new Player(selectedPlayer));
                                displayUpdatedTeamRoster();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();            }
        });
    }


}