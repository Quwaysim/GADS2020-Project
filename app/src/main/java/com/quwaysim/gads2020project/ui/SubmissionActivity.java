package com.quwaysim.gads2020project.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.quwaysim.gads2020project.R;

public class SubmissionActivity extends AppCompatActivity {
    EditText fNameEditText, lNameEditText, emailEditText, gitUrlEditText;
    ImageView back;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        fNameEditText = findViewById(R.id.editTextFirstName);
        lNameEditText = findViewById(R.id.editTextLastName);
        emailEditText = findViewById(R.id.editTextEmail);
        gitUrlEditText = findViewById(R.id.editTextGitUrl);
        submit = findViewById(R.id.submit_form);
        back = findViewById(R.id.appbar_back_btn);
        fNameEditText.requestFocus();

        back.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavUtils.navigateUpFromSameTask(SubmissionActivity.this);
            }
        }));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = fNameEditText.getText().toString();
                String lName = lNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String gitUrl = gitUrlEditText.getText().toString();

                if (!(fName.equals("") && lName.equals("") && email.equals("") && gitUrl.equals(""))) {
                    submitProject();
                    Toast.makeText(SubmissionActivity.this, "Working!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SubmissionActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //TODO finish up
    private void submitProject() {

    }
}