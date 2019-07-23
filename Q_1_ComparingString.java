class Q_1_ComparingString
{
  public static void main(String[] args)
  {
    Q_1_ComparingString q_1_comparingString =  new Q_1_ComparingString();
    q_1_comparingString.Run(args);
  }
  public void Run(String[] args)
  {
    for(var arg : args)
  {
    if(IsTrue(arg)){
      System.out.println("KSU_APです");
    }
    else{
      System.out.printf("KSU_APではなく%sです%n",arg);
    }
  
  }
  
  }
  public boolean IsTrue(String arg){
    String TrueWord = "KSU_AP";
    if(arg.equals(TrueWord)){
      return true;
    }
    return false;
  }
  
}