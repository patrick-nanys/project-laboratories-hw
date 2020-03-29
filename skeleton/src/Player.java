import java.util.ArrayList;
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
		if (!inSea) {
			FunctionLogger.log_call("PlayerContainerI container.movePlayer(this, d)");
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
		ArrayList<String> parameters = FunctionLogger.get_parameters();
		FunctionLogger.log_call("Inventory inventory.contains(" + parameters.get(0) + ")");
		boolean ret = inventory.contains(item);
		FunctionLogger.log_boolean_return(ret);
		return ret;
	}

	/**
	 * Ellenorzi, hogy a jatekos tengerben van-e
	 * és ha nincsen rajta buvarruha, akkor a jatekos meghal.
	 */
	public void checkPlayerStatus() {
		FunctionLogger.log_call("Inventory inventory.contains(new DivingSuit())");
		boolean hasDivingSuit = inventory.contains(new DivingSuit());
		//hasDivingSuit = FunctionLogger.get_return(); ?????????????
		FunctionLogger.log_predefined_return();
		if (inSea && !hasDivingSuit) {
			FunctionLogger.log_call("Player this.die()");
			die();
			FunctionLogger.log_return("");
		}
	}

	/**
	 * Getter az inSea valtozora.
	 * @return tengerben van-e a jatekos
	 */
	public boolean getInSea() {

	}

	/**
	 * Setter az inSea valtozora.
	 * @param b a megadott ertek amire allitjuk az inSea-t
	 */
	public void setInSea(boolean b) {

	}

	/**
	 * A jatekos eletero potjat eggyel csokkenti.
	 */
	public void loseHealth() {
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
