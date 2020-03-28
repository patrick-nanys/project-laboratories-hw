public class Shovel extends Item {
	/**
	 *
	 * @param player
	 */
	public void use(Player player) {
		if(!player.inSea()) {
			IceBlock ib = (IceBlock) player.getLocation();
			FunctionLogger.log_call("IceBlock ib.modifyLayers(-2)");
			ib.modifyLayers(-2);
			FunctionLogger.log_return("");
		}
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public boolean equals(Item item) {
		return item instanceof Shovel;
	}
}
