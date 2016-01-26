package PartB;

import java.util.Iterator;

import PartB.SparseMat.MatNode;
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
      
      FHlinkedList<MatNode> rowA, rowB;
      Iterator<MatNode> itrA, itrB;
      MatNode tempNodeA, tempNodeB;
      double sum;
      
      // resize current matrix
      this.rowSize = matA.rowSize;
      this.colSize = matB.colSize;
      this.defaultVal = 0.0;
      this.clear();
      
      // iterate through each row in matrix A
      for (int i = 0; i < matA.rowSize; i++){
         rowA = matA.rows.get(i);
         // skip the row when all its columns are default value 0
         if (rowA.size() <= 0)
            continue;
         
         // iterate through each column in the row
         itrA = rowA.iterator();
         while (itrA.hasNext()){
             tempNodeA = (MatNode)itrA.next();
             
             // get the corresponding row in matrix B
             rowB = matB.rows.get(tempNodeA.col);
             
             // iterate through columns in row
             itrB = rowB.iterator();
             while (itrB.hasNext()){
                tempNodeB = (MatNode)itrB.next();
                
                this.increment(i, tempNodeB.col, tempNodeA.data * tempNodeB.data);
             }
         }
      }
      return true;
   }
   
   public boolean increment(int r, int c, double x){
	      if (r < 0 || r > rowSize - 1 ||
	         c < 0 || c > colSize - 1)
	         return false;
	      
	      MatNode targetNode = FindNode(r,c);
	      
	      if (targetNode != null){
	         if (x == 0.0){
	            // remove target node
	            rows.get(r).remove(targetNode);
	         }
	         else
	            // add value to existing one
	            targetNode.data += x;
	      }
	      else
	      {
	         if (x != 0.0){
	            // add new node
	            rows.get(r).add(new MatNode(c, x));
	         }
	      }
	      
	      return true;
	   }
}