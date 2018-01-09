package com.example.lawrence.nmcnpm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class FillIInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_iinfo);
        loadData();

        Button btSubmit = findViewById(R.id.buttonSubmitInfo);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent swipingIntent = new Intent(FillIInfoActivity.this, SwipeScreenActivity.class);
                startActivity(swipingIntent);
            }
        });
    }

    private void saveData() {
        EditText etName = findViewById(R.id.FillInfo_edittextName);
        RadioGroup rgGender = findViewById(R.id.FillInfo_RadGpGenderChosen);
        EditText etBirthday = findViewById(R.id.FillInfo_edittextDate);
        RadioGroup rgInteresting = findViewById(R.id.FillInfo_Interest);
        EditText etLeftRange = findViewById(R.id.FillInfo_edittextLeftRange);
        EditText etRightRange = findViewById(R.id.FillInfo_edittextRightRange);
        EditText etMaxDist = findViewById(R.id.FillInfo_edittextMaxDist);
        SharedPreferences info = getSharedPreferences("info", 0);
        SharedPreferences.Editor editor = info.edit();
        editor.putString("name", etName.getText().toString());
        switch (rgGender.getCheckedRadioButtonId()) {
            case R.id.FillInfo_RadButMale:
                editor.putInt("gender", 1);
                break;
            case R.id.FillInfo_RadButFemale:
                editor.putInt("gender", 2);
                break;
            case R.id.FillInfo_RadButOther:
                editor.putInt("gender", 3);
                break;
            default:
                editor.putInt("gender", 0);
                break;
        }
        editor.putString("birthday", etBirthday.getText().toString());
        switch (rgInteresting.getCheckedRadioButtonId()) {
            case R.id.FillInfo_RadButMale_Int:
                editor.putInt("interesting", 1);
                break;
            case R.id.FillInfo_RadButFemale_Int:
                editor.putInt("interesting", 2);
                break;
            case R.id.FillInfo_RadButOther_Int:
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
        EditText etName = findViewById(R.id.FillInfo_edittextName);
        RadioGroup rgGender = findViewById(R.id.FillInfo_RadGpGenderChosen);
        EditText etBirthday = findViewById(R.id.FillInfo_edittextDate);
        RadioGroup rgInteresting = findViewById(R.id.FillInfo_Interest);
        EditText etLeftRange = findViewById(R.id.FillInfo_edittextLeftRange);
        EditText etRightRange = findViewById(R.id.FillInfo_edittextRightRange);
        EditText etMaxDist = findViewById(R.id.FillInfo_edittextMaxDist);
        SharedPreferences info = getSharedPreferences("info", 0);
        etName.setText(info.getString("name", ""));
        switch (info.getInt("gender", 0)) {
            case 1:
                rgGender.check(R.id.FillInfo_RadButMale);
                break;
            case 2:
                rgGender.check(R.id.FillInfo_RadButFemale);
                break;
            case 3:
                rgGender.check(R.id.FillInfo_RadButOther);
                break;
            default:
                rgGender.clearCheck();
                break;
        }
        etBirthday.setText(info.getString("birthday", "DD/MM/YYYY"));
        switch (info.getInt("interesting", 0)) {
            case 1:
                rgInteresting.check(R.id.FillInfo_RadButMale_Int);
                break;
            case 2:
                rgInteresting.check(R.id.FillInfo_RadButFemale_Int);
                break;
            case 3:
                rgInteresting.check(R.id.FillInfo_RadButOther_Int);
                break;
            default:
                rgInteresting.clearCheck();
                break;
        }
        etLeftRange.setText(info.getString("leftRange", ""));
        etRightRange.setText(info.getString("rightRange", ""));
        etMaxDist.setText(info.getString("maxDist", ""));
    }
}
