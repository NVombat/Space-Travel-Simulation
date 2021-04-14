/*U2 Rocket Specifications

Rocket cost = $120 Million
Rocket weight = 18 Tonnes
Max weight (with cargo) = 29 Tonnes
Chance of launch explosion = 4% * (cargo carried / cargo limit)
Chance of landing crash = 8% * (cargo carried / cargo limit)

*/

public class U2 extends Rocket
{
    U2(){
        rocketCost = 120; //120 million 
        rocketWeight = 18000; //Weight in Kgs
        rocketMaxWeight = 29000;
        rateExplosion = 0.04;
        rateCrash = 0.08;
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
