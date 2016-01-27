package PartB;

import java.text.NumberFormat;
import java.util.Locale;

public class Foothill {
   public static void main(String[] args) throws Exception {
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
      int[] matrixSize = new int[] {10,15, 25, 50, 100, 200, 400, 800, 1600, 2400, 3200};
      
      for (int i = 0; i < matrixSaturation.length; i++){
         for (int k = 0; k < matrixSize.length; k++){
            System.out.printf("Testing case #%d Saturation[%f] Size [%d]",
            testCaseNumber, matrixSaturation[i],matrixSize[k]);
            matA = new SparseMatWMult(matrixSize[k], matrixSize[k], 0.);
            matB = new SparseMatWMult(matrixSize[k], matrixSize[k], 0.);
            matC = new SparseMatWMult(matrixSize[k], matrixSize[k], 0.);

            double smallPercent = 
               matrixSize[k] * matrixSize[k] * matrixSaturation[i];
            for (int r = 0; r < smallPercent; r++) {
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
   }
}


/********** RUN ********************

Testing case #1 to check the algorithm
Matrix A
    1.000    2.000    3.000    4.000    5.000
   -1.000   -2.000   -3.000   -4.000   -5.000
    1.000    3.000    1.000    3.000    1.000
    0.000    1.000    0.000    1.000    0.000
   -1.000   -1.000   -1.000   -1.000   -1.000

Matrix B
    2.000    1.000    5.000    0.000    2.000
    1.000    4.000    3.000    2.000    7.000
    4.000    4.000    4.000    4.000    4.000
    7.000    1.000   -1.000   -1.000   -1.000
    0.000    0.000    8.000   -1.000   -6.000

Matrix A X Matrix B
   44.000   25.000   59.000    7.000   -6.000
  -44.000  -25.000  -59.000   -7.000    6.000
   30.000   20.000   23.000    6.000   18.000
    8.000    5.000    2.000    1.000    6.000
  -14.000  -10.000  -19.000   -4.000   -6.000

Testing case #2 Saturation[0.002000] Size [10] Elapsed Time: 0 seconds.
Testing case #3 Saturation[0.002000] Size [15] Elapsed Time: 0 seconds.
Testing case #4 Saturation[0.002000] Size [25] Elapsed Time: 0 seconds.
Testing case #5 Saturation[0.002000] Size [50] Elapsed Time: 0 seconds.
Testing case #6 Saturation[0.002000] Size [100] Elapsed Time: 0.0001 seconds.
Testing case #7 Saturation[0.002000] Size [200] Elapsed Time: 0.0001 seconds.
Testing case #8 Saturation[0.002000] Size [400] Elapsed Time: 0.0003 seconds.
Testing case #9 Saturation[0.002000] Size [800] Elapsed Time: 0.0018 seconds.
Testing case #10 Saturation[0.002000] Size [1600] Elapsed Time: 0.0048 seconds.
Testing case #11 Saturation[0.002000] Size [2400] Elapsed Time: 0.0107 seconds.
Testing case #12 Saturation[0.002000] Size [3200] Elapsed Time: 0.0206 seconds.
Testing case #13 Saturation[0.010000] Size [10] Elapsed Time: 0 seconds.
Testing case #14 Saturation[0.010000] Size [15] Elapsed Time: 0 seconds.
Testing case #15 Saturation[0.010000] Size [25] Elapsed Time: 0 seconds.
Testing case #16 Saturation[0.010000] Size [50] Elapsed Time: 0 seconds.
Testing case #17 Saturation[0.010000] Size [100] Elapsed Time: 0 seconds.
Testing case #18 Saturation[0.010000] Size [200] Elapsed Time: 0.0001 seconds.
Testing case #19 Saturation[0.010000] Size [400] Elapsed Time: 0.0008 seconds.
Testing case #20 Saturation[0.010000] Size [800] Elapsed Time: 0.0063 seconds.
Testing case #21 Saturation[0.010000] Size [1600] Elapsed Time: 0.1104 seconds.
Testing case #22 Saturation[0.010000] Size [2400] Elapsed Time: 1.005 seconds.
Testing case #23 Saturation[0.010000] Size [3200] Elapsed Time: 6.2796 seconds.
Testing case #24 Saturation[0.050000] Size [10] Elapsed Time: 0 seconds.
Testing case #25 Saturation[0.050000] Size [15] Elapsed Time: 0 seconds.
Testing case #26 Saturation[0.050000] Size [25] Elapsed Time: 0 seconds.
Testing case #27 Saturation[0.050000] Size [50] Elapsed Time: 0 seconds.
Testing case #28 Saturation[0.050000] Size [100] Elapsed Time: 0.0001 seconds.
Testing case #29 Saturation[0.050000] Size [200] Elapsed Time: 0.0022 seconds.
Testing case #30 Saturation[0.050000] Size [400] Elapsed Time: 0.0377 seconds.
Testing case #31 Saturation[0.050000] Size [800] Elapsed Time: 1.357 seconds.
Testing case #32 Saturation[0.050000] Size [1600] Elapsed Time: 27.4245 seconds.
Testing case #33 Saturation[0.050000] Size [2400] Elapsed Time: 153.3498 seconds.
Testing case #34 Saturation[0.050000] Size [3200] Elapsed Time: 511.4623 seconds.
Testing case #35 Saturation[0.100000] Size [10] Elapsed Time: 0 seconds.
Testing case #36 Saturation[0.100000] Size [15] Elapsed Time: 0 seconds.
Testing case #37 Saturation[0.100000] Size [25] Elapsed Time: 0 seconds.
Testing case #38 Saturation[0.100000] Size [50] Elapsed Time: 0.0001 seconds.
Testing case #39 Saturation[0.100000] Size [100] Elapsed Time: 0.0008 seconds.
Testing case #40 Saturation[0.100000] Size [200] Elapsed Time: 0.0125 seconds.
Testing case #41 Saturation[0.100000] Size [400] Elapsed Time: 0.2536 seconds.
Testing case #42 Saturation[0.100000] Size [800] Elapsed Time: 6.9055 seconds.
Testing case #43 Saturation[0.100000] Size [1600] Elapsed Time: 119.9499 seconds.
Testing case #44 Saturation[0.100000] Size [2400] Elapsed Time: 648.6995 seconds.
Testing case #45 Saturation[0.100000] Size [3200] Elapsed Time: 2,175.1606 seconds.

************************************/

/*********** Discussion *******************

The timing for matrix multiplication is big-oh of M^4 because
the code contains four nested loops. one of them has size of M, and 
the others have size of M only in worst case situation.

The theta estimate for the method is M^4 
The outer loop always has iteration of M,
but all three inner while loops have size of M*S, while S is the saturation
percentage. This is why this method runs faster with small S

regardless the saturation level of the matrix

Here is a table of run time relative to matrix size when S = 10%:
Size            Time (s)
10              0.0000
15              0.0000
25              0.0000
50              0.0001
100             0.0008
200             0.0125
400             0.2536
800             6.9055
1600          119.9499
2400          648.6995
3200         2175.1606

Q1: The smallest size to give a non-zero time is 25
Q2: when size doubled from 800 to 1600, time increase 17 times
when size tripled from 800 to 2400, time goes up 92 times
when size quadrupled from 800 to 3200, time goes up 310 times
Q3: The largest size I can get is 3200
Q4: The data meets the estimates. For instance, when size doubled,
I would expect run time to increase 2^4 = 16 times if the estimate is accurate.
The actual results are quite close.

*******************************************/