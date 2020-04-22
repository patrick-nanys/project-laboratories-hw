package Model;

import java.util.List;

public interface PlayerContainerI {

	void movePlayer(Player p, PlayerContainerI pc);
	
	void removePlayer(Player p);
	
	void addPlayer(Player p);

	String toString ();

    List<Player> getPlayers();
}
