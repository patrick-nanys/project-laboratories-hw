import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Jatekosokat reprezentalo absztrakt osztály.
 */
public abstract class Player {
	private boolean inSea;
	private int health;
	private int turnCounter;
	private Inventory inventory;
	private Level level;
	private PlayerContainerI container;

	Player(Level level) {
		this.level = level;
	}

	/**
	 * A jatekos lep egyet az adott iranyba.
	 * @param d a megadott irany amibe leptetni akarjuk
	 */
	public void step(DirectionE d) {
		String name = FunctionLogger.get_obj_name();
		ArrayList<String> p = FunctionLogger.get_parameters();
		if (!inSea) {
			FunctionLogger.log_call("PlayerContainerI container.movePlayer(" + name + p.get(0) + ")");
			container.movePlayer(this, d);
			FunctionLogger.log_return("");
		}
	}

	/**
	 * Visszaadja, hogy a jatekosnal van-e a parameterkent kapott fajta targy.
	 * @param item keresett targynak a tipusa
	 * @return jatekosnal van-e a targy
	 */
	public boolean hasItem(Item item) {
		ArrayList<String> p = FunctionLogger.get_parameters();
		FunctionLogger.log_call("Inventory inventory.contains(" + p.get(0) + ")");
		boolean ret = inventory.contains(item);
		FunctionLogger.log_boolean_return(ret);
		return ret;
	}

	/**
	 * Ellenorzi, hogy a jatekos tengerben van-e
	 * és ha nincsen rajta buvarruha, akkor a jatekos meghal.
	 */
	public void checkPlayerStatus() {
		String name = FunctionLogger.get_obj_name();
		FunctionLogger.log_call("Inventory inventory.contains(divingSuit)");
		boolean hasDivingSuit = inventory.contains(new DivingSuit());
		FunctionLogger.log_boolean_return(hasDivingSuit);
		if (inSea && !hasDivingSuit) {
			FunctionLogger.log_call("Player " + name + ".die()");
			die();
			FunctionLogger.log_return("");
		}
	}

	/**
	 * Getter az inSea valtozora.
	 * @return tengerben van-e a jatekos
	 */
	public boolean getInSea() {
		return FunctionLogger.ask_question("Vizben van a jatekos?");
	}

	/**
	 * A jatekos eletero potjat eggyel csokkenti.
	 */
	public void loseHealth() {
		String name = FunctionLogger.get_obj_name();
		if (FunctionLogger.ask_question("Kevesebb mint nulla a jatekos elete?")) {
			FunctionLogger.log_call("Player");
			die();
		}
		// kerdes: kevesebb-e mint nulla az elete
	}

	/**
	 * A jatekos megeszik egy egysegnyi etelt igy az eletero pontjat eggyel noveli.
	 */
	public void eat() {
	}

	/**
	 * A jatekos hasznalja az adott targyat.
	 * @param item a hasznalani kivant targy
	 */
	public void useItem(Item item) {
	}

	/**
	 * A jatekos lesopor egy egysegnyi havat a jegtablarol.
	 */
	public void swipeWithHand() {
	}

	/**
	 * A jatekos kiassa a jegtablabol a benne levo targyat.
	 */
	public void digOutItem() {
	}

	/**
	 * Visszaadja azt a kontenert, amiben a jatekos tartozkodik.
	 * @return a kontener amiben a jatekos tartozkodik
	 */
	public PlayerContainerI getLocation() {
	}

	/**
	 * Ertesiti a szintet, hogy vege a jateknak.
	 */
	public void die() {
	}
}
