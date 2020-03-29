package Skeleton;

/**
 * Ez az osztály reprezentál egy élelem entitást.
 * Ha egy Skeleton.Player kiássa az adott IceBlockból, akkor a Playerhez kerül,
 * aki egyből el is fogyasztja, eggyel növelve az életét.
 */
public class Food extends Item {

	/**
	 * Felülírja az Skeleton.Item pickedUpBy függvényét, meghívja a paraméterként kapott játékos eat függvényét.
	 * @param p a játékos, aki felvette az élelmet
	 */
	public void pickedUpBy(Player p) {
		FunctionLogger.log_call("Skeleton.Player p.eat()");
		p.eat();
		FunctionLogger.log_return("");
	}

	/**
	 * Felülírja az Skeleton.Item addToInventory függvényét, hogy ne kerülhessen be az élelem a játékos inventory-jába
	 * @param inventory A kárdáses inventory
	 * @return Azzal tér vissza, hogy sikeresen hozzáadódott-e az inventoryhoz, tehát mindig false-szal tér vissza.
	 */
	public boolean addToInventory(Inventory inventory) {
		return false;
	}

	/**
	 * Visszaadja, hogy a paraméterként kapott Skeleton.Item élelem-e.
	 * @param item Az item, amiről el szeretnénk dönteni, hogy élelem-e.
	 * @return Ha élelem, akkor igaz, ha nem, akkor hamis.
	 */
	public boolean equals(Item item) {
		return item instanceof Food;
	}
	public String toString(){
		return "Skeleton.Food";
	}
}
