/**
 * Item osztaly. A kulonbozo itemek ososztalya.
 */
public abstract class Item {
	/**
	 * Nem csinal semmit, akinek kell override-olja, akinek nem, nem hasznalja.
	 * @param player A jatekos, akin hasznaljuk az itemet.
	 */
	public void use(Player player) {}

	/**
	 * Hozzaadja az itemet egy jatekos inventory-ahoz, ha tudja.
	 * @param inventory Az invetory, amihez hozza probalja adni magat.
	 * @return Ha hozzaadta true, ha nem false.
	 */
	public boolean addToInventory(Inventory inventory) {
		String name = FunctionLogger.get_obj_name();

		FunctionLogger.log_call(String.format("Inventory inventory.addItem(%s)", name));
		boolean added = inventory.addItem(this);
		FunctionLogger.log_boolean_return(added);

		return added;
	}

	/**
	 * Minden ebbol leszarmazo osztaly feluldefinialja.
	 * Arra hasznaljak, hogy megnezzek a kapott item ugyanolyan tipusu-e,
	 * mint maga az objektum.
	 * @param item A vizsgalando item.
	 * @return Ha ugyanaz true, egyebkent false.
	 */
	public abstract boolean equals(Item item);

	/**
	 * Beallitja, hogy ki vette fel.
	 * @param p Az ot felvevo jatekos.
	 */
	public void pickedUpBy(Player p) {}

	/**
	 * Teszteleshez, visszaadja az osztaly nevet.
	 * @return az adott leszarmazott neve.
	 */
	public abstract String toString();
}
