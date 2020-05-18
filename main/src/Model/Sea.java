package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Sea osztaly, egy IceBlock-ot korulvevo tengert reprezental. Egy IceBlockon tartozkodo jatekos
 * 		inSea booleanje hatarozza meg, hogy a jatekos tengerben van, vagy az IceBlockon
 * 	 IceBlock position: Az IceBlock, amihez tartozik
 * 	 ArrayList<Player> players: A tengerben talalhato jatekosok
 */
public class Sea implements PlayerContainerI {
	private IceBlock position;
	private ArrayList<Player> players;

	public Sea() {
		players = new ArrayList<>();
	}

	/**
	 * Elkeri az adott jegtablan levo tengert
	 * @return visszater az adott jegtablan szomszedos Sea-vel
	 */
	public Sea getNeighbour(IceBlock b) {
		return b.getSea();
	}

	/**
	 * Mozgat egy playert a megadott PlayerContainerre.
	 * @param p a mozgatni kivant jatekos
	 * @param pc a celzott container
	 */
	@Override
	public void movePlayer(Player p, PlayerContainerI pc) {
		pc.addPlayer(p);
		this.removePlayer(p);
		p.getPlayerView().update();
	}

	/**
	 * Eltavolitja az adott jatekost a sea-rol.
	 * @param p az eltavolitani kivant jatekos
	 */
	@Override
	public void removePlayer(Player p) {
		players.remove(p);
	}

	/**
	 * Hozzaad a tengerhez egy jatekost.
	 * @param p a hozzadni kivant jatekos
	 */
	@Override
	public void addPlayer(Player p) {
		players.add(p);
		p.setContainer(this);
	}

	/**
	 * Visszaadja a tengerben talalhato jatekosok listajat.
	 * @return players, a tengerben talalhato jatekosok.
	 */
	@Override
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Beallitja, hogy melyik tabla korul talalhato a tenger.
	 * @param ib a beallitando tabla.
	 */
	public void setPosition(IceBlock ib)  {
		position = ib;
	}

	/**
	 * Visszaadja azt, hogy a tenger melyik tabla korul talalhato.
	 * @return position, a tablaja.
	 */
	public IceBlock getPosition() {
		return position;
	}

	public String ToString(){
		return "Sea";
	}
}
