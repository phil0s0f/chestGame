package com.example.chestgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

//    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickStart(View view) {
    }

    public void clickPlus(View view) {
        TextView plus = (TextView) view;
        TextView tw;
        int count = 0;
        switch (getResources().getResourceEntryName(plus.getId())) {
            case "plusRound":
                tw = findViewById(R.id.countRounds);
                count = Integer.parseInt(tw.getText().toString());
                if (count < 10) {
                    count++;
                    tw.setText("" + count);
                }
                break;
            case "plusChest":
                tw = findViewById(R.id.countChests);
                count = Integer.parseInt(tw.getText().toString());
                if (count < 10) {
                    count++;
                    tw.setText("" + count);
                }
                break;
            case "plusTrap":
                tw = findViewById(R.id.countTraps);
                count = Integer.parseInt(tw.getText().toString());
                if (count < 3) {
                    count++;
                    tw.setText("" + count);
                }
                break;
        }
    }

    public void clickMinus(View view) {
        TextView minus = (TextView) view;
        TextView tw;
        int count = 0;
        switch (getResources().getResourceEntryName(minus.getId())) {
            case "minusRound":
                tw = findViewById(R.id.countRounds);
                count = Integer.parseInt(tw.getText().toString());
                if (count > 1) {
                    count--;
                    tw.setText("" + count);
                }
                break;
            case "minusTrap":
                tw = findViewById(R.id.countTraps);
                count = Integer.parseInt(tw.getText().toString());
                if (count > 1) {
                    count--;
                    tw.setText("" + count);
                }
                break;
            case "minusChest":
                tw = findViewById(R.id.countChests);
                count = Integer.parseInt(tw.getText().toString());
                if (count > 5) {
                    count--;
                    tw.setText("" + count);
                }
                break;
        }
    }
    //public void onClick(View view) {
//        EditText loginText = findViewById(R.id.countRounds);
//        EditText passwordText = findViewById(R.id.countTraps);
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        String message = loginText.getText().toString() + " " + passwordText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);

    //EditText priceText = findViewById(R.id.editPrice);

    //String name = nameText.getText().toString();
    //String company = companyText.getText().toString();
    //int price = Integer.parseInt(priceText.getText().toString());

    // Product product = new Product(name, company, price);

    //Intent intent = new Intent(this, DisplayMessageActivity.class);
    //intent.putExtra(Product.class.getSimpleName(), product);
    //startActivity(intent);
    //}
}