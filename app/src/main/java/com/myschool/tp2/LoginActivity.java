package com.myschool.tp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends ActionBarActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void login(View view) {

        EditText editTextEmail = (EditText) findViewById(R.id.act_login_email);
        String email = editTextEmail.getText().toString();

        EditText editTextPassword = (EditText) findViewById(R.id.act_login_password);
        String password = editTextPassword.getText().toString();

        if (!email.isEmpty() || !password.isEmpty()) {

            String json = preferences.getString(email, "");
            String user_password;

            JSONObject user_data;
            try {
                user_data = new JSONObject(json);
                user_password = user_data.getString("password");
            } catch (JSONException e) {
                user_password = "";
            }

            if (password.equals(user_password)) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("CURRENT_LOGIN", email);
                editor.apply();

                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainIntent);
                finish();
            }
            else {
                Toast toast = Toast.makeText(this, R.string.act_login_wrong, Toast.LENGTH_SHORT);
                toast.show();
            }

        }
        else {
            Toast toast = Toast.makeText(this, R.string.from_empty, Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
