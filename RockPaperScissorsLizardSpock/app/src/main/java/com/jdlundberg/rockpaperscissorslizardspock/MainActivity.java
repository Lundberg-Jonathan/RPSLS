package com.jdlundberg.rockpaperscissorslizardspock;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    String userWeapon, systemWeapon;
    Activity activity;
    Context context;
    Controller controller = new Controller();
    GetSystemWeapon getSystemWeapon = new GetSystemWeapon();
    RecordHighScore recordHighScore;
    ViewHighScores viewHighScores;
    boolean isHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        context = this;

        final Button buttonScissors = (Button) findViewById(R.id.buttonScissors);
        final Button buttonSpock = (Button) findViewById(R.id.buttonSpock);
        final Button buttonPaper = (Button) findViewById(R.id.buttonPaper);
        final Button buttonLizard = (Button) findViewById(R.id.buttonLizard);
        final Button buttonRock = (Button) findViewById(R.id.buttonRock);
        final Button buttonViewHighScores = (Button) findViewById(R.id.buttonViewHighScores);

        final TextView currentScore = (TextView) findViewById(R.id.textCurrentScoreValue);
        final TextView highScore = (TextView) findViewById(R.id.textHighScoreValue);

        currentScore.setText("0");
        highScore.setText("0");

        buttonScissors.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                userWeapon = buttonScissors.getText().toString();
                systemWeapon = getSystemWeapon.getSystemWeapon();
                controller.handle(userWeapon, activity, systemWeapon, Integer.parseInt(currentScore.getText().toString()), currentScore, highScore, isHighScore);

            }

        });

        buttonSpock.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                userWeapon = buttonSpock.getText().toString();
                systemWeapon = getSystemWeapon.getSystemWeapon();
                controller.handle(userWeapon, activity, systemWeapon, Integer.parseInt(currentScore.getText().toString()), currentScore, highScore, isHighScore);

            }

        });

        buttonPaper.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                userWeapon = buttonPaper.getText().toString();
                systemWeapon = getSystemWeapon.getSystemWeapon();
                controller.handle(userWeapon, activity, systemWeapon, Integer.parseInt(currentScore.getText().toString()), currentScore, highScore, isHighScore);

            }

        });

        buttonLizard.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                userWeapon = buttonLizard.getText().toString();
                systemWeapon = getSystemWeapon.getSystemWeapon();
                controller.handle(userWeapon, activity, systemWeapon, Integer.parseInt(currentScore.getText().toString()), currentScore, highScore, isHighScore);

            }

        });

        buttonRock.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                userWeapon = buttonRock.getText().toString();
                systemWeapon = getSystemWeapon.getSystemWeapon();
                controller.handle(userWeapon, activity, systemWeapon, Integer.parseInt(currentScore.getText().toString()), currentScore, highScore, isHighScore);

            }

        });

        buttonViewHighScores.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                viewHighScores = new ViewHighScores();
                viewHighScores.execute();

            }

        });

    }

    public void showAlert(final Integer score) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Personal High Score!");
        builder.setMessage("You scored " + score + "!  Please enter a username to record the score!");

        final EditText username = new EditText(this);
        username.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(username);

        DialogInterface.OnClickListener submitUsernameListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                String usernameText = username.getText().toString();
                recordHighScore = new RecordHighScore();
                recordHighScore.execute(usernameText, score.toString());

            }

        };

        DialogInterface.OnClickListener noThanksListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                dialogInterface.cancel();

            }

        };

        builder.setPositiveButton("Submit", submitUsernameListener);

        builder.setNegativeButton("No Thanks", noThanksListener);

        builder.show();

    }

    public class ViewHighScores extends AsyncTask<Void, Void, Void> {

        ProgressDialog queryDialog;
        ArrayList<String> highScores = new ArrayList<>();
        CharSequence[] highScoresList = new CharSequence[highScores.size()];

        @Override
        protected void onPreExecute() {

            queryDialog = new ProgressDialog(context);

            queryDialog.setTitle("Getting High Scores");
            queryDialog.setMessage("Please wait");
            queryDialog.show();

            super.onPreExecute();

        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        protected Void doInBackground(Void... params) {

            String hostName = "52.26.125.237";
            Integer portNumber = 4444;
            JSONOutputStream outToServer;
            JSONInputStream inFromServer;

            HashMap<String, String> highScore = new HashMap<>();

            highScore.put("Command", "View High Scores");
            Socket toServer = null;

            try {

                toServer = new Socket(hostName, portNumber);
                outToServer = new JSONOutputStream(toServer.getOutputStream());
                outToServer.writeObject(highScore);

            }

            catch (IOException | JSONException e) {

                Toast.makeText(getApplicationContext(), "Couldn't connect to server." +
                        "Please ensure that either Wi-Fi or Data is enabled.", Toast.LENGTH_SHORT).show();

            }

            try {
                inFromServer = new JSONInputStream(toServer.getInputStream());
                highScores = (ArrayList<String>) inFromServer.readObject();

            }

            catch (IOException | JSONException e) {

                e.printStackTrace();

            }

            highScoresList = highScores.toArray(new CharSequence[highScores.size()]);

            return null;

        }

        @Override
        protected void onPostExecute(Void result) {

            queryDialog.dismiss();

            DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int which) {

                    dialogInterface.cancel();

                }

            };

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Top Scores");
            builder.setItems(highScoresList, null);
            builder.setPositiveButton("OK", okListener);

            builder.show();

            super.onPostExecute(result);

        }

    }

    public class RecordHighScore extends AsyncTask<String, Void, Void> {

        ProgressDialog transmitDialog;

        @Override
        protected void onPreExecute() {

            transmitDialog = new ProgressDialog(context);

            transmitDialog.setTitle("Sending High Score");
            transmitDialog.setMessage("Please Wait");
            transmitDialog.show();

            super.onPreExecute();

        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        protected Void doInBackground(String... params) {

            String userName = params[0];
            String userScore = params[1];
            String hostName = "52.26.125.237";
            Integer portNumber = 4444;
            JSONOutputStream outToServer;

            HashMap<String, String> highScore = new HashMap<>();

            highScore.put("Command", "Record High Score");
            highScore.put("Username", userName);
            highScore.put("Score", userScore);

            try (Socket toServer = new Socket(hostName, portNumber)) {

                outToServer = new JSONOutputStream(toServer.getOutputStream());
                outToServer.writeObject(highScore);

            }

            catch (IOException | JSONException e) {

                Toast.makeText(getApplicationContext(), "Couldn't connect to server." +
                        "Please ensure that either Wi-Fi or Data is enabled.", Toast.LENGTH_SHORT).show();

            }


            return null;

        }

        @Override
        protected void onPostExecute(Void result) {

            transmitDialog.dismiss();

            super.onPostExecute(result);

        }

    }

}
