import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Q_7_Freq
{
  public static void main(String[] args)
  {
    Q_7_Freq q_7_freq = new Q_7_Freq();
    q_7_freq.Run(args);
  }
  
  public void Run(String[] args)
  {
    try
    {
      File file = new File(args[0]);
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Shift-JIS"));
      String line;
      HashMap<Integer, String> map = new HashMap<Integer, String>(); //重複無しのmap
      ArrayList<String> list = new ArrayList<>();                    //全体のlist
      int count = 0;
      while ((line = br.readLine()) != null)
      {
        String[] data = line.split(" ");
        for (int i = 0; i < data.length; i++)
        {
          list.add(data[i]);       //listに追加
          map.put(count, data[i]); //mapに追加
          count++;
        }
      }
      for (int i = 0; i < map.size(); i++)
      {
        System.out.println(list.stream().collect(
                Collectors.groupingBy(x -> x, Collectors.counting())
        ));
      }
    } catch (IOException e)
    {
      System.out.println(e);
    }
  }
  
  public int CountWord(String str, ArrayList<String> list)
  {
    int result = 0;
    for (var ele : list)
    {
      if (str.equals("ele"))
      {
        result++;
      }
    }
    return result;
  }
}