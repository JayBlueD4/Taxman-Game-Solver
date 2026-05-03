import java.util.ArrayList;

public class ChosenChequesList {
    private ArrayList<Cheque> chosenCheques;
    private int totalGain;
    
    //Constructor
    public ChosenChequesList(ArrayList<Cheque> chosenChequesList) {
        chosenCheques = new ArrayList<Cheque>();
        for (int i = 0; i < chosenChequesList.size(); i++) {
            chosenCheques.add(chosenChequesList.get(i));
        }
        
        for (Cheque cheque : chosenCheques) {
            totalGain += cheque.getValue();
        }
    }
    
    //Getter Methods
    public int getTotalGain() {
        return totalGain;
    }
    
    public String toString() {
        String chosenChequesList = "";
        for (int i = 0; i < chosenCheques.size(); i++) {
	        if (i == chosenCheques.size() - 1) 
	            chosenChequesList += chosenCheques.get(i).getValue() + " (total gain: " + totalGain + ")";
	        else
	            chosenChequesList += chosenCheques.get(i).getValue() + ", ";
	    }
	    return chosenChequesList;
    }
}