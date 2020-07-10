package com.mattarama.mcstonks.api;

import com.mattarama.mcstonks.McStonks;
import org.bukkit.configuration.ConfigurationSection;

import java.io.IOException;
import java.util.Random;

/**
 * Manages Player interactions with the API
 */
public class PlayerAPI {

    /**
     * Assigns a random user token to a player
     *
     * @param username The username of the player
     */
    public static void AssignIDToPlayer(String username) {

        //Generates token and checks if it is already used
        String token;

        while (true) {

            //Creates token
            token = GenerateToken();

            //Checks if the token is used
            if (!TokenIsTaken(token)) {
                break;
            }

        }

        //Sets user token
        McStonks.playerConfig.set("players." + username + ".api.usertoken", token);
        try {
            McStonks.playerConfig.save(McStonks.playerConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Checks if a generated token is taken
     *
     * @param token String token
     * @return String is taken
     */
    public static boolean TokenIsTaken(String token) {

        //Gets the players section of the player config file
        ConfigurationSection section = McStonks.playerConfig.getConfigurationSection("players");

        //Checks if the token is taken
        for (String s : section.getKeys(false)) {

            if (section.getString(s + ".api.usertoken").equals(token)) {

                //Token is already in use
                return true;

            }

        }

        //Token is not taken
        return false;

    }

    /**
     * Generates a token with a length of 64
     *
     * @return A randomly generated token
     */
    static String GenerateToken() {

        String token = "";
        String characters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890!@#$%^&*()";
        Random rnd = new Random();

        for (int i = 0; i < 64; i++) {

            token += characters.charAt(rnd.nextInt(characters.length()));

        }

        return token;

    }

    /**
     * Gets the ID of a player by their token
     *
     * @param token Token of the player
     * @return The username, if not NULL
     */
    public static String GetUsernameByToken(String token) {

        //Gets the players section of the player config file
        ConfigurationSection section = McStonks.playerConfig.getConfigurationSection("players");

        //Checks if the token is taken
        for (String s : section.getKeys(false)) {

            if (section.get(s + ".api.usertoken").equals(token)) {

                //Token is already in use
                return s;

            }

        }

        return null;

    }

}
