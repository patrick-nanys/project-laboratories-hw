package Model;

/**
 * Model.Shovel osztaly. Az Model.Item leszarmazottja.
 * Segitsegevel 2 horeteg tavolithato el egy jegtablarol egy akcio alatt.
 */
public class Shovel extends Item {
	/**
	 * Model.Shovel hasznalatakor 2 horeteget tavolitunk el a jegtablarol, amelyiken a player all.
	 * @param player A Shovelt hasznalo jatekos.
	 */
	public void use(Player player) {
		if(!player.getInSea()) {
			//FunctionLogger.log_call("player.getLocation()");
			IceBlock ib = (IceBlock) player.getLocation();
			//FunctionLogger.log_return("ib");
			//FunctionLogger.log_call("Model.IceBlock ib.modifyLayers(-2)");
			ib.modifyLayers(-2);
			//FunctionLogger.log_return("");
		}
	}

    /**
     * Megnezi, hogy a kapott Model.Item Model.Shovel tipusu-e.
     * @param item A vizsgalando Model.Item.
     * @return Ha Model.Shovel a kapott Model.Item, akkor true, egyebkent false.
     */
	public boolean equals(Item item) {
		return item instanceof Shovel;
	}
	/**
	 * Teszteleshez, visszaadja az osztaly nevet.
	 * @return az osztaly neve.
	 */
	@Override
	public String toString() {
		return "Model.Shovel";
	}
}
