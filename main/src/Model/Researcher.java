package Model;

/**
 * Egy sarkkutato jatekost reprezental,
 * aki meg tudja mondani, hogy hany jatekost birnak el az egyes jegtablak.
 */
public class Researcher extends Player {


	/**
	 * Konstruktor, hogy beállítsuk a pályáját.
	 * @param level pálya ahol elhelyezkedik
	 */
	public Researcher(Level level) {
		super(level);
	}

	/**
	 * Konstruktor, hogy a tagváltozókat beállítsuk
	 * @param level pálya ahol elhelyezkedik
	 * @param inventory inventory amiben a játékos itemei vannak
	 * @param health életereje
	 */
	public Researcher(Level level, Inventory inventory, int health) {
		super(level, inventory, health);
	}

	/**
	 * Megnezi, hogy a parameterkent kapott jegtable egyszerre hany jatekost bir el.
	 * @param ib a megnezendo jegtabla
	 */
	public int checkStability(IceBlock ib) {
		int capacity = ib.getCapacity();
		return capacity;
	}

}
