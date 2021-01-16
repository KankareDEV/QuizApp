package com.example.teslaquizapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.time.Year;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the tesla button is clicked.
     */
    public void submitPoints(View view) {

        EditText text = (EditText) findViewById(R.id.username);
        String name = text.getText().toString();

        EditText teslaYear = (EditText) findViewById(R.id.questonSix);
        String yearOfTesla = teslaYear.getText().toString();

        RadioButton SouthAfrica = (RadioButton) findViewById(R.id.southAfrica_radio_button);
        boolean hasSouthAfrica = SouthAfrica.isChecked();

        RadioButton Roadster = (RadioButton) findViewById(R.id.roadster_radio_button);
        boolean hasRoadster = Roadster.isChecked();

        RadioButton Daimler = (RadioButton) findViewById(R.id.daimler_radio_button);
        boolean hasDaimler = Daimler.isChecked();

        CheckBox KayneCheckBox = (CheckBox) findViewById(R.id.kayne_west_me_checkbox);
        boolean hasKayne = KayneCheckBox.isChecked();

        CheckBox TaylorCheckBox = (CheckBox) findViewById(R.id.taylor_swift_me_checkbox);
        boolean hasTaylor = TaylorCheckBox.isChecked();

        RadioButton TeslaPrice = (RadioButton) findViewById(R.id.dollar2_radio_button);
        boolean hasTeslaPrice = TeslaPrice.isChecked();

        int points = calculatePoints(hasKayne, hasTaylor, hasSouthAfrica, hasRoadster, hasDaimler, hasTeslaPrice);
        String numberOfPoints = createOrderSummary(name, points, hasKayne, hasTaylor, hasSouthAfrica, hasRoadster, hasDaimler, hasTeslaPrice);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Tesla Quiz App Points: " + name);
        intent.putExtra(Intent.EXTRA_TEXT, numberOfPoints);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the points of the Quiz.
     *
     * @param addTaylor is wheter or not the user answer right
     * @param addKayne is wheter or not the user answer wrong
     * @return text summary
     */
    private int calculatePoints(boolean addTaylor, boolean addKayne, boolean addSouthAfrica, boolean addRoadster, boolean addDaimler, boolean addTeslaPrice) {
        int basePrice = 0;

        {
        }
        if (addTeslaPrice) {
            basePrice = basePrice +1;
        }
        if (addDaimler) {
            basePrice = basePrice + 1;
        }
        if (addRoadster) {
            basePrice = basePrice + 1;
        }
        if (addTaylor) {
            basePrice = basePrice + 1;
        }
        if (addSouthAfrica) {
            basePrice = basePrice + 1;
        }
        if (addKayne) {
            basePrice = basePrice + 1;
        }
        return basePrice;
    }


    private String createOrderSummary(String name, int points, boolean addKayne, boolean addTaylor, boolean addSouthAfrica, boolean addRoadster, boolean addDaimler, boolean addTeslaPrice) {
        String numberOfPoints =getString(R.string.name) + name;
        numberOfPoints += "\nSelect Kayne?" + addKayne;
        numberOfPoints += "\nSelect Kayne?" + addRoadster;
        numberOfPoints += "\nSelect Kayne?" + addDaimler;
        numberOfPoints += "\nSelect Kayne?" + addTeslaPrice;
        numberOfPoints += "\nSelect Kayne?" + addSouthAfrica;
        numberOfPoints += "\nSelect Taylor?" + addTaylor;
        numberOfPoints += "\nYour Points (total 6)" + points;
        numberOfPoints += "\nThank you for playing!";
        return numberOfPoints;
    }

}
