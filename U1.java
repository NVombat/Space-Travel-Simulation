/* U1 Rocket Specifications

Rocket cost = $100 Million
Rocket weight = 10 Tonnes
Max weight (with cargo) = 18 Tonnes
Chance of launch explosion = 5% * (cargo carried / cargo limit)
Chance of landing crash = 1% * (cargo carried / cargo limit)

*/

public class U1 extends Rocket
{
    U1(){
        rocketCost = 100; //100 million 
        rocketWeight = 10000; //Weight in Kgs
        rocketMaxWeight = 18000;
        rateExplosion = 0.05;
        rateCrash = 0.01;
        cargoLimit = rocketMaxWeight - rocketWeight;
        currentWeight = rocketWeight;
    }
    
     public boolean launch() {
        //generate a random number using the probability equation
        double randomNumber = Math.random();
        
        this.chanceLaunchExplosion = (rateExplosion * (this.cargoWeight / this.cargoLimit))*10.0;
        if (chanceLaunchExplosion >= randomNumber) {
            return false;
        }
        return super.launch();
    }
    
    public boolean land() {
        //generate a random number using the probability equation
        double randomNumber = Math.random();
        
        this.chanceLandingCrash = (rateCrash * (this.cargoWeight / this.cargoLimit))*10.0;
        if (chanceLandingCrash >= randomNumber) {
            return false;
        }
        return super.land();
    }
}
