package Model;

import java.util.List;

public interface PlayerContainerI {

	void movePlayer(Player p, PlayerContainerI pc);
	
	void removePlayer(Player p);
	
	void addPlayer(Player p);

	String ToString ();

    List<Player> getPlayers();
}
