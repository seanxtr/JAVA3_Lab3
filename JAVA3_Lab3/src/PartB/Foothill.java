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


/********** RUN ********************

Testing case #1 to check the algorithm
Matrix A
    9.1    8.4    8.9    9.2    6.1
    7.5    4.5    1.5    6.7   10.0
    8.1    9.1    9.9    9.7    4.9
    8.6    3.8    9.2    0.4    2.7
    1.4    8.6    3.6    7.2    4.3

Matrix B
    1.8    2.3    4.6    7.5    0.7
    5.7    5.2    1.8    1.9    6.8
    0.5    2.3    7.0    5.6    4.8
    8.7    8.7    6.4    7.8    8.4
    1.0    5.8    2.6    7.2    3.4

Matrix A X Matrix B
  155.2  199.6  195.4  249.9  204.1
  108.2  159.6  123.0  197.0  132.9
  160.7  200.4  198.6  244.5  212.8
   48.9   79.7  121.6  146.3   89.0
  120.3  143.4  105.2  134.5  151.6

Testing case #2 Saturation[0.002000] Size [50] Elapsed Time: 0.001 seconds.

Testing case #3 Saturation[0.002000] Size [100] Elapsed Time: 0.0044 seconds.

Testing case #4 Saturation[0.002000] Size [200] Elapsed Time: 0.008 seconds.

Testing case #5 Saturation[0.002000] Size [400] Elapsed Time: 0.0219 seconds.

Testing case #6 Saturation[0.002000] Size [800] Elapsed Time: 0.1289 seconds.

Testing case #7 Saturation[0.002000] Size [1600] Elapsed Time: 0.8736 seconds.

Testing case #8 Saturation[0.002000] Size [3200] Elapsed Time: 8.0221 seconds.

Testing case #9 Saturation[0.010000] Size [50] Elapsed Time: 0.0001 seconds.

Testing case #10 Saturation[0.010000] Size [100] Elapsed Time: 0.0013 seconds.

Testing case #11 Saturation[0.010000] Size [200] Elapsed Time: 0.0069 seconds.

Testing case #12 Saturation[0.010000] Size [400] Elapsed Time: 0.0621 seconds.

Testing case #13 Saturation[0.010000] Size [800] Elapsed Time: 0.7308 seconds.

Testing case #14 Saturation[0.010000] Size [1600] Elapsed Time: 11.0933 seconds.

Testing case #15 Saturation[0.010000] Size [3200] Elapsed Time: 233.7011 seconds.

Testing case #16 Saturation[0.050000] Size [50] Elapsed Time: 0.0005 seconds.

Testing case #17 Saturation[0.050000] Size [100] Elapsed Time: 0.0046 seconds.

Testing case #18 Saturation[0.050000] Size [200] Elapsed Time: 0.0576 seconds.

Testing case #19 Saturation[0.050000] Size [400] Elapsed Time: 0.9898 seconds.

Testing case #20 Saturation[0.050000] Size [800] Elapsed Time: 14.7139 seconds.

Testing case #21 Saturation[0.050000] Size [1600]

************************************/