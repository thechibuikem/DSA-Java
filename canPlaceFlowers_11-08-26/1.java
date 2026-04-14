/*
Runtime: 1ms
Beats: 98.60%*/


class Solution{
    public boolean canPlaceFlowers(int[] flowerbed, int n){
        for (int i = 0; (i <flowerbed.length) && (n > 0); i++){
            if ( 
                flowerbed[i] == 0 &&
                i == 0 || (flowerbed[i-1] == 0) &&
                i == flowerbed.length - 1 || flowerbed[i+ 1]== 0
            ){
                n --; //plant
                i ++; //skip cause it's been corrupted
            }
        }
        return n == 0;
    }
}

/* 
This method follows straight forward approach and principle
- for:
    + each port in our flower bed
    + as long as there are flowers to plant
- Check:
    + is this port empty; and
    + is it's left-door neighnour empty, or is this the first element; and
    + is it's right-door neighnour empty, or is this the last element
- plant a seed if these conditions are met
- if we planted a seed; skip the next iteration, because we can't plant anymore
- return true if we planted all seeds.
*/