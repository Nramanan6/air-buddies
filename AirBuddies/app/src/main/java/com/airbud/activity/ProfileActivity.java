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

        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateProfile();
            }
        });
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
        name.setText(profileUser.getName());
        pic.setImageBitmap(profileUser.getBm());

    }

    /**
     * Updates the Profile object associated with the user
     */
    private void updateProfile() {
        Profile updatedPro = new Profile(mName, mAge, null, profileUser.getBm(), mDescription);
        //store into AWS
    }


}
