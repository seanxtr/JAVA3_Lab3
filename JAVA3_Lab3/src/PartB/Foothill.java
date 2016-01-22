package PartB;

import java.text.NumberFormat;
import java.util.Locale;

public class Foothill {

      public static void main(String[] args) throws Exception
      {
         int MAT_SIZE = 5;
    	  
         SparseMatWMult matA = new SparseMatWMult(MAT_SIZE, MAT_SIZE, 0.);
         SparseMatWMult matB = new SparseMatWMult(MAT_SIZE, MAT_SIZE, 0.);
         SparseMatWMult matC = new SparseMatWMult(MAT_SIZE, MAT_SIZE, 0.);
         
         System.out.println("Testing case #1 to check the algorithm");
         
         for (int i = 0; i < MAT_SIZE; i++)
         {
             for (int k = 0; k < MAT_SIZE; k++)
             {
                 matA.set(i, k, Math.random() * 10);
                 matB.set(i, k, Math.random() * 10);
             }
         }
         
         System.out.println("Matrix A");
         matA.showSubSquare(0, 5);
         System.out.println();
         
         System.out.println("Matrix B");
         matB.showSubSquare(0, 5);
         System.out.println();
         
         System.out.println("Matrix A X Matrix B");
         matC.matMult(matA, matB);
         matC.showSubSquare(0, 5);
         System.out.println();
         
         int testCaseNumber = 2;
         
         double[] matrixSaturation = new double[] {0.002, 0.01, 0.05, 0.1};
         int[] matrixSize = new int[] {50, 100, 200, 400, 800, 1600, 3200};
         for (int i = 0; i < matrixSaturation.length; i++){
        	 for (int k = 0; k < matrixSize.length; k++){
        		 
        		 System.out.printf("Testing case #%d Saturation[%f] Size [%d]",
        		    testCaseNumber, matrixSaturation[i],matrixSize[k]);
        		 matA = new SparseMatWMult(matrixSize[k], matrixSize[k], 0.);
                 matB = new SparseMatWMult(matrixSize[k], matrixSize[k], 0.);
                 matC = new SparseMatWMult(matrixSize[k], matrixSize[k], 0.);
                 
                 double smallPercent = 
                	matrixSize[k] * matrixSize[k] * matrixSaturation[i];
                 for (int r = 0; r < smallPercent; r++)
                 {
                    boolean valueInserted = false;
                    
                    while (!valueInserted){
                       int randRow = (int)(Math.random() * matrixSize[k]);
                       int randCol = (int)(Math.random() * matrixSize[k]);
                       
                       if (matA.get(randRow, randCol) == 0.0){
                          matA.set(randRow, randCol, Math.random() * 10);
                          valueInserted = true;
                       }
                    }
                    
                    valueInserted = false;
                    while (!valueInserted){
                        int randRow = (int)(Math.random() * matrixSize[k]);
                        int randCol = (int)(Math.random() * matrixSize[k]);
                        
                        if (matB.get(randRow, randCol) == 0.0){
                           matB.set(randRow, randCol, Math.random() * 10);
                           valueInserted = true;
                        }
                     }
                 }

                 long startTime, stopTime;
                 NumberFormat tidy = NumberFormat.getInstance(Locale.US);
                 tidy.setMaximumFractionDigits(4);
                 
                 startTime = System.nanoTime();
                 matC.matMult(matA, matB);
                 stopTime = System.nanoTime();
                 
                 //matC.showSubSquare(0, 12);
                 System.out.println(" Elapsed Time: "
                    + tidy.format( (stopTime - startTime) / 1e9)
                    + " seconds.");

                 System.out.println();
                 testCaseNumber++;
        	 }
         }
         
         
         //System.out.println("Testing case # to check the algorithm");
      }
}
