package Model;

/**
 * Egy eskimo jatekost reprezental, aki tud iglut epiteni.
 */
public class Eskimo extends Player {

	public Eskimo(Level level) {
		super(level);
	}

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
