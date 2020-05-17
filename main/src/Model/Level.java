package Model;

import Graphics.ItemView;
import Graphics.LevelView;

import javax.swing.text.View;
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
	private LevelView lv;

	private static boolean viewsActive = false;

	/**
	 * Level konstruktor adott jégtáblákra, játékosokra, medvékre, rakétarészekre
	 * @param iceblocks a jégtáblák listája
	 * @param players a játékosok listája
	 * @param bears a medvék listája
	 * @param parts a rakétarészek listája
	 */
	public Level(ArrayList<IceBlock> iceblocks, ArrayList<Player> players, ArrayList<PolarBear> bears, ArrayList<Part> parts) {
		this.gameState = null;
		this.iceblocks = iceblocks;
		this.players = players;
		this.bears = bears;
		this.parts = parts;
	}

	/**
	 * Igazra állítja a setViewActive-ot.
	 */
	public static void setViewsActive(boolean b) { viewsActive = b; }

	/**
	 * Getter a viewsActive-ra.
	 * @return van-e megjelenítés
	 */
	public static boolean viewsActive() { return viewsActive; }

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
     * @param iceBlocks a jégtáblák listája
     */
	public void blizzard(ArrayList<IceBlock> iceBlocks) {
		if (iceBlocks != null) {
			for (IceBlock ib : iceBlocks) {
				ib.modifyLayers(+1);
				if (ib.getBuilding() == null) {
					List<Player> blockPlayers = ib.getPlayers();
					for(Player blockedp : blockPlayers) {
						blockedp.loseHealth();
					}
				}
			}
		}
	}

	public void blizzard() {
		Random r = new Random();
		int numIceblocks = r.nextInt(iceblocks.size()) + 1;
		boolean[] usedIceblock = new boolean[iceblocks.size()];
		for (boolean ib :usedIceblock) { ib = false; }

		int i = 0;
		while (i < numIceblocks) {
			int randomIndex = r.nextInt(iceblocks.size());
			if (!usedIceblock[randomIndex]) {
				usedIceblock[randomIndex] = true;
				IceBlock ib = iceblocks.get(randomIndex);
				ib.modifyLayers(+1);
				if (ib.getBuilding() == null) {
					for (Player blockedp : ib.getPlayers()) {
						blockedp.loseHealth();
					}
				}
				i++;
			}
		}
	}

	/**
	 * Visszaadja a játékosok számát a szinten.
	 * @return játékosok száma a szinten
	 */
	public int getNumberOfPlayers() {
		return players.size();
	}

	/**
	 * Visszaadja a jégtáblák számát.
	 * @return jégtáblák száma a szinten.
	 */
	public int getNumberOfIceblocks() {
		return iceblocks.size();
	}

	/**
	 * Visszaadja a medvék számát a szinten.
	 * @return a medvék száma a szinten
	 */
	public int getNumberOfBears() {
		return bears.size();
	}

	/**
	 * Visszaadja az adott indexu játékost.
	 * @param playerId játékos index
	 * @return az adott indexű játékos
	 */
	public Player getPlayer(int playerId) {
		return players.get(playerId);
	}

	/**
	 * Visszaadja a játékosok listáját a szinten.
	 * @return a játékosok listája
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Visszaadja az adott indexű jegesmedvét.
	 * @param id az adott index
	 * @return az adott indexű jegesmedve
	 */
	public PolarBear getPolarBear(int id) {
		return bears.get(id);
	}

	/**
	 * Beállítja a szint játékállapotát.
	 * @param state a beállítandó állapot.
	 */
	public void setGameState(GameStateE state) {
		gameState = state;
	}

	/**
	 * Visszaadja a jegesmedvék listáját.
	 * @return jegesmedvék listája
	 */
	public ArrayList<PolarBear> getPolarBears() {
		return bears;
	}

	/**
	 * Visszaadja az aktuális játékállapotot.
	 * @return Játékállapot
	 */
	public GameStateE getGameState() {
		return gameState;
	}

	/**
	 * Visszaadja a jégtáblák listáját.
	 * @return Jégtáblák listája
	 */
	public ArrayList<IceBlock> getIceBlocks() {
		return iceblocks;
	}

	public void addLevelView(LevelView _lv){
		lv = _lv;
	}
	public LevelView getLevelView(){
		return lv;
	}
}
