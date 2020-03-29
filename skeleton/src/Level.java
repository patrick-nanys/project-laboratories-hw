import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Level {
	private IceBlock[] iceblocks;
	private Part[] parts;
	private GameStateE gameState;
	private int numberOfTiles, numberOfItems, numberOfPlayers;

	public Level() {
		parts = new Part[3];
		iceblocks = new IceBlock[numberOfTiles];
	}

	public void checkParts() {
		if (parts[0].getLocation() == parts[1].getLocation() && parts[0].getLocation() == parts[2].getLocation()) {

		}
	}
	
	public void addIceBlock(IceBlock ib) {

	}
	
	public void init() {
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



	}
	
	public void levelWon() {
		gameState = GameStateE.WON;
	}
	
	public void levelLost() {
		gameState = GameStateE.LOST;
	}
	
	public void blizzard() {
	}
}
