package Skeleton;

/**
 * Skeleton.Sea osztaly, egy Skeleton.IceBlock-ot korulvevo tengert reprezental. Egy IceBlockon tartozkodo jatekos
 * 		inSea booleanje hatarozza meg, hogy a jatekos tengerben van, vagy az IceBlockon
 * 	 Skeleton.IceBlock position: Az Skeleton.IceBlock, amihez tartozik
 */
public class Sea implements PlayerContainerI {
	private IceBlock position;

	/**
	 * Elkeri az adott iranyban szomszedos tengert
 	 * @param d az irany amire kivancsiak vagyunk
	 * @return visszater az adott iranyban szomszedos Skeleton.Sea-vel
	 */
	public Sea getNeighbour(DirectionE d) {
		FunctionLogger.log_call("Skeleton.IceBlock position.getNeighbour(Skeleton.DirectionE d)");
		IceBlock b = position.getNeighbour(d);
		FunctionLogger.log_return("b");
		FunctionLogger.log_call("Skeleton.Sea s.getSea()");
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
			FunctionLogger.log_call("Skeleton.PlayerContainerI pc.addPlayer(Skeleton.Player p)");
			pc.addPlayer(p);
			FunctionLogger.log_return("");
			FunctionLogger.log_call("Skeleton.Sea this.removePlayer(Skeleton.Player p)");
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
		FunctionLogger.log_call("Skeleton.IceBlock position.movePlayer(Skeleton.Player p, Skeleton.DirectionE d)");
		position.movePlayer(p,d);
	}

	/**
	 * Eltavolitja az adott jatekost a tablarol, igy a sea-rol is
	 * @param p az eltavolitani kivant jatekos
	 */
	@Override
	public void removePlayer(Player p) {
		position.removePlayer(p);
	}

	/**
	 * Hozzaad egy jatekost a tengerhez, ugy, hogy hozzaadja a tablahoz, majd true-ra allitja az inSea-t.
	 * Majd ha kesz a Skeleton.Player osztaly
	 * @param p a hozzadni kivant jatekos
	 */
	@Override
	public void addPlayer(Player p) {
		if(position.players.contains(p)) {
			p.setInSea(true);
		}
		else {
			position.addPlayer(p);
			p.setInSea(true);
		}
	}
}
