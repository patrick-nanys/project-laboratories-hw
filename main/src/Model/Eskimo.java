package Model;

/**
 * Egy eskimo jatekost reprezental, aki tud iglut epiteni.
 */
public class Eskimo extends Player {

	Eskimo(Level level) {
		super(level);
	}

	Eskimo(Level level, Inventory inventory) {
		super(level, inventory);
	}

	/**
	 * Epit egy iglut az aktualis jegtablan.
	 */
	public void buildIglu() {
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
