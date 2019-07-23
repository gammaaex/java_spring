import java.io.*;
import java.util.*;
public class ZipCode
{
  public static void main(String[] args)
  {
    ZipCode zipCode = new ZipCode();
    zipCode.run(args);
  }
  
  protected void run(String[] args)
  {
    try
    {
      File file = new File("zipcode.CSV");
      HashMap<Integer, String> map = new HashMap<>();
      if (file.canRead())
      {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Shift-JIS"));
        String line;
        int i = 0;
        while ((line = br.readLine()) != null)
        {
          i++;
          String[] data = line.split(",", 4);
          for (String elem : data)
          {
            System.out.println(data[2]);
            if(isNumber(data[2]))
            {
              map.put(Integer.valueOf(data[2]), data[3]+data[4]);
              //System.out.println(map.get(Integer.valueOf(data[2])));
            }
          }
        }
        System.out.println(map.get(Integer.valueOf(args[0])));
      } else
      {
        System.out.println("ファイルが読み込めません");
      }
    } catch (IOException e)
    {
      System.out.println(e);
    }
  }
  public boolean isNumber(String num) {
    try {
      Integer.parseInt(num);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
