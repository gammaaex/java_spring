import java.util.*;

public class Echo_854003
{
  public static void main(String[] args)
  {
    Echo_854003 echo_854003 = new Echo_854003();
    echo_854003.run(args);
  }
  
  public void run(String[] args)
  {
    boolean _n = false;
    boolean _h = false;
    for (int i = 0; i < args.length; i++)
    {
      if (args[i].equals("-n"))
      {
        _n = true;
  
      } else if (args[i].equals("-h"))
      {
        _h = true;
      }
    }
    PrintEcho(_n, _h, args);
  }
  
  public void PrintEcho(boolean _n, boolean _h, String[] args)
  {
    if (_h == true)
    {
      System.out.println("java Echo [OPTIONS] <string...>\n" +
                                 "OPTIONS\n" +
                                 "    -h: このメッセージを表示して終了する．\n" +
                                 "    -n: 改行せずに出力する．");
    } else
    {
      for (int i = 0; i < args.length; i++)
      {
        if (!args[i].equals("-n"))
        {
          System.out.printf(args[i]);
        }
      }
      if (_n == true)
      {
        System.out.println();
      }
    }
  }
}