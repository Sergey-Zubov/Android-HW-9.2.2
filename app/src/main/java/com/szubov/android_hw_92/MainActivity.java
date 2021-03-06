package com.szubov.android_hw_92;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner mSpinnerLoc;
    private Spinner mSpinnerTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(ThemeUtils.changeThemeRes(this));
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mSpinnerLoc = findViewById(R.id.spinnerLoc);
        mSpinnerTheme = findViewById(R.id.spinnerTheme);
        findViewById(R.id.btnOk).setOnClickListener(this);

        LanguageUtils.initSpinnerLoc(this, mSpinnerLoc);
        ThemeUtils.initSpinnerTheme(this, mSpinnerTheme);
    }

    @Override
    public void onClick(View v) {
        LanguageUtils.changeLoc(this);
        ThemeUtils.changeThemeRes(this);
    }
}