import java.util.List;

/**
 * IceBlock osztaly
 * @param snowLayers az IceBlockon talalhato horetegek
 * @param hasIglu az IceBlockon talalhato-e iglu
 * @param capacity az IceBlockon egyszerre tartozkodhato jatekosok
 * @param neighbours az IceBlock szomszedai
 * @param sea az IceBlockhoz kapcsolodo sea
 * @param item az IceBlockba fagyott item
 * @param players az IceBlockon talalhato jatekosok
 */
public class IceBlock implements PlayerContainerI {
	protected int snowLayers;
	protected boolean hasIglu;
	protected int capacity;
	protected List <IceBlock> neighbours;
	protected Sea sea;
	protected Item item;
	protected List <Player> players;

	public void modifyLayers(int d) {
	}

	public int getLayer() {
	}
	
	public void addItem(Item i) {
	}
	
	public Item getItem() {
	}
	
	public void removeItem() {
	}
	
	public void setIglu(boolean b) {
	}
	
	public boolean getIglu() {
	}
	
	public int getCapacity() {
	}
	
	public Player[] getPlayers() {
	}
	
	public void addNeighbour(DirectionE d, IceBlock ib) {
	}
	
	public IceBlock getNeighbour(DirectionE d) {
	}

	@Override
	public void movePlayer(Player p, PlayerContainerI pc) {
		FunctionLogger.log_call("PlayerContainerI pc.addPlayer(p)");
		pc.addPlayer(p);
		FunctionLogger.log_return("");
		FunctionLogger.log_call("IceBlock this.removePlayer(p)");
		this.removePlayer(p);
		FunctionLogger.log_return("");
	}

	@Override
	public void movePlayer(Player p, DirectionE d) {
		FunctionLogger.log_call("IceBlock this.getNeighbour(d)");
		IceBlock neighbour=this.getNeighbour(d);
		FunctionLogger.log_return("IceBlock neighbour");
		FunctionLogger.log_call("IceBlock this.movePlayer(Player p, IceBlock neighbour)");
		movePlayer(p, neighbour);
		FunctionLogger.log_return("");
	}

	@Override
	public void removePlayer(Player p) {
		players.remove(p);
	}

	@Override
	public void addPlayer(Player p) {
		players.add(p);
	}
}
