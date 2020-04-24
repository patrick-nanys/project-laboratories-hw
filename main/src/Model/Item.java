package Model;

/**
 * Ez az absztrakt osztaly reprezental egy altalanos targyat a jatekban.
 * Ososztalyul szolgal a specifikacioban szereplo kulonbozo targyaknak.
 */
public abstract class Item {
	/**
	 * A parameterkent kapott jatekos hasznalja az adott targyat,
	 * a leszarmazottak valositjak meg.
	 * @param player A jatekos, akin hasznaljuk az itemet.
	 */
	public void use(Player player) {}

	/**
	 * Hozzaadja magat egy jatekos inventory-ahoz, ha tudja.
	 * @param inventory Az invetory, amihez hozza probalja adni magat.
	 * @return Ha hozzaadta true, ha nem false.
	 */
	public boolean addToInventory(Inventory inventory) {
		return inventory.addItem(this);
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
	 * Beallitja, hogy ki vette fel. Azon targyak hasznaljak,
	 * amiknek szuksege van arra, hogy megjegyezzek/interakcioba
	 * lepjenek az oket felvevo jatekossal.
	 * @param p Az ot felvevo jatekos.
	 */
	public void pickedUpBy(Player p) {}
}
