/**
 *Ez az osztály reprezentál egy vészjelző pisztoly alkatrészt.
 * Mivel a győzelmi feltétel elengedhetetlen része, így fontos
 * szerepe van annak kiértékelésében.
 */
public class Part extends Item {
	private Level level;
	private Player player;

	/**
	 *Az alkatrész használatának függvénye. ellenőrzi a pálya segítségével, hogy
	 * minden alkatrész egy jégtáblán van e.
	 * @param player Az a játékos, aki használni szeretné az adott
	 *               alkatrészt.
	 */
	public void use(Player player) {
		FunctionLogger.log_call("Level level.checkParts()");
		level.checkParts();
		FunctionLogger.log_return("");
	}

	/**
	 *Amikor egy játékos felveszi az alkatrészt, ez a függvény elmenti,
	 * hogy melyik játékosnál van, így lehetővé teszi,
	 * minhárom alkatrész ellenőrzését.
	 * @param p Az a játékos, aki felveszi az alkatrészt.
	 */
	public void pickedUpBy(Player p) {
		player = p;
	}

	/**
	 *Visszaadja azt a helyet, amin az a játékos, akinél az adott
	 *alkatrész van, éppen elhelyezkedik.
	 * @return Azzal a hellyel tér vissza, amin a
	 */
	public PlayerContainerI getLocation() {
		FunctionLogger.log_call("Player player.getLocation()");
		PlayerContainerI location = player.getLocation();
		FunctionLogger.log_return("location of part");
		return location;
	}

	/**
	 *Visszaadja, hogy a paraméterként kapott tárgy alkatrész-e.
	 * @param item Az a tárgy, amiről tudni szeretnénk, hogy
	 *             alkatrész-e.
	 * @return Boolean érték. Ha a tárgy alkatrész, akkor igaz,
	 * 			különben hamis.
	 */
	public boolean equals(Item item) {
		return item instanceof Part;
	}
}
