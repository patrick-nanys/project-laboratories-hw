package Model;

/**
 * A Steppable egy absztrakt ősosztály,
 * ami minden olyan dolgot reprezentál, amely időben lépni tud.
 */
public interface Steppable {
    /**
     * Absztrakt metódus, amivel a dolgok lépnek egy körben.
     * @param pc Ahova lépnek.
     */
    void step(PlayerContainerI pc);
}
