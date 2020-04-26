package Model;

/**
 * Ez az osztály reprezentál egy élelem entitást.
 * Ha egy Model.Player kiássa az adott IceBlockból, akkor a Playerhez kerül,
 * aki egyből el is fogyasztja, eggyel növelve az életét.
 */
public class Food extends Item {

	/**
	 * Felülírja az Item pickedUpBy függvényét,
	 * meghívja a paraméterként kapott játékos eat függvényét.
	 * @param p a játékos, aki felvette az élelmet
	 */
	public void pickedUpBy(Player p) {
		p.eat();
	}

	/**
	 * Felülírja az Item addToInventory függvényét,
	 * hogy ne kerüljön a játékos inventory-jába.
	 * @param inventory A kérdéses inventory
	 * @return Igazzal tér vissza.
	 */
	public boolean addToInventory(Inventory inventory) {
		return true;
	}

	/**
	 * Visszaadja, hogy a paraméterként kapott Model.Item élelem-e.
	 * @param item Az item, amiről el szeretnénk dönteni, hogy élelem-e.
	 * @return Ha élelem, akkor igaz, ha nem, akkor hamis.
	 */
	public boolean equals(Item item) {
		return item instanceof Food;
	}

}
