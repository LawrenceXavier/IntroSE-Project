package com.example.lawrence.nmcnpm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class AccountSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        loadData();

        Button btSave = findViewById(R.id.AccountSettings_buttonSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent swipingIntent = new Intent(AccountSettingActivity.this, SwipeScreenActivity.class);
                startActivity(swipingIntent);
            }
        });

        ImageView iwAvatar = findViewById(R.id.AccountSettings_imageviewAvatar);
        iwAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            ImageView iwAvatar = findViewById(R.id.AccountSettings_imageviewAvatar);
            iwAvatar.setImageURI(selectedImage);
        }
    }

    private void saveData() {
        ImageView iwAvatar = findViewById(R.id.AccountSettings_imageviewAvatar);
        EditText etName = findViewById(R.id.AccountSettings_edittextName);
        RadioGroup rgGender = findViewById(R.id.AccountSettings_RadGpGenderChosen);
        EditText etBirthday = findViewById(R.id.AccountSettings_edittextDate);
        RadioGroup rgInteresting = findViewById(R.id.AccountSettings_Interest);
        EditText etLeftRange = findViewById(R.id.AccountSettings_edittextLeftRange);
        EditText etRightRange = findViewById(R.id.AccountSettings_edittextRightRange);
        EditText etMaxDist = findViewById(R.id.AccountSettings_edittextMaxDist);
        iwAvatar.buildDrawingCache();
        Bitmap bmp = iwAvatar.getDrawingCache();
        try {
            FileOutputStream fos = openFileOutput("avatar", MODE_PRIVATE);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {}
        SharedPreferences info = getSharedPreferences("info", 0);
        SharedPreferences.Editor editor = info.edit();
        editor.putString("name", etName.getText().toString());
        switch (rgGender.getCheckedRadioButtonId()) {
            case R.id.AccountSettings_RadButMale:
                editor.putInt("gender", 1);
                break;
            case R.id.AccountSettings_RadButFemale:
                editor.putInt("gender", 2);
                break;
            case R.id.AccountSettings_RadButOther:
                editor.putInt("gender", 3);
                break;
            default:
                editor.putInt("gender", 0);
                break;
        }
        editor.putString("birthday", etBirthday.getText().toString());
        switch (rgInteresting.getCheckedRadioButtonId()) {
            case R.id.AccountSettings_RadButMale_Int:
                editor.putInt("interesting", 1);
                break;
            case R.id.AccountSettings_RadButFemale_Int:
                editor.putInt("interesting", 2);
                break;
            case R.id.AccountSettings_RadButOther_Int:
                editor.putInt("interesting", 3);
                break;
            default:
                editor.putInt("interesting", 0);
                break;
        }
        editor.putString("leftRange", etLeftRange.getText().toString());
        editor.putString("rightRange", etRightRange.getText().toString());
        editor.putString("maxDist", etMaxDist.getText().toString());
        editor.commit();
    }

    private void loadData() {
        ImageView iwAvatar = findViewById(R.id.AccountSettings_imageviewAvatar);
        EditText etName = findViewById(R.id.AccountSettings_edittextName);
        RadioGroup rgGender = findViewById(R.id.AccountSettings_RadGpGenderChosen);
        EditText etBirthday = findViewById(R.id.AccountSettings_edittextDate);
        RadioGroup rgInteresting = findViewById(R.id.AccountSettings_Interest);
        EditText etLeftRange = findViewById(R.id.AccountSettings_edittextLeftRange);
        EditText etRightRange = findViewById(R.id.AccountSettings_edittextRightRange);
        EditText etMaxDist = findViewById(R.id.AccountSettings_edittextMaxDist);
        try {
            FileInputStream fis = openFileInput("avatar");
            Bitmap bmp = BitmapFactory.decodeStream(fis);
            iwAvatar.setImageBitmap(bmp);
            fis.close();

        } catch (Exception e) {}
        SharedPreferences info = getSharedPreferences("info", 0);
        etName.setText(info.getString("name", ""));
        switch (info.getInt("gender", 0)) {
            case 1:
                rgGender.check(R.id.AccountSettings_RadButMale);
                break;
            case 2:
                rgGender.check(R.id.AccountSettings_RadButFemale);
                break;
            case 3:
                rgGender.check(R.id.AccountSettings_RadButOther);
                break;
            default:
                rgGender.clearCheck();
                break;
        }
        etBirthday.setText(info.getString("birthday", "DD/MM/YYYY"));
        switch (info.getInt("interesting", 0)) {
            case 1:
                rgInteresting.check(R.id.AccountSettings_RadButMale_Int);
                break;
            case 2:
                rgInteresting.check(R.id.AccountSettings_RadButFemale_Int);
                break;
            case 3:
                rgInteresting.check(R.id.AccountSettings_RadButOther_Int);
                break;
            default:
                rgInteresting.clearCheck();
                break;
        }
        etLeftRange.setText(info.getString("leftRange", ""));
        etRightRange.setText(info.getString("rightRange", ""));
        etMaxDist.setText(info.getString("maxDist", ""));
        try {
            FileInputStream fis = openFileInput("avatar");
            Bitmap bmp = BitmapFactory.decodeStream(fis);
            iwAvatar.setImageBitmap(bmp);
            fis.close();

        } catch (Exception e) {}
    }
}
