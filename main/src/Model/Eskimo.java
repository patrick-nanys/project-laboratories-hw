package Model;

/**
 * Egy eskimo jatekost reprezental, aki tud iglut epiteni.
 */
public class Eskimo extends Player {

	/**
	 * Konstruktor, hogy a tagbáltozókat beállítsuk
	 * @param inventory inventory amiben a játékos itemei vannak
	 * @param health életereje
	 */
	public Eskimo(Inventory inventory, int health) {
		super(inventory, health);
	}

	/**
	 * Epit egy iglut az aktualis jegtablan.
	 */
	public void buildIglu() {
		if (!getInSea()) {
			((IceBlock)container).setBuilding(new Iglu());
		}
	}
}
