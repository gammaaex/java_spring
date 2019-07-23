class ForStaticMain extends   NullChecker{
  public static void main (String[] args){
    System.out.println("Start");
    String a = "aaaaa";
    String b = null;
    
    System.out.println(NullChecker.NullCheck(a));
    System.out.println(NullChecker.NullCheck(b));
  
  }
}