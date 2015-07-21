package com.methodia.android.testautomation.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.methodia.android.testautomation.Database.Sport;
import com.methodia.android.testautomation.Database.Team;
import com.methodia.android.testautomation.R;
import com.methodia.android.testautomation.Util;

import java.util.List;

import timber.log.Timber;

public class ActiveAndroidActivity extends Activity {

    Sport rugby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_android);

        Util.toolsSetup(this, this);

        //Database operations
        deleteAllRecordsInDatabase();
        saveRecordsInDatabase();
        List<Team> teams = getAllTeams();
        Timber.d(teams.size() + " are all the teams available.");
        Team team = getRandomTeam();
        Timber.d("A random team is the " + team.toString());
        save100RecordsInDatabase();
        teams = getAllTeams();
        Timber.d(teams.size() + " are all the teams available.");
        team = getRandomTeam();
        Timber.d("A random team is the " + team.toString());
        teams = getAllTeamsBySportName(rugby);
        Timber.d(teams.size() + " are the teams playing Rugby.");
        //For saving complex type objects in the database e.g. dates (Date<->Long) we use Type serializers.
        //More info on https://github.com/pardom/ActiveAndroid/wiki/Type-serializers.
    }

    private void saveRecordsInDatabase() {
        rugby = new Sport();
        rugby.name = "Rugby";
        rugby.save();

        Team misfits = new Team();
        misfits.name = "Murphy's Misfits RC Sofia";
        misfits.sport = rugby;
        misfits.save();

        String baabaasName = "Barbarians RC";
        Team baabaas = new Team(baabaasName, rugby);
        baabaas.save();
    }

    private void save100RecordsInDatabase() {
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < 100; i++) {
                Sport sport = new Sport();
                sport.name = "Sport " + i;
                sport.save();

                Team team = new Team();
                team.name = "Team " + i;
                team.sport = sport;
                team.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    private void deleteAllRecordsInDatabase() {
        new Delete().from(Team.class).execute();
    }

    private void deleteRecordById(int id) {
        Team team = Team.load(Team.class, id);
        team.delete();

        //Or statically:
        //Team.delete(Team.class, id);

        //Or with query builder syntax
        //new Delete().from(Team.class).where("Id = ?", id).execute();
    }

    private Team getRandomTeam() {
        return new Select()
                .from(Team.class)
                .orderBy("RANDOM()")
                .executeSingle();
    }

    private List<Team> getAllTeams() {
        return new Select()
                .from(Team.class)
                .orderBy("Name ASC")
                .execute();
    }

    private List<Team> getAllTeamsBySportName(Sport sport) {
        return new Select()
                .from(Team.class)
                .where("Sport = ?", sport.getId())
                .orderBy("Name ASC")
                .execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_active_android, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
