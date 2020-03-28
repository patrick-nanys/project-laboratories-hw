public class IceBlock implements PlayerContainerI {
	private int snowLayers;
	private boolean hasIglu;
	private int capacity;
	private IceBlock neighbours;
	protected Sea sea;
	private Item item;
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
		System.out.println("\tIceBlock.movePlayer(Player p, PlayerContainerI pc)\n\t");
		pc.addPlayer(p);
		this.removePlayer(p);
	}

	@Override
	public void movePlayer(Player p, DirectionE d) {
		super.movePlayer(p, d);
	}

	@Override
	public void removePlayer(Player p) {
		super.removePlayer(p);
	}

	@Override
	public void addPlayer(Player p) {
		super.addPlayer(p);
	}
}
