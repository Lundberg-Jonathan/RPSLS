package com.jdlundberg.rockpaperscissorslizardspock;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Architect on 4/8/2015.
 */
public class SpockHandler implements Handler {

    boolean isHighScore = false;

    @Override
    public void handleIt(Object... params) {

        MainActivity mainActivity = (MainActivity) params[0];
        Activity activity = (Activity) params[0];
        String systemWeapon = (String) params[1];
        Integer currentScoreValue = Integer.parseInt(params[2].toString());
        TextView currentScore = (TextView) params[3];
        TextView highScore = (TextView) params[4];
        String message = "";

        if (currentScoreValue == null) {

            currentScoreValue = 0;

        }

        switch (systemWeapon) {
            case "Spock":

                message = "It's a tie!";

                break;
            case "Scissors":

                message = "Spock smashes Scissors!  You Win!";
                currentScoreValue++;

                break;
            case "Lizard":

                message = "Lizard poisons Spock!  You Lose!";

                if (isHighScore) {

                    mainActivity.showAlert(currentScoreValue);

                }

                currentScoreValue = 0;

                break;
            case "Rock":

                message = "Spock vaporizes Rock!  You Win!";
                currentScoreValue++;

                break;
            case "Paper":

                message = "Paper disproves Spock!  You Lose!";

                if (isHighScore) {

                    mainActivity.showAlert(currentScoreValue);

                }

                currentScoreValue = 0;

                break;
        }

        Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_SHORT).show();

        currentScore.setText(currentScoreValue.toString());

        if (currentScoreValue > Integer.parseInt(highScore.getText().toString())) {

            isHighScore = true;
            highScore.setText(currentScoreValue.toString());

        }

        else {

            isHighScore = false;

        }

    }

}
