package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Model.Level osztaly, reprezental egy szintet a jatekban.
 * iceblocks, a palyan levo jegtablak
 * parts, a palyan levo, a jatek megnyeresehez szukseges alkatreszek
 * gamestate, a jatek allasa
 * bears a jegesmedvék a pályán
 * numberOfTiles, numberOfItems, numberOfPlayers, numberOfBears a jatekban talalhato
 * jegtablak, targyak, jatekosok, jegesmedvék mennyisege.
 */
public class Level {
	private ArrayList<IceBlock> iceblocks;
	private ArrayList<Part> parts;
	private ArrayList<Player> players;
	private ArrayList<PolarBear> bears;
	private GameStateE gameState;
	private int numberOfTiles, numberOfItems, numberOfPlayers, numberOfBears;

	public Level(int tiles, int items, int nPlayers, int nBears) {
		parts = new ArrayList<>(3);
		iceblocks = new ArrayList<>();
		players = new ArrayList<>();
		bears = new ArrayList<>();
		gameState = GameStateE.IN_PROGRESS;
		numberOfTiles = tiles;
		numberOfItems = items;
		numberOfPlayers = nPlayers;
		numberOfBears = nBears;
	}

	public Level(IceBlock[] iceblocks, Player[] players, PolarBear[] bears, Item[] parts) {

	}

    /**
	 *
	 */
    /*
	public void init() {
		ArrayList<Item> items = new ArrayList<>();
		Random r = new Random();

		for (int i = 0; i < numberOfTiles; i++) {
			boolean iceblockType = r.nextBoolean();
			if (iceblockType)
				addIceBlock(new IceBlock());
			else {
				int cap = r.nextInt(numberOfPlayers);
				addIceBlock(new UnstableIceBlock(cap));
			}
		}

		for (int i = 0; i < numberOfItems-3; i++) {
			int itemType = r.nextInt(6);
			switch (itemType) {
				case 0: items.add(new DivingSuit()); break;
				case 1: items.add(new Food()); break;
				case 2: items.add(new FragileShovel()); break;
				case 3: items.add(new Tent()); break;
				case 4: items.add(new Rope()); break;
				case 5: items.add(new Shovel()); break;
			}
		}

		//TODO parts

		//TODO How about neighbours?
		for (IceBlock ib : iceblocks) {

		}

		for (int i = 0; i < numberOfItems; i++) {
			Random r = new Random();
			int index = r.nextInt(numberOfTiles);
			iceblocks.get(index).addItem();
		}

		for (int i = 0; i < numberOfPlayers; i++) {
			Random r = new Random();
			if (r.nextBoolean())
				Eskimo p = new Eskimo(this, new Inventory(), 5);
			else
				Researcher p = new Researcher(this, new Inventory(), 6);
			//TODO hogy is volt ez? Mennyit életük van?

			int index = r.nextInt(numberOfTiles);
			// mindenki külön blokkon kell, hogy legyen??
			iceblocks.get(index).addPlayer(p);
		}

	}
	*/

	/**
	 * Ellenorzi, hogy az osszes jatekos es az osszes alkatresz egy mezon van-e. Ha igen, akkor atallitja a
	 * szint gameState-jet WON-ra.
	 */
	public void checkParts() {
		for (IceBlock ib: iceblocks) {
			for (Part part : parts) {
				if (!part.getLocation().equals(ib))
					break;
			}
			for (Player p : getPlayers()) {
				if (!p.getLocation().equals(ib))
					break;
			}
			levelWon();
			return;
		}
	}

	/**
	 * Hozzaad egy alkatreszt a szinthez.
	 * @param p a hozzaadni kivant alkatresz.
	 */
	public void addPart(Part p) {
		parts.add(p);
	}

	/**
	 * Lekerdezi az adott indexen levo jegtablat.
	 * @param index a jegtabla indexe, amire kivancsiak vagyunk
	 * @return az adott indexen levo Model.IceBlock
	 */
	public IceBlock getIceBlock(int index) {
		return iceblocks.get(index);
	}

	/**
	 * Hozzaad egy adott IceBlock-ot a levelhez.
	 * @param ib a hozzaadni kivant IceBlock.
	 */
	public void addIceBlock(IceBlock ib) {
		iceblocks.add(ib);
	}

	/**
	 * A jatekosok nyertek.
	 * Beallitja a gameState-et WON-ra.
	 */
	public void levelWon() {
		gameState = GameStateE.WON;
	}

	/**
	 * A jatekosok vesztettek.
	 * Beallitja a gameState-et LOST-ra.
	 */
	public void levelLost() {
		gameState = GameStateE.LOST;
	}

	/**
	 * A vihar függvénye. A megadott jégtáblákra plusz egy réteg havat rak,
	 * valamint sebzi a játékosokat, ha a táblán nincs iglu vagy sátor.
	 * Ha nincs megadva jégtábla lista, akkor random mennyiségű,
	 * random indexű jégtáblákra hívódik meg.
     * @param iceBlocks_
     */
	public void blizzard(IceBlock[] iceBlocks_) {
		if (iceBlocks_ != null) {
			for (IceBlock ib : iceBlocks_) {
				ib.modifyLayers(+1);
				if (ib.getBuilding() == null) {
					List<Player> blockPlayers = ib.getPlayers();
					for(Player blockedp : blockPlayers) {
						blockedp.loseHealth();
					}
				}
			}
		}
		else {
			//TODO random jégtáblák
		}
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public int getNumberOfIceblocks() {
		return numberOfTiles;
	}

	public int getNumberOfBears() {
		return numberOfBears;
	}

	public Player getPlayer(int playerId) {
		return getPlayers()[playerId];
	}

	public Player[] getPlayers() {
		return players.toArray(new Player[0]);
	}

	public PolarBear getPolarBear(int id) {
		return bears.get(id);
	}

	public void setGameState(GameStateE state) {
		gameState = state;
	}

	public PolarBear[] getPolarBears() {
		return bears.toArray(new PolarBear[0]);
	}

	public GameStateE getGameState() {
		return gameState;
	}

	public IceBlock[] getIceBlocks() {
		return iceblocks.toArray(new IceBlock[0]);
	}
}
