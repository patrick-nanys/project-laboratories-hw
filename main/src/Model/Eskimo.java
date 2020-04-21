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
			FunctionLogger.log_call("Model.IceBlock ib.setIglu(true)");
			((IceBlock) container).setIglu(true);
			FunctionLogger.log_return("");
		}
	}

	/**
	 * Teszteleshez
	 * @return az osztaly neve
	 */
	@Override
	public String toString() {
		return "Model.Eskimo";
	}
}
