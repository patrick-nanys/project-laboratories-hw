public abstract class Player {
	private boolean inSea;
	private int health;
	private int turnCounter;
	private Inventory inventory;
	private Level level;
	private PlayerContainerI container;
	public void step(DirectionE d) {
	}
	
	public boolean hasItem(Item item) {
	}
	
	public boolean inSea() {
	}
	
	public void loseHealth() {
	}
	
	public void eat() {
	}
	
	public void useItem(Item item) {
	}
	
	public void swipeWithHand() {
	}
	
	public void digOutItem() {
	}
	
	public PlayerContainerI getLocation() {
	}
	
	public void die() {
	}
}
