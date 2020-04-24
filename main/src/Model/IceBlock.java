package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model.IceBlock osztaly
 * Reprezental egy jegtablat a jatekban. Nem tud felfordulni.
 *  snowLayers az IceBlockon talalhato horetegek
 *  hasIglu az IceBlockon talalhato-e iglu
 *  capacity az IceBlockon egyszerre tartozkodhato jatekosok
 *  neighbours az Model.IceBlock szomszedai
 *  sea az IceBlockhoz kapcsolodo sea
 *  item az IceBlockba fagyott item
 *  players az IceBlockon talalhato jatekosok
 */
public class IceBlock implements PlayerContainerI {
	protected int snowLayers;
	protected boolean hasIglu;
	protected int capacity;
	protected List <IceBlock> neighbours;
	protected Sea sea;
	protected Item item;
	protected List <Player> players;
	protected Building building;

	/**
	 * Model.IceBlock konstruktor
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
		hasIglu=false;
		item=null;
		building = null;
	}

	public IceBlock(Player[] players, Item item, Building building, int snowLayers, int capacity) {

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
	 * Hozzaadja az adott iranyban talalhato szomszedot
	 * @param ib a jegtabla, ami szomszedos lesz az adott "d" iranyban
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

	public Building getBuilding() {
		return building;
	}

	public List<IceBlock> getNeighbours() {
		return neighbours;
	}

	public void setBuilding(Building b) {
		building = b;

	}
	public void damagePlayers(int damage){
		if(building!=null) building.protect(damage);
		else {
			for(Player player:players){
				for(int i =0;i<damage;i++){
					player.loseHealth();
				}
			}
		}
	}
}
