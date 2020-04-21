package Model;

import java.util.ArrayList;

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
 	 * @param d az irany amire kivancsiak vagyunk
	 * @return visszater az adott iranyban szomszedos Model.Sea-vel
	 */
	public Sea getNeighbour(DirectionE d) {
		String direction = d.name();
		FunctionLogger.log_call(String.format("Model.IceBlock position.getNeighbour(%s)", direction));
		IceBlock b = position.getNeighbour(d);
		FunctionLogger.log_return("b");
		FunctionLogger.log_call("Model.Sea s.getSea()");
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
		String playertype = p.toString();
		String playername;
		if(playertype.equals("Model.Researcher")) playername = "r";
		else playername = "e";
		FunctionLogger.log_call(String.format("%s pc.addPlayer(%s)",blocktype, playername));
		pc.addPlayer(p);
		FunctionLogger.log_return("");
		FunctionLogger.log_call(String.format("Model.Sea %s.removePlayer(%s)",name, playername));
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
		String playertype = p.toString();
		String playername;
		if(playertype.equals("Model.Researcher")) playername = "r";
		else playername = "e";
		String direction = d.name();
		FunctionLogger.log_call(String.format("Model.IceBlock position.movePlayer(%s, %s)", playername, direction));
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
	 * Majd ha kesz a Model.Player osztaly
	 * @param p a hozzadni kivant jatekos
	 */
	@Override
	public void addPlayer(Player p) {
		players.add(p);
		p.setContainer(this);
	}

	public void setPosition(IceBlock ib)  {
		position = ib;
	}

	/**
	 * Teszteleshez, visszaadja az osztaly nevet.
	 * @return az osztaly neve.
	 */
	@Override
	public String toString() {
		return "Model.Sea";
	}
}
