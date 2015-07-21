package com.jdlundberg.rockpaperscissorslizardspock;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Architect on 4/8/2015.
 */
public class ScissorsHandler implements Handler {

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
            case "Scissors":

                message = "It's a tie!";

                break;
            case "Paper":

                message = "Scissors cut Paper!  You Win!";
                currentScoreValue++;

                break;
            case "Rock":

                 message = "Rock crushes Scissors!  You Lose!";

                if (isHighScore) {

                    mainActivity.showAlert(currentScoreValue);

                }

                currentScoreValue = 0;

                break;
            case "Lizard":

                message = "Scissors decapitate Lizard!  You Win!";
                currentScoreValue++;

                break;
            case "Spock":

                message = "Spock smashes Scissors!  You Lose!";

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
