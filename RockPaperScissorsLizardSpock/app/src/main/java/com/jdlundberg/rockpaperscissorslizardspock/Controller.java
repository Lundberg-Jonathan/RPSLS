package com.jdlundberg.rockpaperscissorslizardspock;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Architect on 4/7/2015.
 */
public class Controller {

    private HashMap<String, Handler> controlHash = new HashMap<>();

    public Controller() {

        RockHandler rockHandler = new RockHandler();
        controlHash.put("Rock", rockHandler);

        PaperHandler paperHandler = new PaperHandler();
        controlHash.put("Paper", paperHandler);

        ScissorsHandler scissorsHandler = new ScissorsHandler();
        controlHash.put("Scissors", scissorsHandler);

        LizardHandler lizardHandler = new LizardHandler();
        controlHash.put("Lizard", lizardHandler);

        SpockHandler spockHandler = new SpockHandler();
        controlHash.put("Spock", spockHandler);

    }

    public void handle(String command, Object... params) {

        Handler task = controlHash.get(command);

        task.handleIt(params);

    }

}
