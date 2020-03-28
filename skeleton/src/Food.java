/**
 *
 */
public class Food extends Item {

	/**
	 * Felülírja az Item pickedUpBy függvényét, meghívja a paraméterként kapott játékos eat függvényét.
	 * @param p a játékos, aki felvette az élelmet
	 */
	public void pickedUpBy(Player p) {
		System.out.print(".pickedUpBy(Player player) \n\t");
		p.eat();
	}

	/**
	 * Felülírja az Item addToInventory függvényét, hogy ne kerülhessen be az élelem a játékos inventory-jába
	 * @param inventory A kárdáses inventory
	 * @return Azzal tér vissza, hogy sikeresen hozzáadódott-e az inventoryhoz, tehát mindig false-szal tér vissza.
	 */
	public boolean addToInventory(Inventory inventory) {
		System.out.print(".addToInventory(Inventory inventory) return: false");
		return false;
	}

	/**
	 * Visszaadja, hogy a paraméterként kapott Item élelem-e.
	 * @param item Az item, amiről el szeretnénk dönteni, hogy élelem-e.
	 * @return Ha élelem, akkor igaz, ha nem, akkor hamis.
	 */
	public boolean equals(Item item) {
		boolean isfood = item instanceof Food;
		System.out.print(".equals(Item item) return:" + isfood);
		return isfood;
	}
}
