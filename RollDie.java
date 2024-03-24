import java.lang.Math;
class RollDie
{

public static void main(String args[]){
	//Roll a die 1000 times
	int rolls = 1000;	
	//Frequency as integer
	double freqFaceOne = 0;
	double freqFaceTwo = 0;
	double freqFaceThree = 0;
	double freqFaceFour = 0;
	double freqFaceFive = 0;
	double freqFaceSix = 0;

	for(int i = 1; i <= rolls; i++){
		float rollDie = 0;
		rollDie = (float)(Math.random() * 1);		
		if(rollDie >= 0 && rollDie <= 0.1667){
			
			freqFaceOne = freqFaceOne + 1.0;
		} else if(rollDie >= 0.1667 && rollDie <= 0.3333){
			
			freqFaceTwo = freqFaceTwo + 1.0;
		} else if(rollDie >= 0.3333 && rollDie <= 0.5){
			
			freqFaceThree = freqFaceThree + 1.0;
		} else if(rollDie >= 0.5 && rollDie <= 0.6667){
			
			freqFaceFour = freqFaceFour + 1.0;
		} else if(rollDie >= 0.6667 && rollDie <= 0.8333){
			
			freqFaceFive = freqFaceFive + 1.0;
		} else if(rollDie >= 0.8333 && rollDie <= 1.0){
			
			freqFaceSix = freqFaceSix + 1.0;
		}

	}

	// Percentage as float
	double diePercentageOne = (freqFaceOne * 100) / 1000;
	double diePercentageTwo = (freqFaceTwo * 100) / 1000;
	double diePercentageThree = (freqFaceThree * 100) / 1000;
	double diePercentageFour = (freqFaceFour * 100) / 1000;
	double diePercentageFive = (freqFaceFive * 100) / 1000;
	double diePercentageSix = (freqFaceSix * 100) / 1000;


	//Output
	System.out.println("Simulation for a die rolled 1000 times\nFace       Frequency         Percentage");
	System.out.println("1           "+ String.format("%.0f", freqFaceOne) + " rolls               " + diePercentageOne + "%");
	System.out.println("2           "+ String.format("%.0f", freqFaceTwo) + " rolls               " + diePercentageTwo + "%");
	System.out.println("3           "+ String.format("%.0f", freqFaceThree) + " rolls               " + diePercentageThree + "%");
	System.out.println("4           "+ String.format("%.0f", freqFaceFour) + " rolls               " + diePercentageFour + "%");
	System.out.println("5           "+ String.format("%.0f", freqFaceFive) + " rolls               " + diePercentageFive + "%");
	System.out.println("6           "+ String.format("%.0f", freqFaceSix) + " rolls               " + diePercentageSix + "%");
	
	return;
}

}