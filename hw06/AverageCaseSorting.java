/* File: AverageCaseSorting.java
 * Author:Lai Wei
 * Date: 2/25/15
 * Purpose: This is the code for Problem B.2 in HW 06 in Cs 112, Spring 2015
 */



import java.util.Random;
import java.awt.Color;

public class AverageCaseSorting{
  
  
  
  
  private static void swap(int[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }
  
  
  
// insertion sort from Algorithms, Sedgewick and Wayne, p.251
  
  private static  void insertionSort(int[] A) {
    int N = A.length;
    for (int i = 1; i < N; i++) {
      for (int j = i; j > 0 && less(A[j], A[j-1]); j--) {
        swap(A, j, j-1);
      }
    }
  } 
  
  // selection sort from Algorithms, Sedgewick and Wayne, p.249
  
  private static void selectionSort( int[] A) {
    int N = A.length;
    for (int i = 0; i < N; i++) {
      int min = i;
      for (int j = i+1; j < N; j++) {
        if (less(A[j], A[min])) min = j;
      }
      swap(A, i, min);  
    } 
  }
  
    // Mergesort from Sedgewick
  
  private static void mergeSort(int[] A) {
    int[] aux = new int[A.length];
    msHelper(A, aux, 0, A.length-1);
    
  }
  
  // stably merge A[lo .. mid] with A[mid+1 ..hi] using aux[lo .. hi]
  private static void merge(int[] A, int[] aux, int lo, int mid, int hi) {  
    
    // copy to aux[]
    for (int k = lo; k <= hi; k++) {
      aux[k] = A[k]; 
    }
    
    // merge back to A[]
    int i = lo, j = mid+1;
    for (int k = lo; k <= hi; k++) {
      if      (i > mid)              A[k] = aux[j++];   // this copying is unnecessary
      else if (j > hi)               A[k] = aux[i++];
      else if (less(aux[j], aux[i])) A[k] = aux[j++];
      else                           A[k] = aux[i++];
    } 
  }
  
  // mergesort A[lo..hi] using auxiliary array aux[lo..hi]
  private static void msHelper(int[] A, int[] aux, int lo, int hi) {
    if (hi <= lo) return;
    int mid = lo + (hi - lo) / 2;
    msHelper(A, aux, lo, mid);
    msHelper(A, aux, mid + 1, hi);
    merge(A, aux, lo, mid, hi);
  }
  
  
 
  
// Quicksort from Sedgewick
  
  private static void quickSort(int[] A) {   
    qsHelper(A, 0, A.length - 1);
  }
  
  // quicksort the subarray from A[lo] to A[hi]
  
  private static void qsHelper(int[] A, int lo, int hi) { 
    if (hi <= lo) return;
    int j = partition(A, lo, hi);
    qsHelper(A, lo, j-1);
    qsHelper(A, j+1, hi);
  }
  
  // partition the subarray A[lo..hi] so that A[lo..j-1] <= A[j] <= A[j+1..hi]
  // and return the index j.
  private static int partition(int[] A, int lo, int hi) {
    int i = lo;
    int j = hi + 1;
    int v = A[lo];
    while (true) { 
      
      // find item on lo to swap
      while (less(A[++i], v))
        if (i == hi) break;
      
      // find item on hi to swap
      while (less(v, A[--j]))
        if (j == lo) break;      // redundant since A[lo] acts as sentinel
      
      // check if pointers cross
      if (i >= j) break;
      
      swap(A, i, j);
    }
    
    // put partitioning item v at A[j]
    swap(A, lo, j);
    
    // now, A[lo .. j-1] <= A[j] <= A[j+1 .. hi]
    return j;
  }
  
  
  
  
  
  
  
  private static Random R = new Random();  // Make this a private member of your ComplexityLaboratory class
  
  // create an array with size elements and fill it with random four-digit numbers
  
  private static  int [] generateRandomArray(int size) {
    int A [] = new int [size];
    for(int i=0;i<size;++i){
      A[i] =   R.nextInt(10000);                      // this generates a random number from 0 .. 9999
    }
    return A;
  }
  
  
  
  private static int counter = 0; 
  private static boolean less(int v, int w) {
    ++counter;
    return v < w; 
  } 
  
  
  private static void drawGraphs() {
    int N=100;
    StdDraw.setXscale(0, N);
    StdDraw.setYscale(0, 10*N);
    StdDraw.setPenRadius(0.005);
    StdDraw.setPenColor(Color.black);       // Google "Java Color"; the first link gives all the colors
    StdDraw.line(0,0,0,10*N); 
    StdDraw.line(0,0,N,0);
    double prevX = 0;
    double prevY =0;
    counter =0;
    
    // Draw the graph with selectionSort
    for(int i=0;i<N;++i){
      
      StdDraw.setPenRadius(0.005);
      selectionSort(generateRandomArray(i));
      StdDraw.point(i, counter);
      StdDraw.setPenRadius(0.001);
      StdDraw.line(prevX,prevY,i,counter);
      prevX =i;
      prevY =counter;
      counter =0;
    }
    
    // reset the value.
    prevX = 0;
    prevY =0;
    counter =0;
    
    // Draw the graph with insertionSort.
    StdDraw.setPenColor(Color.blue);
    for(int i=0;i<N;++i){
      
      StdDraw.setPenRadius(0.005);
      insertionSort(generateRandomArray(i));
      StdDraw.point(i, counter);
      StdDraw.setPenRadius(0.001);
      StdDraw.line(prevX,prevY,i,counter);
      prevX =i;
      prevY =counter;
      counter =0;
      
   
    }  

   // reset the value.
    prevX = 0;
    prevY =0;
    counter =0;
    
    // Draw the graph with mergesort
    
       StdDraw.setPenColor(Color.green);
    for(int i=0;i<N;++i){
      
      StdDraw.setPenRadius(0.005);
      mergeSort(generateRandomArray(i));
      StdDraw.point(i, counter);
      StdDraw.setPenRadius(0.001);
      StdDraw.line(prevX,prevY,i,counter);
      prevX =i;
      prevY =counter;
      counter =0;
    }
    
      // reset the value.
    prevX = 0;
    prevY =0;
    counter =0;
    
      // Draw the graph with quicksort
    
        StdDraw.setPenColor(Color.red);
    for(int i=0;i<N;++i){
      
      StdDraw.setPenRadius(0.005);
      quickSort(generateRandomArray(i));
      StdDraw.point(i, counter);
      StdDraw.setPenRadius(0.001);
      StdDraw.line(prevX,prevY,i,counter);
      prevX =i;
      prevY =counter;
      counter =0;
    }
  }
      
   
  
    
  
  public static  void main(String[] args) {
    drawGraphs(); 
    
  
  
}
}




    
    
                