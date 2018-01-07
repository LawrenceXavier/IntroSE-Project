package com.example.lawrence.nmcnpm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SwipeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_screen);

        ImageButton settingsButton = findViewById(R.id.SwipeScreen_buttonSettings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingIntent = new Intent(SwipeScreenActivity.this, AccountSettingActivity.class);
                startActivity(settingIntent);
            }
        });
    }
}
