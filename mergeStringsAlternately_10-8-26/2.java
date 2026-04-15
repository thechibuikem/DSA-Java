/*
Runtime: 1ms
Beats: 95.64%
*/ 


class Solution {
    public String mergeAlternately(String word1, String word2) {
      int  length1 = word1.length();
      int  length2 = word2.length();
      int counter = 0;
      StringBuilder sb = new StringBuilder();

      while (counter < length1 && counter < length2){
        sb.append(word1.charAt(counter));
        sb.append(word2.charAt(counter));
        counter ++;
      }
      sb.append(word1.substring(counter));
      sb.append(word2.substring(counter));
      return sb.toString();
    }
}


/*
    APPROACH
    - this method employs the use of string builder class, which makes it possible to mutate a string, using a two-phase processing algorithm.
    - locally, a length1, length2 & counter variable which serve as guards for the loop operating our string construction are initialized
    - a StringBuilder instance is initialized.

    - In a while loop:
    + characters of word1 & word2 are appended to sb in an alternating order.
    + loop breaks once length of word1 or word2 is exceeded

    - On loop termination, the remaining substrings of word1 & word2 are appended to the string builders instance, with one unavoidably always being an empty string ""
    - finally we convert our string builder instance to a string, and return it
    _ this method follows an o(n) time complexity
*/ 