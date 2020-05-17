package Controller;

import Graphics.*;
import Model.*;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ViewController {
    private Level level = null;
    private boolean gameRunning = false;
    private int currentPlayerId = 0;
    private final int numberOfStepsPerPlayer = 4;
    private int numberOfStepsLeft = 0;

    private Action currentAction = null;
    private Menu menu = null;
    private LevelView levelView = null;
    private JFrame frame = null;

    private Random rand = new Random();

    /**
     * Getter az aktuális játékosra.
     * @return soron lévő játékos
     */
    public Player getCurrentPlayer() {
        return level.getPlayer(currentPlayerId);
    }

    /**
     * Setter a currentAction-re.
     * @param action az éppen végrehajtott akció
     */
    public void setCurrentAction(Action action) {
        currentAction = action;
    }

    /**
     * Getter a currentAction-re.
     * @return az éppen végrehajtott akció
     */
    public Action getCurrentAction() {
        return currentAction;
    }

    public void startMenu() {
        frame = new JFrame("Our Awesome OOF titled eskimo game");
        frame.setSize(1200, 800);
        frame.setResizable(false);
        menu = new Menu(this, frame);

        menu.enable();
        frame.setVisible(true);
    }

    private String readViewData(String fileName, Dimension iceBlockDim, Dimension otherDim, ArrayList<Point> iceBlockPositions) {
        try (Scanner scanner = new Scanner(new File(fileName))) {

            // read data in first row
            String[] firstRow = scanner.nextLine().split(";");
            int numberOfTiles = parseInt(firstRow[0]);
            String playerTypes = firstRow[1];

            // go to where the view location data starts
            for (int i = 0; i < playerTypes.length(); i++) scanner.nextLine();
            for (int i = 0; i < numberOfTiles; i++) scanner.nextLine();

            // read in base width and heights
            String[] dimensionData = scanner.nextLine().split(";");
            String[] ibData = dimensionData[0].split(",");
            String[] otherData = dimensionData[1].split(",");
            iceBlockDim.setSize(Integer.parseInt(ibData[0]), Integer.parseInt(ibData[1]));
            otherDim.setSize(Integer.parseInt(otherData[0]), Integer.parseInt(otherData[1]));

            // read iceblock locations
            for (int i = 0; i < numberOfTiles; i++) {
                String[] strs = scanner.nextLine().split(",");
                iceBlockPositions.add(new Point(Integer.parseInt(strs[0]), Integer.parseInt(strs[1])));
            }
        } catch (FileNotFoundException e) {
            return String.format("ERROR: File \"%s\" given to loadGameWithView does not exist!\n", fileName);
        }

        return "";
    }

    public void loadGame(String fileName) throws Exception {
        // load game
        ConsoleController cc = new ConsoleController();
        String ret = cc.interpret("loadGame " + fileName);
        if (ret.contains("ERROR"))
            throw new Exception(ret);
        level = cc.getLevel();

        // read view data
        Dimension iceBlockDim = new Dimension(), otherDim = new Dimension();
        ArrayList<Point> iceBlockPositions = new ArrayList<>();
        ret = readViewData(fileName, iceBlockDim, otherDim, iceBlockPositions);
        if (ret.contains("ERROR"))
            throw new Exception(ret);

        // setup views

        // iceblocks
        ArrayList<IceBlock> iceBlocks = level.getIceBlocks();
        int maxElements = level.getNumberOfPlayers() + 2; // players + item + building
        for (int i = 0; i < iceBlocks.size(); i++)
            iceBlocks.get(i).addIceBlockView(new IceBlockView(iceBlocks.get(i), iceBlockPositions.get(i), maxElements));
        // players
        ArrayList<Player> players = level.getPlayers();
        for (Player player : players)
            player.addPlayerView(new PlayerView(player));
        // bears
        ArrayList<PolarBear> bears = level.getPolarBears();
        for (PolarBear bear : bears)
            bear.addBearView(new BearView(bear));

        levelView = new LevelView(level, frame, menu);

        Level.setViewsActive(true);
    }

    public void startGame() {
        currentPlayerId = 0;
        level.setGameState(GameStateE.IN_PROGRESS);
    }

    public void handlePlayerTurn() {
        numberOfStepsLeft--;
        if (numberOfStepsLeft == 0) {
            skipTurn();
        }
    }

    public void handleEndOfRound() {
        // step bears
        for (PolarBear bear : level.getPolarBears())
            bear.step(null);

        // destroy tents
        for (IceBlock iceBlock : level.getIceBlocks()) {
            Building b = iceBlock.getBuilding();
            if (b instanceof Tent)
                b.selfDestruct();
        }

        // check player statuses
        for (Player player : level.getPlayers()) {
            player.checkPlayerStatus();
        }

        // call blizzard with 33% chance
        if (rand.nextInt(3) == 0)
            level.blizzard();

        // check if game ended
        //if (level.getGameState() != GameStateE.IN_PROGRESS)
            //TODO display game ended stuff
    }

    public void stepPlayer(IceBlock iceBlock) {
        level.getPlayer(currentPlayerId).step(iceBlock);
        handlePlayerTurn();
    }

    public void usePlayerItem(Item item) {
        level.getPlayer(currentPlayerId).useItem(item);
        handlePlayerTurn();
    }

    public void usePlayerItemOnPlayer(Item item, Player player) {
        level.getPlayer(currentPlayerId).useItemOnPlayer(item, player);
        handlePlayerTurn();
    }

    public void usePlayerAbility(Object o) {
        Player player = level.getPlayer(currentPlayerId);
        if (player instanceof Eskimo)
            ((Eskimo) player).buildIglu();
        else if (player instanceof Researcher)
            ((Researcher) player).checkStability( (IceBlock) o );
        handlePlayerTurn();
    }

    public void swipeSnow() {
        level.getPlayer(currentPlayerId).swipeWithHand();
        handlePlayerTurn();
    }

    public void digOutItem() {
        level.getPlayer(currentPlayerId).digOutItem();
        handlePlayerTurn();
    }

    public void skipTurn() {
        numberOfStepsLeft = numberOfStepsPerPlayer;
        currentPlayerId = (currentPlayerId + 1) % level.getNumberOfPlayers();
        if (currentPlayerId == 0)
            handleEndOfRound();
        levelView.update();
    }
}