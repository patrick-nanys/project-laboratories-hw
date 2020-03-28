public abstract class Item {
	/**
	 *
	 * @param player
	 */
	public void use(Player player) {}

	/**
	 *
	 * @param inventory
	 * @return
	 */
	public boolean addToInventory(Inventory inventory) {
		FunctionLogger.log_call("Inventory inventory.addItem(this)");
		FunctionLogger.set_return(FunctionLogger.get_return());
		boolean ret = inventory.addItem(this);
		FunctionLogger.log_predefined_return();

		return ret;
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public abstract boolean equals(Item item);

	/**
	 *
	 * @param p
	 */
	public void pickedUpBy(Player p) {}
}
