public class ComparingString
{
  public static void main(String[] args)
  {
    ComparingString comparingString = new ComparingString();
    comparingString.run(args);
  }
  
  protected void run(String[] args)
  {
    for(var arg :args)
    {
      if (IsString(arg))
      {
        System.out.println("渡された文字列は KSU_APです");
      } else
      {
        System.out.printf("渡された文字列は KSU_APではなく%sです%n", arg);
      }
    }
  }
  
  protected boolean IsString(String arg)
  {
    if(arg.equals("KSU_AP")){
      return true;
    }
    return false;
  }
}