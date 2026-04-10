/*
Runtime: 1ms
Beats: 95.64%
*/ 

class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder(word1.length() + word2.length());

        int i = 0, j = 0;

        while (i < word1.length() || j < word2.length()) {
            if (i < word1.length()) sb.append(word1.charAt(i++));
            if (j < word2.length()) sb.append(word2.charAt(j++));
        }

        return sb.toString();
    }
}

/*
MAJOR IMPROVEMENT ON 2
- sb stands for stringbuilder instance
- uses a single phase algorithm
- i counter for word1
- j counter for word2
- if either counters is less than their respective words length, append the current character of the word being tracked to sb instance, always start from i -> j
- end loop once the both counters are >= their word lengths
- we avoid a two phase algorithm, and most importantly; the strings we'd create using "substring method in 2"
*/