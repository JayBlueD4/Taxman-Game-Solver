import java.util.ArrayList;

public class Cheque {
    private int value;
    private ArrayList<Integer> resultingChequeList;
    private ArrayList<Integer> nextPossibleChoices;
    
    //Constructor
    public Cheque (int chequeValue, ArrayList<Integer> resultingChequeList, ArrayList<Integer> nextPossibleChoices) {
        value = chequeValue;
        
        this.resultingChequeList = new ArrayList<Integer>();
        for (int i = 0; i < resultingChequeList.size(); i++) {
            this.resultingChequeList.add(resultingChequeList.get(i));
        }
        
        this.nextPossibleChoices = new ArrayList<Integer>();
        for (int i = 0; i < nextPossibleChoices.size(); i++) {
            this.nextPossibleChoices.add(nextPossibleChoices.get(i));
        }
    }
    
    //Getter Methods
    public int getValue() {
        return value;
    }
    public ArrayList<Integer> getResultingChequeList() {
        return resultingChequeList;
    }
    public ArrayList<Integer> getNextPossibleChoices() {
        return nextPossibleChoices;
    }
}