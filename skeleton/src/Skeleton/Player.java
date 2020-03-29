package Skeleton;

import java.util.ArrayList;

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
			FunctionLogger.log_call(String.format("Skeleton.PlayerContainerI container.movePlayer(%s, %s)", name, p.get(0)));
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
		FunctionLogger.log_call(String.format("Skeleton.Inventory inventory.contains(%s)", p.get(0)));
		boolean ret = inventory.contains(item);
		FunctionLogger.log_boolean_return(ret);
		return ret;
	}

	/**
	 * Ellenorzi, hogy a jatekos tengerben van-e
	 * és ha nincsen rajta buvarruha, akkor a jatekos meghal.
	 */
	public void checkPlayerStatus() {
		String type = FunctionLogger.get_obj_type();
		String name = FunctionLogger.get_obj_name();
		FunctionLogger.log_call("Skeleton.Inventory inventory.contains(divingSuit)");
		boolean hasDivingSuit = inventory.contains(new DivingSuit());
		FunctionLogger.log_boolean_return(hasDivingSuit);
		if (inSea && !hasDivingSuit) {
			FunctionLogger.log_call(String.format("%s %s.die()", type, name));
			die();
			FunctionLogger.log_return("");
		}
	}

	/**
	 * Getter az inSea valtozora.
	 * @return tengerben van-e a jatekos
	 */
	public boolean getInSea() {
		return FunctionLogger.ask_question("Tengerben van a jatekos?");
	}

	/**
	 * A jatekos eletero potjat eggyel csokkenti.
	 */
	public void loseHealth() {
		String type = FunctionLogger.get_obj_type();
		String name = FunctionLogger.get_obj_name();
		if (FunctionLogger.ask_question("Kevesebb mint nulla a jatekos elete?")) {
			FunctionLogger.log_call(String.format("%s %s.die()", type, name));
			die();
			FunctionLogger.log_return("");
		}
	}

	/**
	 * A jatekos megeszik egy egysegnyi etelt igy az eletero pontjat eggyel noveli.
	 */
	public void eat() {
		// nen csinal semmit sem, mivel allapotot tarolna
	}

	/**
	 * A jatekos hasznalja az adott targyat.
	 * @param item a hasznalani kivant targy
	 */
	public void useItem(Item item) {
		String name = FunctionLogger.get_obj_name();
		ArrayList<String> p = FunctionLogger.get_parameters();
		if (!getInSea()) {
			FunctionLogger.log_call(String.format("Skeleton.Inventory inventory.use(%s, %s)", p.get(0), name));
			inventory.use(item, this);
		}
	}

	/**
	 * A jatekos lesopor egy egysegnyi havat a jegtablarol.
	 */
	public void swipeWithHand() {
		if (!getInSea()) {
			FunctionLogger.log_call("Skeleton.IceBlock ib.modifyLayers(-1)");
			((IceBlock) container).modifyLayers(-1);
			FunctionLogger.log_return("");
		}
	}

	/**
	 * A jatekos kiassa a jegtablabol a benne levo targyat.
	 */
	public void digOutItem() {
		String name = FunctionLogger.get_obj_name();
		if (!getInSea()) {
			FunctionLogger.log_call("Skeleton.IceBlock ib.getLayer()");
			IceBlock ib  = ((IceBlock) container);
			int layer = ib.getLayer();
			FunctionLogger.log_return("");
			if (!FunctionLogger.ask_question("Van ho a jegtablan?")) {
				FunctionLogger.log_call("Skeleton.IceBlock ib.getItem()");
				Item item = ib.getItem();
				String itemType = item.toString();
				String itemName = String.valueOf(itemType.toLowerCase().charAt(0));
				FunctionLogger.log_return(itemName);
				FunctionLogger.log_call(String.format("%s %s.addToInventory(inventory)"));
				boolean success = item.addToInventory(inventory);
				FunctionLogger.log_boolean_return(success);
				if (success) {
					FunctionLogger.log_call("Skeleton.IceBlock ib.removeItem()");
					ib.removeItem();
					FunctionLogger.log_return("");
					FunctionLogger.log_call(String.format("%s %s.pickUpBy(%s)", itemType, itemName, name));
					item.pickedUpBy(this);
					FunctionLogger.log_return("");
				}
			}
		}
	}

	/**
	 * Visszaadja azt a kontenert, amiben a jatekos tartozkodik.
	 * @return a kontener amiben a jatekos tartozkodik
	 */
	public PlayerContainerI getLocation() {
		return container;
	}

	/**
	 * Ertesiti a szintet, hogy vege a jateknak.
	 */
	public void die() {
		FunctionLogger.log_call("Skeleton.Level levelLost()");
		level.levelLost();
		FunctionLogger.log_return("");
	}
}
