import java.util.ArrayList;
import java.util.List;

/**
 * IceBlock osztaly
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
	protected boolean hasIglu;
	protected int capacity;
	protected List <IceBlock> neighbours;
	protected Sea sea;
	protected Item item;
	protected List <Player> players;

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
		hasIglu=false;
		item=null;
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
	 * Beallitja, hogy talalhato-e iglu a tablan
	 * @param b true: van rajta iglu, false: nincs
	 */
	public void setIglu(boolean b) {
		hasIglu=b;
	}

	/**
	 * Visszaadja, hogy talalhato-e a tablan iglu
	 * @return hasIglu, a boolean ami tarolja, hogy van-e iglu a tablan
	 */
	public boolean getIglu() {
		return hasIglu;
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

	/**
	 * Hozzaadja az adott iranyban talalhato szomszedot
	 * @param d az irany, ahova szeretnenk szomszedot hozzaadni
	 * @param ib a jegtabla, ami szomszedos lesz az adott "d" iranyban
	 */
	public void addNeighbour(DirectionE d, IceBlock ib) {
		switch(d) {
			case NORTH:
				neighbours.add(0,ib);
				break;
			case WEST:
				neighbours.add(1,ib);
				break;
			case SOUTH:
				neighbours.add(2, ib);
				break;
			case EAST:
				neighbours.add(3, ib);
				break;
		}
	}

	/**
	 * Visszaadja az adott iranyban szomszedos jegtablat
	 * @param d az adott irany, amire kivancsiak vagyunk
	 * @return IceBlock, az adott iranyban szomszedos tabla
	 */
	public IceBlock getNeighbour(DirectionE d) {
		switch(d) {
			case NORTH:
				return neighbours.get(0);
			case WEST:
				return neighbours.get(1);
			case SOUTH:
				return neighbours.get(2);
			case EAST:
				return neighbours.get(3);
			default:
				return null;
		}
	}

	/**
	 * Elmozditja az adott jatekost az adott containerre
	 * @param p A mozditani kivant jatekos
	 * @param pc A container, amire mozgatunk
	 */
	@Override
	public void movePlayer(Player p, PlayerContainerI pc) {
		String name = FunctionLogger.get_obj_name();
		String blocktype = pc.toString();
		String playertype = p.toString();
		FunctionLogger.log_call(String.format("%s pc.addPlayer(%s p)", blocktype, playertype));
		pc.addPlayer(p);
		FunctionLogger.log_return("");
		FunctionLogger.log_call(String.format("IceBlock %s.removePlayer(%s p)", name, playertype));
		this.removePlayer(p);
		FunctionLogger.log_return("");
	}

	/**
	 * Elmozditja az adott jatekost az adott iranyba
	 * @param p A mozditani kivant jatekos
	 * @param d A mozditas iranya
	 */
	@Override
	public void movePlayer(Player p, DirectionE d) {
		FunctionLogger.log_call("IceBlock this.getNeighbour(DirectionE d)");
		IceBlock neighbour=this.getNeighbour(d);
		FunctionLogger.log_return("neighbour");
		FunctionLogger.log_call("IceBlock this.movePlayer(Player p, IceBlock neighbour)");
		movePlayer(p, neighbour);
		FunctionLogger.log_return("");
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
	}

	@Override
	public String toString() {
		return "IceBlock";
	}
}
