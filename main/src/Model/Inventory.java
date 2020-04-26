package Model;

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
	private ArrayList<Item> items = new ArrayList<>();

	/**
	 * Hozzaad egy itemet magahoz, ha meg nincs olyan tipusu item benne.
	 * Visszaadja, hogy sikerult-e felvennie az itemet a jatekosnak.
	 * @param i A hozzaadando item.
	 * @return Ha volt ilyen iteme false, ha nem true.
	 */
	public boolean addItem(Item i) {
		if(contains(i)){
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
		for (Item value : items) {
			if (item.equals(value))
				return true;
		}
		return false;
	}

	/**
	 * A parameterkent kapott jatekos hasznalja a parameterként kapott targyat.
	 * @param item A hasznalando item.
	 * @param p Akin hasznalni akarja az itemet a jatekos.
	 */
	public void use(Item item, Player p) {
		for(Item _item : items){
			if(item.equals(_item))
				_item.use(p);
		}
	}

	/**
	 * Eltavolitja a kapott itemet az inventorybol, ha van benne olyan item.
	 * @param item Az eltavolitando item.
	 */
	public void removeItem(Item item){
	    for(int i = 0; i < items.size(); i++){
            if(item.equals(items.get(i)))
                items.remove(items.get(i));
        }
	}

	/**
	 * Visszaadja a tarolt itemeket egy tombben.
	 * @return A tarolt itemek.
	 */
	public List<Item> getItems() {
		return items;
	}
}
