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

/**
 * A megjelenítés logikáját kezeli.
 */
public class ViewController {
    private Level level = null;
    private int currentPlayerId = 0;
    private final int numberOfStepsPerPlayer = 4;
    private int numberOfStepsLeft = 0;

    private Action currentAction = null;
    private Menu menu = null;
    private LevelView levelView = null;
    private JFrame frame = null;

    private final Random rand = new Random();

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

    /**
     * Elindítja a start menut.
     */
    public void startMenu() {
        frame = new JFrame("Our Awesome OOF titled eskimo game");
        frame.setSize(1200, 800);
        frame.setResizable(false);
        menu = new Menu(this, frame);

        menu.enable();
        frame.setVisible(true);
    }

    /**
     * Beolvassa a megjelenítéssel kapcsolatos adatokat a megadott nevű fájlból, a megadott pozíció listába.
     * @param fileName fájl neve ahonnan olvasunk
     * @param iceBlockPositions pozíció lista amibe olvassuk az adatokat
     * @return hibaüzenet, ha volt hiba, különben ""
     */
    private String readViewData(String fileName, ArrayList<Point> iceBlockPositions) {
        try (Scanner scanner = new Scanner(new File(fileName))) {

            // read data in first row
            String[] firstRow = scanner.nextLine().split(";");
            int numberOfTiles = parseInt(firstRow[0]);
            String playerTypes = firstRow[1];

            // go to where the view location data starts
            for (int i = 0; i < playerTypes.length(); i++) scanner.nextLine();
            for (int i = 0; i < numberOfTiles; i++) scanner.nextLine();

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

    /**
     * Betölti a játék adatait megjelenítéshez a megadott nevű fájlból
     * @param fileName fájl amiből beolvasunk
     * @throws Exception ha nem sikerült beolvasni, akkor dobja
     */
    public void loadGame(String fileName) throws Exception {
        // load game
        ConsoleController cc = new ConsoleController();
        String ret = cc.interpret("loadGame " + fileName);
        if (ret.contains("ERROR"))
            throw new Exception(ret);
        level = cc.getLevel();

        // read view data
        ArrayList<Point> iceBlockPositions = new ArrayList<>();
        ret = readViewData(fileName, iceBlockPositions);
        if (ret.contains("ERROR"))
            throw new Exception(ret);

        // setup views

        // iceblocks
        ArrayList<IceBlock> iceBlocks = level.getIceBlocks();
        int maxElements = level.getNumberOfPlayers() + 2; // players + item + building
        for (int i = 0; i < iceBlocks.size(); i++)
            iceBlocks.get(i).addIceBlockView(new IceBlockView(iceBlocks.get(i), iceBlockPositions.get(i), maxElements, this));
        // players
        ArrayList<Player> players = level.getPlayers();
        for (Player player : players)
            player.addPlayerView(new PlayerView(player, this));
        // bears
        ArrayList<PolarBear> bears = level.getPolarBears();
        for (PolarBear bear : bears)
            bear.addBearView(new BearView(bear, this));

        levelView = new LevelView(level, frame, menu, this);
        level.addLevelView(levelView);

        Level.setViewsActive(true);
    }

    /**
     * Beállítja az elindított játékhoz a változókat és update-et hív a megjelenítésre.
     */
    public void startGame() {
        currentPlayerId = 0;
        level.setGameState(GameStateE.IN_PROGRESS);
        numberOfStepsLeft = numberOfStepsPerPlayer;
        levelView.update();
    }

    /**
     * Lekezeli egy játékos lépését. Minden lépés után ezt kell meghívni.
     */
    public void handlePlayerTurn() {
        numberOfStepsLeft--;
        if (numberOfStepsLeft == 0) {
            skipTurn();
        }
    }

    /**
     * Lekezeli egy körnek a végét.
     */
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
        if (level.getGameState() == GameStateE.WON)
            levelView.gameWon();
        if (level.getGameState() == GameStateE.LOST)
            levelView.gameLost();

    }

    /**
     * Lépteti az aktuális játékost a megadott iceblock-ra.
     * @param iceBlock amire a játékos lép
     */
    public void stepPlayer(IceBlock iceBlock) {
        level.getPlayer(currentPlayerId).step(iceBlock);
        handlePlayerTurn();
    }

    /**
     * Az aktuális játékos megadott eszközét használja.
     * @param item eszköz amit használ
     */
    public void usePlayerItem(Item item) {
        level.getPlayer(currentPlayerId).useItem(item);
        handlePlayerTurn();
    }

    /**
     * Az aktuális játékos a megadott eszközét használja a megadott játékoson.
     * @param item eszköz amit használ
     * @param player játékos akin használja
     */
    public void usePlayerItemOnPlayer(Item item, Player player) {
        level.getPlayer(currentPlayerId).useItemOnPlayer(item, player);
        handlePlayerTurn();
    }

    /**
     * Használja a játékos képességét.
     * @param o objektum, ha szüksége van rá egy képesség használatakor
     */
    public void usePlayerAbility(Object o) {
        Player player = level.getPlayer(currentPlayerId);
        if (player instanceof Eskimo)
            ((Eskimo) player).buildIglu();
        else if (player instanceof Researcher)
            ((Researcher) player).checkStability( (IceBlock) o );
        handlePlayerTurn();
    }

    /**
     * Az aktuális játékos leseper egy réteg havat.
     */
    public void swipeSnow() {
        level.getPlayer(currentPlayerId).swipeWithHand();
        handlePlayerTurn();
    }

    /**
     * Az aktuális játékos kiássa a eszközt a jégtáblájából.
     */
    public void digOutItem() {
        if(!level.getPlayer(currentPlayerId).getInSea()) {
            IceBlock ib = ((IceBlock) level.getPlayer(currentPlayerId).getLocation());
            if(ib.getItem()!=null) {
                level.getPlayer(currentPlayerId).digOutItem();
                handlePlayerTurn();
                levelView.updateActionViews();
                levelView.update();
            }
        }
    }

    /**
     * Az aktuális játékos átadja a kört a következő játékosnak.
     */
    public void skipTurn() {
        numberOfStepsLeft = numberOfStepsPerPlayer;
        currentPlayerId = (currentPlayerId + 1) % level.getNumberOfPlayers();
        if (currentPlayerId == 0)
            handleEndOfRound();
        levelView.update();
    }
}
