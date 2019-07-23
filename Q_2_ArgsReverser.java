class Q_2_ArgsReverser
{
  public static void main(String[] args)
  {
    Q_2_ArgsReverser q_2_argsReverser = new Q_2_ArgsReverser();
    q_2_argsReverser.Run(args);
  }
  
  public void Run(String[] args)
  {
    for(var arg : args){
      System.out.printf("%s : %s%n",arg,ReverseWord(arg));
    }
  }
  public String ReverseWord(String arg){
    String result = "";
    for(int i=arg.length()-1;i>=0;i--){
      result+=arg.charAt(i);
    }
    return result;
  }
}