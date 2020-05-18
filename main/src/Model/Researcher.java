package Model;

/**
 * Egy sarkkutato jatekost reprezental,
 * aki meg tudja mondani, hogy hany jatekost birnak el az egyes jegtablak.
 */
public class Researcher extends Player {

	/**
	 * Konstruktor, hogy a tagváltozókat beállítsuk
	 * @param inventory inventory amiben a játékos itemei vannak
	 * @param health életereje
	 */
	public Researcher(Inventory inventory, int health) {
		super(inventory, health);
	}

	/**
	 * Megnezi, hogy a parameterkent kapott jegtable egyszerre hany jatekost bir el.
	 * @param ib a megnezendo jegtabla
	 */
	public int checkStability(IceBlock ib) {
		if(!inSea) {
			if(((IceBlock) container).getNeighbours().contains(ib)) {
				int cap = ib.getCapacity();
				if (Level.viewsActive())
					ib.getIceBlockView().capacityChecked();
				return cap;
			}
			else {
				return -1;
			}
		}
		else {
			return -1;
		}
	}

	public String ToString(){
		return "Researcher";
	}

}
