package loginsample.android.scu.edu.loginsample;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;

import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.FacebookCallback;
import com.facebook.CallbackManager;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.Profile;
import com.facebook.ProfileTracker;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {


    private TextView mTextDetails;
    private CallbackManager mCallbackManager;
    private AccessTokenTracker mTokenTracker;
    private ProfileTracker mProfileTracker;

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.d("wajhi","onSuccess");
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            mTextDetails.setText(constructWelcomeMessage(profile));
        }

        @Override
        public void onCancel() {
            Log.d("wajhi", "OnCancel");
        }

        @Override
        public void onError(FacebookException e) {
            Log.d("wajhi", "OnError" + e);
        }
    };

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        setupTokenTracker();
        setupProfileTracker();

        mTokenTracker.startTracking();
        mProfileTracker.startTracking();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mTextDetails = (TextView) view.findViewById(R.id.textdetails);
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton)view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(mCallbackManager,mCallback);
    }

    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        mTextDetails.setText(constructWelcomeMessage(profile));
    }

    public void onStop() {
        super.onStop();
        mTokenTracker.stopTracking();
        mProfileTracker.stopTracking();
    }

    private void setupTokenTracker() {
        mTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                Log.d("wajhi", "" + currentAccessToken);
            }
        };
    }

    private void setupProfileTracker() {
        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Log.d("VIVZ", "" + currentProfile);
                mTextDetails.setText(constructWelcomeMessage(currentProfile));
            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode,resultCode,data);
    }

    private String constructWelcomeMessage(Profile profile) {
        StringBuffer stringBuffer = new StringBuffer();
        if (profile != null) {
            stringBuffer.append("Welcome " + profile.getName());
        }
        return stringBuffer.toString();
    }
}
