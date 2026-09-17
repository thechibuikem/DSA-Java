package timeComplexity;

public class quadraticTime {
    String [] list  = {"1", "2", "3", "4", "5"};
    
    public void printAllPossibleOrderedPairs(){
        for (String a : list) {
            for (String b : list) {
                System.out.println(a + " " + b);
    };
        };
}

public static void main(String[] args) {
    quadraticTime obj = new quadraticTime();
    obj.printAllPossibleOrderedPairs();
}


};