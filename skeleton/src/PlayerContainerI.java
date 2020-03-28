public interface PlayerContainerI {

	public void movePlayer(Player p, PlayerContainerI pc);

	
	public void movePlayer(Player p, DirectionE d);
	
	public void removePlayer(Player p);
	
	public void addPlayer(Player p);
}
