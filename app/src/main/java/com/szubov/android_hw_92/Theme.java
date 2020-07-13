package com.szubov.android_hw_92;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.annotation.StyleRes;

public class Theme {
    private static @StyleRes int themeRes = R.style.AppTheme;
    private static @StyleRes int choiceTheme;

    public static void initSpinnerTheme(final Context activity, Spinner spinner) {
        ArrayAdapter<CharSequence> adapterTheme = ArrayAdapter.createFromResource
                (activity,R.array.spinner_margin_array, android.R.layout.simple_spinner_item);
        adapterTheme.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterTheme);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] themes = activity.getResources().getStringArray(R.array.spinner_margin_array);
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

    public static int changeThemeRes(Activity activity) {
        if (Theme.themeRes != Theme.choiceTheme) {
            themeRes = choiceTheme;
            activity.setTheme(themeRes);
            activity.recreate();
        }
        return themeRes;
    }
}
