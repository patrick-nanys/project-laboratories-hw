package Model;

public interface PlayerContainerI {

	void movePlayer(Player p, PlayerContainerI pc);

	void movePlayer(Player p, DirectionE d);
	
	void removePlayer(Player p);
	
	void addPlayer(Player p);

	String toString ();

    Player[] getPlayers();
}
