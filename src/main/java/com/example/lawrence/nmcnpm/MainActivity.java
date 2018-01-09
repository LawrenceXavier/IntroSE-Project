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
        btLoginFb.setVisibility(View.VISIBLE);
        btLoginFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean notLoggedIn = AccessToken.getCurrentAccessToken() == null;
                if (notLoggedIn) {
                    Intent loginIntent = new Intent(MainActivity.this, LoginWithFacebookActivity.class);
                    startActivityForResult(loginIntent, 1);
                } else {
                    Intent swipingIntent = new Intent(MainActivity.this, SwipeScreenActivity.class);
                    startActivity(swipingIntent);
                }
            }
        });

        Button btLoginPhone = findViewById(R.id.Main_btLoginPhone);
        btLoginPhone.setVisibility(View.VISIBLE);
        btLoginPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(MainActivity.this, FillIInfoActivity.class);
                startActivityForResult(loginIntent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Intent swipingIntent = new Intent(MainActivity.this, SwipeScreenActivity.class);
            startActivity(swipingIntent);
        }
    }
}
