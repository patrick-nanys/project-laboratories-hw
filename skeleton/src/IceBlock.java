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
		System.out.println("IceBlock.movePlayer(Player p, PlayerContainerI pc)\n\t");
		pc.addPlayer(p);
		this.removePlayer(p);
	}

	@Override
	public void movePlayer(Player p, DirectionE d) {
		System.out.println("IceBlock.movePlayer(Player p, DirectionE d)\n\t");
		IceBlock neighbour=this.getNeighbour(d);
		movePlayer(p, neighbour);
	}

	@Override
	public void removePlayer(Player p) {
		System.out.println("IceBlock.removePlayer(Player p)\n\t");
		players.remove(p);
	}

	@Override
	public void addPlayer(Player p) {
		System.out.println("IceBlock.addPlayer(Player p)\n\t");
		players.add(p);
	}
}
