import java.io.*;
import java.util.*;
class Q_4_Sorter
{
  public static void main(String[] args)
  {
    Q_4_Sorter q_4_sorter = new Q_4_Sorter();
    q_4_sorter.Run(args[0]);
  }
  
  public void Run(String arg)
  {
    try
    {
      File file = new File(arg);
      ArrayList<String> list = new ArrayList<String>();
      String line;
      if (file.canRead())
      {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Shift-JIS"));
        while ((line = br.readLine()) != null)
        {
          list.add(line);
        }
        Collections.sort(list);
        for (var word : list)
        {
          System.out.printf("%s%n", word);
        }
      } else
      {
        System.out.println("ファイルを読み込めません");
      }
    }catch (IOException e)
    {
      System.out.println(e);
    }
  
  }
}