package com.myschool.tp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class MyAppActivity extends ActionBarActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String user_email = preferences.getString("CURRENT_LOGIN", "");
        String json = preferences.getString(user_email, "");
        String name;

        JSONObject user_data;
        try {
            user_data = new JSONObject(json);
            name = user_data.getString("name");
        } catch (JSONException e) {
            name = user_email;
        }

        TextView textView = new TextView(this);
        textView.setTextSize(30);
        String welcome = getResources().getString(R.string.act_myapp_welcome);
        textView.setText(welcome + " " + name);

        setContentView(textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_my_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_logout) {

            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("CURRENT_LOGIN");
            editor.apply();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
