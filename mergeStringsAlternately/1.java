/*
10-08-26
Runtime: 4ms
Beats: 20.14%
*/ 


class Solution {
    public String mergeAlternately(String word1, String word2) {
        String newWord = "";  
        newWord = stringGeneration(newWord, word1, word2);
        return newWord;
    }

    public String stringGeneration(String newWord,String word1, String word2){
        // path 1
        if (word1.length()>word2.length()){
            if (newWord.length() < word1.length()+ word2.length()){
                for (int i = 0;i < word2.length(); i++){
                    newWord = newWord + word1.charAt(i) + word2.charAt(i);
                } 
                newWord = newWord + word1.substring(word2.length());
                return newWord ;
            }
        }
        // path 2
        else{
                if (newWord.length() < word1.length()+ word2.length()){
                for (int i = 0; i < word1.length(); i++){
                    newWord = newWord + word1.charAt(i) + word2.charAt(i);
                } 
                newWord = newWord + word2.substring(word1.length());
                return newWord ;
            }
        }
return newWord;
}
}

/*
APPROACH
- my method involved initializing an empty newWord variable that would store the result of our entire computation.
- since there are two realities that determine what our end result would be i.e if word 1 is longer or word 2 is longer. I used a conditional block to denote these paths.
- within a path we conduct another check, we use this to track our newWord variable in order to stop computation
- we then starts a loop, that stops once the characters of the shorter word has been used up.
- from here we assign the newWord, it's current value cancatenated with alternating characters of our two words, following our loops order.
- once the loop breaks we append the remaining characters of our longer variable to the existing newWord.
- we then return newWord.
- core idea: Iterate through two strings simultaneously using two pointers to merge characters alternately.
*/ 


/*
INSUFFICIENCES
- my method was insufficient because my loops were exhibiting o(n^2) behaviour.
- java strings are immutable, meaning that every reassignment of s, creates a new string, and readjusting our pointer s to target the new string object; while dumping the old instance in memory.
- this makes every next iteration more computationally expensive than the previous one. 
- 2.java shows a more resourceful way of tackling this issue
*/ 