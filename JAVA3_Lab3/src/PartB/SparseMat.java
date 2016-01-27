package PartB;
/**
 * SparseMat class definition
 * The class stores large amount of data in matrix-like structure 
 * without using unnecessary memory
 */
import java.util.Iterator;
import cs_1c.*;

public class SparseMat<E> implements Cloneable{
   
   /**
    * inner node class used to store column index and data
    */
   protected class MatNode implements Cloneable{
      public int col;
       public E data;

      // we need a default constructor for lists
      MatNode(){
         col = 0;
         data = null;
      }

      MatNode(int cl, E dt){
         col = cl;
         data = dt;
      }

      public Object clone() throws CloneNotSupportedException {
         // shallow copy
         MatNode newObject = (MatNode)super.clone();
         return (Object) newObject;
      }
   };
   
   // class member data
   protected int rowSize, colSize;
   protected E defaultVal;
   protected FHarrayList <FHlinkedList<MatNode>> rows;
   
   /**
    * 3 parameter constructor
    * @param numRows        number of rows in matrix
    * @param numCols        number of column in matrix
    * @param defaultVal     default value 
    */
   SparseMat(int numRows, int numCols, E defaultVal) {
      // check row and column size
      if (numRows >= 1 || numCols >= 1) {
         this.rowSize = numRows;
         this.colSize = numCols;
         this.defaultVal = defaultVal;
   
         // Initialize the array list
         allocateEmptyMatrix();
      }
   }
   
   /**
    * Method to get data value with given row and column index
    * @param r     row index
    * @param c     column index
    * @return      data value
    */
   public E get(int r, int c){
      if (r < 0 || r > rowSize - 1 ||
         c < 0 || c > colSize - 1)
         throw new IndexOutOfBoundsException();
      
      MatNode targetNode = FindNode(r,c);
      
      return targetNode == null ? defaultVal : (E)targetNode.data;
   }
   
   /**
    * Method to set data value at given row and column index
    * If value exists at given location, update it.
    * If not, add new value to matrix
    * @param r      row index
    * @param c      column index
    * @param x      new data value
    * @return       return false when row or column index is out of range
    */
   public boolean set(int r, int c, E x){
      if (r < 0 || r > rowSize - 1 ||
         c < 0 || c > colSize - 1)
         return false;
      
      MatNode targetNode = FindNode(r,c);
      
      if (targetNode != null){
         if (x.equals(defaultVal)){
            // remove target node
            rows.get(r).remove(targetNode);
         }
         else
            // update target node data
            targetNode.data = x;
      } else {
         if (!x.equals(defaultVal)){
            // add new node
            rows.get(r).add(new MatNode(c, x));
         }
      }
      
      return true;
   }
   
   /**
    * Helper method to get the MatNode at given location
    * @param r      row index
    * @param c      column index
    * @return       target node if exists, otherwise null
    */
   protected MatNode FindNode(int r, int c) {
      // get row
      FHlinkedList<MatNode> row = rows.get(r);
      Iterator<MatNode> itr = row.iterator();
      MatNode tempNode;
      
      // go through the linked list at the row
      while (itr.hasNext()){
         tempNode = (MatNode)itr.next();
         
         // if column index matches
         if (tempNode.col == c)
            return tempNode;
      }
      
      // return null when not found
      return null;
   }
   
   /**
    * Helper method to initialize each row of array list to an empty linked list
    */
   private void allocateEmptyMatrix(){
      rows = new FHarrayList<FHlinkedList<MatNode>>();
      
      // loop through each rows
      for (int i = 0; i < rowSize; i++) {
         rows.add(new FHlinkedList<MatNode>());
      }
   }
   
   /**
    * Method to clear the matrix but keep its size
    */
   public void clear(){
      allocateEmptyMatrix();
   }
   
   /**
    * Method to show a square sub-matrix anchored at (start, start) 
    * and whose size is size x size.
    * @param start      start position of the square
    * @param size       size of the square
    */
   public void showSubSquare(int start, int size){
      MatNode tempNode;
      
      // loop through each row
      for (int i = start; i < start + size; i++){
         // loop through each column
         for (int k = start; k < start + size; k++){
            // try to find node
            tempNode = FindNode(i,k);
            
            // print out data value
            System.out.printf("%9.3f",tempNode == null ? 
               defaultVal : tempNode.data);
         }
         System.out.print('\n');
      }
   }
   
   /**
    * Method to deep copy the current class
    */
   public Object clone() throws CloneNotSupportedException {
      FHlinkedList<MatNode> row;
      Iterator<MatNode> itr;
      
      // create a new sparse mat with property as current class
      SparseMat<E> newMat = 
         new SparseMat<E>(this.rowSize, this.colSize, this.defaultVal);
      
      // loop through each row in current class
      for(int i = 0; i < rows.size(); i++){
         row = rows.get(i);
         itr = row.iterator();
         
         // loop through each column
         while (itr.hasNext()){
            // deep copy each node to new sparse mat
            newMat.rows.get(i).add((MatNode)itr.next().clone());
         }
      }
      return newMat;
   }
}