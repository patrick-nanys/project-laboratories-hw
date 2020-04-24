package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model.Sea osztaly, egy Model.IceBlock-ot korulvevo tengert reprezental. Egy IceBlockon tartozkodo jatekos
 * 		inSea booleanje hatarozza meg, hogy a jatekos tengerben van, vagy az IceBlockon
 * 	 Model.IceBlock position: Az Model.IceBlock, amihez tartozik
 */
public class Sea implements PlayerContainerI {
	private IceBlock position;
	private ArrayList<Player> players;

	public Sea() {
		players = new ArrayList<Player>();
	}

	/**
	 * Elkeri az adott iranyban szomszedos tengert
	 * @return visszater az adott iranyban szomszedos Model.Sea-vel
	 */
	public Sea getNeighbour(IceBlock b) {
		Sea s = b.getSea();
		FunctionLogger.log_return("s");
		return s;
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
	}

	/**
	 * Eltavolitja az adott jatekost a tablarol, igy a sea-rol is
	 * @param p az eltavolitani kivant jatekos
	 */
	@Override
	public void removePlayer(Player p) {
		players.remove(p);
	}

	/**
	 * Hozzaad egy jatekost a tengerhez, ugy, hogy hozzaadja a tablahoz, majd true-ra allitja az inSea-t.
	 * Majd ha kesz a Model.Player osztaly
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
}
