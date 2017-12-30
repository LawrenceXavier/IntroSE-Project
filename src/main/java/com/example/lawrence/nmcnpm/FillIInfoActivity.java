package com.example.lawrence.nmcnpm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FillIInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_iinfo);

        final Button btSubmit = findViewById(R.id.buttonSubmitInfo);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do something to confirm
                Intent swipingIntent = new Intent(FillIInfoActivity.this, SwipeScreenActivity.class);
                startActivity(swipingIntent);
            }
        });
    }
}
