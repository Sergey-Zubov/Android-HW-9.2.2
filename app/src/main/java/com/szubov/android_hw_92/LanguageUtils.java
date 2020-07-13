package com.szubov.android_hw_92;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.Locale;

public class LanguageUtils {
    private static Locale chosenLoc;

    public static void initSpinnerLoc(final Context activity, Spinner spinner) {
        ArrayAdapter<CharSequence> adapterLoc = ArrayAdapter.createFromResource
                (activity, R.array.spinner_loc_array, android.R.layout.simple_spinner_item);
        adapterLoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterLoc);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = activity.getResources().getStringArray(R.array.spinner_loc_array);
                if (languages[position].equals("Английский") || languages[position].equals("English")) {
                    chosenLoc = new java.util.Locale("en");
                } else {
                    chosenLoc = new java.util.Locale("ru");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public static void changeLoc(Activity activity) {
        if (!Locale.getDefault().equals(chosenLoc)) {
            Locale.setDefault(chosenLoc);
            Configuration configuration = new Configuration();
            configuration.setLocale(chosenLoc);
            activity.getResources().updateConfiguration(configuration,
                    activity.getBaseContext().getResources().getDisplayMetrics());
            activity.recreate();
        }
    }
}
