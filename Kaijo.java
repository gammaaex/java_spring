import java.util.*;

public class Kaijo
{
  public void run(String[] args)
  {
    int num = Integer.valueOf(args[0]);
    int result = 1;
    for (int i = 1; i <= num; i++)
    {
      result = i * result;
    }
    System.out.printf("入力した値 : %d , 結果 : %d%n", num, result);
  }
  
  public static void main(String[] args)
  {
    Kaijo application = new Kaijo();
    application.run(args);
  }
}