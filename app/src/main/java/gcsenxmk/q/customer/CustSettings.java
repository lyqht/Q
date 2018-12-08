package gcsenxmk.q.customer;


import android.content.Intent;
import android.content.Context;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import gcsenxmk.q.R;
import gcsenxmk.q.database.UserInformation;
import gcsenxmk.q.login.FirebaseLoginActivity;
import gcsenxmk.q.merchant.QueueActivity;
import gcsenxmk.q.misc.CircleTransform;
import gcsenxmk.q.misc.Utils;

import static android.app.Activity.RESULT_OK;

public class CustSettings extends Fragment {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private StorageReference mStorageRef;
    private StorageTask mUploadTask;
    private DatabaseReference databaseReference;

    boolean passwordUpdated;

    private Button editAccountButton;
    private Button signOutButton;
    private Button saveButton;
    private Button cancelButton;

    private TextView oldEmail;
    private EditText newEmail;

    private TextView displayPassword;
    private EditText oldPassword;
    private EditText newPassword;

    private TextView oldName;
    private EditText newName;

    private ImageView oldProfilePic;
    private ImageButton newProfilePic;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    private String imageURL;

    private SwitchCompat NotificationEnabled;
    private TextView NotificationEnabledText;

    private final String TAG = "CustSettings";

    public CustSettings() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get user

        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        mStorageRef = FirebaseStorage.getInstance().getReference("Customers");
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.cust_settings, container, false);
        saveButton = v.findViewById(R.id.cust_profile_SaveButton);
        editAccountButton = v.findViewById(R.id.cust_profile_editAccountButton);
        cancelButton = v.findViewById(R.id.cust_profile_CancelButton);
        signOutButton = v.findViewById(R.id.cust_profile_signOutButton);
        displayPassword = v.findViewById(R.id.cust_profile_password_textview);
        oldPassword = v.findViewById(R.id.cust_profile_password_userOld);
        newPassword = v.findViewById(R.id.cust_profile_password_userNew);
        oldEmail = v.findViewById(R.id.cust_profile_email_textView);
        newEmail = v.findViewById(R.id.cust_profile_email_editText);
        oldName = v.findViewById(R.id.cust_profile_nameOld);
        newName = v.findViewById(R.id.cust_profile_nameNew);
        oldProfilePic = v.findViewById(R.id.cust_profile_image_old);
        newProfilePic = v.findViewById(R.id.cust_profile_image_new);

        NotificationEnabled = v.findViewById(R.id.cust_profile_notifications_switch);
        NotificationEnabledText = v.findViewById(R.id.cust_profile_enable_notifications_textview);
        NotificationEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(getContext(), "Notifications Enabled", Toast.LENGTH_SHORT).show();
            }
        });

        retrieveDetails();

        // Setting widgets to be invisible at first, until edit account button is clicked
        newPassword.setVisibility(View.GONE);
        oldPassword.setVisibility(View.GONE);
        newEmail.setVisibility(View.GONE);
        saveButton.setVisibility(View.GONE);
        newName.setVisibility(View.GONE);
        newProfilePic.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);

        editAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // UI Aspects of clicking editAccount Button
                changeVisibility();
            }
        });

        newProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String empty_string = "";
                if (newPassword.getText().toString() == empty_string || oldPassword.getText().toString() == empty_string ||
                        newName.getText().toString() == empty_string || newEmail.getText().toString() == empty_string) {
                    Toast.makeText(getContext(), "All fields are necessary!", Toast.LENGTH_SHORT).show();
                } else {
                    verifyUserDetails();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeVisibility();
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getContext(), FirebaseLoginActivity.class));
            }
        });

        return v;
    }

    // Functions for retrieving, checking and uploading account details.
    void retrieveDetails() {
        if (user.getEmail() != null) {
            oldEmail.setText(user.getEmail());
            newEmail.setText(user.getEmail());
        }
        if (user.getDisplayName() != null) {
            oldName.setText(user.getDisplayName());
            newName.setText(user.getDisplayName());
        }

        if (user.getPhotoUrl() != null) {
            mImageUri = user.getPhotoUrl();
            Picasso.with(getContext()).load(mImageUri).transform(new CircleTransform()).into(newProfilePic);
            Picasso.with(getContext()).load(mImageUri).transform(new CircleTransform()).into(oldProfilePic);
        }
    }

    void verifyUserDetails() {
        user.reauthenticate(EmailAuthProvider.getCredential(user.getEmail(),
                oldPassword.getText().toString())).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "User re-authenticated.");
                    if (newPassword.getText().toString() != oldPassword.getText().toString()) {
                        updateDetails();
                    } else {
                        Toast.makeText(getContext(),"New password is the same as old password?", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Old Password entered wrongly.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void updateDetails() {
        // Update Email
        user.updateEmail(newEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User email address updated.");
                        }
                    }
                });

        user.updatePassword(newPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "User password updated.");
                    passwordUpdated = true;
                }
            }
        });

        if (mImageUri != null && mImageUri!= user.getPhotoUrl()) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + Utils.getFileExtension(mImageUri, getContext()));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.d(TAG, "Upload Customer Profile Image successful");

                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful()) ;
                            Uri imageUri = urlTask.getResult();
                            imageURL = String.valueOf(imageUri);

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(newName.getText().toString())
                                    .setPhotoUri(Uri.parse(imageURL))
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User profile updated.");
                                            }
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, e.getMessage());
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.d(TAG, "Image Upload in progress");
                        }
                    });
        }

        UserInformation userInformation = new UserInformation(newName.getText().toString(), imageURL, Init_Cust_Profile.priority);
        databaseReference.child(user.getUid()).setValue(userInformation);


        if (passwordUpdated) {
            Toast.makeText(getContext(), "Account settings saved.", Toast.LENGTH_SHORT).show();
        }
        oldEmail.setText(newEmail.getText());
        oldName.setText(newName.getText());
        Picasso.with(getContext()).load(mImageUri).transform(new CircleTransform()).into(newProfilePic);
        Picasso.with(getContext()).load(mImageUri).transform(new CircleTransform()).into(oldProfilePic);
        changeVisibility();

    }

    // Function to make UI changes according to new account details

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
            cancelButton.setVisibility(View.VISIBLE);
        }
        else if (saveButton.getVisibility() == View.VISIBLE) {
            saveButton.setVisibility(View.GONE);
            editAccountButton.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.GONE);
        }


        if (NotificationEnabled.getVisibility() == View.VISIBLE) {
            NotificationEnabled.setVisibility(View.GONE);
            NotificationEnabledText.setVisibility(View.GONE);
        }
        else {
            NotificationEnabled.setVisibility(View.VISIBLE);
            NotificationEnabledText.setVisibility(View.VISIBLE);
        }

        if (newName.getVisibility() == View.GONE && oldName.getVisibility() == View.VISIBLE) {
            newName.setVisibility(View.VISIBLE);
            oldName.setVisibility(View.GONE);
        } else {
            oldName.setVisibility(View.VISIBLE);
            newName.setVisibility(View.GONE);
        }

        if (newProfilePic.getVisibility() == View.GONE && oldProfilePic.getVisibility() == View.VISIBLE) {
            newProfilePic.setVisibility(View.VISIBLE);
            oldProfilePic.setVisibility(View.GONE);
        } else {
            oldProfilePic.setVisibility(View.VISIBLE);
            newProfilePic.setVisibility(View.GONE);
        }
    }

    // function for uploading image

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.with(getContext()).load(mImageUri).transform(new CircleTransform()).into(newProfilePic);
        }
    }
}