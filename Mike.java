
package mike;
//importing Random for the random numbers
import java.util.Random;

/**
 *
 * @author nicholasgenco
 *
 */
public class Mike {
    /**
     *
     * Array array of integers
     * arraySize size of Array
     * sum what we are looking for
     *
     */
    public
    static boolean findNumbers(int Array[], int arraySize, int sum) {
        //outter loop keeps first number unless it has to change
        for (int i = 0; i < arraySize - 2; i++) {
            //middle loop keeps second number unless it has to change
            for (int j = i + 1; j < arraySize - 1; j++) {
                //changes the every time and will break function if sum is found
                for (int k = j + 1; k < arraySize; k++) {
                    //conditional staement if found print and return true
                    if (Array[i] + Array[j] + Array[k] == sum) {
                        System.out.print("Triplet is " + Array[i] + " ," + Array[j] + " ," + Array[k]);
                        return true;
                    }
                }
            }
        }
        
        // If we reach here then nothing was found
        return false;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //variables array of 373 ints
        int[] anArray = new int[373];
        //how we do random numbers
        Random rand = new Random();
        int min = 1;
        int max = 100;
        //test if find numbers is succesful
        boolean success = false;
        //our goal we want to hit
        int goalNumber = 121;
        
        //filling array with randoms to test
        for (int i = 0; i < 373; i++) {
            int randomNum = rand.nextInt(max - min + 1) + min;
            anArray[i] = randomNum;
            System.out.println(randomNum);
        }
        
        //calling the function to find first 3 numbers
        success = findNumbers(anArray, anArray.length, goalNumber);
        //testing if it works
        
        if (success == false) {
            
            System.out.println("Did not find three numbers that add to Goal Number.");
            System.exit(1);
        }
        
        //exit 1 for not found 0 for found
        
        System.exit(0);
    }
    
}
