class Q_3_Yes
{
  public static void main(String[] args)
  {
    Q_3_Yes q_3_yes = new Q_3_Yes();
    q_3_yes.Run(args);
  }
  
  public void Run(String[] args)
  {
    String word="";
    if (args.length < 1)
    {
      word = "y";
    }
    else{
      word = args[0];
    }
    while (true)
    {
      System.out.println(word);
    }
  }
}