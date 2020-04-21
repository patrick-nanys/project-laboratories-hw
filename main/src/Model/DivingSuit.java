package Model;

/**
 * Ez az osztály reprezentál egy búvárruhát.
 * Ha egy Model.Player kiássa az adott IceBlockból, akkor a Playerhez kerül,
 * aki egyből fel is veszi, így nem halhat meg, ha vízbe esik.
 */
public class DivingSuit extends Item {
	/**
	 * Megadja, hogy a paraméterként kapott tárgy búvárruha-e.
	 * @param item A tárgy, amiről tudni szeretnénk, hogy búvárruha-e.
	 * @return Ha búvárruha, akkor igazzal, ha nem, akkor hamissal tér vissza.
	 */
	public boolean equals(Item item) {
		return item instanceof DivingSuit;
	}

	/**
	 * Teszteleshez, visszaadja a tipust.
	 * @return az osztaly neve.
	 */
	public String toString(){
		return "Model.DivingSuit";
	}
}
