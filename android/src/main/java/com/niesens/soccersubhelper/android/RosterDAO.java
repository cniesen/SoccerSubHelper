package com.niesens.soccersubhelper.android;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;
import com.niesens.soccersubhelper.engine.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RosterDAO {
    private static final String TEAM_ROSTER_TABLE_NAME = "roster";

    public static List<Player> getPlayersFromRoster() {
        List<Player> players = new ArrayList<Player>();
        List<Map<String, String>> results = DatabaseHelper.performSelect(TEAM_ROSTER_TABLE_NAME,
                new ArrayList<String>(Arrays.asList(PlayerColumns.NAME)), new ContentValues());
        for (Map<String, String> row : results) {
            players.add(new Player(row.get(PlayerColumns.NAME)));
        }
        return players;
    }

    public static boolean addPlayerToRoster(Player player) {
        ContentValues values = new ContentValues();
        values.put(PlayerColumns.NAME, player.getName());

        return DatabaseHelper.performInsert(TEAM_ROSTER_TABLE_NAME, values);
    }

    public static boolean removePlayerFromRoster(Player player) {
        ContentValues values = new ContentValues();
        values.put(PlayerColumns.NAME, player.getName());

        int deleteCount = DatabaseHelper.performDelete(TEAM_ROSTER_TABLE_NAME, values);
        if (deleteCount > 0){
            return true;
        } else {
            return false;
        }
    }


    public static final class PlayerColumns implements BaseColumns {
         public static final String NAME = "name";
     }

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TEAM_ROSTER_TABLE_NAME + " ("
                + PlayerColumns._ID + " INTEGER PRIMARY KEY, "
                + PlayerColumns.NAME + " TEXT"
                + ");");

        db.execSQL("INSERT INTO " + TEAM_ROSTER_TABLE_NAME + " ("
                + PlayerColumns.NAME + ") values ('Sahra');");
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.TAG, "Upgrading " + TEAM_ROSTER_TABLE_NAME + " table");
        db.execSQL("DROP TABLE IF EXISTS " + TEAM_ROSTER_TABLE_NAME);
        onCreate(db);
    }

}