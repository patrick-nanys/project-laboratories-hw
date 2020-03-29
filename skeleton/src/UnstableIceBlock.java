/**
 * UnstableIceBlock osztaly, reprezental egy instabil jegtablat a jatekban.
 * Ha tobb jatekos tartozkodik rajta, mint amennyit elbir, felborul
 */
public class UnstableIceBlock extends IceBlock {
	/**
	 * Konstruktor
	 * @param cap a kapacitas
	 */
	public UnstableIceBlock (int cap) {
		super();
		capacity=cap;
	}
	/**
	 * Felboritja az instabil jegtablat, beledobva az osszes rajta allo jatekost a tengerbe.
	 */
	public void flip() {
		String name = FunctionLogger.get_obj_name();
		for(int i=0;i<players.size();i++) {

			FunctionLogger.log_call(String.format("UnstableIceBlock %s.movePlayer(players.get(i), Sea %s.sea)", name, name));
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
		p.setContainer(this);
		if(players.size()>capacity) {
			String name = FunctionLogger.get_obj_name();
			FunctionLogger.log_call(String.format("UnstableIceBlock %s.flip()",name));
			flip();
			FunctionLogger.log_return("");
		}
	}
	@Override
	public String toString(){
		return "UnstableIceBlock";
	}
}
