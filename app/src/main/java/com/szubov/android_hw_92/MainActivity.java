package com.szubov.android_hw_92;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static @StyleRes int themRes = R.style.AppTheme;
    private @StyleRes int choiceTheme;
    private Spinner mSpinnerLoc;
    private Spinner mSpinnerTheme;
    private Button mBtnOk;
    private TextView mTextView;
    private Locale choiceLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(themRes);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mBtnOk = findViewById(R.id.btnOk);
        mTextView = findViewById(R.id.textView);
        mSpinnerLoc = findViewById(R.id.spinnerLoc);
        mSpinnerTheme = findViewById(R.id.spinnerTheme);

        initSpinnerLoc();
        initSpinnerTheme();

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Locale.getDefault().equals(choiceLoc)) {
                    changeLoc(choiceLoc);
                }

                if (themRes != choiceTheme) {
                    changeTheme(choiceTheme);
                }

            }
        });

    }

    private void initSpinnerLoc() {
        ArrayAdapter<CharSequence> adapterLoc = ArrayAdapter.createFromResource
                (this, R.array.spinner_loc_array, android.R.layout.simple_spinner_item);
        adapterLoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerLoc.setAdapter(adapterLoc);

        mSpinnerLoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().getStringArray(R.array.spinner_loc_array);
                if (languages[position].equals("Английский") || languages[position].equals("English")) {
                    choiceLoc = new Locale("en");
                } else {
                    choiceLoc = new Locale("ru");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSpinnerTheme() {
        ArrayAdapter<CharSequence> adapterTheme = ArrayAdapter.createFromResource
                (this,R.array.spinner_margin_array, android.R.layout.simple_spinner_item);
        adapterTheme.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerTheme.setAdapter(adapterTheme);

        mSpinnerTheme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] themes = getResources().getStringArray(R.array.spinner_margin_array);
                switch (themes[position]) {
                    case "Стандартная":
                    case "Standard":
                        choiceTheme = R.style.AppTheme;
                        break;
                    case "Крупная":
                    case "Large":
                        choiceTheme = R.style.AppTheme_MarginLarge;
                        break;
                    case "Средняя":
                    case "Average":
                        choiceTheme = R.style.AppTheme_MarginAverage;
                        break;
                    case "Мелкая":
                    case "Small":
                        choiceTheme = R.style.AppTheme_MarginSmall;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void changeLoc(Locale choiceLoc) {
        Locale.setDefault(choiceLoc);
        Configuration configuration = new Configuration();
        configuration.setLocale(choiceLoc);
        getResources().updateConfiguration(configuration,
                getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }

    private void changeTheme(@StyleRes int choiceTheme) {
        themRes = choiceTheme;
        recreate();
    }
}