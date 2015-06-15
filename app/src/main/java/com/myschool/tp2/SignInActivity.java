package com.myschool.tp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class SignInActivity extends ActionBarActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void onClickSignIn(View view) {

        EditText editTextName = (EditText) findViewById(R.id.act_sign_in_name);
        String name = editTextName.getText().toString();

        EditText editTextEmail = (EditText) findViewById(R.id.act_sign_in_email);
        String email = editTextEmail.getText().toString();

        EditText editTextPassword = (EditText) findViewById(R.id.act_sign_in_password);
        String password = editTextPassword.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {

            Toast toast = Toast.makeText(this, R.string.from_empty, Toast.LENGTH_SHORT);
            toast.show();
        }
        else {

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(email, "{ password: " + password + ", name: " + name + "}");
            editor.putString("CURRENT_LOGIN", email);
            editor.apply();

            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);
            finish();
        }

    }
}
