public interface SpaceShip
{
    boolean launch(); //Launch successful - True or False
    boolean land(); //Landing successful - True or False
    boolean canCarry(Item item); //Takes item as argument and checks if rocket can carry the item
    void carry(Item item); //Takes item as argument and updates current weight of rocket
}
