import java.util.ArrayList;
import java.util.List;

/**
 * Inventory osztaly. Egy jatekos egy inventoryval rendelkezik.
 * Az inventory szamon tartja, hogy milyen itemeket vett fel a jatekos,
 * figyeli, hogy egy itembol max 1 db lehet a jatekosnal.
 *
 * List<Item> items: A felvett itemek listaja.
 */
public class Inventory {
	private List<Item> items = new ArrayList<Item>();

	/**
	 * Hozzaad egy itemet az inventoryhoz, ha meg nem vett fel olyan tipusu itemet a jatekos.
	 * Visszaadja, hogy sikerult-e felvennie az itemet a jatekosnak.
	 * @param i A hozzaadando item.
	 * @return Ha volt ilyen iteme false, ha nem true.
	 */
	public boolean addItem(Item i) {

		FunctionLogger.log_call("Inventory this.contains(i)");
		FunctionLogger.set_return(FunctionLogger.get_return());
		boolean contains = contains(i);
		FunctionLogger.log_predefined_return();

		if(contains){
			return false;
		} else {
			items.add(i);
			return true;
		}
	}

	/**
	 * Megnezi, hogy van-e mar olyan iteme a jatekosnak, amit eppen fel akar venni.
	 * @param item Ellenorizendo item.
	 * @return Ha van olyan iteme, akkor true, ha nincs false.
	 */
	public boolean contains(Item item) {
		for(int i = 0; i < items.size(); i++){
			FunctionLogger.log_call("Item items.get(i).equals(item)");
			FunctionLogger.set_return(FunctionLogger.get_return());
			boolean equals = items.get(i).equals(item);
			FunctionLogger.log_predefined_return();

			if(equals)
				return true;
		}
		return false;
	}

	/**
	 * A kivalasztott itemet a
	 * @param item A hasznalando item.
	 * @param p Akin hasznalni akarja az itemet a jatekos.
	 */
	public void use(Item item, Player p) {
		FunctionLogger.log_call("Inventory this.contains(item)");
		FunctionLogger.set_return(FunctionLogger.get_return());
		boolean contains = contains(item);
		FunctionLogger.log_predefined_return();

		if(contains){
			FunctionLogger.log_call("Item item.use(p)");
			item.use(p);
			FunctionLogger.log_return("");
		}
	}
}
