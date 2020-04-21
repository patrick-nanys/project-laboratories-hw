package Controller;

import Model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

@FunctionalInterface
interface RunInterface {
    String run(ArrayList<String> params);
}

public class Controller {
    private Level level;
    private boolean gameRunning;
    private Player currentPlayer;

    private final HashMap<String, RunInterface> functionMap;

    public Controller(Level level, boolean gameRunning, Player currentPlayer) {
        this.level = level;
        this.gameRunning = gameRunning;
        this.currentPlayer = currentPlayer;

        this.functionMap = new HashMap<>();
        this.functionMap.put("loadGame",         Controller::loadGame);
        this.functionMap.put("blizzard",         Controller::blizzard);
        this.functionMap.put("stepPlayer",       Controller::stepPlayer);
        this.functionMap.put("usePlayerItem",    Controller::usePlayerItem);
        this.functionMap.put("usePlayerAbility", Controller::usePlayerAbility);
        this.functionMap.put("swipeSnow",        Controller::swipeSnow);
        this.functionMap.put("digOutItem",       Controller::digOutItem);
        this.functionMap.put("skipTurn",         Controller::skipTurn);
        this.functionMap.put("stepPolarBear",    Controller::stepPolarBear);
        this.functionMap.put("startGame",        Controller::startGame);
        this.functionMap.put("stat",             Controller::stat);
        this.functionMap.put("status",           Controller::status);
        this.functionMap.put("save",             Controller::save);
    }

    public String interpret(String input) {
        ArrayList<String> parts = new ArrayList<>(Arrays.asList(input.split(" ")));

        String command = parts.get(0);
        ArrayList<String> parameters = new ArrayList<>(parts.subList(1, parts.size()));

        RunInterface function = functionMap.get(command);
        if (function == null)
            return String.format("ERROR: No such command: %s\n", command);
        return function.run(parameters);
    }

    private Item createItem(char c) {
        switch(c) {
            case 'r':
                return new Rope();
            case 'f':
                return new FragileShovel();
            case 's':
                return new Shovel();
            case 'd':
                return new DivingSuit();
            case 'F':
                return new Food();
            case 't':
                return new Tent();
            case 'p':
                return new Part();
            default:
                System.out.println(String.format("ERROR: No such item: %c", c));
                return null;
        }
    }

    private static String loadGame(ArrayList<String> params) {
        // error handling
        if (params.size() != 1)
            return "ERROR: Invalid number of loadGame parameters!\n";

        try {
            Scanner scanner = new Scanner(new File(params.get(0)));
            String[] firstRow = scanner.nextLine().split(";");
            int numberOfTiles = parseInt(firstRow[0]);
            String playerTypes = firstRow[1];
            int numberOfBears = parseInt(firstRow[2]);

            Player[] players = new Player[playerTypes.length()];

            for (int i = 0; i < playerTypes.length(); i++) {

                String[] playerStats = scanner.nextLine().split(";");

                // create inventory
                Inventory inv = new Inventory();
                // have to call pickedUpBy()

                if (playerTypes.charAt(i) == 'e')
                    players[i] = new Eskimo(playerStats[0], );

            }
        } catch (FileNotFoundException e) {
            return String.format("ERROR: File \"%s\" given to loadGame doesnt esist!\n", params.get(0));
        }

        return "";
    }

    private static String blizzard(ArrayList<String> params) {
        return "";
    }

    private static String stepPlayer(ArrayList<String> params) {
        return "";
    }

    private static String usePlayerItem(ArrayList<String> params) {
        return "";
    }

    private static String usePlayerAbility(ArrayList<String> params) {
        return "";
    }

    private static String swipeSnow(ArrayList<String> params) {
        return "";
    }

    private static String digOutItem(ArrayList<String> params) {
        return "";
    }

    private static String skipTurn(ArrayList<String> params) {
        return "";
    }

    private static String stepPolarBear(ArrayList<String> params) {
        return "";
    }

    private static String startGame(ArrayList<String> params) {
        return "";
    }

    private static String stat(ArrayList<String> params) {
        return "";
    }

    private static String status(ArrayList<String> params) {
        return "";
    }

    private static String save(ArrayList<String> params) {
        return "";
    }
}
