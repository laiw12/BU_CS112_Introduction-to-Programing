/*
 * File: StringTest.java
 * Author:Lai Wei
 * email:laiw12@bu.edu
 * Date: January 23rd, 2015
 * Purpose: Lab problem 2
 */



import java.util.Scanner;

public class StringTest { 
  
  public static void main(String[] args) {
    
    // Print out welcome message
    
    System.out.println("\nWelcome to the String Test Program!");
    System.out.println("Demonstrate various features of the String Library");
    
    // Define a scanner for user input
    
    Scanner userInput = new Scanner(System.in);
    
    
    System.out.println("\nType in a sentence or Control-d to end:");                     // input process
    while(userInput.hasNextLine()) {
      
      String line = userInput.nextLine();
      String[] lines =line.split("\\s+");
      
      for(int i=0; i < lines.length; i+=1) {                                      // use substring and replace method to captialize the first letter
        lines[i]= lines[i].toLowerCase();
        String a;
        String b;
        a = lines[i].substring(0,1);
        a = a.replace(a,a.toUpperCase());
        b = lines[i].substring(1,lines[i].length());
        lines[i]= a + b;
        
        
      }
      
      lines[lines.length-1]=lines[lines.length-1].replace('.','!');              // replace the "." with "!"
      
      for(int i=0; i<lines.length;i+=1) {
        System.out.print(lines[i]+" ");}
      System.out.print("\n");
      
      
    }
    
    
    
    
    
    
  }
  
}
