/*
Runtime: 4ms
Beats: 36.99%
 */

import java.util.*;

class Solution {
    public String reverseVowels(String s) {
        HashSet <Character> set = new HashSet<>(Set.of('a','e','i','o','u','A','E','I','O','U'));

        StringBuilder res = new StringBuilder(s);
        int i = 0, j = s.length() -1;

        while (i<j) {
            if (set.contains(s.charAt(i)) && set.contains(s.charAt(j))){
                res.setCharAt(i,s.charAt(j));
                res.setCharAt(j,s.charAt(i));
                i++;
                j--;
            }
            else if (
                !(set.contains(s.charAt(j)))
            ){
                j--;
                continue;
            }
                else if ( 
                !(set.contains(s.charAt(i)))
            ){
                i++;
                continue;
            }
        }

        return res.toString();
    } 
}


/*
MAJOR IMPROVEMENT ON 1
-This method significantly reduces compute time of the initial soln by using a hashset & stringBuilder instance
- Hashset in contrast to string to detect if a char is a vowel or not uses a hash look-up in it's set.contains() method O(1); unlike strings that use indexOf(), which is a linear search O(n)
-Then it uses two pointers within a while loop in a way very similar to soln 1.
- check if both i & j pointers are on vowels, if so swap characters using stringBuilders .setCharAt() method.
- if any vowel isn't found move pointer
- At the end of the day, convert stringbuilder back into a string and return it.
*/


