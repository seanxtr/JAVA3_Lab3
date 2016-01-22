package PartB;

import java.util.Iterator;
import cs_1c.FHlinkedList;

public class SparseMatWMult extends SparseMat<Double>
{
   /**
    * 3-parameter constructor
    * @param numRows
    * @param numCols
    * @param defaultVal
    */
   SparseMatWMult(int numRows, int numCols, Double defaultVal) {
		super(numRows, numCols, defaultVal);
   }

   /**
    * Method to multiply two matrixes and output result to current object
    * @param matA
    * @param matB
    * @return
    */
   public boolean matMult(SparseMatWMult matA, SparseMatWMult matB)
   {
      if (matA.rowSize < 1 || matA.colSize < 1 ||
          matB.rowSize < 1 || matB.colSize < 1)
           return false;
      
      if (matA.rowSize != matB.colSize || matA.colSize != matB.rowSize)
           return false;
      
      FHlinkedList<MatNode> row;
      Iterator<MatNode> itr;
      MatNode tempNode;
      double sum;
      
      // resize current matrix
      this.rowSize = matA.rowSize;
      this.colSize = matB.colSize;
      this.defaultVal = 0.0;
      this.clear();
      
      // iterate through each row in matrix A
      for (int i = 0; i < matA.rowSize; i++){
         
         // skip the row when all its columns are default value 0
         if (matA.rows.get(i).size() <= 0)
            continue;
         
         // iterate through each column in matrix B
         for (int k = 0; k < matB.colSize; k++){
            sum = 0;
            row = matA.rows.get(i);
            itr = row.iterator();
             // go through the linked list at the row
             while (itr.hasNext()){
                tempNode = (MatNode)itr.next();
                
                sum += tempNode.data * matB.get(tempNode.col, k);
                
             }
             
             this.set(i, k, sum);
         }
      }
      return true;
   }
}