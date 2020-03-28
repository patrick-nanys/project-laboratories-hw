/**
 * UnstableIceBlock osztaly, reprezental egy instabil jegtablat a jatekban.
 * Ha tobb jatekos tartozkodik rajta, mint amennyit elbir, felborul
 */
public class UnstableIceBlock extends IceBlock {
	/**
	 * Felboritja az instabil jegtablat, beledobva az osszes rajta allo jatekost a tengerbe.
	 */
	public void flip() {
		for(int i=0;i<players.size();i++) {
			FunctionLogger.log_call("UnstableIceBlock this.movePlayer(Player players.get(i), Sea this.sea)");
			movePlayer(players.get(i), this.sea);
			FunctionLogger.log_return("");
		}
	}

	/**
	 * Hozzaadja az adott jatekost az instabil jegtablahoz. Ha tobben allnak rajta, mint a kapacitas, borul.
	 * @param p A hozzaadni kivant jatekos
	 */
	public void addPlayer(Player p) {
		players.add(p);
		if(players.size()>capacity) {
			FunctionLogger.log_call("UnstableIceBlock this.flip()");
			flip();
			FunctionLogger.log_return("");
		}
	}
}
