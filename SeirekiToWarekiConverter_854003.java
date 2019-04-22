import java.util.*;
public class SeirekiToWarekiConverter_854003{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    System.out.printf("西暦を入力 : ");
    int seireki = scanner.nextInt();
    System.out.printf("西暦 %d: %s%n",seireki,GetWareki(seireki));
  }
  public static String GetWareki(int seireki)
  {
    String result = "";
    if (seireki < 1868)
    {
      result = "明治より前であるため判定できません";
    } else if (seireki >= 1868 && seireki < 1912)
    {
      result = "明治" + (seireki - 1867) + "年";
      if (seireki == 1868) result = "明治元年";
    } else if (seireki >= 1912 && seireki < 1926)
    {
      result = "大正" + (seireki - 1911) + "年";
      if (seireki == 1912)
      {
        result = "明治" + (seireki - 1867) + "年 (大正元年)";
      }
    } else if (seireki >= 1926 && seireki < 1989)
    {
      result = "昭和" + (seireki - 1925) + "年";
      if (seireki == 1926)
      {
        result = "大正" + (seireki - 1911) + "年 (昭和元年)";
      }
    } else if (seireki >= 1989 && seireki < 2019)
    {
      result = "平成" + (seireki - 1988) + "年";
      if (seireki == 1989)
      {
        result = "昭和" + (seireki - 1925) + "年 (平成元年)";
      }
  
    } else if (seireki >= 2019)
    {
      result = "令和" + (seireki - 2018) + "年";
      if (seireki == 2019)
      {
        result = "平成" + (seireki - 1988) + "年 (令和元年)";
      }
  
    }
    return result;
  }
}
