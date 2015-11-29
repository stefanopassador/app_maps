package com.example.antonio.quichica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
    private TextView mTextDetails;
    private  CallbackManager mCallbackManager;

    private FacebookCallback<LoginResult> mCallback= new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(LoginResult loginResult) {


            AccessToken accessToken = loginResult.getAccessToken();
            final Profile profile = Profile.getCurrentProfile();
            if (profile != null) {

                GraphRequest request = GraphRequest.newMeRequest(
                        accessToken,
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                // Insert your code here
                                String name = object.optString("name");
                                String gender = object.optString("gender");

                                String location = object.optJSONObject("location").optString("name");
                                /* mTextDetails.setText("Gli eventi: " )/* "\n" + gender + "\n Sei di: \n" + location)*/;

                            }
                        });


                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,gender,location,events");
                parameters.putString("limit", "200");
                request.setParameters(parameters);
                request.executeAsync();
            }
         /*   GraphRequest request = GraphRequest.newGraphPathRequest(
                    accessToken,
                    "/1621811528070244",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            // Insert your code here

                            JSONObject event = new JSONObject();
                            Bundle bundle = new Bundle();
                            String events = event.optString("eventName");
                           /* mTextDetails.setText("ecco: " +events );
                        }
                    });*/
            GraphRequest request = GraphRequest.newMeRequest(
                    accessToken,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            // Insert your code here
                            String events = object.optString("events");
                            mTextDetails.setText("Gli eventi: " + events + "\n")/* "\n" + gender + "\n Sei di: \n" + location)*/;
                        }
                    });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "events");
            request.setParameters(parameters);
            request.executeAsync();

          /*  Bundle parameters = new Bundle();
            parameters.putString("fields", "events,id,name,gender");
            parameters.putString("limit", "200");
            request.setParameters(parameters);
            request.executeAsync();*/

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError (FacebookException e){

            }
    };

    public MainFragment() {
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        // Initialize the SDK before executing any other operations,
        // especially, if you're using Facebook UI elements.
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);

        loginButton.setReadPermissions("user_friends","user_location", "public_profile","user_likes","email","user_events",  "user_birthday");
        loginButton.setFragment(this);
        loginButton.registerCallback(mCallbackManager, mCallback);
        mTextDetails = (TextView) view.findViewById(R.id.text_details);
        Button button1 = (Button)view.findViewById(R.id.login_button);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(
                        getContext(),
                        SecondaActivity.class
                );
                startActivity(intent);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }


}
