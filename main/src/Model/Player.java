package Model;

import java.util.ArrayList;

/**
 * Jatekosokat reprezentalo absztrakt osztály.
 */
public abstract class Player extends Steppable {
	protected boolean inSea;
	protected int health;
	protected int turnCounter;
	protected Inventory inventory;
	protected Level level;
	protected PlayerContainerI container;

	Player(Level level) {
		this.level = level;
		inSea = false;
		FunctionLogger.log_call("<<create>> Model.Inventory inventory");
		inventory = new Inventory();
		FunctionLogger.log_return("");
	}

	Player(Level level, Inventory inventory, int health) {
		this.level = level;
		inSea = false;
		this.inventory = inventory;
		this.health = health;
	}
	public void addItem(Item i) {
		inventory.addItem(i);
		i.pickedUpBy(this);
	}

	/**
	 * A jatekos lep egyet az adott iranyba.
	 * @param d a megadott irany amibe leptetni akarjuk
	 */
	public void step(IceBlock ib) {
		String name = FunctionLogger.get_obj_name();
		ArrayList<String> p = FunctionLogger.get_parameters();
		String containertype = container.toString();
		if (!getInSea()) {
			FunctionLogger.log_call(String.format("%s container.movePlayer(%s, %s)",containertype, name, p.get(0)));
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
		FunctionLogger.log_call(String.format("Model.Inventory inventory.contains(%s)", p.get(0)));
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
		FunctionLogger.log_call("Model.Inventory inventory.contains(divingSuit)");
		boolean hasDivingSuit = inventory.contains(new DivingSuit());
		FunctionLogger.log_boolean_return(hasDivingSuit);
		if (getInSea() && !hasDivingSuit) {
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
	 * Visszaadja a leltarat
	 * @return A jatekos inventoryja.
	 */
	public Inventory getInventory() {
		return inventory;
	}
	/**
	 * A jatekos eletero potjat eggyel csokkenti.
	 */
	public void loseHealth() {
		String type = FunctionLogger.get_obj_type();
		String name = FunctionLogger.get_obj_name();
		if (FunctionLogger.ask_question("Kevesebb mint egy a jatekos elete?")) {
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
			FunctionLogger.log_call(String.format("Model.Inventory inventory.use(%s, %s)", p.get(0), name));
			inventory.use(item, this);
			FunctionLogger.log_return("");
		}
	}

	public void useItemOnPlayer(Item item, Player player) {
		ArrayList<String> p = FunctionLogger.get_parameters();
		if (!getInSea()) {
			FunctionLogger.log_call(String.format("Model.Inventory inventory.use(%s, %s)", p.get(0), p.get(1)));
			inventory.use(item, player);
			FunctionLogger.log_return("");
		}
	}

	/**
	 * A jatekos lesopor egy egysegnyi havat a jegtablarol.
	 */
	public void swipeWithHand() {
		if (!getInSea()) {
			FunctionLogger.log_call("Model.IceBlock ib.modifyLayers(-1)");
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
			FunctionLogger.log_call("Model.IceBlock ib.getLayer()");
			IceBlock ib  = ((IceBlock) container);
			int layer = ib.getLayer();
			FunctionLogger.log_return("");
			if (!FunctionLogger.ask_question("Van ho a jegtablan?")) {
				FunctionLogger.log_call("Model.IceBlock ib.getItem()");
				Item item = ib.getItem();
				String itemType = item.toString();
				String itemName = String.valueOf(itemType.toLowerCase().charAt(0));
				FunctionLogger.log_return(itemName);
				FunctionLogger.log_call(String.format("%s %s.addToInventory(inventory)", itemType, itemName));
				boolean success = item.addToInventory(inventory);
				FunctionLogger.log_boolean_return(success);
				if (success) {
					FunctionLogger.log_call("Model.IceBlock ib.removeItem()");
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
	 * Beallitja, hogy a jatekos melyik containeren all.
	 * @param cr a kontener, amin all
	 */
	public void setContainer(PlayerContainerI cr) {
		container = cr;
	}
	/**
	 * Ertesiti a szintet, hogy vege a jateknak.
	 */
	public void die() {
		FunctionLogger.log_call("Model.Level levelLost()");
		level.levelLost();
		FunctionLogger.log_return("");
	}

	/**
	 * Model.Level getter
	 * @return a jatekos eppen melyik szinten jatszik
	 */
	public Level getLevel() {
		return level;
	}
	/**
	 * Teszteleshez, visszaadja az osztaly nevet.
	 * @return az osztaly neve.
	 */
	@Override
	public abstract String toString();

    public abstract int getHealth();
}
