package Controller;

import Model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Kezeli a kapcsolatot a bemenet és a modell között.
 * Feldolgozza az egyes bemeneti parancsokat és meghívja a modellnek a megfelelő függvényeit.
 */
public class Controller {
    private Level level;
    private boolean gameRunning;
    private Player currentPlayer;

    /**
     * Egységes séma a futtatandó függvényekhez.
     */
    @FunctionalInterface
    interface RunInterface {
        String run(String[] params);
    }

    private final HashMap<String, RunInterface> functionMap;

    /**
     * Parancs paraméterek lehetséges típusai.
     */
    private enum ParamType {
        PLAYER, ICE_BLOCK, BEAR, ITEM
    }

    /**
     * Alap konstruktor ami beállítja az tagváltozókat.
     */
    public Controller() {
        this.level = null;
        this.gameRunning = false;
        this.currentPlayer = null;

        this.functionMap = new HashMap<>();
        this.functionMap.put("loadGame",         this::loadGame);
        this.functionMap.put("blizzard",         this::blizzard);
        this.functionMap.put("stepPlayer",       this::stepPlayer);
        this.functionMap.put("usePlayerItem",    this::usePlayerItem);
        this.functionMap.put("usePlayerAbility", this::usePlayerAbility);
        this.functionMap.put("swipeSnow",        this::swipeSnow);
        this.functionMap.put("digOutItem",       this::digOutItem);
        this.functionMap.put("skipTurn",         this::skipTurn);
        this.functionMap.put("stepPolarBear",    this::stepPolarBear);
        this.functionMap.put("startGame",        this::startGame);
        this.functionMap.put("stat",             this::stat);
        this.functionMap.put("status",           this::status);
        this.functionMap.put("save",             this::save);
    }

    /**
     * A megadott inputot részekre bontja (parancs és paraméterek)
     * és meghívja a parancshoz tartozó függvényt a paraméterekkel,
     * utána pedig visszatér azzal, amivel a függvény visszatért.
     * @param input parancs és paraméterei
     * @return amivel a megadott parancs visszatért
     */
    public String interpret(String input) {
        String[] inputParts = input.split(" ");

        String command = inputParts[0];
        String[] parameters = Arrays.copyOfRange(inputParts, 1, inputParts.length);

        RunInterface function = functionMap.get(command);
        if (function == null)
            return String.format("ERROR: No such command: %s\n", command);
        return function.run(parameters);
    }

    /**
     * Létrehoz egy item-et a megadott item rövidítésből.
     * @param s item rövidítés
     * @return létrehozott item
     */
    private Item createItem(String s) {
        switch (s) {
            case "r":
                return new Rope();
            case "f":
                return new FragileShovel();
            case "s":
                return new Shovel();
            case "d":
                return new DivingSuit();
            case "F":
                return new Food();
            case "t":
                return new Tent();
            case "p":
                return new Part();
            case "-":
                return null;
            default:
                StaticStandardIO.println(String.format("ERROR: No such item: %s", s));
                return null;
        }
    }

    /**
     * Létrehoz egy building-et a megadott building rövidítésből.
     * @param s building rövidítés
     * @return létrehozott building
     */
    private Building createBuilding(String s) {
        switch (s) {
            case "i":
                return new Iglu();
            case "t":
                return new Tent();
            default:
                StaticStandardIO.println(String.format("ERROR: No such building: %s", s));
                return null;
        }
    }

    /**
     * Visszatér a megadott item szöveges rövidítésével.
     * @param item megadott item
     * @return rövidítés
     */
    private String stringFromItem(Item item) {
        if (item instanceof Rope)
            return "r";
        if (item instanceof FragileShovel)
            return "f";
        if (item instanceof Shovel)
            return "s";
        if (item instanceof DivingSuit)
            return "d";
        if (item instanceof Food)
            return "F";
        if (item instanceof Tent)
            return "t";
        if (item instanceof Part)
            return "p";
        return "-";
    }

    /**
     * Visszatér a megadott building szöveges rövidítésével.
     * @param building megaott building
     * @return rövidítés
     */
    private String stringFromBuilding(Building building) {
        if (building instanceof Iglu)
            return "i";
        if (building instanceof Tent)
            return "t";
        return "-";
    }

    /**
     * Visszatér a megadott player szöveges rövidítésével.
     * @param player megaott player
     * @return rövidítés
     */
    private String stringFromPlayer(Player player) {
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(level.getPlayers()));
        return "p" + players.indexOf(player);
    }

    /**
     * Visszatér a megadott PolarBear szöveges rövidítésével.
     * @param bear megaott PolarBear
     * @return rövidítés
     */
    private String stringFromBear(PolarBear bear) {
        ArrayList<PolarBear> bears = new ArrayList<>(Arrays.asList(level.getPolarBears()));
        return "b" + bears.indexOf(bear);
    }

    /**
     * Visszatér a megadott ice block szöveges rövidítésével.
     * @param iceBlock megaott ice block
     * @return rövidítés
     */
    private String stringFromIceBlock(IceBlock iceBlock) {
        ArrayList<IceBlock> iceBlocks = new ArrayList<>(Arrays.asList(level.getIceBlocks()));
        return String.valueOf(iceBlocks.indexOf(iceBlock));
    }

    /**
     * Megnézi, hogy a megadott paraméterek száma megegyezik a megadott típusok számával,
     * illetve megnézi, hogy az egyes megadott paraméterek azok léteznek-e.
     * @param params paraméterek
     * @param types paraméterek típusai sorrendben
     * @return ha volt valami hiba, akkor hibaüzenet, különben egy üres string
     */
    private String checkForErrors(String[] params, ParamType[] types) {
        try {
            if (params.length != types.length)
                throw new InvalidParameterException("ERROR: Invalid number of parameters!\n");

            for (int i = 0; i < types.length; i++) {
                switch (types[i]) {
                    case PLAYER:
                        if (level.getNumberOfPlayers() < parseInt(params[i]))
                            throw new InvalidParameterException("ERROR: Player does not exist!\n");
                        break;
                    case ICE_BLOCK:
                        if (level.getNumberOfIceblocks() < parseInt(params[i]))
                            throw new InvalidParameterException("ERROR: Ice block does not exist!\n");
                        break;
                    case BEAR:
                        if (level.getNumberOfBears() < parseInt(params[i]))
                            throw new InvalidParameterException("ERROR: Polar bear does not exist!\n");
                        break;
                    default:
                }
            }

        } catch (InvalidParameterException e) {
            return e.getMessage();
        }
        return "";
    }

    /**
     * Betölti a pálya leírást egy fájlból és az egyes táblákat és játékosokat létrehozza.
     * @param params fájl elérési útvonala ahonnan beolvassuk a pályát
     * @return ha volt valami hiba, akkor hibaüzenet, különben egy üres string
     */
    private String loadGame(String[] params) {
        // error handling
        if (params.length != 1)
            return "ERROR: Invalid number of loadGame parameters!\n";

        try {
            Scanner scanner = new Scanner(new File(params[0]));

            String[] firstRow = scanner.nextLine().split(";");
            int numberOfTiles = parseInt(firstRow[0]);
            String playerTypes = firstRow[1];
            int numberOfBears = parseInt(firstRow[2]);


            // create players
            Player[] players = new Player[playerTypes.length()];
            for (int i = 0; i < playerTypes.length(); i++) {

                // read player info
                String[] playerStats = scanner.nextLine().split(";");
                int health = parseInt(playerStats[0]);
                String[] itemLetters = playerStats[1].split(",");

                // create inventory
                Inventory inventory = new Inventory();
                ArrayList<Item> createdItems = new ArrayList<>();
                for (String itemLetter : itemLetters) {
                    Item item = createItem(itemLetter);
                    if (item == null)
                        break;
                    createdItems.add(item);
                    inventory.addItem(item);
                }

                // create player
                if (playerTypes.charAt(i) == 'e')
                    players[i] = new Eskimo(level, inventory, health);
                else if (playerTypes.charAt(i) == 'r')
                    players[i] = new Researcher(level, inventory, health);

                // call picked up by for items
                for (Item item : createdItems)
                    item.pickedUpBy(players[i]);

            }

            // create bears
            PolarBear[] bears = new PolarBear[numberOfBears];

            // create tiles
            IceBlock[] iceblocks = new IceBlock[numberOfTiles];
            String[][] neighboursByTile = new String[numberOfTiles][];
            ArrayList<Item> parts = new ArrayList<>();
            for (int i = 0; i < numberOfTiles; i++) {

                // read tile info

                String[] tileInfo = scanner.nextLine().split(";");
                String[] charactersOnTile = tileInfo[0].split(",");
                String[] playersInSea = tileInfo[1].split(",");
                String itemS = tileInfo[2];
                String buildingS = tileInfo[3];
                int capacity = parseInt(tileInfo[4]);
                int snowLayers = parseInt(tileInfo[5]);
                neighboursByTile[i] = tileInfo[6].split(",");

                // create objects on tile

                // get players on tile
                ArrayList<Player> playersOnTile = new ArrayList<>();
                for (String characterS : charactersOnTile) {
                    if (characterS.charAt(0) == 'p')
                        playersOnTile.add(players[characterS.charAt(1) - 1]);
                }
                // create sea and add players to it
                Sea sea = new Sea();
                for (String pS : playersInSea) {
                    sea.addPlayer(players[pS.charAt(1) - 1]);
                }
                // create item, building
                Item item = createItem(itemS);
                if (itemS.equals("p"))
                    parts.add(item);
                Building building = createBuilding(buildingS);

                // create tile

                if (capacity >= playerTypes.length())
                    iceblocks[i] = new IceBlock((Player[]) playersOnTile.toArray(), item,
                            building, snowLayers, capacity);
                else
                    iceblocks[i] = new UnstableIceBlock((Player[]) playersOnTile.toArray(), item,
                            building, snowLayers, capacity);

                // connect object to tile

                // connect sea
                iceblocks[i].setSea(sea);
                sea.setPosition(iceblocks[i]);
                // connect bears
                for (String characterS : charactersOnTile) {
                    if (characterS.charAt(0) == 'b')
                        bears[characterS.charAt(1) - 1].setIb(iceblocks[i]);
                }
            }

            // connect neighbours
            for (int i = 0; i < numberOfTiles; i++)
                for (int j = 0; j < neighboursByTile[i].length; j++)
                    iceblocks[i].addNeighbour(iceblocks[parseInt(neighboursByTile[i][j]) - 1]);

            // create level
            this.level = new Level(iceblocks, players, bears, (Item[]) parts.toArray());

        } catch (FileNotFoundException e) {
            return String.format("ERROR: File \"%s\" given to loadGame does not esist!\n", params[0]);
        }

        return "";
    }

    /**
     * Meghívja a level blizzard() függvényét. Ha params üres, akkor csak egy üres tömböt ad át,
     * különben a paraméterben kapott mező sorszámoknak megfelelő táblákat.
     * @param params blizzard bemeneti nyelvi parancs paraméterei
     * @return ha volt valami hiba, akkor hibaüzenet, különben egy üres string
     */
    private String blizzard(String[] params) {
        IceBlock[] iceBlocks = new IceBlock[params.length];
        for (int i = 0; i < params.length; i++)
            iceBlocks[i] = level.getIceBlock(parseInt(params[i]));
        level.blizzard(iceBlocks);
        return "";
    }

    /**
     * Ha fut a játék, akkor az aktuálisan soron lévő játékosnak meghívja a step() függvényét
     * a paraméterben megadott sorszámú táblával.
     * Ha nem fut a játék, akkor a megadott sorszámú játékoson hajtja végre ugyanezt.
     * Ha nem létezik a megadott sorszámú tábla vagy a megadott sorszámú játékos,
     * akkor egy hiba üzenettel tér vissza, ami "ERROR:"-ral kezdődik.
     * @param params stepPlayer bemeneti nyelvi parancs paraméterei
     * @return ha volt valami hiba, akkor hibaüzenet, különben egy üres string
     */
    private String stepPlayer(String[] params) {
        if (gameRunning) {
            // error handling
            ParamType[] paramTypes = new ParamType[] {ParamType.ICE_BLOCK};
            String ret = checkForErrors(params, paramTypes);
            if (!ret.equals("")) return ret;

            IceBlock iceBlock = level.getIceBlock(parseInt(params[0]));
            currentPlayer.step(iceBlock);
        } else {
            // error handling
            ParamType[] paramTypes = new ParamType[] {ParamType.PLAYER, ParamType.ICE_BLOCK};
            String ret = checkForErrors(params, paramTypes);
            if (!ret.equals("")) return ret;

            Player player = level.getPlayer(parseInt(params[0]));
            IceBlock iceBlock = level.getIceBlock(parseInt(params[1]));
            player.step(iceBlock);
        }

        return "";
    }

    /**
     * Ha fut a játék, akkor az aktuálisan soron lévő játékosnak hívja meg a useItem() függvényét
     * egy olyan tárggyal amit a paraméterben kapott tárgy rövidítés alapján hoz létre.
     * Ha nem fut a játék, akkor a megadott sorszámú játékoson hajtja végre ugyanezt.
     * Ha nem létezik a megadott sorszámú játékos, vagy nincs a játékosnál olyan tárgy,
     * amit megadtak, akkor visszatér egy hibaüzenettel, ami "ERROR:"-ral kezdődik.
     * @param params usePlayerItem bemeneti nyelvi parancs paraméterei
     * @return ha volt valami hiba, akkor hibaüzenet, különben egy üres string
     */
    private String usePlayerItem(String[] params) {
        if (gameRunning) {
            // error handling
            ParamType[] paramTypes;
            if (params.length <= 1) paramTypes = new ParamType[] {ParamType.ITEM};
            else paramTypes = new ParamType[] {ParamType.ITEM, ParamType.PLAYER};
            String ret = checkForErrors(params, paramTypes);
            if (!ret.equals("")) return ret;

            Item item = createItem(params[0]);
            if (params.length == 1) {
                currentPlayer.useItem(item);
            } else {
                Player other_player = level.getPlayer(parseInt(params[1]));
                currentPlayer.useItemOnPlayer(item, other_player);
            }
        } else {
            // error handling
            ParamType[] paramTypes;
            if (params.length <= 2) paramTypes = new ParamType[] {ParamType.PLAYER, ParamType.ITEM};
            else paramTypes = new ParamType[] {ParamType.PLAYER, ParamType.ITEM, ParamType.PLAYER};
            String ret = checkForErrors(params, paramTypes);
            if (!ret.equals("")) return ret;

            Player player = level.getPlayer(parseInt(params[0]));
            Item item = createItem(params[1]);
            if (params.length == 2) {
                player.useItem(item);
            } else {
                Player other_player = level.getPlayer(parseInt(params[2]));
                player.useItemOnPlayer(item, other_player);
            }
        }

        return "";
    }

    /**
     * Ha fut a játék, akkor az aktuálisan soron lévő játékosnak hívja meg a megfelelő függvényét
     * (Eskimo esetén: buildIglu(), Redearcher esetén: checkStability(megadott sorszámú tábla)).
     * Ha nem fut a játék, akkor a megadott sorszámú játékoson hajtja végre ugyanezt.
     * Ha nem létezik a megadott sorszámú játékos vagy tábla,
     * akkor visszatér egy hibaüzenettel, ami "ERROR:"-ral kezdődik.
     * @param params usePlayerAbility bemeneti nyelvi parancs paraméterei
     * @return ha volt valami hiba, akkor hibaüzenet, különben egy üres string
     */
    private String usePlayerAbility(String[] params) {
        int capacity = -1;
        if (gameRunning) {
            // error handling
            ParamType[] paramTypes = new ParamType[] {ParamType.ICE_BLOCK};
            String ret = checkForErrors(params, paramTypes);
            if (!ret.equals("")) return ret;

            IceBlock iceBlock = level.getIceBlock(parseInt(params[0]));
            if (currentPlayer instanceof Eskimo)
                ((Eskimo)currentPlayer).buildIglu(iceBlock);
            else
                capacity = ((Researcher)currentPlayer).checkStability(iceBlock);

        } else {
            // error handling
            ParamType[] paramTypes = new ParamType[] {ParamType.PLAYER, ParamType.ICE_BLOCK};
            String ret = checkForErrors(params, paramTypes);
            if (!ret.equals("")) return ret;

            Player player = level.getPlayer(parseInt(params[0]));
            IceBlock iceBlock = level.getIceBlock(parseInt(params[0]));
            if (currentPlayer instanceof Eskimo)
                ((Eskimo)player).buildIglu(iceBlock);
            else
                capacity = ((Researcher)player).checkStability(iceBlock);
        }

        return capacity != -1 ? "IceBlock has capacity of: " + capacity : "";
    }

    /**
     * Ha fut a játék, akkor az aktuálisan soron lévő játékosnak hívja meg a swipeWithHand() függvényét.
     * Ha nem fut a játék, akkor a megadott sorszámú játékoson hajtja végre ugyanezt.
     * Ha nem létezik a megadott sorszámú játékos,
     * akkor visszatér egy hibaüzenettel, ami "ERROR:"-ral kezdődik.
     * @param params swipeSnow bemeneti nyelvi parancs paraméterei
     * @return ha volt valami hiba, akkor hibaüzenet, különben egy üres string
     */
    private String swipeSnow(String[] params) {
        if (gameRunning) {
            currentPlayer.swipeWithHand();
        } else {
            // error handling
            ParamType[] paramTypes = new ParamType[] {ParamType.PLAYER};
            String ret = checkForErrors(params, paramTypes);
            if (!ret.equals("")) return ret;

            Player player = level.getPlayer(parseInt(params[0]));
            player.swipeWithHand();
        }

        return "";
    }

    /**
     * Ha fut a játék, akkor az aktuálisan soron lévő játékosnak hívja meg a digOutItem() függvényét.
     * Ha nem fut a játék, akkor a megadott sorszámú játékoson hajtja végre ugyanezt.
     * Ha nem létezik a megadott sorszámú játékos vagy tábla,
     * akkor visszatér egy hibaüzenettel, ami "ERROR:"-ral kezdődik.
     * @param params digOutItem bemeneti nyelvi parancs paraméterei
     * @return ha volt valami hiba, akkor hibaüzenet, különben egy üres string
     */
    private String digOutItem(String[] params) {
        if (gameRunning) {
            currentPlayer.digOutItem();
        } else {
            // error handling
            ParamType[] paramTypes = new ParamType[] {ParamType.PLAYER};
            String ret = checkForErrors(params, paramTypes);
            if (!ret.equals("")) return ret;

            Player player = level.getPlayer(parseInt(params[0]));
            player.digOutItem();
        }

        return "";
    }

    /**
     * Ha fut a játék, akkor a soron következő játékos lesz az aktuális és visszatér egy "skip"-el.
     * Ellenkező esetben egy "ERROR:"-ral kezdődő hibaüzenettel.
     * @param params skipTurn bemeneti nyelvi parancs paraméterei
     * @return ha volt valami hiba, akkor hibaüzenet, különben "skip"
     */
    private String skipTurn(String[] params) {
        if (gameRunning) {
            ArrayList<Player> players = new ArrayList<>(Arrays.asList(level.getPlayers()));
            int idx = players.indexOf(currentPlayer);
            if (idx + 1 >= players.size())
                idx = -1;
            currentPlayer = players.get(idx + 1);
        } else {
            return "ERROR: Game not running, cannot skip turn!\n";
        }

        return "skip";
    }

    /**
     * Meghívja a paraméterben megadott sorszámú jegesmedve step() függvényét
     * Ha meg volt adva tábla sorszám, akkor azzal a táblával, ha nem volt, megadva, akkor null-al.
     * Ha nem létezik a megadott sorszámú jegesmedve vagy tábla,
     * akkor visszatér egy hibaüzenettel, ami "ERROR:"-ral kezdődik.
     * @param params stepPolarBear bemeneti nyelvi parancs paraméterei
     * @return ha volt valami hiba, akkor hibaüzenet, különben egy üres string
     */
    private String stepPolarBear(String[] params) {
        // Error handling
        ParamType[] paramTypes;
        if (params.length <= 1) paramTypes = new ParamType[] {ParamType.BEAR};
        else paramTypes = new ParamType[] {ParamType.BEAR, ParamType.ICE_BLOCK};
        String ret = checkForErrors(params, paramTypes);
        if (!ret.equals("")) return ret;

        PolarBear bear = level.getPolarBear(parseInt(params[0]));
        if (params.length == 1) {
            bear.step(null);
        } else {
            IceBlock iceBlock = level.getIceBlock(parseInt(params[1]));
            bear.step(iceBlock);
        }

        return "";
    }

    /**
     * Elindítja a játékot logikát.
     * @param params startGame bemeneti nyelvi parancs paraméterei
     * @return ha volt valami hiba, akkor hibaüzenet, különben egy üres string
     */
    private String startGame(String[] params) {
        Player[] players = level.getPlayers();
        PolarBear[] bears = level.getPolarBears();

        gameRunning = true;
        currentPlayer = players[0];
        level.setGameState(GameStateE.IN_PROGRESS);
        String ret = "";
        while (gameRunning) {
            // step players
            for (Player player : players) {
                for (int i = 0; i < 4; i++) {
                    boolean valid = false;
                    while (!valid) {
                        String command = StaticStandardIO.readLine();
                        ret = interpret(command);
                        if (ret.equals("skip"))
                            break;
                        if (!ret.contains("ERROR")) {
                            valid = true;
                            StaticStandardIO.print(ret);
                        }
                    }
                }
                if (!ret.equals("skip"))
                    interpret("skipTurn");
            }

            // step bears
            for (PolarBear bear : bears) {
                bear.step(null);
            }

            // check player statuses
            for (Player player : players) {
                player.checkPlayerStatus();
            }

            // check if game ended
            if (level.getGameState() != GameStateE.IN_PROGRESS)
                gameRunning = false;
        }

        if (level.getGameState() == GameStateE.WON)
            return "Game won";
        return "Game over";
    }

    /**
     * Visszatér annak a táblának a sorszámával amin a megadott sorszámú játékos áll,
     * illetve ennek a játékosnak az élet erejével és azokkal a tárgyakkal amik a játékosnál vannak.
     * Ha nem létezik a megadott sorszámú játékos,
     * akkor visszatér egy hibaüzenettel, ami "ERROR:"-ral kezdődik.
     * @param params stat bemeneti nyelvi parancs paraméterei
     * @return ha volt valami hiba, akkor hibaüzenet, különben egy üres string
     */
    private String stat(String[] params) {
        // error handling
        ParamType[] paramTypes = new ParamType[] {ParamType.PLAYER};
        String ret = checkForErrors(params, paramTypes);
        if (!ret.equals("")) return ret;

        // get data of player
        Player player = level.getPlayer(parseInt(params[0]));
        PlayerContainerI container = player.getLocation();
        IceBlock iceBlock;
        if (container instanceof Sea) {
            Sea sea = (Sea) container;
            iceBlock = sea.getPosition();
        } else {
            iceBlock = (IceBlock) container;
        }
        ArrayList<IceBlock> iceBlocks = new ArrayList<>(Arrays.asList(level.getIceBlocks()));
        int iceBlockId = iceBlocks.indexOf(iceBlock);

        // create output
        StringBuilder output = new StringBuilder();
        output.append(iceBlockId).append(";");
        output.append(player.getHealth()).append(";");
        for (Item item : player.getInventory().getItems())
            output.append(stringFromItem(item)).append(",");
        if (player.getInventory().getItems().length == 1)
            output.append("-");

        return output.toString();
    }

    /**
     * Lekéri egyesével a játékosok és az egyes táblák állapotát és visszatér velük
     * olyan formátumban ahogyan a kimeneti nyelvben meg van adva.
     * @param params status bemeneti nyelvi parancs paraméterei
     * @return ha volt valami hiba, akkor hibaüzenet, különben egy üres string
     */
    private String status(String[] params) {
        Player[] players = level.getPlayers();
        IceBlock[] iceBlocks = level.getIceBlocks();
        ArrayList<PolarBear> bears = new ArrayList<>(Arrays.asList(level.getPolarBears()));

        StringBuilder output = new StringBuilder();

        // build first row
        output.append(iceBlocks.length).append(";");
        for (Player player : players)
            output.append(stringFromPlayer(player));
        output.append(";");
        output.append(bears.size()).append("\n");

        // build player info
        for (Player player : players) {
            output.append(player.getHealth()).append(";");
            for (Item item : player.getInventory().getItems()) {
                output.append(stringFromItem(item)).append(",");
            }
            if (player.getInventory().getItems().length == 1)
                output.append("-");
            output.append("\n");
        }

        // build tile info
        for (IceBlock iceBlock : iceBlocks) {
            // characters on ice block
            for (Player player : iceBlock.getPlayers())
                output.append(stringFromPlayer(player)).append(",");
            for (PolarBear bear : bears)
                if (bear.getIb().equals(iceBlock))
                    output.append(stringFromBear(bear)).append(",");
            output.append(";");

            // players in sea
            for (Player player : iceBlock.getSea().getPlayers())
                output.append(stringFromPlayer(player)).append(",");
            output.append(";");

            // item
            output.append(stringFromItem(iceBlock.getItem())).append(";");

            // building
            output.append(stringFromBuilding(iceBlock.getBuilding())).append(";");

            // capacity
            output.append(iceBlock.getCapacity()).append(";");

            // snowLayers
            output.append(iceBlock.getLayer()).append(";");

            // neighbours
            for (IceBlock neighbour : iceBlock.getNeighbours())
                output.append(stringFromIceBlock(neighbour)).append(",");
            output.append(";");
            output.append("\n");
        }

        return output.toString();
    }

    /**
     * Hív egy interpret()-et a paraméterben kapott paranccsal
     * és a visszatérési értékét kiírja a paraméterben megadott fájlba.
     * @param params save bemeneti nyelvi parancs paraméterei
     * @return ha volt valami hiba, akkor hibaüzenet, különben egy üres string
     */
    private String save(String[] params) {
        String fileName = params[0];
        String command = String.join("", Arrays.copyOfRange(params, 1, params.length));
        String ret = interpret(command);

        FileWriter myWriter;
        try {
            myWriter = new FileWriter(fileName);
            myWriter.write(ret);
            myWriter.close();
        } catch (IOException e) {
            return "ERROR: File error!";
        }

        return "";
    }
}
