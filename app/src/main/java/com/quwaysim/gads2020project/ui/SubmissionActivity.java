package com.quwaysim.gads2020project.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.quwaysim.gads2020project.R;
import com.quwaysim.gads2020project.services.DataService;
import com.quwaysim.gads2020project.services.SubmissionServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        back.setOnClickListener((view -> NavUtils.navigateUpFromSameTask(SubmissionActivity.this)));

        submit.setOnClickListener(view -> {
            String fName = fNameEditText.getText().toString();
            String lName = lNameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String gitUrl = gitUrlEditText.getText().toString();

            if (!(fName.equals("") && lName.equals("") && email.equals("") && gitUrl.equals(""))) {
                submitProject(fName, lName, email, gitUrl);
            } else {
                Toast.makeText(SubmissionActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void submitProject(String fN, String lN, String email, String url) {
        new SubmissionConfirmationDialog().show(getSupportFragmentManager(), "Submission Confirmation");
        DataService task = SubmissionServiceBuilder.buildService(DataService.class);
        Call<Void> submitProject = task.submitForm(fN, lN, email, url);
        submitProject.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("MyTAG", "onResponse: Success" + response.code());
                if (response.isSuccessful()) {
                    new SubmissionStatusDialog(R.layout.dialog_successful);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                new SubmissionStatusDialog(R.layout.dialog_successful);
            }
        });

    }
}