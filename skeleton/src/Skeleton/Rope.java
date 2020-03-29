package Skeleton;

/**
 * Skeleton.Rope osztaly. Az Skeleton.Item leszarmazottja.
 * Segitsegevel ki lehet menteni egy vizbeesett jatekost.
 *
 * Skeleton.Player player: A Skeleton.Rope-ot felvevo jatekost tarolja.
 */
public class Rope extends Item {
	private Player player;

	/**
	 * Szomszedos mezorol kihuz egy jatekost a tengerbol, arra a mezore, amelyiken a kimento jatekos all.
	 * @param player Az a jatekos, amelyiket kimentjuk a vizbol.
	 */
	public void use(Player player) {
		if(player.getInSea()){
			FunctionLogger.log_call("Skeleton.Player player.getLocation()");
			Sea from = (Sea) player.getLocation();
			FunctionLogger.log_return("from");

			FunctionLogger.log_call("Skeleton.Player this.player.getLocation()");
			IceBlock to = (IceBlock) this.player.getLocation();
			FunctionLogger.log_return("to");

			FunctionLogger.log_call("Skeleton.IceBlock from.movePlayer(player, to)");
			from.movePlayer(player, to);
			FunctionLogger.log_return("");
		}
	}

	/**
	 * Beallitja a Skeleton.Rope player attributumat arra a jatekosra, amelyik felvette.
	 * @param p Az a jatekos, amelyik felvette a Skeleton.Rope-ot.
	 */
	public void pickedUpBy(Player p) {
		player = p;
	}

	/**
	 * Megnezi, hogy a kapott Skeleton.Item Skeleton.Rope tipusu-e.
	 * @param item A vizsgalando Skeleton.Item.
	 * @return Ha Skeleton.Rope a kapott Skeleton.Item, akkor true, egyebkent false.
	 */
	public boolean equals(Item item) {
		return item instanceof Rope;
	}
}
