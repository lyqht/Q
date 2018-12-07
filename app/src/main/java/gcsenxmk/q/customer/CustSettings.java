package gcsenxmk.q.customer;


import android.content.Intent;
import android.content.Context;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import gcsenxmk.q.R;

public class CustSettings extends Fragment {

    private Button editAccountButton;
    private Button signOutButton;
    private Button saveButton;

    private TextView oldEmail;
    private EditText newEmail;

    private TextView displayPassword;
    private EditText oldPassword;
    private EditText newPassword;

    private TextView Name;
    private SwitchCompat NotificationEnabled;

    public CustSettings() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.cust_settings, container, false);
        saveButton = v.findViewById(R.id.cust_profile_SaveButton);
        editAccountButton = v.findViewById(R.id.cust_profile_editAccountButton);
        signOutButton = v.findViewById(R.id.cust_profile_signOutButton);
        displayPassword = v.findViewById(R.id.cust_profile_password_textview);
        oldPassword = v.findViewById(R.id.cust_profile_password_userOld);
        newPassword = v.findViewById(R.id.cust_profile_password_userNew);
        oldEmail = v.findViewById(R.id.cust_profile_email_textView);
        newEmail = v.findViewById(R.id.cust_profile_email_editText);
        Name = v.findViewById(R.id.cust_profile_name);

        NotificationEnabled = v.findViewById(R.id.cust_profile_notifications_switch);
        NotificationEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) Toast.makeText(getContext(), "Notifications Enabled",Toast.LENGTH_SHORT).show();
            }
        });

        retrieveDetails();

        // Setting widgets to be invisible at first, until change account button is clicked
        newPassword.setVisibility(View.GONE);
        oldPassword.setVisibility(View.GONE);
        newEmail.setVisibility(View.GONE);
        saveButton.setVisibility(View.GONE);

        editAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // UI Aspects of clicking editAccount Button
                changeVisibility();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Firebase function to check if old password input by user matches database.
                // TODO: If yes then update. Refer to updateDetails()
                updateDetails();
            }
        });

        //TODO: Signout Activity
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return v;
    }


    //TODO: Firebase Function - Retrieve Details
    //TODO: Set text to each of the fields after retrieving data.
    void retrieveDetails() {

    }

    //TODO : add on to this function - Save and override data in Firebase and local data
    void updateDetails() {
        oldEmail.setText(newEmail.getText());
        changeVisibility();

    }

    void changeVisibility() {
        if (oldPassword.getVisibility() == View.GONE) oldPassword.setVisibility(View.VISIBLE);
        else if (oldPassword.getVisibility() == View.VISIBLE) oldPassword.setVisibility(View.GONE);

        if (newPassword.getVisibility() == View.GONE) newPassword.setVisibility(View.VISIBLE);
        else if (newPassword.getVisibility() == View.VISIBLE) newPassword.setVisibility(View.GONE);

        if (newEmail.getVisibility() == View.GONE) newEmail.setVisibility(View.VISIBLE);
        else if (newEmail.getVisibility() == View.VISIBLE) newEmail.setVisibility(View.GONE);

        if (oldEmail.getVisibility() == View.GONE) {
            oldEmail.setVisibility(View.VISIBLE);
        }
        else if (oldEmail.getVisibility() == View.VISIBLE) oldEmail.setVisibility(View.GONE);

        if (displayPassword.getVisibility() == View.GONE)
            displayPassword.setVisibility(View.VISIBLE);
        else if (displayPassword.getVisibility() == View.VISIBLE)
            displayPassword.setVisibility(View.GONE);

        if (signOutButton.getVisibility() == View.GONE) signOutButton.setVisibility(View.VISIBLE);
        else if (signOutButton.getVisibility() == View.VISIBLE)
            signOutButton.setVisibility(View.GONE);

        if (editAccountButton.getVisibility() == View.VISIBLE) {
            saveButton.setVisibility(View.VISIBLE);
            editAccountButton.setVisibility(View.GONE);
        }
        else if (saveButton.getVisibility() == View.VISIBLE) {
            saveButton.setVisibility(View.GONE);
            editAccountButton.setVisibility(View.VISIBLE);
        }

        if (NotificationEnabled.getVisibility() == View.VISIBLE) {
            NotificationEnabled.setVisibility(View.GONE);
        }
        else if (NotificationEnabled.getVisibility() == View.GONE) {
            NotificationEnabled.setVisibility(View.VISIBLE);
        }
    }
}