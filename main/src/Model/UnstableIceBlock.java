package Model;

/**
 * UnstableIceBlock osztaly. Az IceBlock leszarmazottja.
 * Ugyanugy viselkedik, mint az IceBlock, kiveve, hogy van egy kapacitasa, ezert
 * ha tobb jatekos tartozkodik rajta, mint amennyit elbir, akkor felborul.
 */
public class UnstableIceBlock extends IceBlock {
	/**
	 * Konstruktor a fajlbol valo beolvasashoz.
	 * @param players Rajta allo jatekosok.
	 * @param item Belefagyott item.
	 * @param building Rajtalevo epulet.
	 * @param snowLayers Horetegek szama.
	 * @param capacity Kapacitas.
	 */
	public UnstableIceBlock(Player[] players, Item item, Building building, int snowLayers, int capacity) {
		super(players, item, building, snowLayers, capacity);
	}

    /**
	 * Felboritja az instabil jegtablat, beledobva az osszes rajta allo jatekost a tengerbe.
	 */
	public void flip() {
		// this loop needs to stay like this, or else it throws ConcurrentModificationException
		for (int i = 0; i < players.size(); i++) {
			players.get(i).setInSea(true);
			movePlayer(players.get(i), this.sea);
		}
	}

	/**
	 * Hozzaadja a kapott jatekost a rajta allo jatekosok listajahoz.
	 * Ha ezutan tobben allnak rajta, mint a kapacitas, felborul.
	 * @param p A hozzaadando jatekos
	 */
	public void addPlayer(Player p) {
		players.add(p);
		p.setContainer(this);
		if(players.size() > capacity) {
			flip();
		}
	}
}
