import java.util.ArrayList;
import java.util.List;

/**
 * Sea osztaly, egy IceBlock-ot korulvevo tengert reprezental. Egy IceBlockon tartozkodo jatekos
 * 		inSea booleanje hatarozza meg, hogy a jatekos tengerben van, vagy az IceBlockon
 * 	 IceBlock position: Az IceBlock, amihez tartozik
 */
public class Sea implements PlayerContainerI {
	private IceBlock position;
	private ArrayList<Player> players;

	Sea() {
		players = new ArrayList<Player>();
	}

	/**
	 * Elkeri az adott iranyban szomszedos tengert
 	 * @param d az irany amire kivancsiak vagyunk
	 * @return visszater az adott iranyban szomszedos Sea-vel
	 */
	public Sea getNeighbour(DirectionE d) {
		FunctionLogger.log_call("IceBlock position.getNeighbour(d)");
		IceBlock b = position.getNeighbour(d);
		FunctionLogger.log_return("b");
		FunctionLogger.log_call("Sea s.getSea()");
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
		String name = FunctionLogger.get_obj_name();
		String blocktype = pc.toString();
		FunctionLogger.log_call(String.format("%s pc.addPlayer(p)",blocktype));
		pc.addPlayer(p);
		FunctionLogger.log_return("");
		FunctionLogger.log_call(String.format("Sea %s.removePlayer(p)",name));
		this.removePlayer(p);
		FunctionLogger.log_return("");
	}

	/**
	 * Mozgat egy jatekost egy adott iranyba
	 * @param p a mozgatni kivant jatekos
	 * @param d az adott irany
	 */
	@Override
	public void movePlayer(Player p, DirectionE d) {
		FunctionLogger.log_call("IceBlock position.movePlayer(p, d)");
		position.movePlayer(p,d);
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
	 * Majd ha kesz a Player osztaly
	 * @param p a hozzadni kivant jatekos
	 */
	@Override
	public void addPlayer(Player p) {
		players.add(p);
	}
	@Override
	public String toString() {
		return "Sea";
	}
}
