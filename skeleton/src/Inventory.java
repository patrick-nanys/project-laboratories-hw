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
		String name = FunctionLogger.get_obj_name();
		ArrayList<String> p = FunctionLogger.get_parameters();

		FunctionLogger.log_call(String.format("Inventory %s.contains(%s)", name, p.get(0)));
		boolean contains = contains(i);
		FunctionLogger.log_boolean_return(contains);

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
		ArrayList<String> p = FunctionLogger.get_parameters();
		for(int i = 0; i < items.size(); i++){
		    Item itemInInventory = items.get(i);

			FunctionLogger.log_call(String.format("Item itemInInventory.equals(%s)", p.get(0)));
			boolean equals = itemInInventory.equals(item);
			FunctionLogger.log_boolean_return(equals);

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
		ArrayList<String> param = FunctionLogger.get_parameters();
		String name = FunctionLogger.get_obj_name();

		FunctionLogger.log_call(String.format("Inventory %s.contains(%s)", name, param.get(0)));
		boolean contains = contains(item);
		FunctionLogger.log_boolean_return(contains);

		if(contains){
			FunctionLogger.log_call(String.format("Item %s.use(%s)", param.get(0), param.get(1)));
			item.use(p);
			FunctionLogger.log_return("");
		}
	}
}
