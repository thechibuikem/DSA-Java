package timeComplexity;

public class logarithmicTime {

    int[] list = {1,3,4,5,10, 15,18,50};


    public int binarySearch (int[] list, int x) {

        int low = 0;
        int high = list.length -1;
        
        while (low <= high){
            int mid = low + (high - low) / 2; 
            
            if (list[mid] == x) {
                return x;
            }

            else if (x > list[mid]){
                low = mid + 1;
            }

            else if (x < list[mid]){
                high = mid - 1;
            } 
        }
        return -1;
    }
}

/*
- when the size input decreses by a certain factor after each step, e.g binary search
- binary search is like opening a dictionary, on each go we split the two inputs in half and discard a group
- binary search works on sorted lists
*/