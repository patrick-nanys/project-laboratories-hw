package Model;

/**
 * Egy eskimo jatekost reprezental, aki tud iglut epiteni.
 */
public class Eskimo extends Player {

	/**
	 * Konstruktor, hogy a tagbáltozókat beállítsuk
	 * @param level pálya ahol elhelyezkedik
	 * @param inventory inventory amiben a játékos itemei vannak
	 * @param health életereje
	 */
	public Eskimo(Level level, Inventory inventory, int health) {
		super(level, inventory, health);
	}

	/**
	 * Epit egy iglut az aktualis jegtablan.
	 */
	public void buildIglu(IceBlock ib) {
		if (!getInSea()) {
			((IceBlock) container).setBuilding(new Iglu());
		}
	}
}
