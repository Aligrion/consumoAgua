package com.ryan.consumogua;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView percentageTextView;
    private ProgressBar progressBar;
    private EditText drinkAmountEditText;
    private Button registerDrinkButton;
    private ListView drinksListView;
    private ArrayList<String> drinkList = new ArrayList<>();
    private ArrayAdapter<String> drinkListAdapter;

    private int totalDrinks = 0;
    private int dailyGoal = 2000; // 2L
    private int percentage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        percentageTextView = findViewById(R.id.percentageTextView);
        progressBar = findViewById(R.id.progressBar);
        drinkAmountEditText = findViewById(R.id.drinkAmountEditText);
        registerDrinkButton = findViewById(R.id.registerDrinkButton);
        drinksListView = findViewById(R.id.drinksListView);

        drinkListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, drinkList);
        drinksListView.setAdapter(drinkListAdapter);

        updateProgress();
        updateDrinkList();

        registerDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String drinkAmountString = drinkAmountEditText.getText().toString();
                if (!drinkAmountString.isEmpty()) {
                    int drinkAmount = Integer.parseInt(drinkAmountString);
                    totalDrinks += drinkAmount;
                    updateProgress();
                    updateDrinkList();
                    drinkAmountEditText.setText("");
                }
            }
        });
    }

    private void updateProgress() {
        percentage = totalDrinks * 100 / dailyGoal;
        percentageTextView.setText(String.format(Locale.getDefault(), "%d%%", percentage));
        progressBar.setProgress(percentage);
    }

    private void updateDrinkList() {
        String drinkAmountString = drinkAmountEditText.getText().toString();
        if (!drinkAmountString.isEmpty()) {
            int drinkAmount = Integer.parseInt(drinkAmountString);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            String drinkString = String.format(Locale.getDefault(), "%s - %d ml", dateFormat.format(calendar.getTime()), drinkAmount);
            drinkList.add(drinkString);
            drinkListAdapter.notifyDataSetChanged();
        }
    }
}
