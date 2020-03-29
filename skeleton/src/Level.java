import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Level {
	private ArrayList<IceBlock> iceblocks;
	private ArrayList<Part> parts;
	private GameStateE gameState;
	private int numberOfTiles, numberOfItems, numberOfPlayers;

	public Level() {
		parts = new ArrayList<>(3);
		iceblocks = new ArrayList<>();
	}

	public void init() {
		/*
		for (int i = 0; i < numberOfTiles; i++) {
			addIceBlock();
		}

		for (int i = 0; i < numberOfItems; i++) {

		}

		for (IceBlock ib : iceblocks) {

		}

		for (int i = 0; i < numberOfItems; i++) {
			Random r = new Random();
			int index = r.nextInt(numberOfTiles);
			iceblocks[index].addItem();
		}

		for (int i = 0; i < numberOfPlayers; i++) {
			Random r = new Random();
			if (r.nextInt(2) == 0)
				Eskimo p = new Eskimo();
			else
				Researcher p = new Researcher();

			int index = r.nextInt(numberOfTiles);
			// mindenki külön blokkon kell, hogy legyen??
			iceblocks[index].addPlayer(p);
		}


*/
	}

	public void checkParts() {
		if (parts.get(0).getLocation() == parts.get(1).getLocation() && parts.get(0).getLocation() == parts.get(2).getLocation()) {
			levelWon();
		}
	}

	public void addPart(Part p) {
		parts.add(p);
	}

	public IceBlock getIceBlock(int index) {
		return iceblocks.get(index);
	}

	public void addIceBlock(IceBlock ib) {
		iceblocks.add(ib);
	}
	
	public void levelWon() {
		gameState = GameStateE.WON;
	}
	
	public void levelLost() {
		gameState = GameStateE.LOST;
	}
	
	public void blizzard() {
		Random r = new Random();
		int blizzardIceBlocks = r.nextInt(numberOfTiles);
		int i = 0;
		while (i < blizzardIceBlocks) {
			int nextib = r.nextInt(numberOfTiles);
			if (!iceblocks.get(nextib).getIglu()) {
				iceblocks.get(nextib).modifyLayers(+1);
				for (Player p : iceblocks.get(nextib).getPlayers())
					p.loseHealth();
				i++;
			}
		}
	}
}
