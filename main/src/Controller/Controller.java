package Controller;

import Model.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReferenceArray;

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
            case "-":
                return null;
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
    private String stringFromItem(Object item) {
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
    private String stringFromPlayer(Object player) {
        ArrayList<Player> players = level.getPlayers();
        return "p" + (players.indexOf((Player) player) + 1);
    }

    /**
     * Visszatér a megadott player típusának szöveges rövidítésével.
     * @param player megaott player
     * @return rövidítés
     */
    private String stringTypeFromPlayer(Player player) {
        if (player instanceof Eskimo)
            return "e";
        if (player instanceof Researcher)
            return "r";
        return "-";
    }

    /**
     * Visszatér a megadott PolarBear szöveges rövidítésével.
     * @param bear megaott PolarBear
     * @return rövidítés
     */
    private String stringFromBear(PolarBear bear) {
        ArrayList<PolarBear> bears = level.getPolarBears();
        return "b" + (bears.indexOf(bear) + 1);
    }

    /**
     * Visszatér a megadott ice block szöveges rövidítésével.
     * @param iceBlock megaott ice block
     * @return rövidítés
     */
    private String stringFromIceBlock(Object iceBlock) {
        ArrayList<IceBlock> iceBlocks = level.getIceBlocks();
        return String.valueOf(iceBlocks.indexOf((IceBlock) iceBlock) + 1);
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
            ArrayList<Player> players = new ArrayList<>();
            List<List<Item>> createdItems = new ArrayList<>(playerTypes.length());
            for (int i = 0; i < playerTypes.length(); i++) {
                // extend createdItems list
                createdItems.add(new ArrayList<>());

                // read player info
                String[] playerStats = scanner.nextLine().split(";");
                int health = parseInt(playerStats[0]);
                String[] itemLetters = playerStats[1].split(",");

                // create inventory
                Inventory inventory = new Inventory();

                for (String itemLetter : itemLetters) {
                    Item item = createItem(itemLetter);
                    if (item == null)
                        break;
                    createdItems.get(i).add(item);
                    inventory.addItem(item);
                }

                // create player
                if (playerTypes.charAt(i) == 'e')
                    players.add(new Eskimo(inventory, health));
                else if (playerTypes.charAt(i) == 'r')
                    players.add(new Researcher(inventory, health));
            }

            // create bears
            ArrayList<PolarBear> bears = new ArrayList<>();
            for (int i = 0; i < numberOfBears; i++)
                bears.add(new PolarBear());

            // create tiles
            ArrayList<IceBlock> iceblocks = new ArrayList<>();
            String[][] neighboursByTile = new String[numberOfTiles][];
            ArrayList<Part> parts = new ArrayList<>();
            for (int i = 0; i < numberOfTiles; i++) {

                // read tile info

                String[] tileInfo = scanner.nextLine().split(";");
                if (tileInfo.length != 7)
                    return "ERROR: There should be 7 properties for an IceBlock!\n";

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
                if (!charactersOnTile[0].equals("-")) {
                    for (String characterS : charactersOnTile) {
                        if (characterS.charAt(0) == 'p')
                            playersOnTile.add(players.get(Character.getNumericValue(characterS.charAt(1)) - 1));
                    }
                }
                // create sea and add players to it
                Sea sea = new Sea();
                if (!playersInSea[0].equals("-")) {
                    for (String pS : playersInSea) {
                        Player playerInSea = players.get(Character.getNumericValue(pS.charAt(1)) - 1);
                        sea.addPlayer(playerInSea);
                        playerInSea.setInSea(true);
                    }
                }
                // create item, building
                Item item = createItem(itemS);
                if (itemS.equals("p"))
                    parts.add((Part) item);
                Building building = createBuilding(buildingS);

                // create tile

                if (capacity >= playerTypes.length())
                    iceblocks.add(new IceBlock(playersOnTile.toArray(new Player[0]), item,
                            building, snowLayers, capacity));
                else
                    iceblocks.add(new UnstableIceBlock(playersOnTile.toArray(new Player[0]), item,
                            building, snowLayers, capacity));

                // connect object to tile

                // connect players
                for (Player player : playersOnTile) {
                    player.setContainer(iceblocks.get(iceblocks.size() - 1));
                    player.setInSea(false);
                }
                // connect bears
                for (String characterS : charactersOnTile) {
                    if (characterS.charAt(0) == 'b')
                        bears.get(Character.getNumericValue(characterS.charAt(1)) - 1).setIb(iceblocks.get(i));
                }
                // connect sea
                iceblocks.get(i).setSea(sea);
                sea.setPosition(iceblocks.get(i));
            }

            // connect neighbours
            for (int i = 0; i < numberOfTiles; i++) {
                for (int j = 0; j < neighboursByTile[i].length; j++) {
                    if (!neighboursByTile[i][j].equals("-"))
                        iceblocks.get(i).addNeighbour(iceblocks.get(parseInt(neighboursByTile[i][j]) - 1));
                }
            }

            // create level
            this.level = new Level(iceblocks, players, bears, parts);

            // set level for players
            for (Player player : players)
                player.setLevel(this.level);

            // call pickedUpBy for items now that level has been set for players
            for (int i = 0; i < playerTypes.length(); i++) {
                for (Item item : createdItems.get(i))
                    item.pickedUpBy(players.get(i));
            }
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
        ArrayList<IceBlock> iceBlocks = new ArrayList<>();
        for (String param : params)
            iceBlocks.add(level.getIceBlock(parseInt(param) - 1));
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

            IceBlock iceBlock = level.getIceBlock(parseInt(params[0]) - 1);
            currentPlayer.step(iceBlock);
        } else {
            // error handling
            ParamType[] paramTypes = new ParamType[] {ParamType.PLAYER, ParamType.ICE_BLOCK};
            String ret = checkForErrors(params, paramTypes);
            if (!ret.equals("")) return ret;

            Player player = level.getPlayer(parseInt(params[0]) - 1);
            IceBlock iceBlock = level.getIceBlock(parseInt(params[1]) - 1);
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
                Player other_player = level.getPlayer(parseInt(params[1]) - 1);
                currentPlayer.useItemOnPlayer(item, other_player);
            }
        } else {
            // error handling
            ParamType[] paramTypes;
            if (params.length <= 2) paramTypes = new ParamType[] {ParamType.PLAYER, ParamType.ITEM};
            else paramTypes = new ParamType[] {ParamType.PLAYER, ParamType.ITEM, ParamType.PLAYER};
            String ret = checkForErrors(params, paramTypes);
            if (!ret.equals("")) return ret;

            Player player = level.getPlayer(parseInt(params[0]) - 1);
            Item item = createItem(params[1]);
            if (params.length == 2) {
                player.useItem(item);
            } else {
                Player other_player = level.getPlayer(parseInt(params[2]) - 1);
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
        Player player;
        ParamType[] paramTypes;

        //error handling
        if (gameRunning) {
            player = level.getPlayer(parseInt(params[0]) - 1);
            if (player instanceof Eskimo)
                paramTypes = new ParamType[] {};
            else
                paramTypes = new ParamType[] {ParamType.ICE_BLOCK};
        } else {
            player = level.getPlayer(parseInt(params[0]) - 1);
            if (player instanceof Eskimo)
                paramTypes = new ParamType[] {ParamType.PLAYER};
            else
                paramTypes = new ParamType[] {ParamType.PLAYER, ParamType.ICE_BLOCK};
        }
        String ret = checkForErrors(params, paramTypes);
        if (!ret.equals("")) return ret;

        if (player instanceof Eskimo) {
            ((Eskimo)player).buildIglu();
        }
        else {
            IceBlock iceBlock = level.getIceBlock(parseInt(params[0]) - 1);
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

            Player player = level.getPlayer(parseInt(params[0]) - 1);
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

            Player player = level.getPlayer(parseInt(params[0]) - 1);
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
            ArrayList<Player> players = level.getPlayers();
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

        PolarBear bear = level.getPolarBear(parseInt(params[0]) - 1);
        if (params.length == 1) {
            bear.step(null);
        } else {
            IceBlock iceBlock = level.getIceBlock(parseInt(params[1]) - 1);
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
        ArrayList<Player> players = level.getPlayers();
        ArrayList<PolarBear> bears = level.getPolarBears();

        gameRunning = (level.getGameState() == null);
        currentPlayer = players.get(0);
        level.setGameState(GameStateE.IN_PROGRESS);
        String ret = "";
        while (gameRunning) {
            // step players
            for (Player ignored : players) {
                for (int i = 0; i < 4; i++) {
                    boolean valid = false;
                    while (!valid) {
                        String command = StaticStandardIO.readLine();
                        if (command == null) return "";
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

            // destroy tents
            for (IceBlock iceBlock : level.getIceBlocks()) {
                Building b = iceBlock.getBuilding();
                if (b instanceof Tent)
                    ((Tent) b).selfDestruct();
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

    //TODO comment
    @FunctionalInterface
    private interface GetStringCommand{
        String getString(Object o);
    }

    //TODO comment
    private void buildStringInfo(ArrayList<?> objects, GetStringCommand command, StringBuilder stringBuilder) {
        if (objects.size() == 0) {
            stringBuilder.append("-");
        } else {
            ArrayList<String> strings = new ArrayList<>();
            for (Object o : objects)
                strings.add(command.getString(o));
            stringBuilder.append(String.join(",", strings));
        }
    }

    /**
     * A megadott játékosnak beleépíti az adatait a megadott StringBuilder-be.
     * @param player játékos akinek kellenek az adatai
     * @param output megadott kimenet az adatoknak
     */
    private void outputPlayerInfo(Player player, StringBuilder output) {
        output.append(player.getHealth()).append(";");
        ArrayList<Item> playerItems = (ArrayList<Item>) player.getInventory().getItems();
        buildStringInfo(playerItems, this::stringFromItem, output);
        output.append("\n");
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

        // get player and id of the ice block he is standing on
        Player player = level.getPlayer(parseInt(params[0]) - 1);
        PlayerContainerI container = player.getLocation();
        IceBlock iceBlock;
        if (container instanceof Sea) {
            Sea sea = (Sea) container;
            iceBlock = sea.getPosition();
        } else {
            iceBlock = (IceBlock) container;
        }
        ArrayList<IceBlock> iceBlocks = level.getIceBlocks();
        int iceBlockId = iceBlocks.indexOf(iceBlock);

        // create output
        StringBuilder output = new StringBuilder();
        output.append(iceBlockId).append(";");
        outputPlayerInfo(player, output);

        return output.toString();
    }

    /**
     * Lekéri egyesével a játékosok és az egyes táblák állapotát és visszatér velük
     * olyan formátumban ahogyan a kimeneti nyelvben meg van adva.
     * @param params status bemeneti nyelvi parancs paraméterei
     * @return ha volt valami hiba, akkor hibaüzenet, különben egy üres string
     */
    private String status(String[] params) {
        ArrayList<Player> players = level.getPlayers();
        ArrayList<IceBlock> iceBlocks = level.getIceBlocks();
        ArrayList<PolarBear> bears = level.getPolarBears();

        StringBuilder output = new StringBuilder();

        // build first row
        output.append(iceBlocks.size()).append(";");
        for (Player player : players)
            output.append(stringTypeFromPlayer(player));
        output.append(";");
        output.append(bears.size()).append("\n");

        // build player info
        for (Player player : players)
            outputPlayerInfo(player, output);

        // build tile info
        for (IceBlock iceBlock : iceBlocks) {
            // characters on ice block
            List<Player> playersOnBlock = iceBlock.getPlayers();
            if (playersOnBlock.size() != 0)
                buildStringInfo((ArrayList<Player>) playersOnBlock, this::stringFromPlayer, output);
            ArrayList<String> bearsOnIceBlockS = new ArrayList<>();
            for (PolarBear bear : bears)
                if (bear.getIb().equals(iceBlock))
                    bearsOnIceBlockS.add(stringFromBear(bear));
            if (playersOnBlock.size() == 0 && bearsOnIceBlockS.size() == 0)
                output.append("-").append(";");
            else
                output.append(String.join(",", bearsOnIceBlockS)).append(";");

            // players in sea
            buildStringInfo((ArrayList<Player>) iceBlock.getSea().getPlayers(), this::stringFromPlayer, output);
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
            buildStringInfo((ArrayList<IceBlock>) iceBlock.getNeighbours(), this::stringFromIceBlock, output);
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
