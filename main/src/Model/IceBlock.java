package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Model.IceBlock osztaly
 * Reprezental egy jegtablat a jatekban. Nem tud felfordulni.
 *  snowLayers az IceBlockon talalhato horetegek
 *  hasIglu az IceBlockon talalhato-e iglu
 *  capacity az IceBlockon egyszerre tartozkodhato jatekosok
 *  neighbours az IceBlock szomszedai
 *  sea az IceBlockhoz kapcsolodo sea
 *  item az IceBlockba fagyott item
 *  players az IceBlockon talalhato jatekosok
 */
public class IceBlock implements PlayerContainerI {
	protected int snowLayers;
	protected int capacity;
	protected List <IceBlock> neighbours;
	protected Sea sea;
	protected Item item;
	protected List <Player> players;
	protected Building building;

	/**
	 * IceBlock konstruktor
	 * Hozzarendelunk egy tengert, letrehozzuk a listakat, nincs rajta alaphelyzetben ho.
	 * Kapacitas 6, mivel maximum 5 jatekos lehet, így nem fog soha felfordulni.
	 * Alaphelyzetben nincs rajta sem iglu, es nincs benne item sem.
	 */
	public IceBlock() {
		sea = new Sea();
		neighbours = new ArrayList<>();
		players = new ArrayList<>();
		snowLayers=0;
		capacity=6;
		item=null;
		building = null;
	}

	/**
	 * IceBlock konstruktor egy elore megadott esetre, pl ha fajlbol olvasunk be
	 * @param players jatekosok
	 * @param item targy
	 * @param building epulet
	 * @param snowLayers horetegek
	 * @param capacity kapacitas
	 */
	public IceBlock(Player[] players, Item item, Building building, int snowLayers, int capacity) {
		this.players = new ArrayList<>(Arrays.asList(players));
		this.item=item;
		this.building=building;
		this.snowLayers=snowLayers;
		this.capacity=capacity;
	}

    /**
	 * Megnoveli a tablan talalhato horetegek mennyiseget az adott ertekkel.
	 * @param d a horetegek valtozasanak merteke
	 */
	public void modifyLayers(int d) {
		snowLayers+=d;
	}

	/**
	 * Visszaadja a tablan talalhato horetegek szamat
	 * @return az int-et ami meghatarozza a horetegek szamat
	 */
	public int getLayer() {
		return snowLayers;
	}

	/**
	 * Hozzaadja (belefagyasztja) a tablaba az adott targyat
	 * @param i a hozzaadando targy
	 */
	public void addItem(Item i) {
		item=i;
	}

	/**
	 * Visszaadja a tablaba fagyott itemet
	 * @return item, a tabla item valtozoja
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Eltavolitja a tablaba fagyott itemet
	 */
	public void removeItem() {
		item=null;
	}

	/**
	 * Visszaadja a tablan egyszerre tartozkodni tudo jatekosok szamat
	 * @return capacity, a tabla teherbirasa
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Visszaadja a tablan talalhato jatekosok listajat
	 * @return players, a jatekosok listaja
	 */
	public List <Player> getPlayers() {
		return players;
	}

	/**
	 * Visszaadja a tabla koruli tengert
	 * @return sea, a tenger
	 */
	public Sea getSea() {
		return sea;
	}

	public void setSea(Sea sea) {
		this.sea = sea;
	}

	/**
	 * Hozzaadja a jegtabla egy szomszedjat.
	 * @param ib a jegtabla, ami a jegtabla uj szomszedja lesz.
	 */
	public void addNeighbour(IceBlock ib) {
		neighbours.add(ib);
	}

	/**
	 * Elmozditja az adott jatekost az adott containerre
	 * @param p A mozditani kivant jatekos
	 * @param pc A container, amire mozgatunk
	 */
	@Override
	public void movePlayer(Player p, PlayerContainerI pc) {
		pc.addPlayer(p);
		this.removePlayer(p);
	}

	/**
	 * Eltavolitja az adott jatekost a tablarol
	 * @param p Az eltavolitani kivant jatekos
	 */
	@Override
	public void removePlayer(Player p) {
		players.remove(p);
	}

	/**
	 * Hozzaadja az adott jatekost a tablahoz
	 * @param p A hozzaadni kivant jatekos
	 */
	@Override
	public void addPlayer(Player p) {
		players.add(p);
		p.setContainer(this);
	}

	/**
	 * Visszaadja a tablan talalhato epuletet
	 * @return building, az epulet
	 */
	public Building getBuilding() {
		return building;
	}

	/**
	 * Visszaadja a szomszedok listajat.
	 * @return neighbours, a tablaval szomszedos tablak
	 */
	public List<IceBlock> getNeighbours() {
		return neighbours;
	}

	/**
	 * Beallitja a tablan talalhato epuletet.
	 * @param b a "felepitendo" epulet
	 */
	public void setBuilding(Building b) {
		building = b;
	}

}
