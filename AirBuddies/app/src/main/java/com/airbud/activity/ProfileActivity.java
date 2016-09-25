package com.airbud.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbud.R;
import com.airbud.entity.Profile;

public class ProfileActivity extends AppCompatActivity {

    public static final String KEY_PROFILE_USER = "profileUser";

    private Profile profileUser;

    private TextView userNameLabel;
    private EditText firstNameText;
    private EditText lastNameText;
    private EditText emailText;
    private CheckBox editCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userNameLabel = (TextView) findViewById(R.id.userNameText);
        firstNameText = (EditText) findViewById(R.id.firstNameText);
        lastNameText = (EditText) findViewById(R.id.lastNameText);
        emailText = (EditText) findViewById(R.id.emailText);
        editCheckBox = (CheckBox) findViewById(R.id.editCheckBox);
        initializeForm();
    }

    /**
     * Helper method for enable editable on an EditText
     *
     * @param e EditText view to enable
     */
    private void enableTextField(EditText e) {
        e.setFocusable(true);
        e.setClickable(true);
        e.setFocusableInTouchMode(true);
    }

    /**
     * Helper method for disabling editable on an EditText
     *
     * @param e EditText view to disable
     */
    private void disableTextField(EditText e) {
        e.setFocusable(false);
        e.setClickable(false);
        e.setFocusableInTouchMode(false);
    }

    /**
     * disables all elements in the profile form
     */
    private void disableForm() {
        disableTextField(firstNameText);
        disableTextField(lastNameText);
        disableTextField(emailText);
        cancelFocus();
    }

    /**
     * enables all elements in the profile form
     */
    private void enableForm() {
        enableTextField(firstNameText);
        enableTextField(lastNameText);
        enableTextField(emailText);
    }

    /**
     * Retrieves user parameter from passed bundle and uses it to initialize
     * form
     */
    private void initializeForm() {
        userNameLabel.setText(profileUser.getFirstName() + " Profile");
        editCheckBox.setOnCheckedChangeListener(new CompoundButton
            .OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean
                isChecked) {
                Log.v("ProfileActivity", "Edit checked: " + (isChecked ?
                    "True" : "False"));
                if (isChecked) {
                    enableForm();
                } else {
                    disableForm();
                }
            }
        });
        cancelFocus();
        populateFields();
        disableForm();
    }

    /**
     * Cancels focus from all form elements
     */
    private void cancelFocus() {
        final RelativeLayout formLayout = (RelativeLayout) findViewById(R.id.profileLayout);
        formLayout.requestFocus();
    }

    /**
     * Called when the Save button is clicked - verifies proper values are
     * entered
     * @return true if the profile was validated
     */
    private boolean validateProfile() {
        final String firstName = firstNameText.getText().toString();
        final String lastName = lastNameText.getText().toString();
        final String email = emailText.getText().toString();
        if ("".equals(firstName)) {
            firstNameText.setError("Enter a first name");
            return false;
        } else if ("".equals(lastName)) {
            lastNameText.setError("Enter a last name");
            return false;
        } else if ("".equals(email) || !email.contains("@")) {
            emailText.setError("Enter a valid email address");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Retrieves existing user profile information and uses it to populate
     * the fields
     */
    private void populateFields() {
        Profile userProfile = profileUser;
        firstNameText.setText(userProfile.getFirstName());
        lastNameText.setText(userProfile.getLastName());
        emailText.setText(userProfile.getEmail());
    }

    /**
     * Updates the Profile object associated with the user
     */
    private void updateProfile() {
        if (validateProfile()) {
            final String firstName = firstNameText.getText().toString();
            final String lastName = lastNameText.getText().toString();
            final String email = emailText.getText().toString();
            final Profile uProfile = profileUser;
            uProfile.setFirstName(firstName);
            uProfile.setLastName(lastName);
            uProfile.setEmail(email);
            finish();
        }
    }

    /**
     * Saves the profile entered
     * @param v the current view
     */
    public void saveProfile(View v) {
        updateProfile();
    }
}
