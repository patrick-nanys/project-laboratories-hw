package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Ez az absztrakt osztály a játékosokat reprezentálja a játékban.
 * A játékosok célja, hogy összegyűjtsék az alkatrészeket, és ezzel megnyerjék a játékot.
 * Ebből az osztályból származnak a Eskimo és Researcher osztályok.
 */
public abstract class Player implements Steppable {
	protected boolean inSea;
	protected int health;
	private Inventory inventory;
	protected Level level;
	protected PlayerContainerI container;

	/**
	 * Player konstruktora adott leltárra és életerő pontra.
	 * @param inventory a játékos leltára.
	 * @param health a játékos életerő pontja
	 */
	Player(Inventory inventory, int health) {
		inSea = false;
		this.inventory = inventory;
		this.health = health;
	}

	/**
	 * Hozzáad egy Item-et a játékoshoz,
	 * aki pedig beteszi a leltárába.
	 * @param i Az Item, amit hozzáad.
	 */
	public void addItem(Item i) {
		inventory.addItem(i);
		i.pickedUpBy(this);
	}

	/**
	 * A jatekos lep az adott konténerre.
	 * @param pc a megadott irany, amibe leptetni akarjuk
	 */
	public void step(PlayerContainerI pc) {
		if (!getInSea()) {
			container.movePlayer(this, pc);
		}
	}

	/**
	 * Visszaadja, hogy a jatekosnal van-e a parameterkent kapott fajta targy.
	 * @param item keresett targynak a tipusa
	 * @return jatekosnal van-e a targy
	 */
	public boolean hasItem(Item item) {
		return inventory.contains(item);
	}

	/**
	 * Ellenorzi, hogy a jatekos tengerben van-e
	 * és ha nincsen rajta buvarruha, akkor a jatekos meghal.
	 */
	public void checkPlayerStatus() {
		boolean hasDivingSuit = inventory.contains(new DivingSuit());
		if (getInSea() && !hasDivingSuit) {
			die();
		}
	}

	/**
	 * Getter az inSea valtozora.
	 * @return tengerben van-e a jatekos
	 */
	public boolean getInSea() {
		return inSea;
	}

	/**
	 * Beállítja az inSea attribútumot.
	 * @param b igaz, vagy hamis-e
	 */
	public void setInSea(boolean b){
		inSea = b;
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
	 * Ha az életereje kevesebb, mint egy, akkor a játékos meghal.
	 */
	public void loseHealth() {
		health--;
		if (health < 1) {
			die();
		}
	}

	/**
	 * A jatekos megeszik egy egysegnyi etelt igy az eletero pontjat eggyel noveli.
	 */
	public void eat() {
		health++;
	}

	/**
	 * A jatekos hasznalja az adott targyat.
	 * @param item a hasznalani kivant targy
	 */
	public void useItem(Item item) {
		if (!getInSea())
			inventory.use(item, this);
	}

	/**
	 * Ha nincs a játékos a tengerben,
	 * akkor az adott itemet az adott playeren használja úgy,
	 * hogy a leltárának átadja a feladatot.
	 * @param item Az eszköz, amit használ a játékoson
	 * @param player A játékos, amin használja az eszközt.
	 */
	public void useItemOnPlayer(Item item, Player player) {
		if (!getInSea())
			inventory.use(item, player);
	}

	/**
	 * A játékos lesöpör egy egységnyi havat a jégtábláról,
	 * ezzel módosítja a jégtábla rétegét -1-el, de nem lehet kevesebb 0-nál.
	 */
	public void swipeWithHand() {
		if (!getInSea()) {
			((IceBlock) container).modifyLayers(-1);
		}
	}

	/**
	 * Ha a játékos nincs a tengerben és nincs hó sem a jégtáblán,
	 * akkor a jégtáblában lévő eszközt hozzáadja a leltárához,
	 * ha nincs még olyan. Ha nincs, akkor kitörli a jégtáblából,
	 * és az eszközhöz hozzáadja a játékost.
	 */
	public void digOutItem() {
		if (!getInSea()) {
			IceBlock ib  = (IceBlock) container;
			if (ib.getLayer() == 0) {
				Item item = ib.getItem();
				boolean success = item.addToInventory(inventory);
				if (success) {
					ib.removeItem();
					item.pickedUpBy(this);
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
		if(health!=0) health = 0;
		level.levelLost();
	}

	/**
	 * Level getter
	 * @return a jatekos eppen melyik szinten jatszik
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * Level setter
	 * @param _level a beallitando level
	 */
	public void setLevel(Level _level){
		level = _level;
	}
	/**
	 * Visszaadja a játékos életerejét.
	 */
    public int getHealth() {
    	return health;
	}

	/**
	 * ELtavolitja a kapott itemet az inventorybol.
	 * @param item Az eltavolitando item.
	 */
	public void removeItem(Item item) {
    	inventory.removeItem(item);
	}
}
