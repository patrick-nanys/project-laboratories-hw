package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Controller {
    private Level level;
    private boolean gameRunning;
    private Player currentPlayer;
    private HashMap<String, Runnable> functionMap;

    public Controller(Level level, boolean gameRunning, Player currentPlayer) {
        this.level = level;
        this.gameRunning = gameRunning;
        this.currentPlayer = currentPlayer;
    }

    public String interpret(String input) {
        ArrayList<String> parts = new ArrayList<String>(Arrays.asList(input.split(" ")));
        String command = parts.get(0);
        ArrayList<String> parameters = new ArrayList<String>(parts.subList(1, parts.size()));
        String out = functionMap.get(command).run(parameters);
    }
}
