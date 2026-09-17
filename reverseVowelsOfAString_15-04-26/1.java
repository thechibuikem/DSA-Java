/*
Runtime: 4ms
Beats: 36.99%
 */

class Solution {
    public String reverseVowels(String s) {
        char[] words = s.toCharArray();
        int start = 0;
        int end = words.length - 1;
        String vowels = "AEIOUaeiou";

        while (start<end){

            if (vowels.indexOf(words[start]) == -1){
                start ++;
                continue;
        }

            if (vowels.indexOf(words[end]) == -1){
                end --;
                continue;
        }

                char temp = words[start];
                words[start] = words[end];
                words[end] = temp;

                start++;
                end--;


        }
        String answer = new String(words);
        return answer;
    }
}

/*
- this method adopts a two (2) pointers approach
- first i convert the string to a char-array so I can traverse through & modify it better
- one iterating from the 'start' and another from the 'end'
- whenever, any pointer cross a consonant, we just advance 
- whenever a pointer approaches a vowel, the other pointer adjusts itself till it hits a vowel too
- when both are on vowels
- store 'start' pointer vowel in a 'temp' variable
- replace the vowel in the 'start' pointers position, with the vowel in the 'end' pointer
- replace the vowel in the 'end' pointers position, with the vowel in the 'temp' pointer
- after loop is done, convert char array back to string
- return string


*/