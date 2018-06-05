/* File: Lab03.java
 * Author: Wayne Snyder
 * Date: 2/5/2015
 * Purpose: This is the starter code for Lab03
 */

import java.util.Scanner;

public class Lab05 {
  
  public static void main(String [] args) {
    
    final int SIZE = 20; 
    
    Scanner input = new Scanner(System.in); 
    
    int[] S = new int[SIZE];              // Array holding a stack of integers
    int next = 0;                         // next available slot in the stack
    
    System.out.println("Welcome to the Integer Addition Program; you may input");
    System.out.println("integers to be stored on a stack, remove them, or add");
    System.out.println("the top two integers and push it back on the stack.");
    
    System.out.println("\nWhat would you like to do?");
    System.out.println("   1 -- Input an integer and push it on the stack;");    
    System.out.println("   2 -- Pop an integer off the stack and print it;");     
    System.out.println("   3 -- Add the top two integers and push the result back on the stack;");
    System.out.println("   4 -- Multiply the top two intergers and push the result back on the stack;");
    System.out.println("   5 -- Subtract  the top two integers and push the result back on the stack;");
    System.out.println("   6 -- Print out the stack;");
    System.out.println("Please input 1, 2, 3, 4, 5 , 6 or hit Control-d to end the program.");
    
    int answer = 0; 
    int n = 0; 
    int m = 0; 
    
    while(input.hasNextInt()) {   
    
     
      answer = input.nextInt(); 
        if (( answer ==3 || answer==4 || answer==5)  &&  next <2){
        System.out.println("You have to input at least 2 intergers. The programme will be terminated.");
        break;
      }
        if(answer==2&& next<1){
       System.out.println("There must be at least one number on the stack");
       break;
        }
      
      if(answer == 1) {
        System.out.println("What integer would you like to push on the stack?");
        n = input.nextInt(); 
        S[next] = n;
        ++next; 
      }
      else if(answer == 2) {
        --next;
        n = S[next];
        System.out.println("\nThe number " + n + " has been popped off the stack."); 
      }
      else if(answer == 3) {
        --next; 
        n = S[next];
        --next;
        m = S[next];
        S[next] = n + m;
        ++next;
      }
      else if(answer ==4){
        --next;
        n =S[next];
        --next;
        m =S[next];
        S[next] =m*n;
        ++next;
      }
      else if(answer ==5){
         --next;
        n =S[next];
        --next;
        m =S[next];
        S[next] =n-m;
        ++next;
      }
      else if(answer ==6){
        if(next ==0){
          System.out.println("The stack is empty.");
        }
        else{
          for(int i=next-1;i>=0;--i){
          System.out.println(S[i]);}
        System.out.println("__");
        }
      }
     
        
      else {
        System.out.println("Illegal input, try again!");
      }
      
      System.out.println("\nWhat would you like to do?");
      System.out.println("   1 -- Input an integer and push it on the stack;");    
      System.out.println("   2 -- Pop an integer off the stack and print it;");     
      System.out.println("   3 -- Add the top two integers and push the result back on the stack;");
      System.out.println("   4 -- Mutiply the top two intergers and push the result back on the stack;");
      System.out.println("   5 -- Substract  the top two integers and push the result back on the stack;");
      System.out.println("   6 -- Print out the stack;");
      System.out.println("Please input 1, 2, 3, 4, 5, 6 or hit Control-d to end the program.");
    }
    
  } 
}
