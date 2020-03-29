package Skeleton;

import java.util.ArrayList;
import java.util.Random;

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

		for (Skeleton.IceBlock ib : iceblocks) {

		}

		for (int i = 0; i < numberOfItems; i++) {
			Random r = new Random();
			int index = r.nextInt(numberOfTiles);
			iceblocks[index].addItem();
		}

		for (int i = 0; i < numberOfPlayers; i++) {
			Random r = new Random();
			if (r.nextInt(2) == 0)
				Skeleton.Eskimo p = new Skeleton.Eskimo();
			else
				Skeleton.Researcher p = new Skeleton.Researcher();

			int index = r.nextInt(numberOfTiles);
			// mindenki külön blokkon kell, hogy legyen??
			iceblocks[index].addPlayer(p);
		}


*/
	}

	public void checkParts() {
		if (FunctionLogger.ask_question("Egy mezon vannak a jatekosok az alkatreszekkel?")) {
			levelWon();
		}
	}

	public void addPart(Part p) {
		ArrayList<String> param =  FunctionLogger.get_parameters();
		FunctionLogger.log_call("Skeleton.Part parts.add(" + param.get(0) + ")");
		parts.add(p);
		FunctionLogger.log_return("");
	}

	public IceBlock getIceBlock(int index) {
		return iceblocks.get(index);
	}

	public void addIceBlock(IceBlock ib) {
		ArrayList<String> param =  FunctionLogger.get_parameters();
		FunctionLogger.log_call("Skeleton.IceBlock iceblocks.add(" + param.get(0) + ")");
		iceblocks.add(ib);
		FunctionLogger.log_return("");
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
