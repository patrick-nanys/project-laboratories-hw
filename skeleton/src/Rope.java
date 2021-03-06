import java.util.ArrayList;

/**
 * Rope osztaly. Az Item leszarmazottja.
 * Segitsegevel ki lehet menteni egy vizbeesett jatekost.
 *
 * Player player: A Rope-ot felvevo jatekost tarolja.
 */
public class Rope extends Item {
	private Player player;

	/**
	 * Szomszedos mezorol kihuz egy jatekost a tengerbol, arra a mezore, amelyiken a kimento jatekos all.
	 * @param player Az a jatekos, amelyiket kimentjuk a vizbol.
	 */
	public void use(Player player) {
		String name = FunctionLogger.get_obj_name();
		ArrayList<String> p = FunctionLogger.get_parameters();
		Player tmp = player;
		if(player.getInSea()){
			String playertype = this.player.toString();
			String ptype = tmp.toString();
			String playername;
			if(playertype.equals("Researcher")) playername = "r";
			else playername = "e";
			String pname;
			if(playertype.equals("Researcher")) pname = "r";
			else pname = "e";
			FunctionLogger.log_call(String.format("%s %s.getLocation()",ptype, pname));
			Sea from = (Sea) player.getLocation();
			FunctionLogger.log_return("from");

			FunctionLogger.log_call(String.format("%s player.getLocation()",playertype));
			IceBlock to = (IceBlock) this.player.getLocation();
			FunctionLogger.log_return("to");

			FunctionLogger.log_call(String.format("IceBlock from.movePlayer(%s, to)", p.get(0)));
			from.movePlayer(player, to);
			FunctionLogger.log_return("");
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
	 * Teszteleshez, visszaadja az osztaly nevet.
	 * @return az osztaly neve.
	 */
	@Override
	public String toString() {
		return "Rope";
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
