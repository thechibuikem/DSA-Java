package timeComplexity;

public class constantTime {
    String alphabets = "abcdefghijklmnopqrstuvwxyz";
    String vowels = "aeiou";
    int count = 0;

    public void checkVowels (){
    for (int i = 0; i < vowels.length(); i++){
        if (alphabets.indexOf(vowels.charAt(i)) != -1){
            count++;
        }
    };
    System.out.println("we have " + count + " vowels in alphabet");
    }

public static void main(String[] args) {
    constantTime obj = new constantTime();
    obj.checkVowels();
}

}

// --constant time when
// we deal with a fixed amount of input
// only touch a fixed amount within input

