public class ArgsReverser{
  public static void main(String[] args){
    ArgsReverser argsReverser = new ArgsReverser();
    argsReverser.run(args);
  }
  protected void run(String[] args){
    for(var arg : args){
      System.out.println(Reverser(arg));
    }
  }
  protected String Reverser(String arg){
    String reArg = new String();
    for(int i=arg.length()-1;i>=0;i--){
      reArg+=arg.charAt(i);
    }
    return reArg;
  }
}