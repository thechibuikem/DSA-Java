package find_duplicates;

import  java.util.HashSet;
import  java.util.Set;

    public class Solution {
        static int[] list = {1,2,3,4,5,2};

        public boolean checkDuplicate(int[] list){
            Set<Integer> seen = new HashSet<>();
            for (int l : list){
                if(seen.contains(l)){
                    return true;
                };
                seen.add(l);
        }
            return false;
        }

        public  static void main(String[] args){
            Solution obj = new Solution();
            boolean result = obj.checkDuplicate(list);
            System.out.println(result);
        }

}
