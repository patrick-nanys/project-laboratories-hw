/**
 * Shovel osztaly. Az Item leszarmazottja.
 * Segitsegevel 2 horeteg tavolithato el egy jegtablarol egy akcio alatt.
 */
public class Shovel extends Item {
	/**
	 * Shovel hasznalatakor 2 horeteget tavolitunk el a jegtablarol, amelyiken a player all.
	 * @param player A Shovelt hasznalo jatekos.
	 */
	public void use(Player player) {
		if(!player.getInSea()) {
			IceBlock ib = (IceBlock) player.getLocation();
			FunctionLogger.log_call("IceBlock ib.modifyLayers(-2)");
			ib.modifyLayers(-2);
			FunctionLogger.log_return("");
		}
	}

    /**
     * Megnezi, hogy a kapott Item Shovel tipusu-e.
     * @param item A vizsgalando Item.
     * @return Ha Shovel a kapott Item, akkor true, egyebkent false.
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
		return "Shovel";
	}
}
