package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model.Level osztaly, reprezental egy szintet a jatekban.
 * iceblocks, a palyan levo jegtablak
 * parts, a palyan levo, a jatek megnyeresehez szukseges alkatreszek
 * gamestate, a jatek allasa
 * numberOfTiles, numberOfItems, numberOfPlayers, a jatekban talalhato
 * jegtablak, targyak, jatekosok mennyisege.
 */
public class Level {
	private ArrayList<IceBlock> iceblocks;
	private ArrayList<Part> parts;
	private GameStateE gameState;
	private int numberOfTiles, numberOfItems, numberOfPlayers;

	public Level(int tiles, int items, int players) {
		parts = new ArrayList<>(3);
		iceblocks = new ArrayList<>();
		gameState = GameStateE.IN_PROGRESS;
		numberOfTiles = tiles;
		numberOfItems = items;
		numberOfPlayers = players;
	}

	/**
	 * Inicializalja a Levelt. Egyenlore tesztelesnel ures, mert kezzel inicializalunk.
	 */
	public void init() {
		/*
		for (int i = 0; i < numberOfTiles; i++) {
			addIceBlock();
		}

		for (int i = 0; i < numberOfItems; i++) {

		}

		for (Model.IceBlock ib : iceblocks) {

		}

		for (int i = 0; i < numberOfItems; i++) {
			Random r = new Random();
			int index = r.nextInt(numberOfTiles);
			iceblocks[index].addItem();
		}

		for (int i = 0; i < numberOfPlayers; i++) {
			Random r = new Random();
			if (r.nextInt(2) == 0)
				Model.Eskimo p = new Model.Eskimo();
			else
				Model.Researcher p = new Model.Researcher();

			int index = r.nextInt(numberOfTiles);
			// mindenki külön blokkon kell, hogy legyen??
			iceblocks[index].addPlayer(p);
		}


*/
	}

	/**
	 * Ellenorzi, hogy az osszes jatekos es az osszes alkatresz egy mezon van-e. Ha igen, akkor atallitja a
	 * szint gameState-jet WON-ra.
	 */
	public void checkParts() {
		String name = FunctionLogger.get_obj_name();
		if (FunctionLogger.ask_question("Egy mezon vannak a jatekosok az alkatreszekkel?")) {
			FunctionLogger.log_call(String.format("%s.levelWon()",name));
			levelWon();
		}
	}

	/**
	 * Hozzaad egy alkatreszt a szinthez.
	 * @param p a hozzaadni kivant alkatresz.
	 */
	public void addPart(Part p) {
		ArrayList<String> param =  FunctionLogger.get_parameters();
		FunctionLogger.log_call(String.format("Model.Part parts.add(%s)",param.get(0)));
		parts.add(p);
		FunctionLogger.log_return("");
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
	 * Hozzaad egy adott Model.IceBlock-ot a levelhez.
	 * @param ib a hozzaadni kivant Model.IceBlock.
	 */
	public void addIceBlock(IceBlock ib) {
		ArrayList<String> param =  FunctionLogger.get_parameters();
		FunctionLogger.log_call(String.format("Model.IceBlock iceblocks.add(%s)",param.get(0)));
		iceblocks.add(ib);
		FunctionLogger.log_return("");
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
	 * Reprezental egy hovihart.
	 * Random Model.IceBlock-oknak noveli a horeteget,
	 * es sebzi a rajta levo jatekosokat, ha
	 * nincs rajta iglu.
	 * Teszteleshez, ha csak 1 Model.IceBlock van, akkor arra mindenkepp meghivodik.
	 */
	public void blizzard() {
		if (iceblocks.size() == 1) {
			FunctionLogger.log_call("Model.IceBlock ib.modifyLayers(+1)");
			iceblocks.get(0).modifyLayers(+1);
			FunctionLogger.log_return("");
			FunctionLogger.log_call("Model.IceBlock ib.getIglu()");
			if (!iceblocks.get(0).getIglu()) {
				FunctionLogger.log_return("");
				FunctionLogger.log_call("Model.IceBlock ib.getPlayers()");
				List<Player> blockPlayers = iceblocks.get(0).getPlayers();
				FunctionLogger.log_return("players");
				for(int n =0;n<blockPlayers.size();n++) {
					String playertype = blockPlayers.get(n).toString();
					String playername;
					if(playertype.equals("Model.Researcher")) playername = "r";
					else playername = "e";
					FunctionLogger.log_call(String.format("%s %s.loseHealth()", playertype, playername));
					iceblocks.get(0).getPlayers().get(n).loseHealth();
					FunctionLogger.log_return("");
				}
			}
		}
		else if(iceblocks.size()>1) {
			for (int i = 0; i < iceblocks.size(); i++) {
				FunctionLogger.log_call("Model.IceBlock ib.modifyLayers(+1)");
				iceblocks.get(i).modifyLayers(+1);
				FunctionLogger.log_return("");
				FunctionLogger.log_call("Model.IceBlock ib.getIglu()");
				if (!iceblocks.get(i).getIglu()) {
					FunctionLogger.log_return("");
					FunctionLogger.log_call("Model.IceBlock ib.getPlayers()");
					List<Player> blockPlayers = iceblocks.get(i).getPlayers();
					FunctionLogger.log_return("players");
					for(int n=0;n<blockPlayers.size();n++) {
						String playertype = blockPlayers.get(n).toString();
						String playername;
						if(playertype.equals("Model.Researcher")) playername = "r";
						else playername = "e";
						FunctionLogger.log_call(String.format("%s %s.loseHealth()",playertype, playername));
						blockPlayers.get(n).loseHealth();
						FunctionLogger.log_return("");
					}
				}
			}
		}
	}
}
