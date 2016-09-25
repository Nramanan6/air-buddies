package com.airbud.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbud.R;
import com.airbud.entity.Profile;

public class ProfileActivity extends AppCompatActivity {

    public static final String KEY_PROFILE_USER = "profileUser";

    private Profile profileUser;

    private ImageView pic;
    private EditText name;
    private EditText age;
    private EditText description;
    Button submitButton;

    private String mName;
    private String mDescription;
    private int mAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = (EditText) findViewById(R.id.profileName);
        age = (EditText) findViewById(R.id.profileAge);
        description = (EditText) findViewById(R.id.profileDescription);
        pic = (ImageView) findViewById(R.id.propic);

<<<<<<< HEAD
        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
=======
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
>>>>>>> 37ea29e5220e7f449b7fb9e2b3511a358f01de6b
            @Override
            public void onClick(View v) {
                validateProfile();
            }
        });
<<<<<<< HEAD
=======
        cancelFocus();
>>>>>>> 37ea29e5220e7f449b7fb9e2b3511a358f01de6b
        populateFields();
    }

    /**
     * Called when the Save button is clicked - verifies proper values are
     * entered
     * @return true if the profile was validated
     */
    private boolean validateProfile() {
        name.setError(null);
        age.setError(null);
        description.setError(null);

        boolean cancel = false;
        View focusView = null;

        mName = name.getText().toString();
        mAge = Integer.parseInt(age.getText().toString());
        mDescription = description.getText().toString();

        if (mAge > 0) {
            age.setError("Invalid Age");
            focusView = age;
            cancel = true;
        }

        if (TextUtils.isEmpty(mDescription)) {
            description.setError(getString(R.string.error_field_required));
            focusView = description;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            return cancel;
        } else {
            updateProfile();
            return true;
        }

    }

    /**
     * Retrieves existing user profile information and uses it to populate
     * the fields
     */
    private void populateFields() {
<<<<<<< HEAD
        name.setText(profileUser.getName());
        pic.setImageBitmap(profileUser.getBm());

=======
        Profile userProfile = profileUser;
        firstNameText.setText(userProfile.getFirstName());
        lastNameText.setText(userProfile.getLastName());
        emailText.setText(userProfile.getEmail());
>>>>>>> 37ea29e5220e7f449b7fb9e2b3511a358f01de6b
    }

    /**
     * Updates the Profile object associated with the user
     */
    private void updateProfile() {
<<<<<<< HEAD
        Profile updatedPro = new Profile(mName, mAge, null, profileUser.getBm(), mDescription);
        //store into AWS
=======
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
>>>>>>> 37ea29e5220e7f449b7fb9e2b3511a358f01de6b
    }


}
