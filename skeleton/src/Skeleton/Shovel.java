package Skeleton;

/**
 * Skeleton.Shovel osztaly. Az Skeleton.Item leszarmazottja.
 * Segitsegevel 2 horeteg tavolithato el egy jegtablarol egy akcio alatt.
 */
public class Shovel extends Item {
	/**
	 * Skeleton.Shovel hasznalatakor 2 horeteget tavolitunk el a jegtablarol, amelyiken a player all.
	 * @param player A Shovelt hasznalo jatekos.
	 */
	public void use(Player player) {
		if(!player.getInSea()) {
			IceBlock ib = (IceBlock) player.getLocation();
			FunctionLogger.log_call("Skeleton.IceBlock ib.modifyLayers(-2)");
			ib.modifyLayers(-2);
			FunctionLogger.log_return("");
		}
	}

    /**
     * Megnezi, hogy a kapott Skeleton.Item Skeleton.Shovel tipusu-e.
     * @param item A vizsgalando Skeleton.Item.
     * @return Ha Skeleton.Shovel a kapott Skeleton.Item, akkor true, egyebkent false.
     */
	public boolean equals(Item item) {
		return item instanceof Shovel;
	}
}
