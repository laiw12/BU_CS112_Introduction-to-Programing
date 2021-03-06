/*
 * Palindrome.java
 * 
 * Author: Lai Wei
 * Boston University CS 112
 * 
 * Purpose: This is my homework 4 problem B.1
 */

import java.util.Stack;               // this is the built-in Java generic Stack

public class Palindrome {
   private static boolean debug = true;                          // set this to true if you want to trace your execution
    private static  void db(String s) {            
          if(debug) 
             System.out.println("\t" + s);
      }
    
  private static  boolean palindrome(String str) {
   
         Stack<Character> s = new Stack<Character>();
      
        /* Push first half of str onto stack. */
        for (int i = 0; i < str.length() / 2; i++) {
            s.push(str.charAt(i));
            db(str.charAt(i)+" is pushed on the stack");
            
        }
      
        if (str.length()%2==0){
        /* Pop second half of str from stack and compare. */
        for (int i = str.length()/2;i<str.length();++i) {
            char c = s.pop();
            
            db(c+" is compared with " +str.charAt(i));
            if (c != str.charAt(i))
         
           
                return false;
        }
        
        return true;
        }   
        else{  for (int i = str.length()/2+1;i<str.length();++i) {
            char c = s.pop();
            
            db(c+" is compared with " +str.charAt(i));
            if (c != str.charAt(i))
         
           
                return false;
        }
        
        return true;
        }   
  }
  
     
    
     public static void main(String[] args) {
         
         System.out.println("Is redder a palindrome? Should be true:");
         System.out.println(palindrome("redder"));
         
         System.out.println("Is reddest a palindrome? Should be false:");
         System.out.println(palindrome("reddest"));
            
         System.out.println("Is STATS a palindrome? Should be true:");
         System.out.println(palindrome("STATS"));
         
           System.out.println("Is banana a palindrome? Should be false:");
         System.out.println(palindrome("banana"));
         
     }
    
}