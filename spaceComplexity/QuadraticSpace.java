package spaceComplexity;

import java.util.Arrays;


public class QuadraticSpace {

    // int number = 4;

    public int[][] createMatrix(int number){
        int[][] matrix = new int[number][number];
        for (int i = 0; i < number ; i ++){
            matrix[i][i] = 1;
            }
            return matrix;
        }

    

    public static void main(String[] args) {
        QuadraticSpace obj = new QuadraticSpace();
        int[][] matrix = obj.createMatrix(3);

        System.out.println(Arrays.deepToString(matrix));
    }
}

























// public class QuadraticSpace {

//     // int number = 4;

//     public Integer[][] createMatrix(int number){
//         Integer[][] matrix = new Integer[number][number];
//         for (int i = 0; i < number ; i ++){
//             for (int j = 0; j < number; j++){
//                 if (i == j){
//                     matrix[i][j] = 1;
//                 }else{
//                     matrix[i][j] = 0;
//                 }
//             }
//         }
//         return matrix;
//     }

//     public static void main(String[] args) {
//         QuadraticSpace obj = new QuadraticSpace();
//         Integer[][] matrix = obj.createMatrix(3);

//         System.out.println(Arrays.deepToString(matrix));
//     }
    
// }


/*
Given an input number, create an identity square matrix

*/