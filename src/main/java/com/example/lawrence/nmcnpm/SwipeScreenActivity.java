package com.example.lawrence.nmcnpm;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

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

        final ImageButton chattingButton = findViewById(R.id.SwipeScreen_buttonChattings);
        chattingButton.setVisibility(View.INVISIBLE);
        chattingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chattingIntent = new Intent(SwipeScreenActivity.this, ChattingActivity.class);
                startActivity(chattingIntent);
            }
        });

        final ImageView iwSwipeScreen = findViewById(R.id.SwipeScreen_imageviewMainProfilePic);
        iwSwipeScreen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData dragData = new ClipData((CharSequence) view.getTag(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(iwSwipeScreen);
                view.startDrag(dragData, myShadow, null, 0);
                return true;
            }
        });

        iwSwipeScreen.setOnDragListener(new View.OnDragListener() {
            float oldX = 0;

            @Override
            public boolean onDrag(View view, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        iwSwipeScreen.setVisibility(View.INVISIBLE);
                        oldX = event.getX();
                        break;
                    case DragEvent.ACTION_DROP:
                        float x = event.getX();
                        Point size = new Point();
                        getWindowManager().getDefaultDisplay().getSize(size);
                        if (Math.abs(x - oldX) >= (size.x)/4.0) {
                            iwSwipeScreen.setImageResource(R.drawable.kurumi_tokisaki_fanart_by_keid88);
                            if (x - oldX >= (size.x)/4.0) {
                                chattingButton.setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        iwSwipeScreen.setVisibility(View.VISIBLE);
                        break;
                    default: break;
                }
                return true;
            }
        });
    }
}

