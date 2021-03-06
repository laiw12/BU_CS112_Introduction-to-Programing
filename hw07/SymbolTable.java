/* File: SymbolTable.java
 * Author: Lai Wei
 * Date: 3/22/15
 * Purpose: This is my HW07 Problem B.2
 */

public class SymbolTable<Value> {
  
  
  // basic definition of the Node class; this is an "inner class" inside class SymbolTable
  
  private class Node {                 // Node class for LLs
    String variable;
    Value value;
    Node next;
    
    public Node(String k, Value v, Node p) {
      variable = k; value = v; next = p;
    }
  }
  
  // pointer to list of bindings for symbol table
  
  private Node head = null; 
  private int count =0;
  
  
  //If the variable var is not in the symbol table, insert a new
  // node containing var and val into the linked list in ascending order
  public void put( String var ,Value val){
    ++count;
    head = putHelper(var, val, head);
  }
  
  
// My helper method of put(...)
  private Node putHelper( String var, Value val, Node p ) {
    if(p == null) 
      return new Node(var, val,p);
    else if(p.variable.compareTo(var)<0){
      p.next = putHelper(var,val,p.next);
      return p;
    }
    else if (p.variable.compareTo(var)==0){
      p.value=val;
      return p;
    }
    
    else{
      Node q = new Node(var,val,p.next);
      q.next=p;
      return q;
    }
  }
  public int size() {
    return count;
  }
  
  
  // Return the value associated with the variable var, or throw the
  // exception shown if var is not in the table.
  public Value get(String var) throws KeyNotFoundException{ 
    
    
    Value a =getHelper(var,head);
    if (a==null){
      throw new KeyNotFoundException("key ",var," does not exist")  ;
    }
    
    return a;
  }
  
  
  // helper function for my get(...)
  private Value getHelper( String var, Node p ){
    if(p==null){
      return null;
    }
    
    if(p.variable != var){
      
      return getHelper(var,p.next);
    }
    else 
      return p.value;
  }
  
  //Return true or false, depending on whether var is in the table or not.
  public boolean contains(String var) {
    return containsHelper(var,head);
  }
  
  // helper function of my contain(...) method.
  private boolean containsHelper(String var, Node p){
    if(p==null){
      return false   ;
    }
    if(p.variable != var){
      
      return containsHelper(var,p.next);
    }
    else 
      return  true;
  }
  
  
  // To check if the link list is empty
  public boolean isEmpty() {
    return isEmptyHelper(head);
  }
  
  // my helper function for my isEmpty method
  private boolean isEmptyHelper( Node p ){
    if(p==null){
      return true;
    }
    else 
      return false;
  }
  
  
  // give a String representation of the current link list
  public String toString() {  
    String s = toStringHelper(head);
    return s;
    
  }
  // helper function of my toString method.
  private String toStringHelper( Node p ) {
    String s="";
    if(p == null){
      return"";
    }
    if(p.next==null){
      return s+"("+p.variable+","+p.value+")";
    }
    else{
      ;
      return s+"("+p.variable+","+p.value+")"+" " +":" + " "+ toStringHelper(p.next);
    }
  }
  
  
  // Remove the node containing var from the table; if var is not
  // in the table, do nothing.  
  public void delete(String var) {
    head = deleteHelper(var,head);
    --count;
  }
  
  
  // this is my helper function of my delete(...) method. 
  private Node deleteHelper(String var, Node p){
    
    if( p == null )                 
      return p; 
    else if ( p.variable == var ) 
      return p.next;               
    else {
      p.next = deleteHelper( var, p.next );
      return p; 
    }
  }  
  
  //Return the smallest variable in the lexicographic ordering (this will be in the first node in the list); if the table is empty, throw the exception. 
  public String min() throws KeyNotFoundException{
    if(isEmpty()){
      throw new KeyNotFoundException("nope! ")  ;
    }
    else
      return head.variable;
  }
  
  // Similar to the previous, but return the largest entry, which will be in the last node in the linked list. 
  public String max() throws KeyNotFoundException{
    if(isEmpty()){
      throw new KeyNotFoundException("nope! ")  ;
    }
    else
      return maxHelper(head);
  }
  // helper function of max(...) method.
  private String maxHelper(Node p){
    if(p.next!=null){
      return maxHelper(p.next);
    }
    else 
      return p.variable;
  }
  
  
  //If the table is empty or if var is smaller than the smallest entry, throw the exception; 
  //if var is in the table, return var; otherwise, return the largest variable which is less 
  //than var (the entry just before where var would be inserted into the table). 
  public String floor(String var) throws  KeyNotFoundException  {
    
    if(isEmpty()){
      throw new KeyNotFoundException("nope! ")  ;
    }
    String str = head.variable;
    if(str.compareTo(var)>0){
      throw new KeyNotFoundException("nope! ")  ;
    }
    
    if (contains(var)){
      return var;
    }
    else{
      return  floorHelper(var,head);
    }
    
  }     
  
// my helper function for floor(....) method.       
  private String floorHelper(String var, Node p){
    Node q = p.next;
    if(q==null){
      return p.variable;
    }
    if(var.compareTo(q.variable)>0){
      return floorHelper(var,p.next);}
    else{
      return p.variable;
    }
  }
  
  
  
  
  
// If the table is empty or if var is larger than the largest entry, throw the exception; 
//  if var is in the table, return var; otherwise, return the smallest variable which is larger 
  //  than var (the entry just after where var would be inserted into the table)
  public String ceiling(String var)  throws  KeyNotFoundException  {
    if(isEmpty()){
      throw new KeyNotFoundException("nope! ")  ;
    }
    if(var.compareTo(max())>0){
      throw new KeyNotFoundException("nope! ")  ;
    }
    
    
    if(contains(var)){
      return var;
    }
    else{
      return ceilingHelper(var,head);
    }
  }
  
// helper function for ceiling(...) method.    
  
  private String ceilingHelper(String var, Node p){
    if(p.variable.compareTo(var)<0){
      return ceilingHelper(var,p.next);
    }
    else{
      return p.variable;
    }
  }
  
  
  //Return the variable which is at rank r in the linked list (starting
  // the count at 0 with the first node, as in an array). If r is not the 
  //rank of any element, i.e., it is negative or is equal to or larger than the length 
  //of the linked list, throw the exception. 
  public String select(int r) throws KeyNotFoundException { 
    if(r>=count){
      throw new KeyNotFoundException("nope! ")  ;
    }
    else return(selectHelper(r,head));
  }
  
  // my helper function of my selectHelper method.
  private String selectHelper(int r, Node p){
    if(r==0){
      return p.variable;
    }
    else{
      r=r-1;
      return selectHelper(r,p.next);
    }
  }
//Remove the smallest entry in the table; if the table is empty, do nothing. 
  public void deleteMin() { 
    if (isEmpty()==false){
      delete(head.variable);
      
    }
  }
  //Remove the biggest entry in the table; if the table is empty, do nothing. 
  public void deleteMax() {
    if (isEmpty()==false){
      
      
      delete(maxHelper(head));
    }
  }
  
  
//Return the "rank" of var, i.e., the number of entries in the table which are smaller than var; the rank
//of variables which are in the table is calculated by counting 0, 1, 2, starting at the first node (as
//if it were an array); if var is not in the table, then it is the rank where var would be if it were 
//inserted (do NOT insert var into the table). 
  public int rank(String var)  {
    
    return rankHelper(0,var,head);
  }
  
  
  // my helper function of my rank(...) method.
  private int rankHelper(int count, String var, Node p){
    
    if(var.compareTo(p.variable)<=0){
      return count;
    }
    if(var.compareTo(p.variable)>0){
      
      
      count=count+1;
      
      return rankHelper(count,var,p.next);
      
    }
    else 
      return count;
  }
  
  
  
  //  Return the number of entries in the table whose variables are in the range [lo .. hi], that is, that are >= lo and <= hi 
  public int size(String lo, String hi) {  
    if(contains(lo)&&contains(hi)){
      return rank(hi)-rank(lo)+1;
    }
    else
      return rank(hi)-rank(lo);
  }
  
  
  
  // Initialize a complete enumeration of the whole table.
  Node p;
  public void initIterator() {  
    p = head;
  }
  //Initialize an enumeration of those entries in the range [lo .. hi], i.e.,all those entries which are between lo and hi,   
  public void initIterator(String lo, String hi) {  
    p = initIteratorHelper1(lo,head);
    
    Node q = initIteratorHelper2(hi,head);
    q.next=null;
  }
  
  
  // return the Node >= lo.
  public Node initIteratorHelper1(String lo,Node p){
    
    if(p.next==null){
      return p;
    }
    if(lo.compareTo(p.variable)==0){
      return p;
    }
    if(lo.compareTo(p.variable)>0){
      return initIteratorHelper1(lo,p.next);
    }
    else{
      return p;
    }
  }
  
  // return the Node <= Hi.
  private Node initIteratorHelper2(String hi,Node p){
    Node q = p.next;
    if(q==null){
      return p;
    }
    if(p.variable.compareTo(hi)==0){
      return p;
    }
    if(q.variable.compareTo(hi)<0){
      return initIteratorHelper2(hi,p.next);
    }
    else{
      return p;
    }
  }
  
  //Return true if there still elements to be enumerated and false otherwise.
  public boolean hasNext() { 
    
    if(p==null){
      return false;
    }
    else{
      
      return true;
    }
    
  }
  
//  You need to return the element you are pointing to, but also chain along to
//  the next one.  [Hint: use a temporary variable to store the previous variable name.]
  public String next() { 
    String a = p.variable;
    p=p.next;
    return a;
    
  }
  
  
  
  
  
  
  
  
  
  
  public static void main(String[] args) {
    
    
    SymbolTable<Integer> S = new SymbolTable<Integer>(); 
    
    
    // Insert three (key,value) pairs and test basic methods
    
    S.put("a",3); 
    S.put("b",1);
    S.put("c",1);
    
    
    
    
    
    System.out.println("\n[1] Should print out:\n(a,3) : (b,1) : (c,1) "); 
    System.out.println(S); 
    
    
    
    System.out.println("\n[2] Should print out:\n3"); 
    System.out.println(S.size());
    
    System.out.println("\n[3] Should print out:\nfalse"); 
    System.out.println(S.isEmpty()); 
    
    
    System.out.println("\n[4] Should print out:\n1");  
    String testKey = "c"; 
    try {
      System.out.println(S.get(testKey)); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key " + testKey + " does not exist!"); 
    }
    
    
    
    System.out.println("\n[5] Should print out:\nKey d does not exist!");  
    testKey = "d"; 
    try {
      System.out.println(S.get(testKey)); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key " + testKey + " does not exist!"); 
    }
    
    System.out.println("\n[6] Should print out:\n(a,3) : (b,1) : (c,4) "); 
    S.put("c",4);
    System.out.println(S);    
    
    System.out.println("\n[7] Should print out:\ntrue"); 
    System.out.println(S.contains("c"));  
    
    System.out.println("\n[8] Should print out:\ntrue"); 
    System.out.println(S.contains("a")); 
    
    System.out.println("\n[9] Should print out:\nfalse"); 
    System.out.println(S.contains("e"));  
    
    S.delete("a"); 
    S.delete("d"); 
    S.delete("c"); 
    System.out.println("\n[10] Should print out:\n(b,1)");     
    System.out.println(S); 
    
    System.out.println("\n[11] Should print out:\n0");  
    S.delete("b"); 
    System.out.println(S.size()); 
    
    
    
    System.out.println("\n[12] Should print out:\nnope! nope! nope! nope! nope!");  
    try{
      System.out.println(S.min()); 
    }
    catch(KeyNotFoundException e) {
      System.out.print("nope! "); 
    }
    
    try{
      System.out.println(S.max()); 
    }
    catch(KeyNotFoundException e) {
      System.out.print("nope! "); 
    }
    
    try{
      System.out.println(S.floor("a")); 
    }
    catch(KeyNotFoundException e) {
      System.out.print("nope! "); 
    }
    try{
      System.out.println(S.ceiling("a")); 
    }
    catch(KeyNotFoundException e) {
      System.out.print("nope! "); 
    }
    
    
    try{
      System.out.println(S.select(0)); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("nope! "); 
    }
    
    
    SymbolTable<String> T = new SymbolTable<String>(); 
    
    T.put("09:00:00","Chicago");
    T.put("09:00:03","Phoenix");
    T.put("09:00:13","Houston");
    T.put("09:00:59","Chicago"); 
    T.put("09:36:14","Seattle");
    T.put("09:37:44","Phoenix");
    T.put("09:10:25","Seattle");
    T.put("09:14:25","Phoenix");
    T.put("09:19:32","Chicago");
    T.put("09:19:46","Chicago");
    T.put("09:21:05","Chicago");
    T.put("09:22:43","Seattle");
    T.put("09:01:10","Houston");
    T.put("09:03:13","Chicago");
    T.put("09:10:11","Seattle");
    T.put("09:25:52","Chicago");
    T.put("09:22:54","Seattle");  
    T.put("09:35:21","Chicago");
    
    System.out.println("\n[13] Should print out:\n(09:00:00,Chicago) : (09:00:03,Phoenix) : (09:00:13,Houston) : (09:00:59,Chicago) : (09:01:10,Houston) : (09:03:13,Chicago) : (09:10:11,Seattle) : (09:10:25,Seattle) : (09:14:25,Phoenix) : (09:19:32,Chicago) : (09:19:46,Chicago) : (09:21:05,Chicago) : (09:22:43,Seattle) : (09:22:54,Seattle) : (09:25:52,Chicago) : (09:35:21,Chicago) : (09:36:14,Seattle) : (09:37:44,Phoenix)");      
    System.out.println("\n" + T);    
    
    try{
      System.out.println("\n[14] Should print out:\n09:00:00");
      System.out.println(T.min()); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key 09:00:00 does not exist!"); 
    } 
    try{
      System.out.println("\n[15] Should print out:\n09:37:44");
      System.out.println(T.max()); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key 09:37:44 does not exist!"); 
    } 
    
    try{
      System.out.println("\n[16] Should print out:\n09:03:13");
      System.out.println(T.floor("09:05:00")); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key 09:05:00 does not exist!"); 
    } 
    
    try{
      System.out.println("\n[17] Should print out:\n09:35:21");
      System.out.println(T.floor("09:35:21")); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key 09:35:21 does not exist!"); 
    }
    
    try{
      System.out.println("\n[18] Should print out:\n09:35:21");
      System.out.println(T.ceiling("09:30:00")); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key 09:35:21 does not exist!"); 
    } 
    
    try{
      System.out.println("\n[19] Should print out:\n09:00:00");
      System.out.println(T.ceiling("09:00:00")); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key 09:00:00 does not exist!"); 
    }
    
    try{
      System.out.println("\n[20] Should print out:\n09:10:25");
      System.out.println(T.select(7)); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key 09:10:25 does not exist!"); 
    } 
    
    
    
    System.out.println("\n[21] Should print out:\n7");
    System.out.println(T.rank("09:10:25")); 
    
    System.out.println("\n[22] Should print out:\n15");
    System.out.println(T.rank("09:30:00"));     
    
    System.out.println("\n[23] Should print out:\n5");
    System.out.println(T.size("09:15:00", "09:25:00")); 
    
    try {
      System.out.println("\n[24] Should print out:\n18 18");
      System.out.println(T.size() + " " + T.size(T.min(), T.max())); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Symbol table empty!"); 
    }
    
    try {   
      System.out.println("\n[25] Should print out:\n09:00:03");
      T.deleteMin(); 
      System.out.println(T.min()); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Symbol table empty!");
    }
    
    try {   
      System.out.println("\n[26] Should print out:\n09:36:14");
      T.deleteMax(); 
      System.out.println(T.max()); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Symbol table empty!");
    }
    
    
    System.out.println("\n[27] Should print out:\n16");
    System.out.println(T.size()); 
    
    // delete all but the last 6 entries
    for(int i = 0; i < 10 ; ++i)
      T.deleteMin(); 
    
    
    System.out.println("\n[28] Should print out:\n09:21:05  09:22:43  09:22:54  09:25:52  09:35:21  09:36:14"); 
    T.initIterator();
    while(T.hasNext()) 
      System.out.print(T.next() + "  ");
    System.out.println();
    
    
    
    System.out.println("\n[29] Should print out:\n09:22:43  09:22:54  09:25:52  09:35:21"); 
    T.initIterator("09:22:43", "09:35:21");
    while(T.hasNext()) 
      System.out.print(T.next() + "  ");
    System.out.println();
    
    System.out.println("\n[30] Should print out:\n09:21:05  09:22:43  09:22:54  09:25:52"); 
    T.initIterator("09:00:00", "09:35:00");
    while(T.hasNext()) 
      System.out.print(T.next() + "  ");
    System.out.println();
    
  }
}



// KeyNotFoundException Class
class KeyNotFoundException extends Exception {
  
  public String num;
  
  public  KeyNotFoundException(String msg, String n,String msg1){
    
    super(msg+n+msg1);
    
    num=n;
  }
  public  KeyNotFoundException(String msg){
    
    super(msg);
    
  }
  
  
}
