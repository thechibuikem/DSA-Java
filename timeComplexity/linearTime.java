package timeComplexity;

public class linearTime {

    String [] fruits =  {"apple", "banana", "orange", "mango"};

    public void printFruits (){
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }

    public static void main(String[] args) {
    linearTime obj = new linearTime();
    obj.printFruits();
}   
}



/*
--constant time when
- work grws as inout size grows
- String[] fruits = new String[4]; creates an empty array with a fixed size of 4
- Ignores constant, O(n/2) == 0(n), cause ops count would always be half n  
*/ 
 

