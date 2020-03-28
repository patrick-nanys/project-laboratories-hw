public class Rope extends Item {
	private Player player;

	/**
	 *
	 * @param player
	 */
	public void use(Player player) {
		if(player.inSea() && !this.player.inSea()){
			FunctionLogger.log_call("Player player.getLocation()");
			Sea from = (Sea) player.getLocation();
			FunctionLogger.log_return("from");

			FunctionLogger.log_call("Player this.player.getLocation()");
			IceBlock to = (IceBlock) this.player.getLocation();
			FunctionLogger.log_return("to");

			FunctionLogger.log_call("IceBlock from.movePlayer(player, to)");
			from.movePlayer(player, to);
			FunctionLogger.log_return("");
		}
	}

	/**
	 *
	 * @param p
	 */
	public void pickedUpBy(Player p) {
		player = p;
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public boolean equals(Item item) {
		return item instanceof Rope;
	}
}
