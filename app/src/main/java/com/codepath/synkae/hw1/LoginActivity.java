package com.codepath.synkae.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        users = new ArrayList<User>();
        createUsers();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsername.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(), "Input field is empty!", Toast.LENGTH_SHORT).show();
                }
                else{
                    //check if username and password is correct
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    if (userExist(username, password)){
                        //goMainActivity(username);
                        Intent intent = MainActivity.mainActivityIntent(v.getContext(), username);
                        startActivity(intent);
                        finish();
                        Toast.makeText(v.getContext(), "Successfully signed in!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(v.getContext(), "Username or password is incorrect!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void goMainActivity(String username) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    /*
        Method to create some predefined users
        stored in a HashMap<Integer, String> - users
        password all defaulted to "password"
     */
    private void createUsers(){
        for (int i=1; i<=10; i++){
            users.add(new User(String.valueOf(i), "password"));
        }
    }

    private boolean userExist(String username, String password){
        for (User u : users){
            if (u.getUsername().equals(username) && u.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }


}