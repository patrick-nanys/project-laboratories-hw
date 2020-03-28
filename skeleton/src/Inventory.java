import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private List<Item> items;

	/**
	 *
	 */
	public Inventory(){
		items = new ArrayList<Item>();
	}

	/**
	 *
	 * @param i
	 * @return
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
	 *
	 * @param item
	 * @return
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
	 *
	 * @param item
	 * @param p
	 */
	public void use(Item item, Player p) {
	}
}
