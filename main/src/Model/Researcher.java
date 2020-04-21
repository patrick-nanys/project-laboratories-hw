package Model;

/**
 * Egy sarkkutato jatekost reprezental,
 * aki meg tudja mondani, hogy hany jatekost birnak el az egyes jegtablak.
 */
public class Researcher extends Player {

	public Researcher(Level level) {
		super(level);
	}

	public Researcher(Level level, Inventory inventory, int health) {
		super(level, inventory, health);
	}

	/**
	 * Megnezi, hogy a parameterkent kapott jegtable egyszerre hany jatekost bir el.
	 * @param ib a megnezendo jegtabla
	 */
	public int checkStability(IceBlock ib) {
		FunctionLogger.log_call("Model.IceBlock ib.getCapacity()");
		int capacity = ib.getCapacity();
		FunctionLogger.log_return("capacity");
		return capacity;
	}

	/**
	 * Teszteleshez, visszaadja az osztaly nevet.
	 * @return az osztaly neve.
	 */
	@Override
	public String toString() {
		return "Model.Researcher";
	}
}
