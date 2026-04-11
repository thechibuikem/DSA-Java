class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        // int i = 0;
        int firstZero = -1;
        int counter = 0;
        // int j = 0;

// first find the first zero in our array via linear search i.e o(n)
for (int a = 0; a < flowerbed.length; a++){
    if (flowerbed[a] == 0){
        // n = n - 1;
        firstZero = a;
        break;
    }
    else {
    return false;
    }
}

if (firstZero == -1){
    return false;
}

while (counter < flowerbed.length || n >= 0 || n <= flowerbed.length){
        if (flowerbed[firstZero + 2] == 0){
           counter = counter + 1;
            n = n-1;
        }

        if (n==0){
            return true;
        }
        // else{
        //     return false;
        // }
        }
        return true;
    }
}

// fails on edge-cases needs strong refactoring
