package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPassword;
    private Button singInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        singInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editTextName.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (user.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.error_field_empty, Toast.LENGTH_SHORT).show();
                } else {
                    launchNextScreen(user);
                }
            }
        });


    }

    private void initView() {
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        singInButton = findViewById(R.id.singInButton);

    }

    private void launchNextScreen(String userName) {
        Intent intent = MakeOrderActivity.newIntent(this, userName);
        startActivity(intent);
    }

}