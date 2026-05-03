/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.Scanner;
import java.util.ArrayList;
public class Main
{
	public static void main(String[] args) {
	    System.out.println("Hello! We are going to determine the optimal solution for the taxman game for any list ranging from 1 to an end value of your choice.\nThe rules of the taxman game are like so:"); 
	    System.out.println("    - You can take one cheque from the list at a time.");
	    System.out.println("    - Every time you take a cheque, the taxman takes away every cheque in the list that divides it evenly (aka any cheque that is a \n      factor).");
	    System.out.println("    - The taxman must receive at least ONE cheque for every cheque you take. Therefore, you cannot take a cheque that has no factors \n      in the list.");
	    System.out.println("    - The game ends when you are out of cheques to choose (whether there are no cheques left or the remaining cheques are not \n      chooseable).");
	    System.out.print("\nPlease indicate your desired end value: ");
	    Scanner input = new Scanner(System.in);
	    int initialNumOfCheques = input.nextInt();
	    
	    //Initialize the initial list of cheques in accordance with user input
	    ArrayList<Integer> initialChequeList = new ArrayList<Integer>();
	    for (int i = 1; i <= initialNumOfCheques; i++) {
	        initialChequeList.add(i);
	    }
	    
	    //Display initial list of cheques
	    System.out.println("\nOkay, here is your created list of cheques:");
	    for (int num : initialChequeList) {
	        System.out.print(num + " ");
	    }
	    System.out.println("\n");
	    
	    //Initializing a list of the initial choices available (copy the initial list of cheques then eliminate unchooseables)
	    ArrayList<Integer> initialAvailableChoices = new ArrayList<Integer>();
	    for (int i = 0; i < initialChequeList.size(); i++) {
	        initialAvailableChoices.add(initialChequeList.get(i));
	    }
	    updatePossibleChoices(initialAvailableChoices, initialChequeList);
	    
	    //Initializing currentChequeList, currentPossibleChoices, chosenChequeValue, chosenCheques, and combinationsList
	    ArrayList<Integer> currentChequeList = new ArrayList<Integer>();
	    for (int i = 0; i < initialChequeList.size(); i++) {
	        currentChequeList.add(initialChequeList.get(i));
	    }
	    ArrayList<Integer> currentAvailableChoices = new ArrayList<Integer>();
	    for (int i = 0; i < initialAvailableChoices.size(); i++) {
	        currentAvailableChoices.add(initialAvailableChoices.get(i));
	    }
	    int chosenChequeValue = 0;
	    ArrayList<Cheque> chosenCheques = new ArrayList<Cheque>();
	    ArrayList<ChosenChequesList> combinationsList = new ArrayList<ChosenChequesList>();
	    
	    //Setting first choice to largest prime (this will be permanent)
	    int i = currentAvailableChoices.size() - 1;
	    while (!isPrime(currentAvailableChoices.get(i))) {
	        i--;   
	    }
	    chosenChequeValue = currentAvailableChoices.get(i);
	    updateChequeList(currentChequeList, chosenChequeValue);
	    updatePossibleChoices(currentAvailableChoices, currentChequeList);
	    chosenCheques.add(new Cheque(chosenChequeValue, currentChequeList, currentAvailableChoices));
	    
	    //Notify user program is currently calculating to find the optimal solution
	    System.out.println("Calculating...");
	    
	    //First combination
	    while (currentAvailableChoices.size() > 0) {
	        chosenChequeValue = currentAvailableChoices.get(0); //change index back to 0 after test
	        updateChequeList(currentChequeList, chosenChequeValue);
	        updatePossibleChoices(currentAvailableChoices, currentChequeList);
	        chosenCheques.add(new Cheque(chosenChequeValue, currentChequeList, currentAvailableChoices));
	    }
	    combinationsList.add(new ChosenChequesList(chosenCheques));
	    
	    //Find all possible combinations 
	    while (!(allCombinationsFound(0, chosenCheques))) {
	        int index = chosenCheques.size() - 2;
	        while (index > 0 && allCombinationsFound(index, chosenCheques)) {
	            index--;    
	        }
	        int initialNextChequeChosen = chosenCheques.get(index + 1).getValue(); 
	        
	        //Reset chosenCheques accordingly
	        for (int j = chosenCheques.size() - 1; j > index; j--) {
	            chosenCheques.remove(j);
	        }
	        
	        //Reset currentChequeList and currentAvailableChoices accordingly
	        currentChequeList = new ArrayList<Integer>();
	        for (int j = 0; j < chosenCheques.get(index).getResultingChequeList().size(); j++) {
	            currentChequeList.add(chosenCheques.get(index).getResultingChequeList().get(j));
	        }
	        currentAvailableChoices = new ArrayList<Integer>();
	        for (int j = 0; j < chosenCheques.get(index).getNextPossibleChoices().size(); j++) {
	            currentAvailableChoices.add(chosenCheques.get(index).getNextPossibleChoices().get(j));
	        }
	        
	        //Choose the next option for the next cheque
	        chosenChequeValue = currentAvailableChoices.get(search(currentAvailableChoices, initialNextChequeChosen) + 1);
	        updateChequeList(currentChequeList, chosenChequeValue);
	        updatePossibleChoices(currentAvailableChoices, currentChequeList);
	        chosenCheques.add(new Cheque(chosenChequeValue, currentChequeList, currentAvailableChoices));
	        
	        //Choose first possible options for any remaining choices
	        while (currentAvailableChoices.size() > 0) {
	            chosenChequeValue = currentAvailableChoices.get(0);
	            updateChequeList(currentChequeList, chosenChequeValue);
	            updatePossibleChoices(currentAvailableChoices, currentChequeList);
	            chosenCheques.add(new Cheque(chosenChequeValue, currentChequeList, currentAvailableChoices));
	        }
	        combinationsList.add(new ChosenChequesList(chosenCheques));
	    }
	    
	    //Commented out for efficiency
	    //Display all possible solutions
	    /*System.out.println("There are " + combinationsList.size() + " possible solutions: ");
	    for (ChosenChequesList chequesList : combinationsList) {
	        System.out.println(chequesList);
	    }
	    System.out.println();*/
	    
	    
	    //Find & display best solution
	    int bestSolutionIndex = 0;
	    for (int j = 1; j < combinationsList.size(); j++) {
	        if (combinationsList.get(j).getTotalGain() > combinationsList.get(bestSolutionIndex).getTotalGain())
	            bestSolutionIndex = j;
	    }
	    
	    System.out.println("The optimal solution is: " + combinationsList.get(bestSolutionIndex));
	}
	public static void updateChequeList(ArrayList<Integer> currentChequeList, int chosenChequeValue) {
	    currentChequeList.remove(search(currentChequeList, chosenChequeValue));
	    for (int i = 0; i < currentChequeList.size();) {
	        if (chosenChequeValue % currentChequeList.get(i) == 0)
	            currentChequeList.remove(i);
	        else
	            i++;
	    }
	}
	public static void updatePossibleChoices(ArrayList<Integer> currentPossibleChoices, ArrayList<Integer> currentChequeList) {
	    for (int i = 0; i < currentPossibleChoices.size();) {
	        int num = currentPossibleChoices.get(i);
	        int numOfFactors = 0;
	        for (int j = 0; j < currentChequeList.size(); j++) {
	            if (num % currentChequeList.get(j) == 0)
	                numOfFactors++;
	        }
	        //num will always have at least one factor in list: itself. Our method determines whether num has any OTHER factors
	        if (numOfFactors < 2) {
	            currentPossibleChoices.remove(i);
	        }
	        else
	            i++;
	    }
	}
	public static int search(ArrayList<Integer> list, int num) {
	    for (int i = 0; i < list.size(); i++) {
	        if (list.get(i) == num)
	            return i;
	    }
	    return -1;
	}
	public static int search(ArrayList<Cheque> chequeList, Cheque cheque) {
	    for (int i = 0; i < chequeList.size(); i++) {
	        if (chequeList.get(i).equals(cheque))
	            return i;
	    }
	    return -1;
	}
	public static boolean isPrime(int num) {
	    for (int i = 2; i <= num / 2; i++) {
	        if (num % i == 0)
	            return false;
	    }
	    return true;
	}
	public static boolean allCombinationsFound(int index, ArrayList<Cheque> chosenCheques) {
	    //Initialize Latest Combination Found for Cheque at Specified Index
	    ArrayList<Integer> latestCombination = new ArrayList<Integer>();
	    for (int i = index; i < chosenCheques.size(); i++) {
	        latestCombination.add(chosenCheques.get(i).getValue());
	    }
	    
	    ArrayList<Cheque> lastPossibleCombination = new ArrayList<Cheque>();
	    int chosenChequeValue = chosenCheques.get(index).getValue();
	    ArrayList<Integer> currentChequeList = new ArrayList<Integer>();
	    for (int i = 0; i < chosenCheques.get(index).getResultingChequeList().size(); i++) {
	        currentChequeList.add(chosenCheques.get(index).getResultingChequeList().get(i));
	    }
	    ArrayList<Integer> currentAvailableChoices = new ArrayList<Integer>();
	    for (int i = 0; i < chosenCheques.get(index).getNextPossibleChoices().size(); i++) {
	        currentAvailableChoices.add(chosenCheques.get(index).getNextPossibleChoices().get(i));
	    }
	    lastPossibleCombination.add(new Cheque(chosenChequeValue, currentChequeList, currentAvailableChoices));
	    
	    //Determine lastPossibleCombination
	    while (currentAvailableChoices.size() > 0) {
	        chosenChequeValue = currentAvailableChoices.get(currentAvailableChoices.size() - 1);
	        updateChequeList(currentChequeList, chosenChequeValue);
	        updatePossibleChoices(currentAvailableChoices, currentChequeList);
	        lastPossibleCombination.add(new Cheque(chosenChequeValue, currentChequeList, currentAvailableChoices));
	    }
	    
	    //Test if latestCombination is equivalent to lastPossibleCombination
	    if (latestCombination.size() != lastPossibleCombination.size())
	        return false;
	    
	    boolean foundLastCombination = true;
	    for (int i = 0; i < latestCombination.size(); i++) {
	        if (latestCombination.get(i) != lastPossibleCombination.get(i).getValue())
	            foundLastCombination = false;
	    }
	    return foundLastCombination;
	}
}
