package com.jdlundberg.rockpaperscissorslizardspock;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Architect on 4/8/2015.
 */
public class GetSystemWeapon {

    public String getSystemWeapon() {

        ArrayList<String> systemWeaponList = new ArrayList<>();
        String systemWeapon;
        Random random = new Random();
        Integer weaponIndex = random.nextInt(4);

        systemWeaponList.add("Scissors");
        systemWeaponList.add("Spock");
        systemWeaponList.add("Paper");
        systemWeaponList.add("Lizard");
        systemWeaponList.add("Rock");

        systemWeapon = systemWeaponList.get(weaponIndex);

        return systemWeapon;

    }
}
