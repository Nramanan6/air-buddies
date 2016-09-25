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
import com.amazonaws.AmazonClientException;
import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobile.user.IdentityManager;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.models.nosql.PersonDO;

public class ProfileActivity extends AppCompatActivity {

    public static final String KEY_PROFILE_USER = "profileUser";
    public static final String LOG_TAG = "ProfileActivity.java";

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

        IdentityManager id = AWSMobileClient.defaultMobileClient().getIdentityManager();
        Profile person = new Profile(id.getUserName(), 0, null, id.getUserImage(), null);
        insertData(person);


        populateFields();
    }

    public void insertData(Profile Person) {
        // Fetch the default configured DynamoDB ObjectMapper
        final DynamoDBMapper dynamoDBMapper = AWSMobileClient.defaultMobileClient().getDynamoDBMapper();
        final PersonDO note = new PersonDO(); // Initialize the Notes Object



//            HashMap<String, Profile> map = new HashMap<String, Profile>();
        IdentityManager id = AWSMobileClient.defaultMobileClient().getIdentityManager();
//            map.put(id, Person);

        // The userId has to be set to user's Cognito Identity Id for private / protected tables.
        // User's Cognito Identity Id can be fetched by using:
        // AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID()
        note.setUserId(AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID());
        note.setAge(0.0);
        note.setDescription("HI");
        note.setName(id.getUserName());
//            note.setImage(null);
        Log.d("TEST", "" +id.isUserSignedIn());
        Log.d("TEST1", "" +note.getDescription());
        AmazonClientException lastException = null;

        try {
            dynamoDBMapper.save(note);
            System.out.println("Success!");
        } catch (final AmazonClientException ex) {
            Log.e(LOG_TAG, "Failed saving item : " + ex.getMessage(), ex);
            lastException = ex;
        }

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
