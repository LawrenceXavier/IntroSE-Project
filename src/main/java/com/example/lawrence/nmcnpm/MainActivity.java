package com.example.lawrence.nmcnpm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btLoginFb = findViewById(R.id.Main_btLoginFb);
        btLoginFb.setVisibility(View.INVISIBLE);
        btLoginFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean notLoggedIn = AccessToken.getCurrentAccessToken() == null;
                if (notLoggedIn) {
                    Intent loginIntent = new Intent(MainActivity.this, LoginWithFacebookActivity.class);
                    startActivity(loginIntent);
                }

                /*
                if (not <account exists>) {
                    Intent fillInfoIntent = new Intent(...);    // go to FillIInfoActivity
                    startActivity(fillInfoIntent);
                }
                 */

                Intent swipingIntent = new Intent(MainActivity.this, SwipeScreenActivity.class);
                startActivity(swipingIntent);
            }
        });

        Button btLoginPhone = findViewById(R.id.Main_btLoginPhone);
        btLoginFb.setVisibility(View.INVISIBLE);
        btLoginPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show a new activity for user to fill in the phone number
            }
        });

        /*
        if (<already logged in to SOD>) {   // back-end part
            Intent swipingIntent = new Intent(MainActivity.this, SwipeScreenActivity.class);
            startActivity(swipingIntent);
        }
        else {
            btLoginFb.setVisibility(View.VISIBLE);
            btLoginFb.setVisibility(View.VISIBLE);
        }
        */
    }
}
