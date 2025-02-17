import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Simulation
{
    Simulation(){
        
    }
    
    ArrayList<Item> loadItem(ArrayList<Item> phase, int phaseNum)
    {
        String lineRead;
        String fileName = "";

        try {
            if (phaseNum == 1){
                System.out.println("Load items for Phase 1");
                fileName = "phase-1.txt";
            } else if (phaseNum == 2){
                System.out.println("Load items for Phase 2");
                fileName = "phase-2.txt";
            } else {
                System.out.println("Can not load Items for Phase-1 or 2, wrong call");
                System.out.println("Program stop");
                System.exit(1);
            }
            
            File file = new File(fileName);
            Scanner fscanner = new Scanner(file);
            while (fscanner.hasNextLine()){
                Item item = new Item();
                lineRead = fscanner.nextLine();
                int i = lineRead.indexOf("=");
                item.name = lineRead.substring(0, i);
                item.weight = lineRead.parseInt(lineRead.substring(i+1));
                phase.add(item);
            }
            fscanner.close();
        }
        return phase;
    }
    
    ArrayList<U1> loadU1(ArrayList<Item> items)
    {
        ArrayList<U1> fleetU1 = new ArrayList<>();      // create ArrayList object fleetU1

        int i = 0;
        while (i < (items.size() - 1)){       // repeat until all items are loaded
            U1 u1 = new U1();                                   // create a new U1 rocket
            while (u1.currentWeight <= u1.rocketMaxWeight) {     // repeat until rocket full
                if (items.get(i).weight > u1.cargoLimit ) {     // item weight > cargo limit?
                    System.out.println("Correct file phase-1.txt item " + items.get(i).name + " from line " + (i+1)
                            + " has weight=" + items.get(i).weight + " which is heavier than cargo limit of U1 rocket=" + u1.cargoLimit);
                    System.out.println("Program stop");
                    System.exit(1);
                }
                if (u1.canCarry(items.get(i))) {                 //  can carry item i ?
                    u1.carry(items.get(i));         // update rocket current weight and cargo carried
                    i++;                    // point next item to load
                    if (i >= items.size()) {
                        fleetU1.add(u1);                // add rocket U1 to the fleet
                        break;              // all items from file phase-1.txt are loaded --> exit loop
                    }
                } else {                                        // if can not carry item (is almost full loaded):
                    fleetU1.add(u1);                // add rocket U1 to the fleet
                    break;
                }
            }
        }
        return fleetU1;      // The method should then return that ArrayList of those U1 rockets that are fully loaded.
    }
    
    ArrayList <U2> loadU2 (ArrayList <Item> items) {
        ArrayList <U2> fleetU2 = new ArrayList<>();      // create ArrayList object fleetU2

        int i = 0;
        while (i < (items.size() - 1)){       // repeat until all items are loaded
            U2 u2 = new U2();                                   // create a new U2 rocket
            while (u2.currentWeight <= u2.rocketMaxWeight) {     // repeat until rocket full
                if (items.get(i).weight > u2.cargoLimit ) {     // item weight > cargo limit?
                    System.out.println("Correct file phase-1.txt item " + items.get(i).name + " from line " + (i+1)
                            + " has weight=" + items.get(i).weight + " which is heavier than cargo limit of U2 rocket=" + u2.cargoLimit);
                    System.out.println("Program stop");
                    System.exit(1);
                }
                if (u2.canCarry(items.get(i))) {                 //  can carry item i ?
                    u2.carry(items.get(i));         // update rocket current weight and cargo carried
                    i++;                    // point next item to load
                    if (i >= items.size()) {
                        fleetU2.add(u2);                // add rocket U2 to the fleet
                        break;              // all items from file phase-1.txt are loaded --> exit loop
                    }
                } else {                                        // if can not carry item (is almost full loaded):
                    fleetU2.add(u2);                // add rocket U2 to the fleet
                    break;
                }
            }
        }
        return fleetU2;      // The method should then return that ArrayList of those U2 rockets that are fully loaded.
    }
    @SuppressWarnings ("unchecked")
    int runSimulation (ArrayList fleet) {
    int totalBudget;

        Rocket uTemp = new Rocket();        // create new object of type Rocket for getting U1 or U2
        //uTemp = (Rocket) fleet.get(0);    // get object of type U1 or U2 in common object Rocket
        int crtRocket = 1;      // rocket counter
        for (int i = 0; i < fleet.size(); i++) {        // repeat until all fleet landed on Mars
            uTemp = (Rocket) fleet.get(i);
            // launch the rocket i
            if (uTemp.launch()){                     // launch the rocket i
                System.out.println("Rocket " + crtRocket + " successfully launched");
                uTemp.rocketStatus = "launched";     // set status to launched
                // land the rocket i
                if (uTemp.land()) {                   // land the rocket i
                    System.out.println("Rocket " + crtRocket + " successfully landed");
                    uTemp.rocketStatus = "landed";    // set status to landed
                    crtRocket = crtRocket +1;
                } else {
                    System.out.println("Rocket " + crtRocket + " crashed at landing. Repeat the launch with the same cargo.");
                    uTemp.rocketStatus = "crashed";     // set status to crashed
                    // we have to launch another new rocket loaded with the same cargo:
                    fleet = insertNewRocket(fleet, i);
                }
            } else {
                System.out.println("Rocket " + crtRocket + " exploded at launch. Repeat the launch with the same cargo.");
                uTemp.rocketStatus = "exploded";     // set status to exploded
                // we have to launch another new rocket loaded with the same cargo:
                fleet = insertNewRocket(fleet, i);
            }
        }
        totalBudget = fleet.size() * uTemp.rocketCost;
        return totalBudget;
    }

    // Function to insert into the fleet another new rocket loaded with the same cargo:
    @SuppressWarnings ("unchecked")
    private ArrayList <Rocket> insertNewRocket(ArrayList fleet, int index){

	//Rocket uTemp = new Rocket();        // create new object of type Rocket for getting U1 or U2
        Rocket uTemp;        // create new object of type Rocket for getting U1 or U2
        uTemp = (Rocket) fleet.get(index);  // get rocket at index

        if (fleet.get(index) instanceof U1) {
            U1 u1New = new U1();                        // create a new U1 rocket with the same values:
            u1New.rocketStatus = "loaded";              // loaded
            u1New.cargoWeight = uTemp.cargoWeight;    // same cargo carried
            u1New.currentWeight = uTemp.currentWeight;  // same current weight
            fleet.add(index + 1, u1New);                 // insert new rocket U1 at index i+1 to the fleet
        } else if (fleet.get(index) instanceof U2) {
            U2 u2New = new U2();                        // create a new U2 rocket with the same values:
            u2New.rocketStatus = "loaded";              // loaded
            u2New.cargoCarried = uTemp.cargoCarried;
            u2New.currentWeight = uTemp.currentWeight;  // same cargo carried
            fleet.add(index + 1, u2New);                 // same current weight
        } else {
            System.out.println("Can not run simulation, wrong call");
            System.out.println("Program stop");
            System.exit(2);
        }
        
        return fleet;
    }
}
