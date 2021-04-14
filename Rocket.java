public class Rocket implements SpaceShip
{
    int rocketCost;
    int rocketWeight;
    int rocketMaxWeight;
    int cargoLimit;
    int cargoWeight;
    int currentWeight;
    
    double rateExplosion;
    double rateCrash;
    double chanceLaunchExplosion;
    double chanceLandingCrash;
    
    //Rocket constructor
    Rocket(){
        currentWeight = 0;
        cargoWeight = 0;
        cargoLimit = 0;
        chanceLandingCrash = 0.0;
        chanceLaunchExplosion = 0.0;
    }
    
    public boolean launch(){
        return true;
    }
    
    public boolean land(){
        return true;
    }
    
    ///Takes in an item and checks if item can be carried by rocket
    public boolean canCarry(Item item){
        if((this.currentWeight + item.weight) <= rocketMaxWeight)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //Takes in an item and updates weight of rocket and cargo
    public void carry(Item item){
        this.currentWeight += item.weight;
        this.cargoWeight = this.currentWeight - this.rocketWeight;
    }
}
