import java.util.*;
import java.io.*;

class Q_5_Tac
{
  public static void main(String[] args)
  {
    Q_5_Tac q_5_tac = new Q_5_Tac();
    for(var arg : args)
    {
      q_5_tac.Run(arg);
    }
  }
  
  public void Run(String arg)
  {
    try
    {
      File file = new File(arg);
      Deque<String> stack = new ArrayDeque<>();
      int cnt = 0;
      if (file.canRead())
      {
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Shift-JIS"));
        while ((line = br.readLine()) != null)
        {
          stack.addLast(line);
          cnt++;
        }
      }
      else{
        System.out.println("ファイルを読み込めませんでした。");
      }
      for (int i =0;i<cnt;i++)
      {
        System.out.println(stack.removeLast());
      }
    } catch (IOException e)
    {
      System.out.println(e);
    }
  }
}