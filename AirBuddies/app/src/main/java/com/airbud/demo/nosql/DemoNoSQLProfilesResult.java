package com.airbud.demo.nosql;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amazonaws.AmazonClientException;
import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.models.nosql.ProfilesDO;

import java.util.Set;

public class DemoNoSQLProfilesResult implements DemoNoSQLResult {
    private static final int KEY_TEXT_COLOR = 0xFF333333;
    private final ProfilesDO result;

    DemoNoSQLProfilesResult(final ProfilesDO result) {
        this.result = result;
    }
    @Override
    public void updateItem() {
        final DynamoDBMapper mapper = AWSMobileClient.defaultMobileClient().getDynamoDBMapper();
        final String originalValue = result.getAge();
        result.setAge(DemoSampleDataGenerator.getRandomSampleString("Age"));
        try {
            mapper.save(result);
        } catch (final AmazonClientException ex) {
            // Restore original data if save fails, and re-throw.
            result.setAge(originalValue);
            throw ex;
        }
    }

    @Override
    public void deleteItem() {
        final DynamoDBMapper mapper = AWSMobileClient.defaultMobileClient().getDynamoDBMapper();
        mapper.delete(result);
    }

    private void setKeyTextViewStyle(final TextView textView) {
        textView.setTextColor(KEY_TEXT_COLOR);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(dp(5), dp(2), dp(5), 0);
        textView.setLayoutParams(layoutParams);
    }

    /**
     * @param dp number of design pixels.
     * @return number of pixels corresponding to the desired design pixels.
     */
    private int dp(int dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
    private void setValueTextViewStyle(final TextView textView) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(dp(15), 0, dp(15), dp(2));
        textView.setLayoutParams(layoutParams);
    }

    private void setKeyAndValueTextViewStyles(final TextView keyTextView, final TextView valueTextView) {
        setKeyTextViewStyle(keyTextView);
        setValueTextViewStyle(valueTextView);
    }

    private static String bytesToHexString(byte[] bytes) {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("%02X", bytes[0]));
        for(int index = 1; index < bytes.length; index++) {
            builder.append(String.format(" %02X", bytes[index]));
        }
        return builder.toString();
    }

    private static String byteSetsToHexStrings(Set<byte[]> bytesSet) {
        final StringBuilder builder = new StringBuilder();
        int index = 0;
        for (byte[] bytes : bytesSet) {
            builder.append(String.format("%d: ", ++index));
            builder.append(bytesToHexString(bytes));
            if (index < bytesSet.size()) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public View getView(final Context context, final View convertView, int position) {
        final LinearLayout layout;
        final TextView resultNumberTextView;
        final TextView userIdKeyTextView;
        final TextView userIdValueTextView;
        final TextView firstNameKeyTextView;
        final TextView firstNameValueTextView;
        final TextView ageKeyTextView;
        final TextView ageValueTextView;
        final TextView descriptionKeyTextView;
        final TextView descriptionValueTextView;
        final TextView lastNameKeyTextView;
        final TextView lastNameValueTextView;
        final TextView pictureKeyTextView;
        final TextView pictureValueTextView;
        if (convertView == null) {
            layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            resultNumberTextView = new TextView(context);
            resultNumberTextView.setGravity(Gravity.CENTER_HORIZONTAL);
            layout.addView(resultNumberTextView);


            userIdKeyTextView = new TextView(context);
            userIdValueTextView = new TextView(context);
            setKeyAndValueTextViewStyles(userIdKeyTextView, userIdValueTextView);
            layout.addView(userIdKeyTextView);
            layout.addView(userIdValueTextView);

            firstNameKeyTextView = new TextView(context);
            firstNameValueTextView = new TextView(context);
            setKeyAndValueTextViewStyles(firstNameKeyTextView, firstNameValueTextView);
            layout.addView(firstNameKeyTextView);
            layout.addView(firstNameValueTextView);

            ageKeyTextView = new TextView(context);
            ageValueTextView = new TextView(context);
            setKeyAndValueTextViewStyles(ageKeyTextView, ageValueTextView);
            layout.addView(ageKeyTextView);
            layout.addView(ageValueTextView);

            descriptionKeyTextView = new TextView(context);
            descriptionValueTextView = new TextView(context);
            setKeyAndValueTextViewStyles(descriptionKeyTextView, descriptionValueTextView);
            layout.addView(descriptionKeyTextView);
            layout.addView(descriptionValueTextView);

            lastNameKeyTextView = new TextView(context);
            lastNameValueTextView = new TextView(context);
            setKeyAndValueTextViewStyles(lastNameKeyTextView, lastNameValueTextView);
            layout.addView(lastNameKeyTextView);
            layout.addView(lastNameValueTextView);

            pictureKeyTextView = new TextView(context);
            pictureValueTextView = new TextView(context);
            setKeyAndValueTextViewStyles(pictureKeyTextView, pictureValueTextView);
            layout.addView(pictureKeyTextView);
            layout.addView(pictureValueTextView);
        } else {
            layout = (LinearLayout) convertView;
            resultNumberTextView = (TextView) layout.getChildAt(0);

            userIdKeyTextView = (TextView) layout.getChildAt(1);
            userIdValueTextView = (TextView) layout.getChildAt(2);

            firstNameKeyTextView = (TextView) layout.getChildAt(3);
            firstNameValueTextView = (TextView) layout.getChildAt(4);

            ageKeyTextView = (TextView) layout.getChildAt(5);
            ageValueTextView = (TextView) layout.getChildAt(6);

            descriptionKeyTextView = (TextView) layout.getChildAt(7);
            descriptionValueTextView = (TextView) layout.getChildAt(8);

            lastNameKeyTextView = (TextView) layout.getChildAt(9);
            lastNameValueTextView = (TextView) layout.getChildAt(10);

            pictureKeyTextView = (TextView) layout.getChildAt(11);
            pictureValueTextView = (TextView) layout.getChildAt(12);
        }

        resultNumberTextView.setText(String.format("#%d", + position+1));
        userIdKeyTextView.setText("userId");
        userIdValueTextView.setText(result.getUserId());
        firstNameKeyTextView.setText("First Name");
        firstNameValueTextView.setText(result.getFirstName());
        ageKeyTextView.setText("Age");
        ageValueTextView.setText(result.getAge());
        descriptionKeyTextView.setText("Description");
        descriptionValueTextView.setText(result.getDescription());
        lastNameKeyTextView.setText("Last Name");
        lastNameValueTextView.setText(result.getLastName());
        pictureKeyTextView.setText("Picture");
        pictureValueTextView.setText(result.getPicture());
        return layout;
    }
}
