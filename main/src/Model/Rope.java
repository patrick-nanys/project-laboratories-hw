package Model;

/**
 * Rope osztaly. Az Item leszarmazottja.
 * Segitsegevel ki lehet menteni egy vizbeesett jatekost.
 *
 * Player player: A Rope-ot felvevo jatekost tarolja.
 */
public class Rope extends Item {
	private Player player;

	/**
	 * Szomszedos mezorol kihuz egy jatekost a tengerbol,
     * arra a mezore, amelyiken a kimento jatekos all.
	 * @param player Az a jatekos, amelyiket kimentjuk a vizbol.
	 */
	public void use(Player player) {
		if(player.getInSea()){
			Sea from = (Sea) player.getLocation();
			IceBlock to = (IceBlock) this.player.getLocation();
			from.movePlayer(player, to);
			player.setInSea(false);
		}
	}

	/**
	 * Beallitja a Rope player attributumat arra a jatekosra, amelyik felvette.
	 * @param p Az a jatekos, amelyik felvette a Rope-ot.
	 */
	public void pickedUpBy(Player p) {
		player = p;
	}

	/**
	 * Megnezi, hogy a kapott Item Rope tipusu-e.
	 * @param item A vizsgalando Item.
	 * @return Ha Rope a kapott Item, akkor true, egyebkent false.
	 */
	public boolean equals(Item item) {
		return item instanceof Rope;
	}
}
