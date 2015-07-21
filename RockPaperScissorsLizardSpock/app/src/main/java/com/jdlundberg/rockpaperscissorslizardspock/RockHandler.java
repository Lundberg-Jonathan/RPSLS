package com.jdlundberg.rockpaperscissorslizardspock;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Architect on 4/8/2015.
 */
public class RockHandler implements Handler {

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
            case "Rock":

                message = "It's a Draw!";

                break;
            case "Scissors":

                message = "Rock crushes Scissors!  You Win!";
                currentScoreValue++;

                break;
            case "Paper":

                message = "Paper covers Rock!  You Lose!";

                if (isHighScore) {

                    mainActivity.showAlert(currentScoreValue);

                }

                currentScoreValue = 0;

                break;
            case "Lizard":

                message = "Rock crushes Lizard!  You Win!";
                currentScoreValue++;

                break;
            case "Spock":

                message = "Spock vaporizes Rock!  You Lose!";

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
