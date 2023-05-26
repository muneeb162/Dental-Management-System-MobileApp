package com.csharpui.myloginform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity1 extends AppCompatActivity {

    EditText lemail,lpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        lemail = findViewById(R.id.loginemailtext);
        lpass = findViewById(R.id.loginpasstext);

        Button button = findViewById(R.id.loginbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isallfieldschecked = validate();
                if (isallfieldschecked) {
                    Toast.makeText(LoginActivity1.this,"Login Successful",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validate(){
        if (lemail.length() == 0) {
            lemail.setError("This field is required");
            return false;
        }

        if (lpass.length() == 0) {
            lpass.setError("This field is required");
            return false;
        }
        // after all validation return true.
        return true;
    }
}