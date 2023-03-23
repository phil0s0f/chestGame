package com.example.chestgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Game game = null;
    Map<Button, Integer> chestMap = new HashMap<>();
    int roundPoint = 0;
    int sumPoint = 0;
    int currentRound = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            game = (Game) arguments.getSerializable(Game.class.getSimpleName());
            generateActivity(game);
        }
    }

    public void generateActivity(Game game) {
        ConstraintLayout constraintLayout = new ConstraintLayout(this);

        TextView pointSumText = new TextView(this);
        pointSumText.setTextSize(26);
        pointSumText.setText("Всего очков: " + sumPoint + "\n\nРаунд " + currentRound + "\nТекущие очки: " + roundPoint);
        pointSumText.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);

        ConstraintLayout.LayoutParams textViewLayout = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        textViewLayout.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        textViewLayout.width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        textViewLayout.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        //textViewLayout.bottomToTop = gridLayout.getId();
        textViewLayout.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        textViewLayout.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;

        pointSumText.setLayoutParams(textViewLayout);
        constraintLayout.addView(pointSumText);

        GridLayout gridLayout = new GridLayout(this);

        gridLayout.setRowCount(game.getCountChests() / 2);
        gridLayout.setColumnCount(2);
        for (int i = 0; i < game.getCountChests(); i++) {
            Button btn = new Button(this);
            btn.setText("?");
            btn.setBackgroundColor(0xFFA39E9E);
            chestMap.put(btn, generateRandomInt(10));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button btn = (Button) view;
                    if (chestMap.get(btn) != -1) {
                        btn.setText("" + chestMap.get(btn));
                        btn.setBackgroundColor(0xFF4CAF50);
                        refreshRoundPoint(pointSumText, chestMap.get(btn));
                    } else {
                        btn.setText("Л");
                        btn.setBackgroundColor(0xFFF44336);
                        refreshCurrentRound(pointSumText);
                    }
                }
            });
            gridLayout.addView(btn);
        }
        setTraps();

        for (int i = 0; i < game.getCountTraps() - 1; i++) {

        }
        ConstraintLayout.LayoutParams gridViewlayout = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        gridViewlayout.height = GridLayout.LayoutParams.WRAP_CONTENT;
        gridViewlayout.width = GridLayout.LayoutParams.WRAP_CONTENT;
        gridViewlayout.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        gridViewlayout.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        gridViewlayout.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        gridViewlayout.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        gridLayout.setLayoutParams(gridViewlayout);
        constraintLayout.addView(gridLayout);


        Button stopButton = new Button(this);
        stopButton.setText("Хватит");
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumPoint += roundPoint;
                refreshCurrentRound(pointSumText);
            }
        });

        ConstraintLayout.LayoutParams buttonLayout = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        buttonLayout.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        buttonLayout.width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        buttonLayout.topToBottom = gridLayout.getId();
        buttonLayout.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        buttonLayout.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        buttonLayout.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;

        stopButton.setLayoutParams(buttonLayout);
        constraintLayout.addView(stopButton);

        setContentView(constraintLayout);

    }

    public void refreshCurrentRound(TextView tw) {
        if (currentRound + 1 > game.getCountRounds()) {
            roundPoint = 0;
            tw.setText("Всего очков: " + sumPoint + "\n\nРаунд " + currentRound + "\nТекущие очки: " + roundPoint);
            disableButton();
            Toast toast = Toast.makeText(this, "Игра окончена! Всего набрано очков: " + sumPoint, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            roundPoint = 0;
            currentRound++;
            refreshButton();
            tw.setText("Всего очков: " + sumPoint + "\n\nРаунд " + currentRound + "\nТекущие очки: " + roundPoint);
        }

    }

    public void refreshButton() {
        for (Map.Entry<Button, Integer> item : chestMap.entrySet()) {
            Button btn = item.getKey();
            btn.setText("?");
            btn.setBackgroundColor(0xFFA39E9E);
            chestMap.put(btn, generateRandomInt(10));
        }
        setTraps();
    }

    public void setTraps() {
        int setTrap = 0;
        while (true) {
            for (Map.Entry<Button, Integer> item : chestMap.entrySet()) {
                Button btn = item.getKey();
                if (generateRandomInt(3) == 2 && item.getValue() != -1) {
                    chestMap.put(btn, -1);
                    setTrap++;
                }
                if (setTrap == game.getCountTraps()) {
                    return;
                }
            }
        }
    }

    public void disableButton() {
        for (Map.Entry<Button, Integer> item : chestMap.entrySet()) {
            Button btn = item.getKey();
            btn.setEnabled(false);
        }
    }

    public static int generateRandomInt(int upperRange) {
        Random random = new Random();
        return random.nextInt(upperRange) + 1;
    }

    public void refreshRoundPoint(TextView tw, int point) {
        roundPoint += point;
        tw.setText("Всего очков: " + sumPoint + "\n\nРаунд " + currentRound + "\nТекущие очки: " + roundPoint);
    }
}