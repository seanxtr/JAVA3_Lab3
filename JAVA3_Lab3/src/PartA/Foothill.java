package PartA;

import java.text.NumberFormat;
import java.util.Locale;

public class Foothill
{
   final static int MAT_SIZE = 800;
   final static double SMALL_PCT = 0.1;
   final static double DEFAULT_VALUE = 0.0;
   
   // -------  proof of correctness --------------
   public static void main(String[] args) throws Exception
   {
      int r, randRow, randCol;
      long startTime, stopTime;
      double smallPercent;
      boolean valueInserted;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      // non-sparse matrices
      double[][] mat, matAns;

      // allocate matrices
      mat = new double[MAT_SIZE][MAT_SIZE];
      matAns = new double[MAT_SIZE][MAT_SIZE];
      
      // generate small% of non-default values bet 0 and 1
      smallPercent = MAT_SIZE * MAT_SIZE * SMALL_PCT;
      for (r = 0; r < smallPercent; r++)
      {
         valueInserted = false;
         
         while (!valueInserted){
            randRow = (int)(Math.random() * MAT_SIZE);
            randCol = (int)(Math.random() * MAT_SIZE);
            
            if (mat[randRow][randCol] == DEFAULT_VALUE){
               mat[randRow][randCol] = Math.random();
               valueInserted = true;
            }
         }
      }
    
      // 10x10 sub-matrix in lower right
      System.out.println("Orignial matrix:");
      matShow(mat, MAT_SIZE - 10, 10);
      System.out.println();
      
      startTime = System.nanoTime();
      matMult(mat, mat, matAns);
      stopTime = System.nanoTime();

      System.out.println("Result matrix after multiplication:");
      matShow(matAns, MAT_SIZE - 10, 10);
    
      System.out.println("\nSize = " + MAT_SIZE + " Mat. Mult. Elapsed Time: "
         + tidy.format( (stopTime - startTime) / 1e9)
         + " seconds.");
   }
   
   public static void matMult( double[][] matA,  double[][] matB, 
      double[][] matC){
      // check two matrixes has same number of rows
      if (matA.length != matB.length)
         return;
      
      // loop through each row in matrix A
      for(int i = 0; i < MAT_SIZE; i++){
         // loop through each column in matrix B
         for (int k = 0; k < MAT_SIZE; k++){
            // calculate the dot product between the two
            matC[i][k] = calcDotProduct(i,k, matA, matB);
         }
      }
   }
   
   private static double calcDotProduct(int row, int column, 
      double[][] matA, double[][] matB) {
      double sum = 0.0;
      
      // loop through each item in row of matrix A
      for(int i = 0; i < MAT_SIZE; i++){
         // calculate the dot product
         sum += matA[row][i] * matB[i][column];
      }
      return sum;
   }
   
   public static void matShow(double[][] matA, int start, int size){
// loop through each row
      for (int i = start; i < start + size; i++){
         // loop through each column
         for (int k = start; k < start + size; k++){
            // print out data value
            System.out.printf("%7.5s",matA[i][k]);
         }
         System.out.print('\n');
      }
   }
}