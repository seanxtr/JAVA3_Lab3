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
         
         // fill data into matrix A
         matA.set(0, 0, 1.);
         matA.set(0, 1, 2.);
         matA.set(0, 2, 3.);
         matA.set(0, 3, 4.);
         matA.set(0, 4, 5.);
         
         matA.set(1, 0, -1.);
         matA.set(1, 1, -2.);
         matA.set(1, 2, -3.);
         matA.set(1, 3, -4.);
         matA.set(1, 4, -5.);
         
         matA.set(2, 0, 1.);
         matA.set(2, 1, 3.);
         matA.set(2, 2, 1.);
         matA.set(2, 3, 3.);
         matA.set(2, 4, 1.);
         
         matA.set(3, 0, 0.);
         matA.set(3, 1, 1.);
         matA.set(3, 2, 0.);
         matA.set(3, 3, 1.);
         matA.set(3, 4, 0.);
         
         matA.set(4, 0, -1.);
         matA.set(4, 1, -1.);
         matA.set(4, 2, -1.);
         matA.set(4, 3, -1.);
         matA.set(4, 4, -1.);
         
         // fill data into matrix B
         matB.set(0, 0, 2.);
         matB.set(0, 1, 1.);
         matB.set(0, 2, 5.);
         matB.set(0, 3, 0.);
         matB.set(0, 4, 2.);
         
         matB.set(1, 0, 1.);
         matB.set(1, 1, 4.);
         matB.set(1, 2, 3.);
         matB.set(1, 3, 2.);
         matB.set(1, 4, 7.);
         
         matB.set(2, 0, 4.);
         matB.set(2, 1, 4.);
         matB.set(2, 2, 4.);
         matB.set(2, 3, 4.);
         matB.set(2, 4, 4.);
         
         matB.set(3, 0, 7.);
         matB.set(3, 1, 1.);
         matB.set(3, 2, -1.);
         matB.set(3, 3, -1.);
         matB.set(3, 4, -1.);
         
         matB.set(4, 0, 0.);
         matB.set(4, 1, 0.);
         matB.set(4, 2, 8.);
         matB.set(4, 3, -1.);
         matB.set(4, 4, -6.);
         
         System.out.println("Testing case #1 to check the algorithm");
         System.out.println("Matrix A");
         matA.showSubSquare(0, MAT_SIZE);
         System.out.println();
         
         System.out.println("Matrix B");
         matB.showSubSquare(0, MAT_SIZE);
         System.out.println();
         
         System.out.println("Matrix A X Matrix B");
         matC.matMult(matA, matB);
         matC.showSubSquare(0, MAT_SIZE);
         System.out.println();
         
         int testCaseNumber = 2;
         
         double[] matrixSaturation = new double[] {0.002, 0.01, 0.05, 0.1};
         int[] matrixSize = new int[] {10,15, 25, 50, 100, 200, 400, 800, 1600, 3200};
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
                 
                 System.out.println(" Elapsed Time: "
                    + tidy.format( (stopTime - startTime) / 1e9)
                    + " seconds.");

                 testCaseNumber++;
        	 }
         }
         
         
         //System.out.println("Testing case # to check the algorithm");
      }
}


/********** RUN ********************

Testing case #1 to check the algorithm
Matrix A
    5.601    4.662    7.304
    4.228    8.818    6.818
    8.339    4.106    5.937

Matrix B
    8.522    8.592    6.851
    5.819    1.225    1.273
    9.868    2.658    3.046

Matrix A X Matrix B
  146.944   73.255   66.559
  154.624   65.253   60.961
  153.542   92.458   80.439

Testing case #2 Saturation[0.002000] Size [50] Elapsed Time: 0 seconds.

Testing case #3 Saturation[0.002000] Size [100] Elapsed Time: 0.0001 seconds.

Testing case #4 Saturation[0.002000] Size [200] Elapsed Time: 0.0002 seconds.

Testing case #5 Saturation[0.002000] Size [400] Elapsed Time: 0.0004 seconds.

Testing case #6 Saturation[0.002000] Size [800] Elapsed Time: 0.0024 seconds.

Testing case #7 Saturation[0.002000] Size [1600] Elapsed Time: 0.0075 seconds.

Testing case #8 Saturation[0.002000] Size [3200] Elapsed Time: 0.0341 seconds.

Testing case #9 Saturation[0.010000] Size [50] Elapsed Time: 0.0001 seconds.

Testing case #10 Saturation[0.010000] Size [100] Elapsed Time: 0.0001 seconds.

Testing case #11 Saturation[0.010000] Size [200] Elapsed Time: 0.0002 seconds.

Testing case #12 Saturation[0.010000] Size [400] Elapsed Time: 0.0015 seconds.

Testing case #13 Saturation[0.010000] Size [800] Elapsed Time: 0.0153 seconds.

Testing case #14 Saturation[0.010000] Size [1600] Elapsed Time: 0.2034 seconds.

Testing case #15 Saturation[0.010000] Size [3200] Elapsed Time: 7.1705 seconds.

Testing case #16 Saturation[0.050000] Size [50] Elapsed Time: 0 seconds.

Testing case #17 Saturation[0.050000] Size [100] Elapsed Time: 0.0001 seconds.

Testing case #18 Saturation[0.050000] Size [200] Elapsed Time: 0.002 seconds.

Testing case #19 Saturation[0.050000] Size [400] Elapsed Time: 0.0369 seconds.

Testing case #20 Saturation[0.050000] Size [800] Elapsed Time: 1.415 seconds.

Testing case #21 Saturation[0.050000] Size [1600] Elapsed Time: 29.622 seconds.

Testing case #22 Saturation[0.050000] Size [3200] Elapsed Time: 541.8371 seconds.

Testing case #23 Saturation[0.100000] Size [50] Elapsed Time: 0.0001 seconds.

Testing case #24 Saturation[0.100000] Size [100] Elapsed Time: 0.0008 seconds.

Testing case #25 Saturation[0.100000] Size [200] Elapsed Time: 0.0126 seconds.

Testing case #26 Saturation[0.100000] Size [400] Elapsed Time: 0.4814 seconds.

Testing case #27 Saturation[0.100000] Size [800] Elapsed Time: 7.3678 seconds.

Testing case #28 Saturation[0.100000] Size [1600] Elapsed Time: 130.2968 seconds.

Testing case #29 Saturation[0.100000] Size [3200] Elapsed Time: 2,177.481 seconds.

************************************/

/*********** Discussion *******************

The timing for matrix multiplication is big-oh of M^4 because
the code contains four nested loops. one of them has size of M, and 
the others have size of M only in worst case situation.

The theta estimate for the method is M^4*S^3 where S states 
for saturation percentage. The outer loop always has iteration of M,
but all three inner while loops have size of M*S. This is why this method 
runs faster with low saturation

regardless the saturation level of the matrix

Here is a table of run time relative to matrix size when S = 1%:
Size            Time (s)


Q1: The smallest size to give a non-zero time is 50
Q2: when size doubled from 800 to 1600, time increase 15 times
when size tripled from 800 to 2400, time goes up 61 times
when size quadrupled from 800 to 3200, time goes up 171 times
Q3: The largest size I can get is 3200
Q4: The data meets the estimates. For instance, when size doubled,
I would expect run time to increase 2^4 = 16 times if the estimate is accurate.
The acutal results are quite close.

*******************************************/