/**
 * Egy sarkkutato jatekost reprezental,
 * aki meg tudja mondani, hogy hany jatekost birnak el az egyes jegtablak.
 */
public class Researcher extends Player {

	Researcher(Level level) {
		super(level);
	}

	Researcher(Level level, Inventory inventory) {
		super(level, inventory);
	}

	/**
	 * Megnezi, hogy a parameterkent kapott jegtable egyszerre hany jatekost bir el.
	 * @param ib a megnezendo jegtabla
	 */
	public int checkStability(IceBlock ib) {
		FunctionLogger.log_call("IceBlock ib.getCapacity()");
		int capacity = ib.getCapacity();
		FunctionLogger.log_return("capacity");
		return capacity;
	}
	@Override
	public String toString() {
		return "Researcher";
	}
}
