package Model;

import Graphics.BuildingView;

/**
 * Egy eskimo jatekost reprezental, aki tud iglut epiteni.
 */
public class Eskimo extends Player {

	/**
	 * Konstruktor, hogy a tagbáltozókat beállítsuk
	 * @param inventory inventory amiben a játékos itemei vannak
	 * @param health életereje
	 */
	public Eskimo(Inventory inventory, int health) {
		super(inventory, health);
	}

	/**
	 * Epit egy iglut az aktualis jegtablan.
	 */
	public void buildIglu() {
		if (!getInSea()) {
			Iglu i = new Iglu();
			((IceBlock)container).setBuilding(i);
			i.setIceblock((IceBlock)container);
			if (Level.viewsActive()) {
				IceBlock ib = (IceBlock) container;
				BuildingView bv = new BuildingView(i,((IceBlock) container).getIceBlockView(),((IceBlock) container).getIceBlockView().getViewController());
				i.addBuildingView(bv);
				i.getBuildingView().update();
			}
		}
	}

	public String ToString(){
		return "Eskimo";
	}
}
